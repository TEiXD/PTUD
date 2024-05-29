package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

import DAO.ChuyenTauDAO;
import DAO.NhaGaDAO;
import DAO.TauDAO;
import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.NhaGa;
import entity.Tau;
import javax.swing.JTextField;

public class TimKiemVe extends javax.swing.JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private ChuyenTauDAO ctDAO;
    private TauDAO tauDAO;
    private NhaGaDAO nhaGaDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboGaDi;
    private JSpinner spinnerNgayDi;
    private JComboBox<String> cboTau;
    private JComboBox<String> cboGaDen;
    private JSpinner spinnerNgayVe;
    private DefaultTableModel modelCT;
    private JTable table;
    private JButton btnTim;
    private JButton btnLamMoi;
    private JCheckBox chkKhuHoi;
    private JPanel panel;
    private JPanel panel_1;
    private JLabel lbl_HoTen;
    private JLabel lbl_CCCD;
    private JComboBox<String> cboGaDi_1;
    private JLabel lbl_GioiTinh;
    private JTextField txt_HoTen;
    private JComboBox cbo_GioiTinh;
    private JLabel lbl_SDT;
    private JTextField txt_SDT;
    private JLabel lbl_Email;
    private JTextField txt_Email;
    private JTextField txt_CCCD;

    public TimKiemVe() {
        ConnectDB.getInstance().connect();
        ctDAO = new ChuyenTauDAO();
        tauDAO = new TauDAO();
        nhaGaDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("Đặt vé"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 45));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 30, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        // Add input fields to the panel
        JLabel lblGaDi = new JLabel("Ga đi:");
        lblGaDi.setFont(lblGaDi.getFont().deriveFont(Font.BOLD, 16));
        inputPanel.add(lblGaDi);
        cboGaDi = new JComboBox<>();
        cboGaDi.addItem("Tất cả");
        inputPanel.add(cboGaDi);

        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(lblNgayDi.getFont().deriveFont(Font.BOLD, 16));
        inputPanel.add(lblNgayDi);
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spinnerNgayDi = new JSpinner(dateModel);
        spinnerNgayDi.setEditor(new JSpinner.DateEditor(spinnerNgayDi, "dd/MM/yyyy"));
        inputPanel.add(spinnerNgayDi);

        JLabel lblGaDen = new JLabel("Ga đến:");
        lblGaDen.setFont(lblGaDen.getFont().deriveFont(Font.BOLD, 16));
        inputPanel.add(lblGaDen);
        cboGaDen = new JComboBox<>();
        cboGaDen.addItem("Tất cả");
        inputPanel.add(cboGaDen);

        JLabel lblNgayVe = new JLabel("Ngày về:");
        lblNgayVe.setFont(lblNgayVe.getFont().deriveFont(Font.BOLD, 16));
        inputPanel.add(lblNgayVe);
        SpinnerDateModel dateModelVe = new SpinnerDateModel();
        spinnerNgayVe = new JSpinner(dateModelVe);
        spinnerNgayVe.setEditor(new JSpinner.DateEditor(spinnerNgayVe, "dd/MM/yyyy"));
        inputPanel.add(spinnerNgayVe);

        // Hide the "Ngày về" spinner initially
        lblNgayVe.setVisible(false);
        spinnerNgayVe.setVisible(false);

