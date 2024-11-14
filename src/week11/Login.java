package week11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    public static void main(String[] args) {
        new LoginFrame("로그인").setVisible(true);
    }
}

class LoginFrame extends JFrame implements ActionListener {
    JTextField loginField, passwdField;
    public LoginFrame(String title) {
        setTitle(title);
        setSize(800,800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBackground(Color.LIGHT_GRAY);

        JLabel hospitalLabel = new JLabel("MJ HOSPITAL", new ImageIcon("src/week11/mjicon.png"), JLabel.LEFT);
        hospitalLabel.setFont(new Font("Serif", Font.BOLD, 30));
        logoPanel.add(hospitalLabel, BorderLayout.WEST);

        add(logoPanel, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        JLabel imgLabel = new JLabel(new ImageIcon("src/week11/mjicon2.png"), JLabel.CENTER);
        JLabel titleLabel = new JLabel("MJ HOSPITAL", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        JLabel loginLabel = new JLabel("Login");
        loginField = new JTextField(20);
        JLabel passwdLabel = new JLabel("Password");
        passwdField = new JPasswordField(20);
        JButton loginButton = new JButton("로그인");
        JButton registerButton = new JButton("회원 추가");

        imgLabel.setBounds(250,150,300,100);
        titleLabel.setBounds(250,250,300,100);
        loginLabel.setBounds(250, 500, 100, 30);
        loginField.setBounds(350, 500, 200, 30);
        passwdLabel.setBounds(250, 550, 100, 30);
        passwdField.setBounds(350, 550, 200, 30);
        loginButton.setBounds(250, 600, 140, 30);
        registerButton.setBounds(410, 600, 140, 30);

        passwdField.addActionListener(this);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        loginPanel.add(imgLabel);
        loginPanel.add(titleLabel);
        loginPanel.add(loginLabel);
        loginPanel.add(loginField);
        loginPanel.add(passwdLabel);
        loginPanel.add(passwdField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        add(loginPanel, BorderLayout.CENTER);
    }

    public void checkLogin(String id, String pass) {
        if (id.equals("doctor") && pass.equals("1234")) {
            JOptionPane.showMessageDialog(null, "로그인 성공");
        } else {
            JOptionPane.showMessageDialog(null, "로그인 실패");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("회원 추가")) {
            dispose();
            new RegisterPanel();
        } else {
            checkLogin(loginField.getText(), passwdField.getText());
        }
    }
}

class RegisterPanel extends JFrame{

    public RegisterPanel() {
        setTitle("의료진 추가");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.LIGHT_GRAY);

        JLabel titleLabel = new JLabel("의료진 추가");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel, BorderLayout.CENTER);

        String[] labels = {"이름", "연락처", "ID", "전공", "PASSWORD", "휴무일", "주민번호", "주소", "직급"};
        int[] x = {20, 320};
        int y = 80;

        JTextField[] textFields = new JTextField[labels.length];

        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBounds(x[i % 2], y, 80, 30);
            mainPanel.add(label);

            JTextField textField = new JTextField();
            textField.setBounds(x[i % 2] + 100, y, 150, 30);
            mainPanel.add(textField);

            textFields[i] = textField;

            if (i % 2 == 1) {
                y += 50;
            }
        }

        JButton addButton = new JButton("추가");
        addButton.setBounds(320, 280, 150, 30);
        mainPanel.add(addButton);

        setVisible(true);
    }
}