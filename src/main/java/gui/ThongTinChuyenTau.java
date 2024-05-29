package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.text.*;
import java.util.*;
import DAO.*;
import connectDB.*;
import entity.*;
import java.util.List;

public class ThongTinChuyenTau extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaChuyenTau;
    private DefaultTableModel modelCT;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private ChuyenTauDAO ctDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboGaDi;
    private JComboBox<String> cboTau;
    private JComboBox<String> cboGaDen;
    private SpinnerDateModel dateModelGioDi;
    private SpinnerDateModel dateModelGioDen;
    private JSpinner spinGioDi;
    private JSpinner spinGioDen;
    private TitledBorder inputPanelBorder;
    private TauDAO tauDAO;
    private NhaGaDAO nhaGaDAO;
	private List<String> tenGaList;
	private JDateChooser dateNgayDen;
	private JDateChooser dateNgayDi;

    public ThongTinChuyenTau() {

        ConnectDB.getInstance().connect();
        ctDAO = new ChuyenTauDAO();
        tauDAO = new TauDAO();
        nhaGaDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN CHUYẾN TÀU"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 8, 2, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        // Add input fields to the panel
        JLabel lblMaChuyenTau = new JLabel("Mã chuyến tàu:");
        lblMaChuyenTau.setFont(lblMaChuyenTau.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaChuyenTau);

        txtMaChuyenTau = new JTextField();
        inputPanel.add(txtMaChuyenTau);
        txtMaChuyenTau.setColumns(10);

        JLabel lblGaDi = new JLabel("Ga đi:");
        inputPanel.add(lblGaDi);
        cboGaDi = new JComboBox<>();
        cboGaDi.addItem("Tất cả");
        inputPanel.add(cboGaDi);

        
        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(lblNgayDi.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblNgayDi);
        dateNgayDi = new JDateChooser();
        dateNgayDi.setDateFormatString("yyyy-MM-dd");
        inputPanel.add(dateNgayDi);


        dateModelGioDi = new SpinnerDateModel();
        dateModelGioDi.setCalendarField(Calendar.MINUTE);

        JLabel lblGioDi = new JLabel("Giờ đi:");
        lblGioDi.setFont(lblGioDi.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblGioDi);
        spinGioDi = new JSpinner(dateModelGioDi);
        spinGioDi.setEditor(new JSpinner.DateEditor(spinGioDi, "HH:mm:ss"));
        inputPanel.add(spinGioDi);

        JLabel lblTau = new JLabel("Tàu:");
        inputPanel.add(lblTau);
        cboTau = new JComboBox<>();
        cboTau.addItem("Tất cả");
        inputPanel.add(cboTau);

        JLabel lblGaDen = new JLabel("Ga đến:");
        inputPanel.add(lblGaDen);
        cboGaDen = new JComboBox<>();
        cboGaDen.addItem("Tất cả");
        inputPanel.add(cboGaDen);
        
        JLabel lblNgayDen = new JLabel("Ngày đến:");
        lblNgayDen.setFont(lblNgayDen.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblNgayDen);
        dateNgayDen = new JDateChooser();
        dateNgayDen.setDateFormatString("yyyy-MM-dd");
        inputPanel.add(dateNgayDen);
        

        JLabel lblGioDen = new JLabel("Giờ đến:");
        lblGioDen.setFont(lblGioDen.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblGioDen);
        dateModelGioDen = new SpinnerDateModel();
        dateModelGioDen.setCalendarField(Calendar.MINUTE);
        spinGioDen = new JSpinner(dateModelGioDen);
        spinGioDen.setEditor(new JSpinner.DateEditor(spinGioDen, "HH:mm:ss"));
        inputPanel.add(spinGioDen);

        inputPanelBorder = BorderFactory.createTitledBorder("Thông Tin Chuyến Tàu");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));


        ArrayList<Tau> dstau = tauDAO.layThongTin();
        for (Tau tau : dstau) {
            cboTau.addItem(tau.getTenTau());
        }

        List<String> tenGaList = nhaGaDAO.layHetTenGa();
        for (String tenGa : tenGaList) {
            cboGaDi.addItem(tenGa);
            cboGaDen.addItem(tenGa);
        }
        
        // Table
        String[] columns = {
                "Mã chuyến tàu", "Tàu", "Ga đi", "Ga đến", "Ngày đi", "Giờ đi", "Ngày đến", "Giờ đến"
        };
        modelCT = new DefaultTableModel(columns, 0);
        table = new JTable(modelCT);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	    int selectedRow = table.getSelectedRow();
        	    if (selectedRow != -1) {
        	        txtMaChuyenTau.setText(modelCT.getValueAt(selectedRow, 0).toString());
        	        cboTau.setSelectedItem(modelCT.getValueAt(selectedRow, 1).toString());
        	        cboGaDi.setSelectedItem(modelCT.getValueAt(selectedRow, 2));
        	        cboGaDen.setSelectedItem(modelCT.getValueAt(selectedRow, 3));
        	        dateNgayDi.setDate(parseDate(modelCT.getValueAt(selectedRow, 4).toString()));
        	        String gioDiFormat = modelCT.getValueAt(selectedRow, 5).toString();
        	        // Tạo format để phân tích cú pháp time
        	        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        	        try {
        	        	// Phân tích chuỗi time vào java.util.Date 
        	            java.util.Date parsedDate = timeFormat.parse(gioDiFormat);
        	         // Đưa chuỗi date time đã phân tích vào java.util.Time
        	            Time timeGioDi = new Time(parsedDate.getTime());
        	            // set vào spinner
        	            spinGioDi.setValue(timeGioDi);
        	        } catch (ParseException ex) {
        	            ex.printStackTrace();
        	        }
  	        
        	        dateNgayDen.setDate(parseDate(modelCT.getValueAt(selectedRow, 6).toString()));
        	        String gioDenFormat = modelCT.getValueAt(selectedRow, 7).toString();
        	        try {
        	            java.util.Date parsedDate = timeFormat.parse(gioDenFormat);
        	            Time timeGioDen = new Time(parsedDate.getTime());
        	            spinGioDen.setValue(timeGioDen);
        	        } catch (ParseException ex) {
        	            ex.printStackTrace();
        	        }


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
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));

        // Thêm các nút vào panelButton
        panelButton.add(Box.createHorizontalGlue());
        btnThem = new JButton("Thêm");
        panelButton.add(btnThem);
        panelButton.add(Box.createHorizontalStrut(10));
        btnSua = new JButton("Sửa");
        panelButton.add(btnSua);
        panelButton.add(Box.createHorizontalStrut(10));
        btnXoa = new JButton("Xóa");
        panelButton.add(btnXoa);
        panelButton.add(Box.createHorizontalStrut(10));
        panelButton.add(Box.createHorizontalGlue());

        // Đặt font cho các nút
        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        btnThem.setFont(textFieldFont);
        btnSua.setFont(textFieldFont);
        btnXoa.setFont(textFieldFont);

        add(panelButton, BorderLayout.SOUTH);

        // Đăng ký ActionListener cho các nút
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);


        docDuLieuDBVaoTable();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
        	if(ValidData()) {
        		// Lấy thông tin từ các trường nhập liệu
                String maCT = txtMaChuyenTau.getText().trim();
                String tenTau = cboTau.getSelectedItem().toString();
                
                NhaGa ngDi = null;
                ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
                for (NhaGa ngDi1 : dsNG) {
                    if (cboGaDi.getSelectedItem().toString().equalsIgnoreCase(ngDi1.getTenNhaGa())) {
                        ngDi = new NhaGa(ngDi1.getMaNhaGa());
                    }
                }
                NhaGa ngDen = null;
                ArrayList<NhaGa> dsNG1 = nhaGaDAO.layThongTin();
                for (NhaGa ngDen1 : dsNG) {
                    if (cboGaDen.getSelectedItem().toString().equalsIgnoreCase(ngDen1.getTenNhaGa())) {
                        ngDen = new NhaGa(ngDen1.getMaNhaGa());
                    }
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    	         // For ngayDiStr
    	         Date ngayDiStr = (Date) dateNgayDi.getDate();
    	
    	         // For ngayDenStr
    	         Date ngayDenStr = (Date) dateNgayDen.getDate();
    	
    	         // For gioDiStr (extracting time only)
    	         Time gioDiStr = new Time(((Date) spinGioDi.getValue()).getTime());
    	
    	         // For gioDenStr (extracting time only)
    	         Time gioDenStr = new Time(((Date) spinGioDen.getValue()).getTime());
    	
                List<Tau> dsTau = tauDAO.layTheoTenTau(tenTau);
                Tau tau = dsTau.get(0);

                // Tạo một đối tượng ChuyenTau mới
                ChuyenTau chuyenTau = new ChuyenTau(maCT, tau, ngDi, ngDen,ngayDiStr, gioDiStr,ngayDenStr, gioDenStr);


                boolean trungMa = false;
                // Cho vòng lặp chạy hết bảng
                for (int i = 0; i < modelCT.getRowCount(); i++) {
                    // Lấy giá trị maCT của từng dòng để so
                    if (maCT.equals(modelCT.getValueAt(i, 0))) {
                        trungMa = true;
                        break;
                    }
                }

                if (trungMa) {
                    JOptionPane.showMessageDialog(this, "Mã chuyến tàu đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    ctDAO.themChuyenTau(chuyenTau);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    docDuLieuDBVaoTable();
                    xoaRong();
                }
        	}
            
     } else if (o.equals(btnSua)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
            	if(ValidData()) {
            		String maCT = txtMaChuyenTau.getText();
                    String tenTau = String.valueOf(cboTau.getSelectedItem());
                    NhaGa ngDi = null;
                    ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
                    for (NhaGa ngDi1 : dsNG) {
                        if (cboGaDi.getSelectedItem().toString().equalsIgnoreCase(ngDi1.getTenNhaGa())) {
                            ngDi = new NhaGa(ngDi1.getMaNhaGa());
                        }
                    }
                    NhaGa ngDen = null;
//                    ArrayList<NhaGa> dsNG1 = nhaGaDAO.layThongTin();
                    for (NhaGa ngDen1 : dsNG) {
                        if (cboGaDen.getSelectedItem().toString().equalsIgnoreCase(ngDen1.getTenNhaGa())) {
                            ngDen = new NhaGa(ngDen1.getMaNhaGa());
                        }
                    }


                    // For ngayDiStr
                    Date ngayDiStr = (Date) dateNgayDi.getDate();

                    // For ngayDenStr
                    Date ngayDenStr = (Date) dateNgayDen.getDate();

                    // For gioDiStr (extracting time only)
                    Time gioDiStr = new Time(((Date) spinGioDi.getValue()).getTime());

                    // For gioDenStr (extracting time only)
                    Time gioDenStr = new Time(((Date) spinGioDen.getValue()).getTime());

                    List<Tau> dsTau = tauDAO.layTheoTenTau(tenTau);
                    Tau tau = dsTau.get(0);
                    ChuyenTau chuyenTau = new ChuyenTau(maCT, tau, ngDi, ngDen, ngayDiStr, gioDiStr, ngayDenStr, gioDenStr);

                    int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
                    if (action == JOptionPane.YES_OPTION) {
                        if (ctDAO.suaChuyenTau(chuyenTau)) {
                        	docDuLieuDBVaoTable();
                            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                            
                            xoaRong();
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi khi sửa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
            	}
                
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để cập nhật!");
            }
        } else if (o.equals(btnXoa)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String maCT = txtMaChuyenTau.getText();
                try {
                    // Xóa đối tượng ChuyenTau khỏi cơ sở dữ liệu
                    ctDAO.xoaChuyenTau(maCT);

                    // Xóa hàng khỏi table
                    modelCT.removeRow(selectedRow);

                    JOptionPane.showMessageDialog(this, "Xóa thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hàng để xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean ValidData() {
    	if (txtMaChuyenTau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã chuyến tàu không được để trống");
            txtMaChuyenTau.selectAll();
            txtMaChuyenTau.requestFocus();
            return false;
        }

        if (!txtMaChuyenTau.getText().matches("[a-zA-Z0-9]{1,10}")) {
            JOptionPane.showMessageDialog(null, "Mã chuyến tàu có tối đa 10 ký tự và không chứa ký tự đặc biệt");
            txtMaChuyenTau.selectAll();
            txtMaChuyenTau.requestFocus();
            return false;
        }
        
        if (cboTau.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu");
            return false;
        }
        
        if (cboGaDi.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đi");
            return false;
        }
        
        if (cboGaDen.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ga đến");
            return false;
        }
    	
        if (cboGaDi.getSelectedItem().toString().equalsIgnoreCase(cboGaDen.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(this, "Ga đi và ga đến không được trùng nhau! Vui lòng chọn lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Date ngayDi = dateNgayDi.getDate();
        Date ngayDen = dateNgayDen.getDate();
        if (ngayDi == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đi");
            return false;
        }
        if (ngayDen == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đến");
            return false;
        }
        if (ngayDen.before(ngayDi)) {
            JOptionPane.showMessageDialog(null, "Ngày đến phải sau ngày đi");
            return false;
        }
        
//        Date gioDi = (Date) spinGioDi.getValue();
//        Date gioDen = (Date) spinGioDen.getValue();
//        if (gioDi == null) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ đi");
//            return false;
//        }
//        if (gioDen == null) {
//            JOptionPane.showMessageDialog(null, "Vui lòng chọn giờ đến");
//            return false;
//        }

        
		return true;
	}


	public void docDuLieuDBVaoTable() {
		modelCT.setRowCount(0);
        List<ChuyenTau> listCT = ctDAO.layThongTin();
        for (ChuyenTau ct : listCT) {
            modelCT.addRow(new Object[]{ct.getMaChuyenTau(), ct.getTau().getMaTau(), ct.getGaDi().getMaNhaGa(), ct.getGaDen().getMaNhaGa(), ct.getNgayDi(), ct.getGioDi(), ct.getNgayDen(), ct.getGioDen()});
        }
    }

    public void xoaRong() {
        txtMaChuyenTau.setText("");
        cboTau.setSelectedIndex(0);
        cboGaDi.setSelectedIndex(0);
        cboGaDen.setSelectedIndex(0);
        spinGioDi.setValue(new java.util.Date());
        spinGioDen.setValue(new java.util.Date());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
       
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


    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
