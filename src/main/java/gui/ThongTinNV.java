package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import DAO.NhanVienDAO;
import connectDB.ConnectDB;
import entity.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
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
    private TitledBorder inputPanelBorder;
    private JSpinner spinNgaySinh;

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
        
        SpinnerDateModel model = new SpinnerDateModel();
        spinNgaySinh = new JSpinner(model);
        spinNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinNgaySinh, "yyyy-MM-dd");
        spinNgaySinh.setEditor(editor);
        inputPanel.add(spinNgaySinh);

        JLabel lblTrinhDo = new JLabel("Trình độ");
        lblTrinhDo.setFont(lblTrinhDo.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblTrinhDo);
        cboTrinhDo = new JComboBox<>(new String[]{"Đại học", "Cử nhân", "Cao đẳng"});
        cboTrinhDo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(cboTrinhDo);

        JLabel lblMaNhaGa = new JLabel("Mã nhà ga");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblMaNhaGa);
        txtMaNhaGa = new JTextField();
        txtMaNhaGa.setFont(new Font("Tahoma", Font.PLAIN, 14));
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

                	try {
                        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(modelNV.getValueAt(SelectedRows, 6).toString());
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                        spinNgaySinh.setValue(sqlDate);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                	cboTrinhDo.setSelectedItem(modelNV.getValueAt(SelectedRows, 7).toString());
                	txtMaNhaGa.setText(modelNV.getValueAt(SelectedRows, 8).toString());
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
	    		 // Get the information from the input fields
	    	    String maNV = txtMaNV.getText().trim();
	    	    String hoTen = txtHoTen.getText().trim();
	    	    String gioiTinh = cboGioiTinh.getSelectedItem().toString().trim();
	    	    String cccd = txtCCCD.getText().trim();
	    	    String sdt = txtSDT.getText().trim();
	    	    String email = txtEmail.getText().trim();
	    	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    	    String ngaySinhstr = sdf.format((java.util.Date) spinNgaySinh.getValue());
	    	    String trinhDo = cboTrinhDo.getSelectedItem().toString().trim();
	    	    String maNhaGa = txtMaNhaGa.getText().trim();
	
	    	    // Check for empty inputs
	    	    if (maNV.isEmpty() || hoTen.isEmpty() || cccd.isEmpty() || sdt.isEmpty() || email.isEmpty() || trinhDo.isEmpty() || maNhaGa.isEmpty()) {
	    	        JOptionPane.showMessageDialog(this, "Please fill in all fields!");
	    	        return;
	    	    }
	
	    	    NhaGa ng = new NhaGa(maNhaGa);
	
	    	    // Create a NhanVien object from the input information
	    	    NhanVien nv = new NhanVien(maNV, hoTen, cccd, gioiTinh, sdt, email, ngaySinhstr, trinhDo, ng);
	
	    	    // Add the NhanVien to the database
	    	    try {
	    	        boolean isAdded = nvDAO.addNV(nv);
	    	        if (isAdded) {
	    	            modelNV.addRow(new Object[]{maNV, hoTen, gioiTinh, cccd, sdt, email, ngaySinhstr, trinhDo, ng});
	    	            JOptionPane.showMessageDialog(this, "Dữ liệu của nhân viên được thêm thành công");
	    	        } 
	    	    } catch (Exception e2) {
	    	        e2.printStackTrace();
	    	        JOptionPane.showMessageDialog(this, "Quá trình sửa dữ liệu đã xảy ra lỗi");
	    	    } finally {
	    	        // Reload the table after adding
	    	        docDuLieuVaoTable();
	    	    }

        } else if (o.equals(btnSua)) {
            // Code xử lý sự kiện sửa nhân viên
        	int selectedRow = table.getSelectedRow();
        	if (selectedRow != -1) {
        	    String maNV = txtMaNV.getText();
        	    String hoTen = txtHoTen.getText();
        	    String gioiTinh = cboGioiTinh.getSelectedItem().toString();
        	    String cccd = txtCCCD.getText();
        	    String sdt = txtSDT.getText();
        	    String email = txtEmail.getText();
        	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	    String ngaySinhstr = sdf.format((java.util.Date) spinNgaySinh.getValue());
        	    String trinhDo = cboTrinhDo.getSelectedItem().toString();
        	    String maNhaGa = txtMaNhaGa.getText();

        	    NhaGa ng = new NhaGa(maNhaGa);
        	    NhanVien nv = new NhanVien(maNV, hoTen, cccd, gioiTinh, sdt, email, ngaySinhstr, trinhDo, ng);

        	    try {
        	        // Update data in the database
        	        boolean isUpdated = nvDAO.SuaNV(nv);

        	        if (isUpdated) {
        	            // Update data in the table
        	            modelNV.setValueAt(maNV, selectedRow, 0);
        	            modelNV.setValueAt(hoTen, selectedRow, 1);
        	            modelNV.setValueAt(gioiTinh, selectedRow, 2);
        	            modelNV.setValueAt(cccd, selectedRow, 3);
        	            modelNV.setValueAt(sdt, selectedRow, 4);
        	            modelNV.setValueAt(email, selectedRow, 5);
        	            modelNV.setValueAt(ngaySinhstr, selectedRow, 6);
        	            modelNV.setValueAt(trinhDo, selectedRow, 7);
        	            modelNV.setValueAt(maNhaGa, selectedRow, 8);

        	            JOptionPane.showMessageDialog(this, "Dữ liệu của nhân viên đã được sửa thành công");
        	        } else {
        	            JOptionPane.showMessageDialog(this, "");
        	        }
        	    } catch (Exception e2) {
        	        e2.printStackTrace();
        	        JOptionPane.showMessageDialog(this, "Quá trình cập nhật thông tin xảy ra lỗi");
        	    }
        	} else {
        	    JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để sửa");
        	}

            // Cập nhật thông tin của nhân viên được chọn trong bảng
        } else if (o.equals(btnXoa)) {
        	// Code xử lý sự kiện xóa nhân viên
        	int selectedRow = table.getSelectedRow();
        	if (selectedRow != -1) {
        	    String maNV = txtMaNV.getText();
        	    try {
        	        // Delete the NhanVien object from the database
        	        boolean isDeleted = nvDAO.xoaNV(maNV);

        	        if (isDeleted) {
        	            // Remove the row from the table
        	            modelNV.removeRow(selectedRow);
        	            JOptionPane.showMessageDialog(this, "NhanVien data deleted successfully!");
        	        }
        	    } catch (Exception e2) {
        	        e2.printStackTrace();
        	        JOptionPane.showMessageDialog(this, "Error deleting NhanVien data!");
        	    } finally {
        	    	docDuLieuVaoTable();
        	       
        	    }
        	}
        }
    }
}