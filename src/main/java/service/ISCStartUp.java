package service;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;
import utils.CommonUtil;
import utils.LogUtil;

import java.io.File;

/**
 * ide启动时的回调，可以在ide启动时，做一些初始化的操作
 */
public class ISCStartUp implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        LogUtil.log("ISC startup ");
        CommonUtil.createPoizonCacheDir(project);
    }
}