//        List<String> tenGaList = nhaGaDAO.layHetTenGa();
//        for (String tenGa : tenGaList) {
//            cboGaDi.addItem(tenGa);
//            cboGaDen.addItem(tenGa);
//        }
      List<NhaGa> tenGaList = nhaGaDAO.layThongTin();
      for (NhaGa tenGa : tenGaList) {
          cboGaDi.addItem(tenGa.getTenNhaGa());
      }
      List<NhaGa> dstenGa = nhaGaDAO.layThongTin();
      for (NhaGa tenGa : dstenGa) {
          cboGaDen.addItem(tenGa.getTenNhaGa());
      }
        // Panel to hold the checkbox
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.X_AXIS));
        checkboxPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Add the "Khứ hồi" checkbox
        chkKhuHoi = new JCheckBox("Khứ hồi");
        chkKhuHoi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        checkboxPanel.add(chkKhuHoi);

        contentPanel.add(checkboxPanel);

        // Table
        String[] columns = { "Mã chuyến tàu", "Tàu", "Ga đi", "Ga đến", "Giờ đi", "Giờ đến dự kiến" };
        modelCT = new DefaultTableModel(columns, 0);
        table = new JTable(modelCT);
        table.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
        
        panel = new JPanel();
        contentPanel.add(panel);
        panel.setLayout(new GridLayout(1, 0, 0, 0));
        
     // Tạo panel
     // Tạo panel
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setLayout(new GridLayout(8, 2, 0, 0)); // Sửa số hàng và số cột

        // Thêm label và text field cho Họ tên
        JLabel lbl_HoTen = new JLabel("Họ tên");
        lbl_HoTen.setFont(lbl_HoTen.getFont().deriveFont(16f));
        panel_1.add(lbl_HoTen);

        JTextField txt_HoTen = new JTextField();
        txt_HoTen.setColumns(10);
        panel_1.add(txt_HoTen);

        // Thêm label và text field cho CCCD
        JLabel lbl_CCCD = new JLabel("CCCD:");
        lbl_CCCD.setFont(lbl_CCCD.getFont().deriveFont(16f));
        panel_1.add(lbl_CCCD);

        JTextField txt_CCCD = new JTextField();
        txt_CCCD.setColumns(10);
        panel_1.add(txt_CCCD);

        // Thêm label và combo box cho Giới tính
        JLabel lbl_GioiTinh = new JLabel("Giới tính:");
        panel_1.add(lbl_GioiTinh);

        JComboBox<String> cbo_GioiTinh = new JComboBox<String>();
        cbo_GioiTinh.addItem("Nam");
        cbo_GioiTinh.addItem("Nữ");
        panel_1.add(cbo_GioiTinh);

        // Thêm label và text field cho SDT
        JLabel lbl_SDT = new JLabel("SDT:");
        panel_1.add(lbl_SDT);

        JTextField txt_SDT = new JTextField();
        txt_SDT.setColumns(10);
        panel_1.add(txt_SDT);

        // Thêm label và text field cho Email
        JLabel lbl_Email = new JLabel("Email:");
        panel_1.add(lbl_Email);

        JTextField txt_Email = new JTextField();
        txt_Email.setColumns(10);
        panel_1.add(txt_Email);


        JPanel panelButton = new JPanel();
        panelButton.setBackground(new Color(173, 216, 230));
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));

        // Add buttons to panelButton
        panelButton.add(Box.createHorizontalGlue());
        btnTim = new JButton("Tìm chuyến");
        panelButton.add(btnTim);
        panelButton.add(Box.createHorizontalStrut(10));
        btnLamMoi = new JButton("Làm mới");
        panelButton.add(btnLamMoi);
        panelButton.add(Box.createHorizontalGlue());

        // Set font for buttons
        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        btnTim.setFont(textFieldFont);
        btnLamMoi.setFont(textFieldFont);

        add(panelButton, BorderLayout.SOUTH);

        // Register ActionListener for buttons
        btnTim.addActionListener(this);
        btnLamMoi.addActionListener(this);

        // Register ActionListener for the "Khứ hồi" checkbox
        chkKhuHoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean selected = chkKhuHoi.isSelected();
                lblNgayVe.setVisible(selected);
                spinnerNgayVe.setVisible(selected);
            }
        });

        docDuLieuDBVaoTable();
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTim)) {
            String gaDi = cboGaDi.getSelectedItem().toString();
            String gaDen = cboGaDen.getSelectedItem().toString();
            Date ngayDi = (Date) spinnerNgayDi.getValue();

            List<ChuyenTau> listCT = ctDAO.timChuyenTauTheoGa(gaDi, gaDen, new java.sql.Date(ngayDi.getTime()));

            modelCT.setRowCount(0);
            if (listCT.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy chuyến tàu nào!", "Thông báo",JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (ChuyenTau ct : listCT) {
                    modelCT.addRow(new Object[] { ct.getMaChuyenTau(), ct.getTau().getMaTau(), ct.getGaDi().getMaNhaGa(),
                                                  ct.getGaDen().getMaNhaGa(), ct.getGioDi(), ct.getGioDen() });
                }
            }
        } else if (o.equals(btnLamMoi)) {
            xoaRong();
            docDuLieuDBVaoTable();
        }
    }

    public void docDuLieuDBVaoTable() {
      modelCT.setRowCount(0);
      List<ChuyenTau> listCT = ctDAO.layThongTin();
        for (ChuyenTau ct : listCT) {
            modelCT.addRow(new Object[] { ct.getMaChuyenTau(), ct.getTau().getMaTau(), ct.getGaDi().getMaNhaGa(),
                                          ct.getGaDen().getMaNhaGa(), ct.getGioDi(), ct.getGioDen() });
        }
    }

    public void xoaRong() {
        cboGaDi.setSelectedIndex(0);
        cboGaDen.setSelectedIndex(0);
        spinnerNgayDi.setValue(new Date());
        spinnerNgayVe.setValue(new Date());
        chkKhuHoi.setSelected(false);
        spinnerNgayVe.setVisible(false);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}

