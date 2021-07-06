package test.swing;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ProgressBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JComponentT {

    public static void main(String[] args) {
        testJLabel();
//        testJTextField();
//        testJCheckBox();
//        testJRadioButton();
//        testJComboBox();
//        testJList();
//        testJSlider_JProgressBar();
//        testJTextArea();
    }

    private static void testJTextArea(){
        JFrame jf = new JFrame("swing JComboBox");
        Box bV = Box.createHorizontalBox();
        bV.setBackground(Color.gray);
        JTextArea jTextArea = new JTextArea();
        jTextArea.setText("要用到一个文本显示区，为了便于复制，我用了JTextArea。" +
                "JTextArea本身默认的是不换行，不滚动条显示。你设置完他的大小之后，他就只是显示在这个窗口大小内的内容。");
        jTextArea.setPreferredSize(new Dimension(100, 60));
        jTextArea.setSize(new Dimension(100, 60));
        jTextArea.setLineWrap(true);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 60));
        panel.add(jTextArea);

        bV.add(panel);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    static Timer timer = null;
    private static void testJSlider_JProgressBar() {
        JFrame jf = new JFrame("swing JComboBox");
        Box bV = Box.createVerticalBox();

        JLabel jLabel = new JLabel("当前刻度：0");
        bV.add(jLabel);

        JSlider jSlider = new JSlider(0, 100);//0 -100 取值范围
        jSlider.setMajorTickSpacing(10);//设置主刻度
        jSlider.setMinorTickSpacing(5);//设置次刻度
        jSlider.setPaintLabels(true);//展示标签
        jSlider.setPaintTicks(true);//展示刻度
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                jLabel.setText("当前刻度：" + jSlider.getValue());
            }
        });

        bV.add(jSlider);

        JPanel jPanel = new JPanel();
        jPanel.setSize(new Dimension(300, 30));
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setString("升级中");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true) {
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    jProgressBar.setValue(jProgressBar.getValue() + 10);
//                    if (jProgressBar.getValue() >= jProgressBar.getMaximum()) {
//                        break;
//                    }
//                }
//                jProgressBar.setString("升级完成");
//            }
//        }).start();
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jProgressBar.setValue(jProgressBar.getValue() + 10);
                if (jProgressBar.getValue() >= jProgressBar.getMaximum()) {
                    jProgressBar.setString("升级完成");
                    if(timer.isRunning()) {
                        timer.stop();
                    }
                }
            }
        });
        timer.setInitialDelay(3000);
        timer.start();

        jPanel.add(jProgressBar);

        bV.add(jPanel);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);


    }

    private static void testJList() {
        JFrame jf = new JFrame("swing JComboBox");
        Box bV = Box.createVerticalBox();

        JScrollPane scrollPane = new JScrollPane();
        JList jList = new JList();
        scrollPane.setViewportView(jList);

        String[] list = new String[12];
        for(int i=0;i<list.length;i++){
            list[i] = "列表第" + i + "个元素";
        }
        jList.setListData(list);
        bV.add(scrollPane);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    private static void testJComboBox() {
        JFrame jf = new JFrame("swing JComboBox");
        Box bV = Box.createVerticalBox();

        JPanel panel = new JPanel();
        panel.add(new JLabel("请选择证件类型"));
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("--请选择--");
        comboBox.addItem("身份证");
        comboBox.addItem("驾驶证");
        comboBox.addItem("军官证");
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("itemEvent:" + comboBox.getSelectedItem());
                }
            }
        });

        panel.add(comboBox);


        bV.add(panel);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);

    }

    private static void testJRadioButton() {
        JFrame jf = new JFrame("swing JRadioButton");
        Box bV = Box.createVerticalBox();

        JPanel jPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        JRadioButton b1 = new JRadioButton("春天", true);
        JRadioButton b2 = new JRadioButton("夏天", true);
        JRadioButton b3 = new JRadioButton("秋天", true);
        JRadioButton b4 = new JRadioButton("冬天", true);

        group.add(b1);
        group.add(b2);
        group.add(b3);
        group.add(b4);

        jPanel.add(b1);
        jPanel.add(b2);
        jPanel.add(b3);
        jPanel.add(b4);

        bV.add(jPanel);
        bV.add(new JLabel("selected: " + group.getSelection().getActionCommand() + " " + group.getSelection().getMnemonic()));


        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    private static void testJCheckBox() {
        JFrame jf = new JFrame("swing JTextField");
        Box bV = Box.createVerticalBox();

        JPanel jPanel = new JPanel();
        jPanel.add(new JCheckBox("java", true));
        jPanel.add(new JCheckBox("c++", false));
        bV.add(jPanel);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    private static void testJTextField(){
        JFrame jf = new JFrame("swing JTextField");
        Box bV = Box.createVerticalBox();

        JTextField textField = new JTextField(28);
        textField.setText("java 文本框组件，示例");
        textField.setBackground(Color.gray);
//        textField.setAutoscrolls(true);
        bV.add(textField);

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    private static void testJLabel(){
        JFrame jf = new JFrame("swing 标签");
        Box bV = Box.createVerticalBox();
        JLabel panel = new JLabel("new JLabel(\"第一个标签\")new JLabel(\"第一个标签\")new JLabel(\"第一个标签\")");
        panel.setPreferredSize(new Dimension(100, 30));
        bV.add(panel);

        Icon logo = new ImageIcon("/Users/songbinwang/IdeaProjects/IdePluginDemo/src/main/resources/icons/poizon-logo.png");

        bV.add(new JLabel(logo));

        bV.add(new JLabel("标题", logo, JLabel.RIGHT));

        jf.add(bV);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }


}
