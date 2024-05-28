package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import connectDB.ConnectDB;
import entity.*;
import java.util.List;

public class ThongTinTau extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private JTextField txtMaTau;
    private DefaultTableModel modelTau;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private TauDAO tauDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JComboBox<String> cboLoaiTau;
    private JComboBox<String> cboMaNhaGa;
    private TitledBorder inputPanelBorder;
    private JLabel lblNhap;
    private JTextField txtNhap;
    private NhaGaDAO nhagaDAO;
    private JSpinner SLToa;
    private JSpinner SLGhe;

    public ThongTinTau() {
        ConnectDB.getInstance().connect();
        tauDAO = new TauDAO();
        nhagaDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN TÀU"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 15)); // Updated GridLayout
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);

        JLabel lblMaTau = new JLabel("Mã tàu:");
        lblMaTau.setFont(lblMaTau.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaTau);

        txtMaTau = new JTextField();
        inputPanel.add(txtMaTau);
        txtMaTau.setColumns(10);

        JLabel lblLoaiTau = new JLabel("Loại tàu:");
        lblLoaiTau.setFont(lblLoaiTau.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblLoaiTau);
        cboLoaiTau = new JComboBox<>(new String[]{"Tàu cao tốc", "Tàu hỏa"});
        inputPanel.add(cboLoaiTau);

        JLabel lblMaNhaGa = new JLabel("Mã nhà ga:");
        lblMaNhaGa.setFont(lblMaNhaGa.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblMaNhaGa);
        cboMaNhaGa = new JComboBox<>();
        cboMaNhaGa.setEditable(false);
        List<NhaGa> listNG = nhagaDAO.layThongTin();
        for (NhaGa ng : listNG) {
            cboMaNhaGa.addItem(ng.getMaNhaGa());
        }
        inputPanel.add(cboMaNhaGa);

        JLabel lblSoLuongToa = new JLabel("Số lượng toa:");
        lblSoLuongToa.setFont(lblSoLuongToa.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblSoLuongToa);
        
        SLToa = new JSpinner();
        inputPanel.add(SLToa);

        JLabel lblSoLuongGhe = new JLabel("Số lượng ghế:");
        lblSoLuongGhe.setFont(lblSoLuongGhe.getFont().deriveFont(Font.BOLD, 14));
        inputPanel.add(lblSoLuongGhe);

        inputPanelBorder = BorderFactory.createTitledBorder("Thông Tin Tàu");
        inputPanelBorder.setTitleFont(new Font("Times New Roman", Font.ITALIC, 18));
        inputPanelBorder.setTitleJustification(TitledBorder.LEFT);
        inputPanelBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        inputPanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10), inputPanelBorder));
        inputPanel.setPreferredSize(new Dimension(300, 250));
        
        SLGhe = new JSpinner();
        inputPanel.add(SLGhe);

        String[] columns = {"Mã tàu", "Mã nhà ga", "Loại tàu", "Số lượng toa", "Số lượng ghế"};
        modelTau = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(modelTau);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    txtMaTau.setText(modelTau.getValueAt(selectedRow, 0).toString());
                    Object maNhaGaValue = modelTau.getValueAt(selectedRow, 1);
                    if (maNhaGaValue != null) {
                        cboMaNhaGa.setSelectedItem(maNhaGaValue.toString());
                    }
                    cboLoaiTau.setSelectedItem(modelTau.getValueAt(selectedRow, 2).toString());
                    SLToa.setValue((int)modelTau.getValueAt(selectedRow, 3));
                    SLGhe.setValue((int)modelTau.getValueAt(selectedRow, 4));
                }
            }
        });
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
        table.addMouseListener(this);

        docDuLieuDBVaoTable();
    }

    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();

        if (o.equals(btnThem)) {
            String maTau = txtMaTau.getText().trim();
            String maNhaGa = cboMaNhaGa.getSelectedItem().toString().trim();
            String loaiTau = cboLoaiTau.getSelectedItem().toString().trim();
            int soLuongToa = (int) SLToa.getValue(); 
            int soLuongGhe = (int) SLGhe.getValue(); 

            if (maTau.isEmpty() || loaiTau.isEmpty() || maNhaGa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NhaGa ng = new NhaGa(maNhaGa);
            Tau tau = new Tau(maTau, ng, loaiTau, soLuongToa, soLuongGhe);

            boolean trungMa = false;
            for (int i = 0; i < modelTau.getRowCount(); i++) {
                if (maTau.equals(modelTau.getValueAt(i, 0))) {
                    trungMa = true;
                    break;
                }
            }

            if (trungMa) {
                JOptionPane.showMessageDialog(this, "Mã tàu đã tồn tại. Vui lòng chọn mã tàu khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            } else {
                	tauDAO.addTau(tau);
                    modelTau.addRow(new Object[]{tau.getMaTau(), tau.getNhaGa().getMaNhaGa(), tau.getLoaiTau(), tau.getSoLuongToa(), tau.getSoLuongGhe()});
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    xoaRong();
                
            }    


        } else if (o.equals(btnSua)) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn sửa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String maTau = txtMaTau.getText().trim();
            String maNhaGa = cboMaNhaGa.getSelectedItem().toString().trim();
            String loaiTau = cboLoaiTau.getSelectedItem().toString().trim();
            int soLuongToa = Integer.parseInt((String) SLToa.getValue());
            int soLuongGhe = Integer.parseInt((String) SLGhe.getValue());

            if (maTau.isEmpty() || loaiTau.isEmpty() || maNhaGa.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            NhaGa ng = new NhaGa(maNhaGa);
            Tau tau = new Tau(maTau, ng, loaiTau, soLuongToa, soLuongGhe);

            try {
                tauDAO.suaTau(tau);
                modelTau.setValueAt(maTau, selectedRow, 0);
                modelTau.setValueAt(maNhaGa, selectedRow, 1);
                modelTau.setValueAt(loaiTau, selectedRow, 2);
                modelTau.setValueAt(soLuongToa, selectedRow, 3);
                modelTau.setValueAt(soLuongGhe, selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Sửa thành công");
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi khi sửa dữ liệu!");
            }
            xoaRong();
        } else if (o.equals(btnXoa)) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String maTau = modelTau.getValueAt(selectedRow, 0).toString();

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
    }

    private void docDuLieuDBVaoTable() {
    	List<Tau> listTau = tauDAO.layThongTin();
    	for (Tau tau : listTau) {
    	    cboMaNhaGa.addItem(tau.getNhaGa().getMaNhaGa());
    	    modelTau.addRow(new Object[]{tau.getMaTau(), tau.getNhaGa().getMaNhaGa(), tau.getLoaiTau(), tau.getSoLuongToa(), tau.getSoLuongGhe()});
    
        }
    }

    public void xoaRong() {
        txtMaTau.setText("");
        cboLoaiTau.setSelectedIndex(0);
        cboMaNhaGa.setSelectedIndex(0);
        SLToa.setValue(0);
        SLGhe.setValue(0);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            txtMaTau.setText(modelTau.getValueAt(selectedRow, 0).toString());
            Object maNhaGaValue = modelTau.getValueAt(selectedRow, 1);
            if (maNhaGaValue != null) {
                cboMaNhaGa.setSelectedItem(maNhaGaValue.toString());
            }
            cboLoaiTau.setSelectedItem(modelTau.getValueAt(selectedRow, 2).toString());
            SLToa.setValue((int)modelTau.getValueAt(selectedRow, 3));
            SLGhe.setValue((int)modelTau.getValueAt(selectedRow, 4));
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
