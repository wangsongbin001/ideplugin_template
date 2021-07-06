package utils;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.vcs.VcsNotifier;
import com.intellij.ui.JBColor;

import java.awt.*;

/**
 * m.poizon.com Inc.
 * Copyright (c) 1999-2020 All Rights Reserved.
 *
 * @author panes
 * @contact pantao@theduapp.com
 */
public class NotificationUtil {
    public static void show(String msg, Project project){
        show(msg, "ISC", project);
    }
    public static void show(String msg, String title, Project project){
        LogUtil.log("show notification "+msg);
        Notification notification = new Notification(VcsNotifier.IMPORTANT_ERROR_NOTIFICATION.getDisplayId(), title,
                msg,
                NotificationType.INFORMATION);
        Notifications.Bus.notify(notification, project);
    }

    /**
     * 显示dialog
     *
     * @param editor
     * @param result 内容
     * @param time   显示时间，单位秒
     */
    public static void showPopupBalloon(final Editor editor, final String result, final int time) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(new Color(116, 214, 238), new Color(76, 112, 117)), null)
                        .setFadeoutTime(time * 1000)
                        .createBalloon()
                        .show(factory.guessBestPopupLocation(editor), Balloon.Position.below);
            }
        });
    }
}
