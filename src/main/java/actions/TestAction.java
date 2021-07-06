package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.psi.JavaDirectoryService;
import org.jetbrains.annotations.NotNull;

public class TestAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        //展示弹窗。
        JavaDirectoryService aa;
    }
}
