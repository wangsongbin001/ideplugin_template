package actions;

import actions.bean.MElement;
import android.telephony.ims.feature.CapabilityChangeRequest;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.psi.xml.XmlFile;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;
import ui.AutoBindingDialog;
import ui.FindViewByIdDialog;
import utils.CommonUtil;
import utils.LogUtil;
import utils.NotificationUtil;

import java.util.ArrayList;
import java.util.List;

public class AutoFindViewById extends AnAction {

    private final static String matching = "R.layout.";

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        //获取用户选择的layout名称
        String layoutName = getLayoutName(anActionEvent);
        LogUtil.log("wangs layoutName:" + layoutName);
        //解析布局文件
        List<MElement> list = parseIdFromXml(anActionEvent.getProject(), layoutName);
        LogUtil.log("wangs elements:" + list.toString());
        //生成代码
        generateCode(anActionEvent, list, layoutName);
    }

    private String getLayoutName(AnActionEvent anActionEvent) {
        //获得编辑区对象
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);

        //获取用户选择的字符
        SelectionModel selectionModel = editor.getSelectionModel();
        String xmlName = selectionModel.getSelectedText();
        //获取光标所在位置行中的布局文件名称
        if (TextUtils.isEmpty(xmlName)) {
            Document document = editor.getDocument();
            if (document != null) {
                CaretModel caretModel = editor.getCaretModel();
                int lineNum = document.getLineNumber(caretModel.getOffset());
                int startOffset = document.getLineStartOffset(lineNum);
                int endOffset = document.getLineEndOffset(lineNum);
                String txt = document.getText(new TextRange(startOffset, endOffset));

                if (!TextUtils.isEmpty(txt) && txt.contains(matching)) {
                    //获取layout文件的字符串
                    int startPosition = txt.indexOf(matching) + matching.length();
                    int endPosition = txt.indexOf(")", startPosition);
                    LogUtil.log("startPosition:" + startPosition + " end:" + endPosition);
                    if (startOffset < endOffset) {
                        xmlName = txt.substring(startPosition, endPosition);
                    }
                }
            }
        }
        //用户输入布局名称
        if (TextUtils.isEmpty(xmlName)) {
            xmlName = Messages.showInputDialog(anActionEvent.getProject(), "请输入layout名", "未输入", Messages.getInformationIcon());
            if (TextUtils.isEmpty(xmlName)) {
                NotificationUtil.showPopupBalloon(editor, "用户没有输入layout", 5);
                return "";
            }
        }
        return xmlName;
    }

    private List<MElement> parseIdFromXml(Project project, String xmlFileName) {
        String fullName = xmlFileName + ".xml";
        PsiFile[] files = FilenameIndex.getFilesByName(project, fullName, GlobalSearchScope.allScope(project));
        if (files == null || files.length == 0) {
            NotificationUtil.show(fullName, project);
            return null;
        }
        XmlFile xmlFile = (XmlFile) files[0];
        List<MElement> elements = new ArrayList<MElement>();
        //开始解析
        CommonUtil.getIDsFromLayout(xmlFile, elements);
        LogUtil.log("wangs parseIdFromXml: " + elements.toString());
        return elements;
    }

    private void generateCode(AnActionEvent anActionEvent, List<MElement> elements, String xmlFilename) {
        Project project = anActionEvent.getProject();
        if (project == null) return;
        //得到一个psiFile
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        PsiFile psiFile = PsiUtilBase.getPsiFileInEditor(editor, project);
        //
        PsiClass psiClass = CommonUtil.getTargetClass(editor, psiFile);
        //生成UI
        AutoBindingDialog mDialog = new AutoBindingDialog(project, elements, new AutoBindingDialog.ConfirmListener() {
            @Override
            public void onConfirm(@NotNull List<MElement> elements) {
                //generateFields(project, elements, psiClass);
                WriteCommandAction.runWriteCommandAction(project, new Runnable() {
                    @Override
                    public void run() {
                        //生成属性
                        generateFields(project, elements, psiClass);
                        //生成onClik文件
                        generateOnClickMethod(project, elements, psiClass);
                    }
                });
            }
        });
        mDialog.show();


    }

    /**
     * 创建字段的注入代码
     */
    private void generateFields(Project project, List<MElement> mElements, PsiClass psiClass) {
//        @BindView(R.id.tvText)
//        public TextView mTvText;
        // 获取Factory
        PsiElementFactory mFactory = JavaPsiFacade.getElementFactory(project);
        PsiField field;

        for (MElement element : mElements) {
            field = psiClass.findFieldByName(element.getFieldName(), false);
            if (field != null) {
                LogUtil.log("generateFields " + field.getType().getCanonicalText());
            } else {
                StringBuilder text = new StringBuilder();
                //text.append("@BindView(" + element.getFullID() + ")\n");
                text.append("public ");
                text.append(element.getName() + " ");
                text.append(element.getFieldName() + ";");
                if (element.isCreateFiled()) {
                    psiClass.add(mFactory.createFieldFromText(text.toString(), psiClass));
                }
            }
        }

        PsiMethod[] initViews = psiClass.findMethodsByName("initViews", true);
        if (initViews != null && initViews.length > 0) {
            return;
        }
        StringBuilder method = new StringBuilder();
        method.append("private void initViews(){ \n");
        for (MElement element : mElements) {
            if (element.isCreateFiled()) {
                method.append(element.getFieldName()).append(" = (" + element.getName() + ")findViewById(R.id.").append(element.getId()).append(");\n");
            }
        }
        method.append("}");
        psiClass.add(mFactory.createMethodFromText(method.toString(), psiClass));
    }

    /**
     * 创建监听事件方法
     */
    private void generateOnClickMethod(Project project, List<MElement> elements, PsiClass psiClass) {
        PsiElementFactory mFactory = JavaPsiFacade.getElementFactory(project);
        PsiMethod[] onClicks = psiClass.findMethodsByName("onClick", false);
        if (onClicks != null && onClicks.length > 0) {
            LogUtil.log("generateOnClickMethod" + onClicks[0].getBody());
            return;
        }
        StringBuilder onClick = new StringBuilder();
        onClick.append("public void onClick(View view){ \n")
                .append("switch(view.getId()){ \n");
        for (MElement element : elements) {
            if (element.isCreateClickMethod()) {
                onClick.append("case ").append(element.getFullID()).append(":\n")
                        .append("break;\n");
            }
        }
        onClick.append("default:\n").append("break;\n")
                .append("}\n").append("}\n");
        psiClass.add(mFactory.createMethodFromText(onClick.toString(), psiClass));
    }

}
