package test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JToolT extends JFrame {

    JTextField jTextField;
    public JToolT(){
        setTitle("工具测试");
        Box bV = Box.createVerticalBox();

        Box b1 = Box.createHorizontalBox();
        b1.add(Box.createHorizontalStrut(10));
        b1.add(new JLabel("workspace："));
        jTextField = new JTextField(25);
        b1.add(jTextField);
        b1.add(Box.createHorizontalStrut(10));
        JButton jButton = new JButton("文件选择器");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

                int val = jFileChooser.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION == val) {
                    jTextField.setText(jFileChooser.getSelectedFile().toString());
                }else{
                    jTextField.setText("");
                }
            }
        });
        b1.add(jButton);

        JButton b2 = new JButton("颜色选择器");
        setBackground(Color.GRAY);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JColorChooser cc=new JColorChooser();
                Color color = cc.showDialog(JToolT.this,"颜色选择器", JToolT.this.getBackground());
                if(color != null){
                    JToolT.this.setBackground(color);
                }
            }
        });

        bV.add(b1);
        bV.add(b2);

        getContentPane().add(bV);
        pack();
    }

    public static void main(String[] args) {
        JFrame jFrame = new JToolT();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setBounds(100, 100, 400, 200);
        jFrame.setVisible(true);
    }
}
