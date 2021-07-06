package utils;

//import com.android.tools.idea.Projects;
import com.google.common.base.Charsets;
import com.intellij.openapi.project.Project;
import org.apache.tools.ant.util.ReflectUtil;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * m.poizon.com Inc.
 * Copyright (c) 1999-2020 All Rights Reserved.
 *
 * @author panes
 * @contact pantao@theduapp.com
 */
public class GradleProperties {
    public static String get(Project project, String key) {
//        try {
            Properties properties = new Properties();
//            String value = properties.getProperty(key);
//            LogUtil.log(key + " = " + value);
//            return value;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static File getFile(Project project){
//        return new File(Projects.getBaseDirPath(project), "gradle.properties");
        return null;
    }

    public static String getBaseline(Project project) {
        return get(project, "Baseline");
    }
    public static String getAVN(Project project){
        return get(project, "AVN");
    }

    public static String getAVC(Project project){
        return get(project, "AVC");
    }
    public static String getAVB(Project project){
        return get(project, "AVB");
    }
    public static String getCommonModuleVersion(Project project){
        return get(project, "CommonModuleVersion");
    }
    public static String getMallModuleVersion(Project project){
        return get(project, "MallModuleVersion");
    }
    public static String getCommunityModuleVersion(Project project){
        return get(project, "CommunityModuleVersion");
    }
    public static String getAccountModuleVersion(Project project){
        return get(project, "AccountModuleVersion");
    }

    public static void main(String[] args) {
        String path = "/Users/songbinwang/IdeaProjects/IdePluginDemo/gradle.properties";
        Properties properties = new Properties();
        File file = new File(path);
        if(file.exists()){
            try{
                Reader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(path)), Charsets.UTF_8);
                properties.load(reader);
                Map<Object, Object> map = (Map<Object, Object>)ReflectUtil.getField(properties, "map");
                map.keySet().forEach(it-> {
                    String key = (String)it;
                    String value = (String) map.get(it);
                    System.out.println("key " + key + ", value " + value);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
