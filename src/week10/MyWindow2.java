package week10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow2 {

    public static void main(String[] args) {
        MyTextField myTextField = new MyTextField();
        myTextField.setSize(400, 200);
        myTextField.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myTextField.setVisible(true);
    }

    static class MyTextField extends JFrame implements ActionListener {
        JTextField jtf;
        JTextArea jta1;
        JTextArea jta2;
        JButton btn;
        JLabel label;
        boolean isOne = true;

        MyTextField() {
            Container cp = getContentPane();
            cp.setLayout(new FlowLayout());

            jtf = new JTextField(20);
            jta1 = new JTextArea(5,20);
            jta2 = new JTextArea(5,20);
            btn = new JButton("변경");
            label = new JLabel("1번째에 입력");

            cp.add(jtf);
            cp.add(jta1);
            cp.add(jta2);
            cp.add(btn);
            cp.add(label);

            jtf.addActionListener(this);
            btn.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jtf) {
                if (isOne) {
                    jta1.append(jtf.getText() + "\n");
                    jtf.setText("");
                } else {
                    jta2.append(jtf.getText() + "\n");
                    jtf.setText("");
                }
            }

            if (e.getSource() == btn) {
                if (isOne) {
                    isOne = false;
                    label.setText("2번째에 입력");
                } else {
                    isOne = true;
                    label.setText("1번째에 입력");
                }
            }
        }
    }
}
