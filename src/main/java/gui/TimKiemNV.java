package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import connectDB.*;
import entity.*;
import java.util.List;


public class TimKiemNV extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelNV;
    private JButton btnTimKiem;
    private JButton btnLamMoi;
    private NhanVienDAO nvDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboGioiTinh; 	
    private JComboBox<String> cboTrinhDo;
    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtNgaySinh;					 
    private JTextField txtSDT;
    private JTextField txtEmail;
    private TitledBorder inputPanelBorder;
    private JComboBox<String> cboMaNhaGa;
    
    
    public TimKiemNV() {
    	
        ConnectDB.getInstance().connect();
        nvDAO = new NhanVienDAO();
        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("TÌM KIẾM NHÂN VIÊN"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(lblMaNV.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblMaNV, gbc);
        txtMaNV = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(txtMaNV, gbc);
        txtMaNV.setColumns(20);

        JLabel lblHoTen = new JLabel("Họ tên");
        lblHoTen.setFont(lblHoTen.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.gridy = 0;
        inputPanel.add(lblHoTen, gbc);
        txtHoTen = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 0;
        inputPanel.add(txtHoTen, gbc);
        
        JLabel lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setFont(lblNgaySinh.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 6;
        gbc.gridy = 0;
        inputPanel.add(lblNgaySinh, gbc);
        txtNgaySinh = new JTextField(20);
        gbc.gridx = 7;
        gbc.gridy = 0;
        inputPanel.add(txtNgaySinh, gbc);
        
        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(lblGioiTinh.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblGioiTinh, gbc);
        cboGioiTinh = new JComboBox<>(new String[]{"None","Nam", "Nữ"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(cboGioiTinh, gbc);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(lblSDT.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.gridy = 1;
        inputPanel.add(lblSDT, gbc);
        txtSDT = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 1;
        inputPanel.add(txtSDT, gbc);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(lblEmail.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 6;
        gbc.gridy = 1;
        inputPanel.add(lblEmail, gbc);
        txtEmail = new JTextField(20);
        gbc.gridx = 7;
        gbc.gridy = 1;
        inputPanel.add(txtEmail, gbc);

        JLabel lblTrinhDo = new JLabel("Trình độ");
        lblTrinhDo.setFont(lblTrinhDo.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(lblTrinhDo, gbc);
        cboTrinhDo = new JComboBox<>(new String[]{"None","Đại học", "Cử nhân", "Cao đẳng"});
        gbc.gridx = 1;
        gbc.gridy = 3;
        inputPanel.add(cboTrinhDo, gbc);
        
        JLabel lblMaNhaGa = new JLabel("Mã nhà ga");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.gridy = 3;
        inputPanel.add(lblMaNhaGa, gbc);

        cboMaNhaGa = new JComboBox<>(new String[]{"None"});
        List<NhaGa> listNG = NhaGaDAO.layThongTin();
        for(NhaGa ng : listNG) {
        	cboMaNhaGa.addItem(ng.getMaNhaGa());
        }
        gbc.gridx = 4;
        gbc.gridy = 3;
        inputPanel.add(cboMaNhaGa, gbc);
        
        inputPanelBorder = BorderFactory.createTitledBorder("Tìm kiếm nhân viên");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));
        
   
        
        //
        String[] columns = {
        		"Mã nhân viên", "Họ tên", "Giới tính", "Số điện thoại", "Email", "Ngày Sinh", "Trình độ", "Mã nhà ga"
        };
        modelNV = new DefaultTableModel(columns, 0);
        table = new JTable(modelNV);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 550));
        table.setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.setRowHeight(25);
        
        //
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

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.addActionListener(this);
        panelButton.add(btnTimKiem);
        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.addActionListener(this);
        panelButton.add(btnLamMoi);

        docDuLieuVaoTable();
    }
    
    private void docDuLieuVaoTable() {
        modelNV.setRowCount(0); // Clear table
        List<NhanVien> listNV = nvDAO.layThongTin();
        for (NhanVien nv : listNV) {
            Object[] rowData = {
                    nv.getMaNV(), nv.getHoTen(), nv.getGioiTinh(), nv.getSDT(), nv.getEmail(), nv.getNgaySinh(), nv.getTrinhDo(), nv.getNhaGa().getMaNhaGa()};
            modelNV.addRow(rowData);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTimKiem) {
            timKiemNhanVien();
        } else if (e.getSource() == btnLamMoi) {
            lamMoi();
        }
    }

    private void timKiemNhanVien() {
        String maNV = txtMaNV.getText().trim();
        String hoTen = txtHoTen.getText().trim();
        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
        String sdt = txtSDT.getText().trim();
        String email = txtEmail.getText().trim();
        String ngaySinh = txtNgaySinh.getText().trim();
        String trinhDo = (String) cboTrinhDo.getSelectedItem();
        String maNhaGa = (String) cboMaNhaGa.getSelectedItem();
        NhaGa ng = new NhaGa(maNhaGa);
        NhanVien nv = new NhanVien(maNV, hoTen, gioiTinh, sdt, email, ngaySinh, trinhDo, trinhDo, ng);
        List<NhanVien> dsNV = nvDAO.timKiemNhanVien(nv);

        modelNV.setRowCount(0);

        for (NhanVien nhanVien : dsNV) {
            modelNV.addRow(new Object[]{nhanVien.getMaNV(), nhanVien.getHoTen(), nhanVien.getNgaySinh(),
                    nhanVien.getGioiTinh(), nhanVien.getSDT(), nhanVien.getEmail(), nhanVien.getTrinhDo()});
        }
    }

    private void lamMoi() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        cboGioiTinh.setSelectedIndex(0);
        txtSDT.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
        cboTrinhDo.setSelectedIndex(0);
        modelNV.setRowCount(0);
        docDuLieuVaoTable();
    }
}
