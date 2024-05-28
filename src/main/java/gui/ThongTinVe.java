package gui;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DAO.ChuyenTauDAO;
import DAO.VeDAO;
import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ve;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThongTinVe extends javax.swing.JPanel implements ActionListener, MouseListener {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtMaNV;
	private DefaultTableModel modelVe;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnXoaTrang;
	private VeDAO veDAO;
	private JPanel pNorth;
	private JLabel lblTieuDe;
	private TitledBorder inputPanelBorder;
	private JTextField txtMaVe; 
	private JTextField txtTenVe;
	private JComboBox<String> cboLoaiVe;
	private JTextField txtMaKH; 
	private JButton btnReset; 
	private JSpinner spinNgayVe;
	private JSpinner spinNgayDi;
	private JComboBox<String> cboMaChuyenTau;
	private ChuyenTauDAO ctDAO;
	
     //Creates new form NewJPanel
    public ThongTinVe() {
    	
        ConnectDB.getInstance().connect();
        veDAO = new VeDAO();
        ctDAO = new ChuyenTauDAO();
        
    	setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN VÉ"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.BLUE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 50, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        // Add input fields to the panel
        JLabel lblMaVe = new JLabel("Mã vé");
        lblMaVe.setFont(lblMaVe.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaVe);

        txtMaVe = new JTextField();
        txtMaVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtMaVe);
        txtMaVe.setColumns(10);

        JLabel lblTenVe = new JLabel("Tên vé");
        lblTenVe.setFont(lblTenVe.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblTenVe);

        txtTenVe = new JTextField();
        txtTenVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtTenVe);
        txtTenVe.setColumns(10);

        JLabel lblLoaiVe = new JLabel("Loại vé");
        lblLoaiVe.setFont(lblLoaiVe.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblLoaiVe);

        cboLoaiVe = new JComboBox<>(new String[]{"Loại 1", "Loại 2"});
        cboLoaiVe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(cboLoaiVe);

        JLabel lblNgayDi = new JLabel("Ngày đi");
        lblNgayDi.setFont(lblNgayDi.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblNgayDi);
        
        spinNgayDi = new JSpinner();
        inputPanel.add(spinNgayDi);

        JLabel lblNgayVe = new JLabel("Ngày về");
        lblNgayVe.setFont(lblNgayVe.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblNgayVe);
        
        spinNgayVe = new JSpinner();
        inputPanel.add(spinNgayVe);

        JLabel lblMaKH = new JLabel("Mã khách hàng");
        lblMaKH.setFont(lblMaKH.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaKH);

        txtMaKH = new JTextField();
        txtMaKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtMaKH);

        JLabel lblMaNV = new JLabel("Mã nhân viên");
        lblMaNV.setFont(lblMaNV.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaNV);

        txtMaNV = new JTextField();
        txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 14));
        inputPanel.add(txtMaNV);

        JLabel lblMaChuyenTau = new JLabel("Mã chuyến tàu");
        lblMaChuyenTau.setFont(lblMaChuyenTau.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaChuyenTau);

        cboMaChuyenTau = new JComboBox<String>();
        cboMaChuyenTau.setEditable(false);
        ArrayList<ChuyenTau> listCT = ctDAO.layThongTin();
        for(ChuyenTau ct : listCT) {
        	cboMaChuyenTau.addItem(ct.getMaChuyenTau());
        }
        inputPanel.add(cboMaChuyenTau);

        inputPanelBorder = BorderFactory.createTitledBorder("Thông Tin Vé");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));

        // Table
        String[] columns = {
        	    "Mã vé", "Tên vé", "Loại vé", "Ngày đi", "Ngày về", "Mã khách hàng", "Mã nhân viên", "Mã chuyến tàu"
        	};
        	modelVe = new DefaultTableModel(columns, 0) {
        	    private static final long serialVersionUID = 1L;
        	    //Ko chỉnh table i mean code...
				@Override
        	    public boolean isCellEditable(int row, int column) {
        	        return false;
        	    }
        	};
        	table = new JTable(modelVe);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int SelectedRows = table.getSelectedRow();
        		if(SelectedRows !=-1) {
        			txtMaVe.setText(modelVe.getValueAt(SelectedRows, 0).toString());
        			txtTenVe.setText(modelVe.getValueAt(SelectedRows, 1).toString());
        			cboLoaiVe.setSelectedItem(modelVe.getValueAt(SelectedRows, 2));
        			txtMaKH.setText(modelVe.getValueAt(SelectedRows, 3).toString());
        			txtMaNV.setText(modelVe.getValueAt(SelectedRows, 4).toString());
        			cboMaChuyenTau.setSelectedItem(modelVe.getValueAt(SelectedRows, 5));
        		}
        	}
        });
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 550));
        table.setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.setRowHeight(25);

        // Table header
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 30));
        header.setBackground(Color.LIGHT_GRAY);
        header.setFont(new Font("Times New Roman", Font.BOLD, 21));

        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        contentPanel.add(scrollPane);

        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(173, 216, 230));
        panelButton.setBorder(new EmptyBorder(10, 20, 10, 20));
        contentPanel.add(panelButton);

        btnThem = new JButton("Thêm");
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnThem.addActionListener((ActionListener) this);
        panelButton.add(btnThem);

        btnSua = new JButton("Sửa");
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSua.addActionListener((ActionListener) this);
        panelButton.add(btnSua);

        btnXoa = new JButton("Xóa");
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnXoa.addActionListener((ActionListener) this);
        panelButton.add(btnXoa);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReset.addActionListener((ActionListener) this);
        panelButton.add(btnReset);
        docDuLieuVaoTable();
    }
    
    private void docDuLieuVaoTable() {
       // modelVe.setRowCount(0);
        List<Ve> listVe = veDAO.layThongTin();
        for (Ve v : listVe) {
            Object[] rowData = {
                v.getMaVe(), v.getTenVe(), v.getLoaiVe(), v.getNgayDi(), v.getNgayVe(), v.getMaKH().getMaKH(), v.getnhanVien().getMaNV(), v.getMaCT().getMaChuyenTau()
            };
            modelVe.addRow(rowData);
            }
        }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			Object o = e.getSource();
			if(o.equals(btnThem)) {
				String maVe = txtMaVe.getText();
				String tenVe = txtTenVe.getText();
				String loaiVe = cboLoaiVe.getSelectedItem().toString();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String ngayDistr = sdf.format((java.util.Date) spinNgayDi.getValue());
	            String ngayVestr = sdf.format((java.util.Date) spinNgayVe.getValue());
				String maKH = txtMaKH.getText(); 
				String maNV =  txtMaNV.getText();
				String maCT = String.valueOf(cboMaChuyenTau.getSelectedItem());
				
				KhachHang kh = new KhachHang(maKH);
				NhanVien nv = new NhanVien(maNV);
				ChuyenTau ct = new ChuyenTau(maCT);
				
		        // Tạo đối tượng NhanVien từ thông tin nhập liệu
		        Ve v = new Ve(maVe, tenVe, loaiVe, ngayDistr, ngayVestr, kh, nv, ct);
		        modelVe.addRow(new Object[]{maVe,tenVe,loaiVe,ngayDistr,ngayVestr,maKH,maNV,maCT});
		        // Thêm nhân viên vào cơ sở dữ liệu
		        try {
		        	veDAO.themVe(v);
				} catch (Exception ex) {
					ex.printStackTrace();
	                // Xử lý nếu có lỗi khi thêm vào cơ sở dữ liệu
					JOptionPane.showMessageDialog(this, "Lỗi khi thêm vào cơ sở dữ liệu!");
				}
		     // Load lại bảng sau khi thêm
		        docDuLieuVaoTable();
		        xoaRong();

			}else if(o.equals(btnSua)) {
				int SelectedRows = modelVe.getRowCount();
				for(int i = 0; i < SelectedRows; i++) {
					txtMaVe.setText(modelVe.getValueAt(i, 0).toString());
	    			txtTenVe.setText(modelVe.getValueAt(i, 1).toString());
	    			cboLoaiVe.setSelectedItem(modelVe.getValueAt(i, 2));
	    			txtMaKH.setText(modelVe.getValueAt(i, 3).toString());
	    			txtMaNV.setText(modelVe.getValueAt(i, 4).toString());
	    			cboMaChuyenTau.setSelectedItem(modelVe.getValueAt(i, 5));;
				}
			}

	}
	public void xoaRong() {
        txtMaVe.setText("");
        txtTenVe.setText("");
        cboLoaiVe.setSelectedIndex(0);
        spinNgayDi.setValue(new java.util.Date());
        spinNgayVe.setValue(new java.util.Date());
        txtMaKH.setText("");
        txtMaNV.setText("");
        cboMaChuyenTau.setSelectedIndex(0);;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
