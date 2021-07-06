package test.swing;

import javax.swing.*;
import java.awt.*;

public class JFrameT extends JFrame {

    JFrameT(){
        setTitle("java 第一个Gui程序");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //标签
        JLabel jl = new JLabel("这是使用JFrame类创建的窗口");
        getContentPane().add(jl);
        setVisible(true);
    }

    public static void main(String[] args) {
//        new JFrameT();
         showJPanel();

    }

    private static void showJPanel(){
        JFrame jf = new JFrame("java 第二个Gui程序");
        jf.setSize(400, 200);
        JPanel jp = new JPanel();
        jf.getContentPane().add(jp);

        jp.setSize(200, 200);
        jp.setToolTipText("hello jpanel");
        jp.setBackground(Color.blue);
        jp.add(new JLabel("这个是放在jPancel上的标签"));

        jf.setVisible(true);
    }
}
