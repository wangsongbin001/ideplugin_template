package test.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 {
 "name": "com.shizhuang.duapp.modules:du_developer",
 "version": "default",
 "group": "Account",
 "type": "classpath",  //"classpath","aar"
 "configuration": ["debug"]
 }
 */

public class DuComponent implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("version")
    public String version;
    @SerializedName("group")
    public String group;
    @SerializedName("type")
    public String type;

    @Override
    public String toString() {
        return "DuComponent{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", group='" + group + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
