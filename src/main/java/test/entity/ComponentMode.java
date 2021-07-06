package test.entity;

public class ComponentMode {
    public String name;//: String?,
    public String enable;//: String?,
    public String group;//: String? = "",
    public String subPath;//: String? = "",
    public String installed;//: String?, //是否安装（即本地拉取代码）

    public String remote;//: String?
    public String mavenGroup;//: maven

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public ComponentMode(){
        installed = "false";
        enable = "false";
    }

    public ComponentMode(DuComponent component){
        this();
        name = component.name.substring(component.name.lastIndexOf(":") + 1);
        group = component.group;
        mavenGroup = component.name;
    }

}
