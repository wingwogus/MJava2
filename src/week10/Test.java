package week10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JFrame {

    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextField textField;
    private JButton button;
    private JLabel label;
    private final String first = "첫번째";
    private final String second = "두번째";
    private int flag = 0;

    public Test() {
        textField = new JTextField(20);
        textArea1 = new JTextArea(7, 20);
        textArea2 = new JTextArea(7, 20);
        button = new JButton("변경");
        label = new JLabel(first+"에 입력");
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag == 0) {
                    JTextField tf = (JTextField) e.getSource();
                    textArea1.append(tf.getText() + "\n");
                    tf.setText("");
                }
                else if (flag == 1) {
                    JTextField tf = (JTextField) e.getSource();
                    textArea2.append(tf.getText() + "\n");
                    tf.setText("");
                }
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (flag == 0) {
                    flag = 1;
                    label.setText(second+"에 입력");
                }
                else if (flag == 1) {
                    flag = 0;
                    label.setText(first+"에 입력");
                }
            }
        });
        Container con = getContentPane();
        con.setLayout(new FlowLayout());
        con.add(textField);
        con.add(textArea1);
        con.add(textArea2);
        con.add(new JScrollPane(textArea1));
        con.add(new JScrollPane(textArea2));
        con.add(button);
        con.add(label);
    }

    public static void main(String[] args) {
        Test win = new Test();
        win.setTitle("sex");
        win.setSize(750, 300);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.setVisible(true);
    }
}