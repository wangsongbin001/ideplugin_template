package test;

import javax.swing.*;
import java.awt.*;

public class JDialogUIT extends JFrame {

    Font font = new Font("楷体",Font.PLAIN,14);

    Color colorSelected = new Color(0, 255, 140);

    public JDialogUIT(){
        setTitle("java 第一个Gui程序");

        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(600, 200));
        container.setBackground(Color.gray);
        ((FlowLayout)container.getLayout()).setAlignment(FlowLayout.LEFT);
//        container.add(Box.createVerticalStrut(50));

        Box boxV = Box.createVerticalBox();
        boxV.setBackground(Color.gray);
//        boxV.setAlignmentX();
        boxV.add(Box.createVerticalStrut(10));


        JPanel panel1 = new JPanel();
//        panel1.setPreferredSize(new Dimension(600, 30));

        panel1.setAlignmentX(20);
        JLabel jLabel = new JLabel("工作区(workspace)：");
        jLabel.setFont(font);
        panel1.add(jLabel);

        jLabel = new JLabel("/Users/songbinwang/AndroidStudioProjects/Du");
        jLabel.setFont(font);
        jLabel.setForeground(colorSelected);
        panel1.add(jLabel);
        boxV.add(panel1);

        boxV.add(Box.createVerticalStrut(10));

        JPanel panel2 = new JPanel();
//        panel2.setPreferredSize(new Dimension(600, 30));
        panel2.setAlignmentX(50);
        jLabel = new JLabel("当前基线版本：");
        jLabel.setFont(font);
        panel2.add(jLabel);
        boxV.add(panel2);

        boxV.add(Box.createVerticalStrut(10));

        container.add(boxV);
        getContentPane().add(container);

        setBounds(100, 100,600, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new JDialogUIT();
    }
}
