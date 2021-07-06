package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.gradle.internal.impldep.org.eclipse.jgit.api.Git;

import java.io.File;


public class PupupDialogAction extends AnAction {

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        Messages.showMessageDialog(project, "Hello world!", "Greeting", Messages.getInformationIcon());
        System.out.println("PupupDialogAction actionPerformed");

        String filePath = "/Users/songbinwang/AndroidStudioProjects/du_workspace/";
        String remote = "git@pkg.poizon.com:duapp/android/module/duapp_base.git";

        String userHome = System.getProperty("user.home");
        System.out.println("result: " + userHome);

        try {
            Git.cloneRepository().setDirectory(new File("/Users/songbinwang/AndroidStudioProjects/du_workspace/duapp_base")).setURI(remote).call();
        } catch (Exception ee) {
            ee.printStackTrace();
            System.out.println("result: " + ee.getMessage());
        }
    }
}
