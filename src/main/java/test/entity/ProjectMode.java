package test.entity;

import java.util.ArrayList;

public class ProjectMode {

    public String component;//: String;
    public String enable;//: String;   //是否依赖本地true/false
    public String root_path;//: String;
    public ArrayList<ComponentMode> modules;
    public String remote;//: String?,    //远程url
    public String installed;//: String?, //是否安装（即本地拉取代码）
    public String group;//: String? //分组

    public ProjectMode() {
        modules = new ArrayList<>();
        installed = "false";
        enable = "false";
    }

    public ProjectMode(String remote) {
        this();
        this.remote = remote;
    }

}
