package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import connectDB.*;
import entity.*;

import java.util.ArrayList;
import java.util.List;

public class TimKiemTau extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaTau;
    private DefaultTableModel modelTau;
    private JButton btnTimKiem;
    private JButton btnLamMoi;
    private TauDAO tauDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboLoaiTau;
    private JComboBox<String> cboNhaGa;
    private NhaGaDAO nhaGaDAO;
    private JTextField txtTenTau;

    public TimKiemTau() {
        ConnectDB.getInstance().connect();
        tauDAO = new TauDAO();
        nhaGaDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("TÌM KIẾM TÀU"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 4, 20, 15));
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        JLabel lblMaTau = new JLabel("Mã tàu:");
        inputPanel.add(lblMaTau);
        txtMaTau = new JTextField();
        inputPanel.add(txtMaTau);
        txtMaTau.setColumns(10);

        JLabel lblTenTau = new JLabel("Tên tàu:");
        inputPanel.add(lblTenTau);
        txtTenTau = new JTextField();
        inputPanel.add(txtTenTau);
        txtTenTau.setColumns(10);

        JLabel lblLoaiTau = new JLabel("Loại tàu:");
        inputPanel.add(lblLoaiTau);
        cboLoaiTau = new JComboBox<>(new String[]{"Tất cả", "Tàu cao tốc", "Tàu hỏa"});
        inputPanel.add(cboLoaiTau);

        JLabel lblNhaGa = new JLabel("Nhà ga:");
        inputPanel.add(lblNhaGa);
        cboNhaGa = new JComboBox<>();
        cboNhaGa.addItem("Tất cả");
        ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
        for (NhaGa ng : dsNG) {
            cboNhaGa.addItem(ng.getTenNhaGa());
        }
        inputPanel.add(cboNhaGa);

        inputPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Thông Tin Tàu", TitledBorder.LEFT, TitledBorder.TOP, new Font("Times New Roman", Font.ITALIC, 18)));

        String[] columns = {"Mã tàu", "Tên tàu", "Nhà ga", "Loại tàu"};
        modelTau = new DefaultTableModel(columns, 0);
        table = new JTable(modelTau);
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
        panelButton.setLayout(new BoxLayout(panelButton, BoxLayout.X_AXIS));

        panelButton.add(Box.createHorizontalGlue());
        btnTimKiem = new JButton("Tìm kiếm");
        panelButton.add(btnTimKiem);
        panelButton.add(Box.createHorizontalStrut(10));
        btnLamMoi = new JButton("Làm mới");
        panelButton.add(btnLamMoi);
        panelButton.add(Box.createHorizontalStrut(80));
        panelButton.add(Box.createHorizontalGlue());

        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        btnTimKiem.setFont(textFieldFont);
        btnLamMoi.setFont(textFieldFont);

        add(panelButton, BorderLayout.SOUTH);

        btnTimKiem.addActionListener(this);
        btnLamMoi.addActionListener(this);
        
        docDuLieuDBVaoTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnTimKiem)) {
            String maTau = txtMaTau.getText().trim();
            String tenTau = txtTenTau.getText().trim();
            String nhaGa = cboNhaGa.getSelectedItem().toString();
            String loaiTau = cboLoaiTau.getSelectedItem().toString();
            Tau t = new Tau(maTau, tenTau, new NhaGa(nhaGa), loaiTau);
            List<Tau> listTau = tauDAO.timKiemTau(t);
            modelTau.setRowCount(0);
            if (listTau.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy tàu nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Tau tau : listTau) {
                    modelTau.addRow(new Object[]{tau.getMaTau(), tau.getTenTau(), tau.getNhaGa().getTenNhaGa(), tau.getLoaiTau()});
                }
                JOptionPane.showMessageDialog(this, "Đã tìm thấy " + listTau.size() + " tàu!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (o.equals(btnLamMoi)) {
            lamMoi();
            docDuLieuDBVaoTable();
        }
    }

    private void lamMoi() {
        txtMaTau.setText("");
        txtTenTau.setText("");
        cboLoaiTau.setSelectedItem("Tất cả");
        cboNhaGa.setSelectedItem("Tất cả");
    }

    private void docDuLieuDBVaoTable() {
        ArrayList<Tau> listTau = tauDAO.layThongTin();
        modelTau.setRowCount(0);
        for (Tau tau : listTau) {
            modelTau.addRow(new Object[]{tau.getMaTau(), tau.getTenTau(), tau.getNhaGa().getMaNhaGa(), tau.getLoaiTau()});
        }
    }
}
