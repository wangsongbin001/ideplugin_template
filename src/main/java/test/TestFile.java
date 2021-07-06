package test;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.http.util.TextUtils;
import org.apache.tools.ant.util.ReflectUtil;
import test.entity.*;
import utils.Network;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestFile {
    public static void main2(String[] args) throws IOException {
//        File parent = new File("./");
//        File folder = new File(parent, "poizon-cache");
//        if(!folder.exists() || !folder.isDirectory()){
//            folder.mkdirs();
//        }
        //解析数据
        String content = Network.request("https://apk.poizon.com/develop/gradle/lib-version/4.70.0.config");
        System.out.println("content:" + content);
        Gson gson = new Gson();
        DuBaseline baseline = gson.fromJson(content, DuBaseline.class);

        BufferedReader br = null;
        InputStreamReader isr = null;
        FileInputStream fis = null;
        StringBuilder sb = new StringBuilder();
        try {
            fis = new FileInputStream("./project_mapping.json");
            isr = new InputStreamReader(fis, "utf-8");
            br = new BufferedReader(isr);
            String temp = "";
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }

//        ProjectMap projectMap = gson.fromJson(sb.toString(), ProjectMap.class);
        String mapStr = Network.request("https://apk.poizon.com/develop/gradle/project_mapping.json");
        System.out.println("mapStr:" + mapStr);
        ProjectMap projectMap = new Gson().fromJson(mapStr, ProjectMap.class);
        projectMap.adJust();
        System.out.println("projectMap:" + projectMap.toString());

        /**
         * 排除不合规的数据
         * type = classpath
         * name 非startWith "com.shizhuang.duapp"
         */
        Map<String, ProjectMode> map = new HashMap();

        ArrayList<DuComponent> dependencies = new ArrayList<>();
        String remote = "";
        String projectName = "";
        for (DuComponent component : baseline.dependencies) {
            if ("classpath".equals(component.type)) {
                continue;
            } else if (TextUtils.isEmpty(component.name) || !component.name.startsWith("com.shizhuang.duapp")) {
                continue;
            } else {
                remote = "";
                ComponentMode componentMode = new ComponentMode(component);
                System.out.println("component:" + component.toString());
                if (!TextUtils.isEmpty(component.group)) {
                    remote = projectMap.getRemoteByGroup(component.group);
                }
                if (TextUtils.isEmpty(remote)) {
                    remote = projectMap.getRemoteByComponentGroup(component.name);
                }
                System.out.println("remote:" + remote);
                if (!TextUtils.isEmpty(remote)) {//有remote 可以安装&依赖的
                    System.out.println("remote:" + remote);
                    componentMode.setRemote(remote);
                    projectName = remote.substring(remote.lastIndexOf("/") + 1, remote.lastIndexOf("."));
                    System.out.println("projectName:" + projectName);
                    ProjectMode projectMode = map.get(projectName);
                    if (projectMode == null) {
                        projectMode = new ProjectMode(remote);
                        map.put(projectName, projectMode);
                    }
                    projectMode.modules.add(componentMode);
                } else {
                    ProjectMode projectMode = map.get("unkown");
                    if (projectMode == null) {
                        projectMode = new ProjectMode("unkown");
                        map.put("unkown", projectMode);
                    }
                    projectMode.modules.add(componentMode);
                }
            }
            dependencies.add(component);
        }

        System.out.println("dependencies:" + dependencies.toString());

        System.out.println("dependencies json:" + gson.toJson(dependencies));

        System.out.println("map json:" + gson.toJson(map));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("result: " + testCmdWithReturn());
        String input =  JOptionPane.showInputDialog("请输入");
    }

    public static String testCmdWithReturn() {
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {
            Process process = Runtime.getRuntime().exec("git config user.name");
            is = process.getInputStream();
            StringBuilder sb = new StringBuilder();
            if (is != null) {
                isr = new InputStreamReader(is);
                reader = new BufferedReader(isr);

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static void testGitClone() throws InterruptedException {
//        String filePath = "/Users/songbinwang/AndroidStudioProjects/du_workspace/android_poizon_duapp/.idea/poizon.pzn";
//        File file = new File(filePath);
//        if(file.exists()){
//            String content = FileUtil.read(filePath);
//            System.out.println(content);
//            try {
//                PoizonPzn a = new Gson().fromJson(content, PoizonPzn.class);
//                System.out.println("a: " + a.toString());
//
//                Set<String> set = new HashSet(a.local.size() * 2);
//                for(PoizonPzn.Entry entry : a.local){
//                    set.add(entry.target);
//                }
//                System.out.println("set: " + set);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        String comds = "git clone git@pkg.poizon.com:duapp/android/module/duapp_base.git";
        String filePath = "/Users/songbinwang/AndroidStudioProjects/du_workspace/du_common";

        File dir = new File(filePath);
        System.out.println(filePath.substring(0, filePath.lastIndexOf("/")));


        String fa = 1 == 1 ? "" : "";

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Process process = Runtime.getRuntime().exec(comds, null, dir);
                    System.out.println(" " + process.waitFor());

                    File file = new File(dir, "duapp_base");
                    if (file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0) {
                        System.out.println("执行成功！");
                    } else {
                        System.out.println("执行失败！");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        thread.start();
        thread.join();
        File file = new File(dir, "duapp_base");
        if (file.isDirectory() && file.listFiles() != null && file.listFiles().length > 0) {
            System.out.println("执行成功！");
        } else {
            System.out.println("执行失败！");
        }
    }

    public static void testProperties() {
//        String path = "/Users/songbinwang/IdeaProjects/IdePluginDemo/gradle.properties";
        String path = "/Users/songbinwang/AndroidStudioProjects/du_workspace/android_poizon_duapp/buildsystem/remote_map.properties";
        Properties properties = new Properties();
        File file = new File(path);
        if (file.exists()) {
            try {
                InputStreamReader reader = new InputStreamReader(new BufferedInputStream(new FileInputStream(path)));
                properties.load(reader);
//                Field field = Properties.class.getDeclaredField("map");
//                field.setAccessible(true);
//                Map<String, String> map = (Map<String, String>) field.get(properties);
                for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                    System.out.println("key " + entry.getKey() + ", value " + entry.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
