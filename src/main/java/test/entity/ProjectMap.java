package test.entity;

import com.google.gson.annotations.SerializedName;
import org.apache.http.util.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProjectMap implements Serializable {
    @SerializedName("group_map")
    public ArrayList<RemoteEntry> groupList;
    @SerializedName("component_map")
    public ArrayList<RemoteEntry> componentList;

    Map<String, String> groupMap = null;
    Map<String, String> componentMap = null;

    public void adJust() {
        groupMap = new HashMap<>(8);
        componentMap = new HashMap<>(16);
        if (groupList != null) {
            for (RemoteEntry entry : groupList) {
                groupMap.put(entry.name, entry.remote);
            }
        }
        if (componentList != null) {
            for (RemoteEntry entry : componentList) {
                componentMap.put(entry.name, entry.remote);
            }
        }
    }

    public String getRemoteByGroup(String group) {
        if (groupMap == null) {
            adJust();
        }
        if (TextUtils.isEmpty(group)) {
            return "";
        }
        return groupMap.get(group);
    }

    public String getRemoteByComponentGroup(String componentGroup) {
        if (componentMap == null) {
            adJust();
        }
        if (TextUtils.isEmpty(componentGroup)) {
            return "";
        }
        return componentMap.get(componentGroup);
    }

    static class RemoteEntry implements Serializable {
        @SerializedName("name")
        public String name;
        @SerializedName("remote")
        public String remote;

        @Override
        public String toString() {
            return "RemoteMap{" +
                    "name='" + name + '\'' +
                    ", remote='" + remote + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ProjectMap{" +
//                "group_map=" + group_map +
//                ", component_map=" + component_map +
                ", groupMap=" + groupMap +
                ", componentMap=" + componentMap +
                '}';
    }
}
