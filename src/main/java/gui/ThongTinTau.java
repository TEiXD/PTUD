package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import DAO.*;
import connectDB.ConnectDB;
import entity.*;

public class ThongTinTau extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaTau;
    private JTextField txtTenTau;
    private DefaultTableModel modelTau;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private TauDAO tauDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboLoaiTau;
    private JComboBox<String> cboNhaGa;
    private JButton btnTim;
    private NhaGaDAO nhaGaDAO;

    public ThongTinTau() {
        ConnectDB.getInstance().connect();
        tauDAO = new TauDAO();
        nhaGaDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN TÀU"));
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
        table.addMouseListener(this);
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
        btnThem = new JButton("Thêm");
        panelButton.add(btnThem);
        panelButton.add(Box.createHorizontalStrut(10));
        btnSua = new JButton("Sửa");
        panelButton.add(btnSua);
        panelButton.add(Box.createHorizontalStrut(10));
        btnXoa = new JButton("Xóa");
        panelButton.add(btnXoa);
        panelButton.add(Box.createHorizontalStrut(80));
        panelButton.add(Box.createHorizontalGlue());

        Font textFieldFont = new Font("Times New Roman", Font.PLAIN, 18);
        btnThem.setFont(textFieldFont);
        btnSua.setFont(textFieldFont);
        btnXoa.setFont(textFieldFont);

        add(panelButton, BorderLayout.SOUTH);

        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);

        docDuLieuDBVaoTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnThem)) {
            if (ValidData()) {
                String maTau = txtMaTau.getText().trim();
                String ten = txtTenTau.getText().trim();
                NhaGa ng = null;
                ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
                for (NhaGa ng1 : dsNG) {
                    if (cboNhaGa.getSelectedItem().toString().equalsIgnoreCase(ng1.getTenNhaGa())) {
                        ng = new NhaGa(ng1.getMaNhaGa());
                    }
                }
                String loaiTau = cboLoaiTau.getSelectedItem().toString().trim();

                Tau tau = new Tau(maTau, ten, ng, loaiTau);

                boolean trungMa = false;
                for (int i = 0; i < modelTau.getRowCount(); i++) {
                    if (maTau.equals(modelTau.getValueAt(i, 0))) {
                        trungMa = true;
                        break;
                    }
                }
                if (trungMa) {
                    JOptionPane.showMessageDialog(this, "Mã tàu đã tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                } else {
                    tauDAO.addTau(tau);
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    docDuLieuDBVaoTable();
                    xoaRong();
                }
            }
        } else if (o.equals(btnSua)) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                if (ValidData()) {
                    String maTau = txtMaTau.getText().trim();
                    String ten = txtTenTau.getText().trim();
                    NhaGa ng = null;
                    ArrayList<NhaGa> dsNG = nhaGaDAO.layThongTin();
                    for (NhaGa ng1 : dsNG) {
                        if (cboNhaGa.getSelectedItem().toString().equalsIgnoreCase(ng1.getTenNhaGa())) {
                            ng = new NhaGa(ng1.getMaNhaGa());
                        }
                    }
                    String loaiTau = cboLoaiTau.getSelectedItem().toString().trim();

                    Tau tau = new Tau(maTau, ten, ng, loaiTau);
                    int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION);
                    if (action == JOptionPane.YES_OPTION) {
                        if (tauDAO.suaTau(tau)) {
                            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                            docDuLieuDBVaoTable();
                            xoaRong();
                        } else {
                            JOptionPane.showMessageDialog(this, "Lỗi khi sửa dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tàu cần sửa", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }

        } else if (o.equals(btnXoa)) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                String maTau = modelTau.getValueAt(selectedRow, 0).toString();
                int action = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
                if (action == JOptionPane.YES_OPTION) {
                    try {
                        tauDAO.xoaTau(maTau);
                        modelTau.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Xóa thành công");
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu!");
                    }
                    xoaRong();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn tàu cần xóa", "Lỗi", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private boolean ValidData() {
        if (txtMaTau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã tàu không được để trống");
            txtMaTau.selectAll();
            txtMaTau.requestFocus();
            return false;
        }

        if (!txtMaTau.getText().matches("[a-zA-Z0-9]{1,10}")) {
            JOptionPane.showMessageDialog(null, "Mã tàu có tối đa 10 ký tự và không chứa ký tự đặc biệt");
            txtMaTau.selectAll();
            txtMaTau.requestFocus();
            return false;
        }

        if (txtTenTau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên tàu không được để trống");
            txtTenTau.selectAll();
            txtTenTau.requestFocus();
            return false;
        }

        if (!txtTenTau.getText().matches("^[a-zA-Z ]+$")) {
            JOptionPane.showMessageDialog(null, "Tên tàu không chứa số và ký tự đặc biệt");
            txtTenTau.selectAll();
            txtTenTau.requestFocus();
            return false;
        }

        if (cboNhaGa.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà ga");
            return false;
        }

        if (cboLoaiTau.getSelectedItem().toString().equalsIgnoreCase("Tất cả")) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại tàu");
            return false;
        }
        return true;
    }

    public void docDuLieuDBVaoTable() {
        ArrayList<Tau> listTau = tauDAO.layThongTin();
        ArrayList<NhaGa> listNG = nhaGaDAO.layThongTin();
        modelTau.setRowCount(0);
        for (Tau tau : listTau) {
            modelTau.addRow(new Object[]{tau.getMaTau(), tau.getTenTau(), tau.getNhaGa().getMaNhaGa(), tau.getLoaiTau()});
        }
    }

    public void xoaRong() {
        txtMaTau.setText("");
        txtTenTau.setText("");
        cboLoaiTau.setSelectedItem("Tất cả");
        cboNhaGa.setSelectedItem("Tất cả");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            txtMaTau.setText(modelTau.getValueAt(selectedRow, 0).toString());
            txtTenTau.setText(modelTau.getValueAt(selectedRow, 1).toString());
            Object nhaGa = modelTau.getValueAt(selectedRow, 2);
            if (nhaGa != null) {
                cboNhaGa.setSelectedItem(nhaGa.toString());
            }
            cboLoaiTau.setSelectedItem(modelTau.getValueAt(selectedRow, 3).toString());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
