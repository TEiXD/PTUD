package ui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import DAO.*;
import connectDB.*;
import entity.*;
import java.util.List;


public class ThongTinChuyenTau extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaChuyenTau;
    private DefaultTableModel modelCT;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnXoaTrang;
    private ChuyenTauDAO ctDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboGaDi;
    private JComboBox<String> cboLoaiTau;
    private JComboBox<String> cboGaDen;
    private SpinnerDateModel dateModelGioDi;
    private SpinnerDateModel dateModelGioDen;
    private JSpinner spinGioDi;
    private JSpinner spinGioDen;
    private TitledBorder inputPanelBorder;

    public ThongTinChuyenTau() {

        try {
            ConnectDB.getInstance().connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ctDAO = new ChuyenTauDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN CHUYẾN TÀU"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 6, 2, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        // Add input fields to the panel
        JLabel lblMaChuyenTau = new JLabel("Mã chuyến tàu");
        lblMaChuyenTau.setFont(lblMaChuyenTau.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblMaChuyenTau);

        txtMaChuyenTau = new JTextField();
        inputPanel.add(txtMaChuyenTau);
        txtMaChuyenTau.setColumns(10);

        JLabel lblGaDi = new JLabel("Ga đi");
        lblGaDi.setFont(lblGaDi.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGaDi);
        cboGaDi = new JComboBox<>(new String[]{"Ga A", "Ga B", "Ga C", "Ga D", "Ga E", "Ga F", "Ga G", "Ga I", "Ga K"});
        inputPanel.add(cboGaDi);

        dateModelGioDi = new SpinnerDateModel();
        dateModelGioDi.setCalendarField(Calendar.MINUTE);

        JLabel lblGioDi = new JLabel("Giờ đi");
        lblGioDi.setFont(lblGioDi.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGioDi);
        spinGioDi = new JSpinner(dateModelGioDi);
        spinGioDi.setEditor(new JSpinner.DateEditor(spinGioDi, "yyyy-MM-dd HH:mm:ss"));
        inputPanel.add(spinGioDi);

        JLabel lblLoaiTau = new JLabel("Loại tàu");
        lblLoaiTau.setFont(lblLoaiTau.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblLoaiTau);
        cboLoaiTau = new JComboBox<>(new String[]{"Tàu hỏa", "Tàu cao tốc"});
        inputPanel.add(cboLoaiTau);

        JLabel lblGaDen = new JLabel("Ga đến");
        lblGaDen.setFont(lblGaDen.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGaDen);
        cboGaDen = new JComboBox<>(new String[]{"Ga A", "Ga B", "Ga C", "Ga D", "Ga E", "Ga F", "Ga G", "Ga I", "Ga K"});
        inputPanel.add(cboGaDen);

        JLabel lblGioDen = new JLabel("Giờ đến");
        lblGioDen.setFont(lblGioDen.getFont().deriveFont(Font.BOLD, 14)); // Set font size and style
        inputPanel.add(lblGioDen);
        dateModelGioDen = new SpinnerDateModel();
        dateModelGioDen.setCalendarField(Calendar.MINUTE);
        spinGioDen = new JSpinner(dateModelGioDen);
        spinGioDen.setEditor(new JSpinner.DateEditor(spinGioDen, "yyyy-MM-dd HH:mm:ss"));
        inputPanel.add(spinGioDen);

        inputPanelBorder = BorderFactory.createTitledBorder("Thông Tin Chuyến Tàu");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Thay đổi màu của LineBorder thành đen
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));

        // Table
        String[] columns = {
                "Mã chuyến tàu", "Loại tàu", "Ga đi", "Ga đến", "Giờ đi", "Giờ đến"
        };
        modelCT = new DefaultTableModel(columns, 0);
        table = new JTable(modelCT);
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
        btnXoaTrang = new JButton("Xóa trắng");
        panelButton.add(btnXoaTrang);
        panelButton.add(Box.createHorizontalGlue());

        // Đặt font cho các nút
        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        btnThem.setFont(textFieldFont);
        btnSua.setFont(textFieldFont);
        btnXoa.setFont(textFieldFont);
        btnXoaTrang.setFont(textFieldFont);

        // Thêm panelButton vào phần SOUTH của JPanel
        add(panelButton, BorderLayout.SOUTH);

        // Đăng ký ActionListener cho các nút
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaTrang.addActionListener(this);

        // Load data from database into the table
        docDuLieuDBVaoTable();
    }

    // Action handling for buttons
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            // Lấy thông tin từ các trường nhập liệu
            String maCT = txtMaChuyenTau.getText().trim();
            String loaiTau = String.valueOf(cboLoaiTau.getSelectedItem());
            String gaDi = String.valueOf(cboGaDi.getSelectedItem());
            String gaDen = String.valueOf(cboGaDen.getSelectedItem());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String gioDiStr = sdf.format((java.util.Date) spinGioDi.getValue());
            String gioDenStr = sdf.format((java.util.Date) spinGioDen.getValue());

            Tau tau = new Tau(loaiTau);

            // Tạo một đối tượng ChuyenTau mới
            ChuyenTau chuyenTau = new ChuyenTau(maCT, tau, gaDi, gaDen, gioDiStr, gioDenStr);

            // Thêm đối tượng ChuyenTau vào bảng để hiển thị trên giao diện
            modelCT.addRow(new Object[]{maCT, loaiTau, gaDi, gaDen, gioDiStr, gioDenStr});

            // Clear input fields
            txtMaChuyenTau.setText("");
            cboLoaiTau.setSelectedIndex(0);
            cboGaDi.setSelectedIndex(0);
            cboGaDen.setSelectedIndex(0);
            spinGioDi.setValue(new java.util.Date());
            spinGioDen.setValue(new java.util.Date());
            try {
                // Thêm đối tượng ChuyenTau vào cơ sở dữ liệu
                ctDAO.addCT(chuyenTau);
            } catch (Exception ex) {
                ex.printStackTrace();
                // Xử lý nếu có lỗi khi thêm vào cơ sở dữ liệu
                JOptionPane.showMessageDialog(this, "Lỗi khi thêm vào cơ sở dữ liệu!");
            }
        } else if (o.equals(btnSua)) {
            int r = modelCT.getRowCount();
            for (int i = 0; i < r; i++) {
                String maCT = (String) modelCT.getValueAt(i, 0);
                String loaiTau = (String) modelCT.getValueAt(i, 1);
                String gaDi = (String) modelCT.getValueAt(i, 2);
                String gaDen = (String) modelCT.getValueAt(i, 3);
                String gioDi = (String) modelCT.getValueAt(i, 4);
                String gioDen = (String) modelCT.getValueAt(i, 5);

                Tau tau = new Tau(loaiTau);

                ChuyenTau chuyenTau = new ChuyenTau(maCT, tau, gaDi, gaDen, gioDi, gioDen);

                try {
                    ctDAO.updateCT(chuyenTau);
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Lỗi khi sửa dữ liệu!");
                }
            }
            JOptionPane.showMessageDialog(this, "Dữ liệu đã được sửa thành công");

        } else if (o.equals(btnXoa)) {
            int r = table.getSelectedRow();
            if (r != -1) {
                String maCT = (String) modelCT.getValueAt(r, 0);
                modelCT.removeRow(r);
                ctDAO.removeCT(maCT);
            }
        } else if (o.equals(btnXoaTrang)) {
            // Xóa trắng các trường
            txtMaChuyenTau.setText("");
            cboGaDi.setSelectedIndex(0);
            cboLoaiTau.setSelectedIndex(0);
            cboGaDen.setSelectedIndex(0);
            spinGioDi.setValue(new java.util.Date());
            spinGioDen.setValue(new java.util.Date());
        }
    }

    // Method to fetch data from the database and populate the table
    public void docDuLieuDBVaoTable() {
        List<ChuyenTau> listCT = ctDAO.layThongTin();
        for (ChuyenTau ct : listCT) {
            modelCT.addRow(new Object[]{ct.getMaChuyenTau(), ct.getLTau().getLoaiTau(), ct.getGaDi(), ct.getGaDen(), ct.getGioDi(), ct.getGioDen()});
        }
    }

}
