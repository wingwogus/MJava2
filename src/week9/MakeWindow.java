package week9;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class MakeWindow {

    public static void main(String[] args) {
        MyWindow window = new MyWindow("내가 만든 내 왕국");
        window.setSize(400,400);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(window);

    }

    static class MyWindow extends JFrame {
        public MyWindow(String title) throws HeadlessException {
            super(title);
            Container contentPane = getContentPane();
            setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
            setBackground(Color.RED);
        }
    }
}
