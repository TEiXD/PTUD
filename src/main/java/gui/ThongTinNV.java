package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import DAO.*;
import connectDB.ConnectDB;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.List;

public class ThongTinNV extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaNV;
    private DefaultTableModel modelNV;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnXoaTrang;
    private NhanVienDAO nvDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboGioiTinh;
    private JComboBox<String> cboTrinhDo;
    private JTextField txtHoTen;
    private JTextField txtCCCD;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JTextField txtMaNhaGa;
    private JTextField txtNgaySinh;
    private TitledBorder inputPanelBorder;

    public ThongTinNV() throws SQLException {

        ConnectDB.getInstance().connect();
        nvDAO = new NhanVienDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 6, 50, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        // Add input fields to the panel
        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(lblMaNV.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblMaNV);

        txtMaNV = new JTextField();
        inputPanel.add(txtMaNV);
        txtMaNV.setColumns(10);

        JLabel lblHoTen = new JLabel("Họ tên");
        lblHoTen.setFont(lblHoTen.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblHoTen);
        txtHoTen = new JTextField();
        inputPanel.add(txtHoTen);

        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(lblGioiTinh.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGioiTinh);
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        inputPanel.add(cboGioiTinh);

        JLabel lblCCCD = new JLabel("CCCD");
        lblCCCD.setFont(lblCCCD.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblCCCD);
        txtCCCD = new JTextField();
        inputPanel.add(txtCCCD);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(lblSDT.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblSDT);
        txtSDT = new JTextField();
        inputPanel.add(txtSDT);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(lblEmail.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblEmail);
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        JLabel lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setFont(lblNgaySinh.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblNgaySinh);
        txtNgaySinh = new JTextField();
        inputPanel.add(txtNgaySinh);

        JLabel lblTrinhDo = new JLabel("Trình độ");
        lblTrinhDo.setFont(lblTrinhDo.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblTrinhDo);
        cboTrinhDo = new JComboBox<>(new String[]{"Đại học", "Cử nhân", "Cao đẳng"});
        inputPanel.add(cboTrinhDo);

        JLabel lblMaNhaGa = new JLabel("Mã nhà ga");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblMaNhaGa);
        txtMaNhaGa = new JTextField();
        inputPanel.add(txtMaNhaGa);

        inputPanelBorder = BorderFactory.createTitledBorder("Thông Tin Nhân Viên");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Thay đổi màu của LineBorder thành đen
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));

        // Table
        String[] columns = {
                "Mã nhân viên", "Họ tên", "CCCD", "Giới tính", "Số điện thoại", "Email", "Ngày sinh", "Trình độ", "Mã nhà ga"
        };
        modelNV = new DefaultTableModel(columns, 0);
        table = new JTable(modelNV);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 550));
        table.setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.setRowHeight(25);

        // Table header
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setBackground(Color.lightGray);
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        contentPanel.add(scrollPane);

        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(173, 216, 230));
        panelButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.add(panelButton);

        btnThem = new JButton("Thêm");
        btnThem.addActionListener(this);
        panelButton.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.addActionListener(this);
        panelButton.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(this);
        panelButton.add(btnXoa);

        btnXoaTrang = new JButton("Xóa trắng");
        btnXoaTrang.addActionListener(this);
        panelButton.add(btnXoaTrang);

        docDuLieuVaoTable();

    }

    private void docDuLieuVaoTable() {
        modelNV.setRowCount(0); // Clear table
        List<NhanVien> listNV = nvDAO.layThongTin();
        for (NhanVien nv : listNV) {
            Object[] rowData = {
                    nv.getMaNV(), nv.getHoTen(), nv.getCCCD(), nv.getGioiTinh(), nv.getSDT(), nv.getEmail(), nv.getNgaySinh(), nv.getTrinhDo(), nv.getNhaGa().getMaNhaGa()};
            modelNV.addRow(rowData);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
        if (e.equals(btnThem)) {
            // Lấy thông tin từ các trường nhập liệu
            String maNV = txtMaNV.getText();
            String hoTen = txtHoTen.getText();
            String gioiTinh = cboGioiTinh.getSelectedItem().toString();
            String cccd = txtCCCD.getText();
            String sdt = txtSDT.getText();
            String email = txtEmail.getText();
            String ngaySinh = txtNgaySinh.getText();
            String trinhDo = cboTrinhDo.getSelectedItem().toString();
            String maNhaGa = txtMaNhaGa.getText();
            
            NhaGa ng = new NhaGa(maNhaGa);

            // Tạo đối tượng NhanVien từ thông tin nhập liệu
            NhanVien nv = new NhanVien(maNV, hoTen, cccd, gioiTinh, sdt, email, null, trinhDo, null);

            // Thêm nhân viên vào cơ sở dữ liệu
            nvDAO.addNV(nv);

            // Load lại bảng sau khi thêm
            docDuLieuVaoTable();
            

        } else if (e.getSource() == btnSua) {
            // Code xử lý sự kiện sửa nhân viên
            // Cập nhật thông tin của nhân viên được chọn trong bảng
        } else if (e.getSource() == btnXoa) {
            // Code xử lý sự kiện xóa nhân viên
            // Xóa nhân viên được chọn trong bảng và cơ sở dữ liệu
        } else if (e.getSource() == btnXoaTrang) {
        	txtMaNV.setText("");
            txtHoTen.setText("");
            cboGioiTinh.setSelectedIndex(0);
            txtCCCD.setText("");
            txtSDT.setText("");
            txtEmail.setText("");
            txtNgaySinh.setText("");
            cboTrinhDo.setSelectedIndex(0);
            txtMaNhaGa.setText("");
        }
    }

 }