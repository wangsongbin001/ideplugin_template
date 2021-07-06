package test.swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

public class JListenerT extends JFrame {

    int clicked = 0;
    public JListenerT(){
        setTitle("事件监听");
        Box bV = Box.createVerticalBox();

        JButton jButton = new JButton("按钮，点击次数：" + clicked);
        jButton.setBackground(Color.blue);
        jButton.setOpaque(true);
        jButton.setBorderPainted(false);
//        jButton.setBorder(BorderFactory.createLineBorder(Color.black));
        jButton.setForeground(Color.red);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clicked ++;
                jButton.setText("按钮，点击次数：" + clicked);
                System.out.println("source:" + (actionEvent.getSource() == jButton));
                System.out.println("commndLine:" + actionEvent.getActionCommand());
            }
        });
        bV.add(jButton);

        JScrollPane scrollPane = new JScrollPane();
        JList jList = new JList();
        scrollPane.setViewportView(jList);

        String[] list = new String[12];
        for(int i=0;i<list.length;i++){
            list[i] = "列表第" + i + "个元素";
        }
        jList.setListData(list);

        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                System.out.println("listSelectionEvent:" + listSelectionEvent.toString());
            }
        });
        bV.add(scrollPane);

        setContentPane(bV);

        jButton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                System.out.println("focusGained:" + e.getID());
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                System.out.println("focusLost:" + e.getID());
            }
        });
    }


    public static void main(String[] args) {
//        JFrame jFrame = new JListenerT();
//        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        jFrame.setBounds(100, 100, 400, 200);
//        jFrame.setVisible(true);
        testMouseListener();
    }

    private static void testMouseListener(){
        JFrame jf = new JFrame("swing JComboBox");
        JPanel container = new JPanel(new BorderLayout());
        container.setName("container");

        JPanel north = new JPanel();
        north.setName("north");
        north.add(new JButton("button1"));
        north.add(new JButton("button2"));
        north.add(new JButton("button3"));
        container.add(north, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setName("center");
        center.add(new Label("label1"));
        center.add(new Label("label2"));
        center.add(new Label("label3"));
        container.add(center, BorderLayout.CENTER);

        container.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("source" + mouseEvent.getSource().toString());
                JComponent component = (JComponent) mouseEvent.getSource();
                while(component.getParent() != null){
                    component = (JComponent) component.getParent();
                    System.out.println("source" + component.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });

        jf.add(container);
        jf.setBounds(100, 100, 400, 200);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }
}
