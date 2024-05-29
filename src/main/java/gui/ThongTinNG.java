package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import DAO.NhaGaDAO;
import connectDB.ConnectDB;
import entity.NhaGa;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class ThongTinNG extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable table;
    private DefaultTableModel modelNG;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnXoaTrang;
    private NhaGaDAO ngDAO;
    private JPanel pNorth;
    private JLabel lblTieuDe;
    private JTextField txt_MaNG;
    private JLabel lbl_TenNG;
    private JTextField txt_TenNG;
    private JLabel lbl_DiaChi;
    private JComboBox<String> cbo_DiaDiem;

    public ThongTinNG() throws SQLException {

        ConnectDB.getInstance().connect();
        ngDAO = new NhaGaDAO();

        setLayout(new BorderLayout());

        contentPanel.setBorder(new EmptyBorder(30, 20, 0, 20));
        add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        add(pNorth = new JPanel(), BorderLayout.NORTH);
        pNorth.add(lblTieuDe = new JLabel("THÔNG TIN NHÀ GA"));
        lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 40));
        lblTieuDe.setForeground(Color.blue);

        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        contentPanel.add(inputPanel);
        inputPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel lbl_MaNG = new JLabel("Mã nhà ga");
        inputPanel.add(lbl_MaNG);
        
        txt_MaNG = new JTextField();
        inputPanel.add(txt_MaNG);
        txt_MaNG.setColumns(10);
        
        lbl_TenNG = new JLabel("Tên nhà ga");
        inputPanel.add(lbl_TenNG);
        
        txt_TenNG = new JTextField();
        inputPanel.add(txt_TenNG);
        txt_TenNG.setColumns(10);
        
        lbl_DiaChi = new JLabel("Địa chỉ");
        inputPanel.add(lbl_DiaChi);
        
        cbo_DiaDiem = new JComboBox<String>();
        ArrayList<NhaGa> listNhaGa = ngDAO.layThongTin();
        for (NhaGa ng : listNhaGa) {
            cbo_DiaDiem.addItem(ng.getDiaChi());
        }
        inputPanel.add(cbo_DiaDiem);
        // Table
        String[] columns = {
                "Mã nhà ga", "Tên nhà ga", "Địa chỉ"
        };
        modelNG = new DefaultTableModel(columns, 0);
        table = new JTable(modelNG);
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int SelectedRows = table.getSelectedRow(); 
                if (SelectedRows != -1) {
                	String maNhaGa = (String) modelNG.getValueAt(SelectedRows, 0);
                    String tenNhaGa = (String) modelNG.getValueAt(SelectedRows, 1);
                    String diaChi = (String) modelNG.getValueAt(SelectedRows, 2);

                    // Set the values to the input fields
                    txt_MaNG.setText(maNhaGa);
                    txt_TenNG.setText(tenNhaGa);
                    cbo_DiaDiem.setSelectedItem(diaChi);
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
    
    private void xoaRong() {
		txt_MaNG.setText("");
		txt_TenNG.setText("");
	}

    private void docDuLieuVaoTable() {
        modelNG.setRowCount(0);
        List<NhaGa> listNG = ngDAO.layThongTin();
        for (NhaGa ng : listNG) {
            modelNG.addRow(new Object[]{	
            		ng.getMaNhaGa(),ng.getTenNhaGa(),ng.getDiaChi()
            });      
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Object o = e.getSource();
	    	if (o.equals(btnThem)) {
	    		String maNG = txt_MaNG.getText().trim();
	    		String tenNG = txt_TenNG.getText().trim();
	    		String diadiem = (String) cbo_DiaDiem.getSelectedItem();
	    		 if (maNG.isEmpty() || tenNG.isEmpty() || diadiem.isEmpty()) {;
		    	        JOptionPane.showMessageDialog(this, "Please fill in all fields!");
		    	        return;
		    	    }
	    		 try {
	    			 NhaGa ng = new NhaGa(maNG, tenNG, diadiem);
		    	        boolean isAdded = ngDAO.addNhaGa(ng);
		    	        if (isAdded) {
		    	            modelNG.addRow(new Object[]{maNG,tenNG,diadiem});
		    	            JOptionPane.showMessageDialog(this, "Dữ liệu của nhà ga được thêm thành công");
		    	        } 
		    	    } catch (Exception e2) {
		    	        e2.printStackTrace();
		    	        JOptionPane.showMessageDialog(this, "Quá trình sửa dữ liệu đã xảy ra lỗi");
		    	    } finally {
		    	        // Reload the table after adding
		    	        docDuLieuVaoTable();
		    	    }

        } else if (o.equals(btnSua)) {
        	 int selectedRow = table.getSelectedRow();
        	    if (selectedRow != -1) {
        	        String maNhaGa = txt_MaNG.getText();
        	        String tenNhaGa = txt_TenNG.getText();
        	        String diaChi = (String) cbo_DiaDiem.getSelectedItem();
        	        NhaGa ng = new NhaGa(maNhaGa, tenNhaGa, diaChi);
        	        try {
        	            // Update data in the database
        	            boolean isUpdated = ngDAO.suaNhaGa(ng);
        	            if (isUpdated) {
        	                // Update data in the table
        	                modelNG.setValueAt(maNhaGa, selectedRow, 0);
        	                modelNG.setValueAt(tenNhaGa, selectedRow, 1);
        	                modelNG.setValueAt(diaChi, selectedRow, 2);
        	            JOptionPane.showMessageDialog(this, "Dữ liệu của nhà ga đã được sửa thành công");
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

        } else if (o.equals(btnXoa)) {
        	 int selectedRow = table.getSelectedRow();
        	    if (selectedRow != -1) {
        	        String maNhaGa = txt_MaNG.getText();
        	        try {
        	            // Delete the NhaGa object from the database
        	            boolean isDeleted = ngDAO.xoaNhaGa(maNhaGa);

        	            if (isDeleted) {
        	                // Remove the row from the table
        	                modelNG.removeRow(selectedRow);
        	                JOptionPane.showMessageDialog(this, "Dữ liệu của nhà ga đã được xóa thành công!");
        	            }
        	        } catch (Exception e2) {
        	            e2.printStackTrace();
        	            JOptionPane.showMessageDialog(this, "Lỗi xảy ra khi xóa dữ liệu của nhà ga!");
        	        } finally {
        	            docDuLieuVaoTable();
        	        }
        	    }
        } else if (o.equals(btnXoaTrang)) {
			
		}
    }
}
    
