package week10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow {

    public static void main(String[] args) {
        MyTextField myTextField = new MyTextField();
        myTextField.setSize(500, 200);
        myTextField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTextField.setVisible(true);
    }

    static class MyTextField extends JFrame implements ActionListener {
        JTextField jtf;
        JTextArea jta;
        JButton btn;
        JButton btn2;
        JScrollPane jsp;

        MyTextField() {
            Container cp = getContentPane();
            cp.setLayout(new FlowLayout());

            jtf = new JTextField(20);
            jta = new JTextArea(7,20);
            jsp = new JScrollPane(jta);
            btn = new JButton("취소");
            btn2 = new JButton("초기화");

            cp.add(jtf);
            cp.add(jsp);
            cp.add(btn);
            cp.add(btn2);

            jtf.addActionListener(this);
            btn.addActionListener(this);
            btn2.addActionListener(this);
            jtf.requestFocus();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jtf) {
                jta.append(jtf.getText().trim() + "\n");
                jtf.setText("");
            }else if (e.getSource() == btn) {
                jtf.setText("");
            }else if (e.getSource() == btn2) {
                jta.setText("");
            }
        }
    }
}
