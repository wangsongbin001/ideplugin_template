package test.swing;

import javax.swing.*;

public class JDialogT {

    public static void main(String[] args) {
//        JOptionPane jOptionPane = new JOptionPane();
//        JOptionPane.showConfirmDialog(null, "是否确认删除", "删除提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        testJTabbedPancel();
    }

    private static void testJTabbedPancel(){
        JFrame jFrame = new JFrame();

        jFrame.add(makeJTabbedPane());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static JTabbedPane makeJTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("计算机"));
        tabbedPane.add("计算机名", jPanel);

        JPanel jPanel2 = new JPanel();
        jPanel2.add(new JLabel("硬件"));
        tabbedPane.add("", jPanel2);

        tabbedPane.setSelectedIndex(1);
        return tabbedPane;
    }

}
