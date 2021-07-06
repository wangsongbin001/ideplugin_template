package actions.bean;

import com.intellij.psi.xml.XmlTag;
import org.apache.http.util.TextUtils;
import utils.CommonUtil;

import java.util.regex.Pattern;

public class MElement {
    // 判断id正则                                                  android:id="@+id/btn"
    private static final Pattern sIdPattern = Pattern.compile("@\\+?(android:)?id/([^$]+)$", Pattern.CASE_INSENSITIVE);
    // id
    private String id;
    // 名字如TextView  Button
    private String name;
    //
    private XmlTag xml;

    /**辅助field*/
    private String fieldName;
    // 是否生成
    private boolean isCreateFiled = true;
    // 是否Clickable
    private boolean isCreateClickMethod = false;

    public MElement(String id, String name, XmlTag tag) {
        this.id = id;
        this.name = name;
        this.xml = tag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public XmlTag getXml() {
        return xml;
    }

    public void setXml(XmlTag xml) {
        this.xml = xml;
    }

    public String getFullID() {
        StringBuilder fullID = new StringBuilder();
        String rPrefix = "R.id.";
        fullID.append(rPrefix);
        fullID.append(id);
        return fullID.toString();
    }

    public String getFieldName() {
        if (TextUtils.isEmpty(this.fieldName)) {
            String fieldName = id;
            String[] names = id.split("_");
            // mAaBbCc
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < names.length; i++) {
                if (i == 0) {
                    sb.append("m");
                }
                sb.append(CommonUtil.firstToUpperCase(names[i]));
            }
            this.fieldName = fieldName;
        }
        return this.fieldName;
    }

    public boolean isCreateFiled() {
        return isCreateFiled;
    }

    public void setCreateFiled(boolean createFiled) {
        isCreateFiled = createFiled;
    }

    public boolean isCreateClickMethod() {
        return isCreateClickMethod;
    }

    public void setCreateClickMethod(boolean createClickMethod) {
        isCreateClickMethod = createClickMethod;
    }

    @Override
    public String toString() {
        return "MElement{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", xml=" + xml +
                ", fieldName='" + fieldName + '\'' +
                ", isCreateFiled=" + isCreateFiled +
                ", isCreateClickMethod=" + isCreateClickMethod +
                '}';
    }
}
