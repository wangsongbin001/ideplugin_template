package utils;

import actions.bean.MElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import org.apache.http.util.TextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Locale;

public class CommonUtil {

    /**
     * 从AndroidManifest.xml文件中获取当前app的包名
     *
     * @return
     */
    public static String getPackageName(Project project) {
        String package_name = "";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(project.getBasePath() + "/app/src/main/AndroidManifest.xml");

            NodeList nodeList = doc.getElementsByTagName("manifest");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                package_name = element.getAttribute("package");
                if (package_name != null) {
                    return package_name;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return package_name;
    }

    /**
     * 根据当前文件获取对应的psiClass文件
     *
     * @param editor
     * @param file
     * @return
     */
    public static PsiClass getTargetClass(Editor editor, PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
        PsiElement element = file.findElementAt(offset);
        if (element == null) {
            return null;
        } else {
            PsiClass target = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            return target instanceof SyntheticElement ? null : target;
        }
    }

    public static void createPoizonCacheDir(Project project) {
        File parent = new File(project.getBasePath()).getParentFile();
        File folder = new File(parent, ".poizon-cache");
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }
    }

    public static java.util.List<MElement> getIDsFromLayout(final PsiFile file, final java.util.List<MElement> elements) {
        //遍历一个文件的所有元素(Xml递归元素访问程序)
        file.accept(new XmlRecursiveElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                super.visitElement(element);

                LogUtil.log("wangs visitElement: step 1");
                //element就是一个XML文件中的所有节点
                if (element instanceof XmlTag) {
                    XmlTag tag = (XmlTag) element;
                    //获取Tag的名字(TextView)或自定义的
                    String name = tag.getName();

                    LogUtil.log("wangs visitElement: step 2");
                    //如果有include
                    if (name.equalsIgnoreCase("include")) {
                        //获取布局  LinearLayout
                        XmlAttribute layout = tag.getAttribute("layout", null);
                        String includeValue = getLayoutName(layout.getValue());
                        if (TextUtils.isEmpty(includeValue)) {
                            return;
                        }
                        Project project = file.getProject();
                        PsiFile[] psiFiles = FilenameIndex.getFilesByName(project, includeValue + ".xml", GlobalSearchScope.allScope(project));
                        if (psiFiles == null || psiFiles.length == 0) {
                            return;
                        }
                        //布局文件
                        XmlFile include = (XmlFile) psiFiles[0];
                        if (include != null) {
                            //开始递归
                            getIDsFromLayout(include, elements);
                        }
                        return;
                    }

                    //获取id属性  android:id="@+id/tvText2"
                    XmlAttribute id = tag.getAttribute("android:id", null);
                    if (id == null || id.getValue() == null) {
                        return;
                    }
                    //获取id的值   @+id/tvText2
                    String idValue = id.getValue().replace("@+id/", "");

                    //获取节点对应的类  比如 TextView  Button
                    XmlAttribute aClass = tag.getAttribute("class", null);
                    if (aClass != null) {
                        //得到类名    "包名.TextView"
                        name = aClass.getValue();
                    }
                    LogUtil.log("wangs visitElement: step 3 " + name);
                    //添加到list中
                    MElement e = new MElement(idValue, name, tag);
                    elements.add(e);
                }
            }
        });
        return elements;
    }

    /**
     * layout.getValue()返回的值为@layout/layout_view
     * 该方法返回layout_view
     *
     * @param layout
     * @return
     */
    public static String getLayoutName(String layout) {
        if (layout == null || !layout.startsWith("@") || !layout.contains("/")) {
            return null;
        }
        // @layout layout_view
        String[] parts = layout.split("/");
        if (parts.length != 2) {
            return null;
        }
        // layout_view
        return parts[1];
    }

    public static String firstToUpperCase(String key) {
        return key.substring(0, 1).toUpperCase(Locale.CHINA) + key.substring(1);
    }

}
