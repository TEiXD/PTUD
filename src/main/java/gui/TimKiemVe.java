package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DAO.ChuyenTauDAO;
import connectDB.ConnectDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimKiemVe extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelCT;
    private JPanel pNorth;
    private JLabel lblTieuDe;

    private JTextField txtgadi;
    private JTextField txtgaden;
    private JTextField txtngaydi;
    private JTextField txtngayve;

    private JPanel panelThongTinKhachHang;
    private JLabel lblTenKhachHang;
    private JTextField txtTenKhachHang;
    private JLabel lblMaTau;
    private JTextField txtMaTau;
    private JLabel lblMaVe;
    private JTextField txtMaVe;
    private JLabel lblLoaiVe;
    private JTextField txtLoaiVe;
    private JLabel lblToa;
    private JTextField txtToa;
    private JLabel lblMaSoGhe;
    private JTextField txtMaSoGhe;
    private JLabel lblGiaTien;
    private JTextField txtGiaTien;

    public TimKiemVe(){
        ConnectDB.getInstance().connect();
        new ChuyenTauDAO();
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("Đặt Vé"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 50));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        JLabel lblgadi = new JLabel("Ga đi:");
        txtgadi = new JTextField();
        panel.add(lblgadi);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0); 
        panel.add(txtgadi, gbc);

        panel.add(Box.createVerticalStrut(5));
        
        JLabel lblgaden = new JLabel("Ga đến:");
        txtgaden = new JTextField();
        panel.add(lblgaden);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 0, 0); 
        panel.add(txtgaden, gbc);

        panel.add(Box.createVerticalStrut(10));

        JLabel lblngaydi = new JLabel("Ngày đi:");
        txtngaydi = new JTextField();
        panel.add(lblngaydi);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 0); 
        panel.add(txtngaydi, gbc);

        panel.add(Box.createVerticalStrut(10)); 

        JLabel lblngayve = new JLabel("Ngày về:");
        txtngayve = new JTextField();
        panel.add(lblngayve);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 0); 
        panel.add(txtngayve, gbc);

        panel.add(Box.createVerticalStrut(10)); 
        
        JLabel lblTripType = new JLabel("Một chiều hoặc khứ hồi:");
        JRadioButton rbtnmotchieu = new JRadioButton("Một chiều");
        JRadioButton rbtnkhuhoi = new JRadioButton("Khứ hồi");
        ButtonGroup tripTypeGroup = new ButtonGroup();
        tripTypeGroup.add(rbtnmotchieu);
        tripTypeGroup.add(rbtnkhuhoi);
        panel.add(lblTripType);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST; 
        gbc.insets = new Insets(0, 10, 0, 0);
        panel.add(rbtnmotchieu, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST; 
        panel.add(rbtnkhuhoi, gbc);

        contentPanel.add(panel);
        
        
        JButton btnTimVe = new JButton("Tìm Vé");
        btnTimVe.addActionListener(this);
        contentPanel.add(btnTimVe);

        // Bảng
        String[] columns = { "Mã vé", "Mã tàu", "Thời gian đi", "Thời gian về" };
        modelCT = new DefaultTableModel(columns, 0);
        table = new JTable(modelCT);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 500));
        table.setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.setRowHeight(25);

        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setBackground(Color.lightGray);
        header.setFont(new Font("Times New Roman", Font.BOLD, 20));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        contentPanel.add(scrollPane);

        panelThongTinKhachHang = new JPanel();
        panelThongTinKhachHang.setLayout(new GridLayout(7, 2));
        panelThongTinKhachHang.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(panelThongTinKhachHang);

        lblTenKhachHang = new JLabel("Tên khách hàng:");
        txtTenKhachHang = new JTextField();
        panelThongTinKhachHang.add(lblTenKhachHang);
        panelThongTinKhachHang.add(txtTenKhachHang);

        lblMaTau = new JLabel("Mã tàu:");
        txtMaTau = new JTextField();
        panelThongTinKhachHang.add(lblMaTau);
        panelThongTinKhachHang.add(txtMaTau);

        lblMaVe = new JLabel("Mã vé:");
        txtMaVe = new JTextField();
        panelThongTinKhachHang.add(lblMaVe);
        panelThongTinKhachHang.add(txtMaVe);

        lblLoaiVe = new JLabel("Loại vé:");
        txtLoaiVe = new JTextField();
        panelThongTinKhachHang.add(lblLoaiVe);
        panelThongTinKhachHang.add(txtLoaiVe);

        lblToa = new JLabel("Toa:");
        txtToa = new JTextField();
        panelThongTinKhachHang.add(lblToa);
        panelThongTinKhachHang.add(txtToa);

        lblMaSoGhe = new JLabel("Mã số ghế:");
        txtMaSoGhe = new JTextField();
        panelThongTinKhachHang.add(lblMaSoGhe);
        panelThongTinKhachHang.add(txtMaSoGhe);

        lblGiaTien = new JLabel("Giá tiền:");
        txtGiaTien = new JTextField();
        panelThongTinKhachHang.add(lblGiaTien);
        panelThongTinKhachHang.add(txtGiaTien);

        JButton btnDatVe = new JButton("Đặt vé");
        btnDatVe.addActionListener(this);
        contentPanel.add(btnDatVe);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

    /*public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện khi nút "Tìm vé" hoặc "Đặt vé" được nhấn
        if (e.getActionCommand().equals("Tìm Vé")) {
            // Xử lý tìm vé
            String gadi = txtgadi.getText();
            String gaden = txtgaden.getText();
            String ngaydi = txtngaydi.getText();
            String ngayve = txtngayve.getText();
            // Thực hiện tìm vé và cập nhật dữ liệu lên bảng modelCT
            // ...
        } else if (e.getActionCommand().equals("Đặt vé")) {
            // Xử lý đặt vé
            String tenKhachHang = txtTenKhachHang.getText();
            String maTau = txtMaTau.getText();
            String maVe = txtMaVe.getText();
            String loaiVe = txtLoaiVe.getText();
            String toa = txtToa.getText();
            String maSoGhe = txtMaSoGhe.getText();
            String giaTien = txtGiaTien.getText();
            // Thực hiện đặt vé và lưu thông tin khách hàng
            // ...
        }
    }*/
}