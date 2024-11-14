package week10;
import javax.swing.*;
import java.awt.*;

public class TabbedPaneExample {

    public static void main(String[] args) {
        // JFrame 생성
        JFrame frame = new JFrame("Tabbed Pane Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        Container contentPane = frame.getContentPane();
        // JTabbedPane 생성
        JTabbedPane tabbedPane = new JTabbedPane();
        contentPane.add(tabbedPane);

        // 첫 번째 탭 생성
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("This is the first tab."));
        tabbedPane.addTab("Tab 1", panel1);

        // 두 번째 탭 생성
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is the second tab."));
        tabbedPane.addTab("Tab 2", panel2);

        // 세 번째 탭 생성
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("This is the third tab."));
        tabbedPane.addTab("Tab 3", panel3);

        // JTabbedPane을 JFrame에 추가
        frame.add(tabbedPane, BorderLayout.CENTER);

        // JFrame 표시
        frame.setVisible(true);
    }
}