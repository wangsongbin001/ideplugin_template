package component;

import com.intellij.openapi.components.ProjectComponent;
import utils.LogUtil;

public class PComponent implements ProjectComponent {

    @Override
    public void projectOpened() {
        ProjectComponent.super.projectOpened();
        LogUtil.log("projectOpened");
    }

    @Override
    public void projectClosed() {
        ProjectComponent.super.projectClosed();
        LogUtil.log("projectClosed");
    }
}
