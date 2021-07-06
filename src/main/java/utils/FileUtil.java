package utils;

import com.intellij.openapi.project.Project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

/**
 * m.poizon.com Inc.
 * Copyright (c) 1999-2020 All Rights Reserved.
 *
 * @author panes
 * @contact pantao@theduapp.com
 */
public class FileUtil {
    public static void write(String content, String filePath) {
        try {
            Files.write(Paths.get(filePath), content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.log(e.getLocalizedMessage());
        }
    }

    public static String read(String filePath) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            LogUtil.log(e.getLocalizedMessage());
        }
        return null;
    }

    public static void deleteAll(String path) {
        if (path == null){
            return;
        }
        if (!new File(path).exists()) {
            return;
        }
        try {
            Files.walk(Paths.get(path)).sorted(Comparator.reverseOrder()).map(Path::toFile)
                    .peek(System.out::println).forEach(File::delete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String fileNameToSuffix(String fileName) {
        if (fileName.contains(".")) {
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            if (suffix.length() == 0) {
                //处理(xxx.)的情况
                return fileName;
            } else {
                return suffix;
            }
        }
        return fileName;

    }

    public static void createPoizonCacheDir(Project project){
        File parent = new File(project.getBasePath()).getParentFile();
        File folder = new File(parent, ".poizon-cache");
        if(!folder.exists() || !folder.isDirectory()){
            folder.mkdirs();
        }
    }

}
