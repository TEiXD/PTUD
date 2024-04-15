package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
    private JComboBox<String> cboMckh;

    private JPanel panelThongTinKhachHang;
    private JLabel lblTenKhachHang;
    private JTextField txtTenKhachHang;
    private JLabel lblMaTau;
    private JTextField txtMaTau;
    private JLabel lblMaVe;
    private JTextField txtMaVe;
    private JLabel lblLoaiVe;
    private JComboBox<String> cboLoaive;
    private JLabel lblToa;
    private JTextField txtToa;
    private JLabel lblMaSoGhe;
    private JTextField txtMaSoGhe;
    private JLabel lblGiaTien;	
    private JTextField txtGiaTien;
    private TitledBorder inputPanelBorder;

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

        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblgadi = new JLabel("Ga đi:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblgadi, gbc);

        txtgadi = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtgadi, gbc);

        JLabel lblgaden = new JLabel("Ga đến:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblgaden, gbc);

        txtgaden = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtgaden, gbc);

        JLabel lblngaydi = new JLabel("Ngày đi:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblngaydi, gbc);

        txtngaydi = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtngaydi, gbc);

        JLabel lblngayve = new JLabel("Ngày về:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(lblngayve, gbc);

        txtngayve = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(txtngayve, gbc);

        JLabel lblMckh = new JLabel();
        lblMckh.setFont(lblMckh.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        panel.add(lblMckh, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        cboMckh = new JComboBox<>(new String[]{"Một chiều", "Khứ Hồi"});
        panel.add(cboMckh, gbc);
        
        JButton btnTimVe = new JButton("Tìm Vé");
        btnTimVe.addActionListener(this);
        
        inputPanelBorder = BorderFactory.createTitledBorder("Thông tin lộ trình");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));
        

        inputPanel.add(panel);
        inputPanel.add(btnTimVe);

        // Bảng
        String[] columns = { "Mã vé", "Mã tàu", "Thời gian đi", "Thời gian về" };
        modelCT = new DefaultTableModel(columns, 0);
        table = new JTable(modelCT);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 300));
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

        
        JLabel lblLoaive = new JLabel("Loại vé");
        lblLoaive.setFont(lblLoaive.getFont().deriveFont(Font.BOLD, 14));
        panelThongTinKhachHang.add(lblLoaive, gbc);
        cboLoaive = new JComboBox<>(new String[]{"Loại 1", "Loại 2"});
        panelThongTinKhachHang.add(cboLoaive, gbc);

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
        
        // Thêm panel trung gian vào inputPanelBorder
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
        Border emptyBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border compoundBorder = BorderFactory.createCompoundBorder(lineBorder, emptyBorder);
        TitledBorder inputPanelBorder = BorderFactory.createTitledBorder(compoundBorder, "Thông tin khách hàng");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        panelThongTinKhachHang.setBorder(inputPanelBorder);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public JComboBox<String> getCboLoaive() {
		return cboLoaive;
	}

	public void setCboLoaive(JComboBox<String> cboLoaive) {
		this.cboLoaive = cboLoaive;
	}

	public JLabel getLblLoaiVe() {
		return lblLoaiVe;
	}

	public void setLblLoaiVe(JLabel lblLoaiVe) {
		this.lblLoaiVe = lblLoaiVe;
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