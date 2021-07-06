package actions;

import utils.CommonUtil;
import com.intellij.ide.IdeView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.apache.http.util.TextUtils;
import utils.LogUtil;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CreateJavaBeanByStringAction extends AnAction {

    //定义生成文件的协议
    private String pasteStr="name String\n"+
            "age int\n"+"id Integer\n";
    private String member="public";

    @Override
    public void actionPerformed(AnActionEvent e) {
        //点击后会执行的方法。展示一个输入框
        //OptionPane.showInputDialog(e.getProject(), "请输入");
        LogUtil.log("wang start");
        //生成一个文件
        String input = pasteStr;
        generateJavaBean(e, "User", input);
        LogUtil.log("wang end");
    }

    //生成文件的代码
    private void generateJavaBean(AnActionEvent actionEvent, String fileName, String json) {
        LogUtil.log("wang step 1");
        //1,得到当前的工程对象
        Project project = actionEvent.getProject();

        //2,得到当前的目录服务
        JavaDirectoryService javaDirectoryService = JavaDirectoryService.getInstance();

        //3,相对路径光标的当前相对路径
        IdeView ideView = actionEvent.getRequiredData(LangDataKeys.IDE_VIEW);
        PsiDirectory psiDirectory = ideView.getOrChooseDirectory();
        LogUtil.log("wang step 2 " + psiDirectory.getName() + " " + psiDirectory.getParentDirectory());

        //3，新建一个文件模版.java.ft 文件模版都是以ft结尾
        String templateFile = "GenerateFileByString.java.ft";
        //4，创建填入文件模版的参数,包括包名
        String cPackage = CommonUtil.getPackageName(project);
        String name = TextUtils.isEmpty(fileName) ? "JavaBean" : fileName;
        String cInterface = " implements Serializable ";

        Map<String, String> params = new HashMap<>();
        params.put("PACKAGE_NAME", cPackage);
        params.put("NAME", name);
        params.put("INTERFACES", cInterface);

        /**
         * 5，生成 生成文件的类：
         * 目录，文件名，文件模版，params参数
         */
        PsiClass psiClass = javaDirectoryService.createClass(psiDirectory, fileName, "GenerateFileByString", false, params);

        LogUtil.log("wang step 3 " + (psiClass == null) );
        //6，添加属性，开始加入字段
        WriteCommandAction.runWriteCommandAction(project, new Runnable() {
            @Override
            public void run() {
                generateModelField(pasteStr, psiClass, project);
            }
        });
    }

    private void generateModelField(String pasteStr, PsiClass psiClass, Project project) {
        if(psiClass == null){
            return;
        }
        LogUtil.log("wang step 4 ");
        PsiElementFactory factory = JavaPsiFacade.getInstance(project).getElementFactory();
        //根据用户输入的字符串，生成代码
        String[] strs = pasteStr.split("\n");
        StringBuilder sb=new StringBuilder();
        for(String line: strs){
            String[] temp=line.split(" ");
            String fileName = temp[0];
            String fileType = temp[1];
            sb.append(member + " " + fileType + " " + fileName + ";");
            PsiField field = factory.createFieldFromText(sb.toString(), psiClass);
            psiClass.add(field);
            sb.delete(0, sb.length());
        }
        LogUtil.log("wang step 5 " );
    }

}
