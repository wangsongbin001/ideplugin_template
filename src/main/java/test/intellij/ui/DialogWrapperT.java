package test.intellij.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class DialogWrapperT extends DialogWrapper {

    public DialogWrapperT() {
        super(true);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setSize(new Dimension(400, 200));
        panel.add(new JLabel("hello world"));
        return panel;
    }

    public static void main(String[] args) {
       boolean result = new DialogWrapperT().showAndGet();
    }
}
