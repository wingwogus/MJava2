package week9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyEvent {
    public static void main(String[] args) {
        MyButton window = new MyButton("커몬");
    }

    static class MyButton extends JFrame implements ActionListener {
        private JLabel result;

        public MyButton(String title) throws HeadlessException {
            super(title);
            Container c = getContentPane();
            c.setLayout(new FlowLayout());

            setSize(1000, 1000);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JButton manBtn = new JButton("남자");
            JButton wmnBtn = new JButton("여자");
            manBtn.addActionListener(this);
            wmnBtn.addActionListener(this);
            JLabel jLabel1 = new JLabel("당신의 성별은?");

            result = new JLabel("");
            c.add(manBtn);
            c.add(wmnBtn);
            c.add(jLabel1);
            c.add(result);

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            result.setText(actionCommand);
        }
    }
}
