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

public class TimKiemTau extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelTau;
    private JButton btnTimKiem;
    private JButton btnLamMoi;
    private TauDAO tauDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboLoaiTau; 	
    private JComboBox<String> cboMaNhaGa;
    private JTextField txtMaTau;
    private JTextField txtSoLuongToa;
    private JTextField txtSoLuongGhe;					 
    private TitledBorder inputPanelBorder;

    public TimKiemTau() {
        ConnectDB.getInstance().connect();
        tauDAO = new TauDAO();
        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("TÌM KIẾM TÀU"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblMaTau = new JLabel("Mã tàu");
        lblMaTau.setFont(lblMaTau.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(lblMaTau, gbc);
        txtMaTau = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 0;
        inputPanel.add(txtMaTau, gbc);
        txtMaTau.setColumns(20);

        JLabel lblSoLuongToa = new JLabel("Số lượng toa");
        lblSoLuongToa.setFont(lblSoLuongToa.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.gridy = 0;
        inputPanel.add(lblSoLuongToa, gbc);
        txtSoLuongToa = new JTextField(20);
        gbc.gridx = 4;
        gbc.gridy = 0;
        inputPanel.add(txtSoLuongToa, gbc);
        
        JLabel lblSoLuongGhe = new JLabel("Số lượng ghế");
        lblSoLuongGhe.setFont(lblSoLuongGhe.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 6;
        gbc.gridy = 0;
        inputPanel.add(lblSoLuongGhe, gbc);
        txtSoLuongGhe = new JTextField(20);
        gbc.gridx = 7;
        gbc.gridy = 0;
        inputPanel.add(txtSoLuongGhe, gbc);
        
        JLabel lblLoaiTau = new JLabel("Loại tàu");
        lblLoaiTau.setFont(lblLoaiTau.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(lblLoaiTau, gbc);
        cboLoaiTau = new JComboBox<>(new String[]{"None","Tàu cao tốc", "Tàu hỏa"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        inputPanel.add(cboLoaiTau, gbc);

        JLabel lblMaNhaGa = new JLabel("Mã nhà ga");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14));
        gbc.gridx = 3;
        gbc.gridy = 1;
        inputPanel.add(lblMaNhaGa, gbc);
        cboMaNhaGa = new JComboBox<>(new String[] {"None"});
        gbc.gridx = 4;
        gbc.gridy = 1;
        inputPanel.add(cboMaNhaGa, gbc);
        
        inputPanelBorder = BorderFactory.createTitledBorder("Tìm kiếm tàu");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));
        
        
        String[] columns = {
            "Mã tàu","Mã nhà ga", "Loại tàu",  "Số lượng toa", "Số lượng ghế"
        };
        modelTau = new DefaultTableModel(columns, 0);
        table = new JTable(modelTau);
        table.setBorder(new EmptyBorder(100, 10, 100, 10));
        table.setPreferredSize(new Dimension(50, 550));
        table.setFont(new Font("Times New Roman", Font.BOLD, 18));
        table.setRowHeight(25);
        
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
        
        List<String> danhSachMaNhaGa = tauDAO.layDanhSachMaNhaGa();
        for (String maNhaGa : danhSachMaNhaGa) {
            cboMaNhaGa.addItem(maNhaGa);
        }
    }
    
    private void docDuLieuVaoTable() {
        modelTau.setRowCount(0);
        List<Tau> listTau = tauDAO.layThongTin();
        for (Tau tau : listTau) {
            Object[] rowData = {
                tau.getMaTau(),  tau.getNhaGa().getMaNhaGa(), tau.getLoaiTau(), tau.getSoLuongToa(), tau.getSoLuongGhe()};
            modelTau.addRow(rowData);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTimKiem) {
            timKiemTau();
        } else if (e.getSource() == btnLamMoi) {
            lamMoi();
        }
    }

    private void timKiemTau() {
        String maTau = txtMaTau.getText().trim();
        String loaiTau = (String) cboLoaiTau.getSelectedItem();
        String maNhaGa = (String) cboMaNhaGa.getSelectedItem();
        NhaGa ng = new NhaGa(maNhaGa);
        int soLuongToa = 0;
        int soLuongGhe = 0;
        if (!txtSoLuongToa.getText().trim().isEmpty()) {
            soLuongToa = Integer.parseInt(txtSoLuongToa.getText().trim());
        }
        if (!txtSoLuongGhe.getText().trim().isEmpty()) {
            soLuongGhe = Integer.parseInt(txtSoLuongGhe.getText().trim());
        }

        Tau tau = new Tau(maTau, ng, loaiTau, soLuongToa, soLuongGhe);
        List<Tau> dsTau = tauDAO.timKiemTau(tau);

        modelTau.setRowCount(0);

        for (Tau t : dsTau) {
            modelTau.addRow(new Object[]{t.getMaTau(), t.getLoaiTau(), t.getNhaGa().getMaNhaGa(),
                t.getSoLuongToa(), t.getSoLuongGhe()});
        }	
    }

    private void lamMoi() {
        txtMaTau.setText("");
        cboLoaiTau.setSelectedIndex(0);
        cboMaNhaGa.setSelectedIndex(0);
        txtSoLuongToa.setText("");
        txtSoLuongGhe.setText("");
        modelTau.setRowCount(0);
        docDuLieuVaoTable();
    }
}
