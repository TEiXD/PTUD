package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import DAO.NhaGaDAO;
import DAO.NhanVienDAO;
import connectDB.ConnectDB;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private TitledBorder inputPanelBorder;
	private JComboBox<String> cboNhaGa;
	private NhaGaDAO nhaGaDAO;
	private JDateChooser dateNgaySinh;

    public ThongTinNV() throws SQLException {

        ConnectDB.getInstance().connect();
        nvDAO = new NhanVienDAO();
        nhaGaDAO = new NhaGaDAO();

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
        txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtMaNV);
        txtMaNV.setColumns(10);

        JLabel lblHoTen = new JLabel("Họ tên");
        lblHoTen.setFont(lblHoTen.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblHoTen);
        txtHoTen = new JTextField();
        txtHoTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtHoTen);

        JLabel lblGioiTinh = new JLabel("Giới tính");
        lblGioiTinh.setFont(lblGioiTinh.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGioiTinh);
        cboGioiTinh = new JComboBox<>(new String[]{"Nam", "Nữ"});
        cboGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(cboGioiTinh);

        JLabel lblCCCD = new JLabel("CCCD");
        lblCCCD.setFont(lblCCCD.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblCCCD);
        txtCCCD = new JTextField();
        txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtCCCD);

        JLabel lblSDT = new JLabel("Số điện thoại");
        lblSDT.setFont(lblSDT.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblSDT);
        txtSDT = new JTextField();
        txtSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtSDT);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(lblEmail.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtEmail);

        JLabel lblNgaySinh = new JLabel("Ngày sinh");
        lblNgaySinh.setFont(lblNgaySinh.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblNgaySinh);     
        dateNgaySinh = new JDateChooser();
        dateNgaySinh.setDateFormatString("yyyy-MM-dd");
        inputPanel.add(dateNgaySinh);

        JLabel lblTrinhDo = new JLabel("Trình độ");
        lblTrinhDo.setFont(lblTrinhDo.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblTrinhDo);
        cboTrinhDo = new JComboBox<>(new String[]{"Đại học", "Cử nhân", "Cao đẳng"});
        cboTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(cboTrinhDo);

        JLabel lblMaNhaGa = new JLabel("Mã nhà ga");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblMaNhaGa);
        cboNhaGa = new JComboBox<>();
        cboNhaGa.addItem("Tất cả");
        ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
        for (NhaGa ng : dsNG) {
            cboNhaGa.addItem(ng.getTenNhaGa());
        }
        inputPanel.add(cboNhaGa);

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
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int SelectedRows = table.getSelectedRow(); // Get the selected row index
                if (SelectedRows != -1) { // Check if a row is selected
                	txtMaNV.setText(modelNV.getValueAt(SelectedRows, 0).toString());
                	txtHoTen.setText(modelNV.getValueAt(SelectedRows, 1).toString());
                	txtCCCD.setText(modelNV.getValueAt(SelectedRows, 2).toString());
                	cboGioiTinh.setSelectedItem(modelNV.getValueAt(SelectedRows, 3).toString());
                	txtSDT.setText(modelNV.getValueAt(SelectedRows, 4).toString());
                	txtEmail.setText(modelNV.getValueAt(SelectedRows, 5).toString());
                	String ngaySinhFormat = modelNV.getValueAt(SelectedRows, 6).toString(); // Correct index for date of birth
                	try {
                	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                	    Date parsedDate = dateFormat.parse(ngaySinhFormat);
                	    dateNgaySinh.setDate(parsedDate);
                	} catch (ParseException ex) {
                	    ex.printStackTrace();
                	}
                	cboTrinhDo.setSelectedItem(modelNV.getValueAt(SelectedRows, 7).toString());
                	cboNhaGa.setSelectedItem(modelNV.getValueAt(SelectedRows, 8).toString());
                }
        	}
        });
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
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnThem.addActionListener(this);
        panelButton.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSua.addActionListener(this);
        panelButton.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnXoa.addActionListener(this);
        panelButton.add(btnXoa);

        btnXoaTrang = new JButton("Xóa trắng");
        btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 18));
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

	    if (o.equals(btnThem)) {
	        if (validateData()) {
	            // Generate a new maNV
	            String maNV = txtMaNV.getText().trim();
	            String hoTen = txtHoTen.getText().trim();
	            String gioiTinh = cboGioiTinh.getSelectedItem().toString().trim();
	            String cccd = txtCCCD.getText().trim();
	            String sdt = txtSDT.getText().trim();
	            String email = txtEmail.getText().trim();
	            Date ngaySinh = (Date) dateNgaySinh.getDate();
	            String trinhDo = cboTrinhDo.getSelectedItem().toString().trim();
	            String tenNhaGa = cboNhaGa.getSelectedItem().toString().trim();

        	    ArrayList<NhaGa> nhaGaList = nhaGaDAO.layThongTin();

        	    NhaGa ng = timTenNhaGa(nhaGaList,tenNhaGa);
        	    
                NhanVien nv = new NhanVien(maNV, hoTen, cccd, gioiTinh, sdt, email, ngaySinh, trinhDo, ng);
	            boolean trungMa = false;
	            for (int i = 0; i < modelNV.getRowCount(); i++) {
	                if (maNV.equals(modelNV.getValueAt(i, 0))) {
	                    trungMa = true;
	                    break;
	                }
	            }

	            if (trungMa) {
	                JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	            } else {
	                try {
	                    nvDAO.addNV(nv);
	                    
	                    Object[] rowData = {
	                            nv.getMaNV(), nv.getHoTen(), nv.getCCCD(), nv.getGioiTinh(), nv.getSDT(), nv.getEmail(), nv.getNgaySinh(), nv.getTrinhDo(), ng.getTenNhaGa()};
	                    modelNV.addRow(rowData);
	                    JOptionPane.showMessageDialog(this, "Thêm thành công");
	                    xoaRong();;
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                    JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        }

	    } else if (o.equals(btnSua)) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            if (validateData()) {
	                String maNV = txtMaNV.getText();
	                String hoTen = txtHoTen.getText();
	                String gioiTinh = cboGioiTinh.getSelectedItem().toString();
	                String cccd = txtCCCD.getText();
	                String sdt = txtSDT.getText();
	                String email = txtEmail.getText();
	                Date ngaySinh = (Date) dateNgaySinh.getDate();
	                String trinhDo = cboTrinhDo.getSelectedItem().toString();
	                String tenNhaGa = cboNhaGa.getSelectedItem().toString().trim();

	                
	                
	        	    ArrayList<NhaGa> nhaGaList = nhaGaDAO.layThongTin();

	        	    NhaGa ng = timTenNhaGa(nhaGaList,tenNhaGa);
	        	    
	                NhanVien nv = new NhanVien(maNV, hoTen, cccd, gioiTinh, sdt, email, ngaySinh, trinhDo, ng);

	                int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
	                if (action == JOptionPane.YES_OPTION) {
	                    try {
	                        boolean isUpdated = nvDAO.SuaNV(nv);
	                        if (isUpdated) {
	                            docDuLieuVaoTable();
	                            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
	                            xoaRong();;
	                        } else {
	                            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                        }
	                    } catch (Exception e2) {
	                        e2.printStackTrace();
	                        JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    }
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }

	    } else if (o.equals(btnXoa)) {
	        int selectedRow = table.getSelectedRow();
	        if (selectedRow >= 0) {
	            String maNV = txtMaNV.getText();
	            int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION);
	            if (action == JOptionPane.YES_OPTION) {
	                try {
	                    boolean isDeleted = nvDAO.xoaNV(maNV);
	                    if (isDeleted) {
	                        modelNV.removeRow(selectedRow);
	                        JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                        xoaRong();;
	                    } else {
	                        JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    }
	                } catch (Exception e2) {
	                    e2.printStackTrace();
	                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }
	    } else if (o.equals(btnXoaTrang)) {
			xoaRong();
		}
	}

	private boolean validateData() {
		if (txtMaNV.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên không được để trống");
            txtMaNV.selectAll();
            txtMaNV.requestFocus();
            return false;
        }

        if (!txtMaNV.getText().matches("[a-zA-Z0-9]{1,8}")) {
            JOptionPane.showMessageDialog(null, "Mã nhân viên có tối đa 8 ký tự và không chứa ký tự đặc biệt");
            txtMaNV.selectAll();
            txtMaNV.requestFocus();
            return false;
        }
        
        if (txtHoTen.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Họ tên không được để trống");
            txtHoTen.selectAll();
            txtHoTen.requestFocus();
            return false;
        }

        
        if (txtCCCD.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "CCCD không được để trống");
            txtCCCD.selectAll();
            txtCCCD.requestFocus();
            return false;
        }

        if (!txtCCCD.getText().matches("\\d{1,12}")) {
            JOptionPane.showMessageDialog(null, "CCCD chỉ được chứa số và tối đa 12 ký tự");
            txtCCCD.selectAll();
            txtCCCD.requestFocus();
            return false;
        }
        
        if (txtSDT.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "SDT không được để trống");
            txtSDT.selectAll();
            txtSDT.requestFocus();
            return false;
        }
        
        if (!txtSDT.getText().matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, "SDT chỉ được chứa số và tối đa 10 ký tự");
            txtSDT.selectAll();
            txtSDT.requestFocus();
            return false;
        }
        
        if (cboGioiTinh.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính");
            return false;
        }
        
        if (cboTrinhDo.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trình độ");
            return false;
        }
        
        if (txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email.");
            txtEmail.requestFocus();
            return false;
        }
        
        if (cboNhaGa.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà ga");
            return false; 
        }
        
        Date ngaySinh = (Date) dateNgaySinh.getDate();
        if (ngaySinh == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh");
            return false;
        }
		return true;
        }
	
	private void xoaRong() {
		txtMaNV.setText("");
	    txtHoTen.setText("");
	    cboGioiTinh.setSelectedIndex(0);
	    txtCCCD.setText("");
	    txtSDT.setText("");
	    txtEmail.setText("");
	    dateNgaySinh.setDate(new java.util.Date());
	    cboTrinhDo.setSelectedIndex(0);
	    cboNhaGa.setSelectedItem("Tất cả");;
	}
	private NhaGa timTenNhaGa(ArrayList<NhaGa> dsNG, String tenNG) {
	    for (NhaGa ng : dsNG) {
	        if (tenNG.equalsIgnoreCase(ng.getTenNhaGa())) {
	            return ng;
	        }
	    }
	    return null;
	}
	
	private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}