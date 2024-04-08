package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class DangNhap extends JFrame implements ActionListener {

    private JLabel lblTitle;
    private JLabel lblUser;
    private JLabel lblPwd;
    private JTextField txtUser;
    private JPasswordField txtPwd;
    private JButton btnExit;
    private JButton btnLogin;
    private JCheckBox chkShow;

    public DangNhap() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Đăng nhập");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        lblTitle = new JLabel("ĐĂNG NHẬP");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(130, 20, 150, 30);
        panel.add(lblTitle);

        lblUser = new JLabel("Tài khoản:");
        lblUser.setFont(new Font("Arial", Font.BOLD, 16));
        lblUser.setBounds(50, 80, 100, 30);
        panel.add(lblUser);

        txtUser = new JTextField();
        txtUser.setBounds(160, 80, 180, 30);
        panel.add(txtUser);

        lblPwd = new JLabel("Mật khẩu:");
        lblPwd.setFont(new Font("Arial", Font.BOLD, 16));
        lblPwd.setBounds(50, 130, 100, 30);
        panel.add(lblPwd);

        txtPwd = new JPasswordField();
        txtPwd.setBounds(160, 130, 180, 30);
        panel.add(txtPwd);

        chkShow = new JCheckBox("Hiển thị mật khẩu");
        chkShow.setFont(new Font("Arial", Font.PLAIN, 14));
        chkShow.setBounds(160, 170, 150, 25);
        panel.add(chkShow);

        btnLogin = new JButton("Đăng nhập");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogin.setBounds(60, 220, 120, 40);
        panel.add(btnLogin);

        btnExit = new JButton("Thoát");
        btnExit.setFont(new Font("Arial", Font.BOLD, 16));
        btnExit.setBounds(220, 220, 120, 40);
        panel.add(btnExit);

        btnLogin.addActionListener(this);
        btnExit.addActionListener(this);

        chkShow.addActionListener(e -> {
            if (chkShow.isSelected()) {
                txtPwd.setEchoChar((char) 0);
            } else {
                txtPwd.setEchoChar('*');
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            // Xử lý đăng nhập
            String usern = txtUser.getText();
            String pass = new String(txtPwd.getPassword());
            if (usern.equals("")) {
            	JOptionPane.showMessageDialog(null, "Vui lòng nhập username");	
            }else if (pass.equals("")) {
            	JOptionPane.showMessageDialog(null, "Vui lòng nhập password");
            }else if (usern.contains("test") && pass.contains("1234")) {
            	gui.Menu menu = new Menu();
                menu.setVisible(true);
                dispose(); 
            }else {
            	JOptionPane.showMessageDialog(null, "Username hoặc password sai");
            }
            
            
            // Thêm code xử lý đăng nhập ở đây
        } else if (e.getSource() == btnExit) {
            int option = JOptionPane.showConfirmDialog(null, "Bạn có thực sự muốn thoát?", "Thoát", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DangNhap login = new DangNhap();
            login.setVisible(true);
        });
    }
}
