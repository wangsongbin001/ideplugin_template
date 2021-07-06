package test.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutT {

    public static void main(String[] args) {
        testBorderLayout();
//        testFlowLayout();
//        testCardLayout();
//        testGridLayout();
//        testBoxLayout();
    }

    //盒布局，类似Android中的LinearLayout
    private static void testBoxLayout(){
        JFrame jf = new JFrame("java 第7个Gui程序");
        Box bH = Box.createHorizontalBox();
        Box bV = Box.createVerticalBox();
        bH.add(Box.createVerticalStrut(50));//50的垂直框架
        bH.add(new JButton("西"));
        bH.add(Box.createHorizontalStrut(140));//140的水平框架
        bH.add(new JButton("东"));
        bH.add(Box.createHorizontalGlue());//可拉伸的水平框架（weight=1的view）
        bH.add(bV);
        bH.add(Box.createHorizontalStrut(40));

        bV.add(Box.createVerticalGlue());
        bV.add(new JButton("北"));
        bV.add(Box.createVerticalGlue());
        bV.add(new JButton("南"));
        bV.add(Box.createVerticalGlue());

        jf.add(bH);
        jf.setBounds(100,100,400,200);
        //设置窗口关闭动作
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //网格包布局
    private static void testGridbagLayout(){
        //在网格的基础上提供复杂的布局

    }

    //网格布局
    private static void testGridLayout(){
        JFrame jf = new JFrame("java 第6个Gui程序");
        JPanel panel = new JPanel(new GridLayout(4,5,20, 20));
        panel.add(new JButton("7"));    //添加按钮
        panel.add(new JButton("8"));
        panel.add(new JButton("9"));
        panel.add(new JButton("/"));
        panel.add(new JButton("4"));
        panel.add(new JButton("5"));
        panel.add(new JButton("6"));
        panel.add(new JButton("*"));
        panel.add(new JButton("1"));
        panel.add(new JButton("2"));
        panel.add(new JButton("3"));
        panel.add(new JButton("-"));
        panel.add(new JButton("0"));
        panel.add(new JButton("."));
        panel.add(new JButton("="));
        panel.add(new JButton("+"));

        jf.add(panel);
        jf.setBounds(300,200,600,300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //边框布局
    private static void testBorderLayout(){
        JFrame jf = new JFrame("java 第三个Gui程序");
        jf.setSize(400,200);
        jf.setLayout(new BorderLayout());
        jf.add(new JButton("上"), BorderLayout.NORTH);
        jf.add(new JButton("下"), BorderLayout.SOUTH);
        jf.add(new JButton("左"), BorderLayout.WEST);
        jf.add(new JButton("右"), BorderLayout.EAST);

        jf.setBounds(300,200,600,300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //流式布局
    private static void testFlowLayout(){
        JFrame jf = new JFrame("java 第四个Gui程序");
        jf.setSize(400,200);
        jf.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        jf.add(new JButton("1"));
        jf.add(new JButton("2"));
        jf.add(new JButton("3"));
        jf.add(new JButton("4"));
        jf.add(new JButton("5"));
        jf.add(new JButton("6"));
        jf.add(new JButton("7"));
        jf.add(new JButton("8"));
        jf.add(new JButton("9"));

        jf.setBackground(Color.gray);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //流式布局
    private static void testCardLayout(){
        JFrame jf = new JFrame("java 第五个Gui程序");
        jf.setSize(400,200);

        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);
        JPanel p1 = new JPanel();
        p1.add(new JLabel("第一个面板"));
        JPanel p2 = new JPanel();
        p2.add(new JLabel("第二个面板"));

        cards.add(p1, "p1");
        cards.add(p2, "p2");

        JPanel tabs = new JPanel();
        JButton button = new JButton("面板切换");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                cardLayout.next(cards);
                cardLayout.show(cards, "p1");
            }
        });

        tabs.add(button);
        tabs.add(cards);

        jf.add(tabs);
        cardLayout.show(cards, "p2");

        jf.setBackground(Color.gray);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
