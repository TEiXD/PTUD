/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.VeDAO;
import connectDB.ConnectDB;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import java.awt.ScrollPane;

/**
 *
 * @author Tei
 */
public class ThongTinVe extends javax.swing.JPanel {
	private Panel panel;
	private JTable table;
    /**
     * Creates new form NewJPanel
     */
    public ThongTinVe() {
    	setLayout(new BorderLayout(0, 0));
    	
    	Panel Headerpanel = new Panel();
    	add(Headerpanel, BorderLayout.NORTH);
    	
    	JLabel lblNewLabel = new JLabel("THÔNG TIN VÉ");
    	Headerpanel.add(lblNewLabel);
    	lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 36));
    	
    	Panel contentpanel = new Panel();
    	add(contentpanel, BorderLayout.CENTER);
    	contentpanel.setLayout(new BoxLayout(contentpanel, BoxLayout.Y_AXIS));
    	
    	Panel panel_1 = new Panel();
    	contentpanel.add(panel_1);
    	panel_1.setLayout(new GridLayout(1, 0, 0, 0));
    	
    	Panel panel_2 = new Panel();
    	contentpanel.add(panel_2);
    	
    	table = new JTable();
    	contentpanel.add(table);
    	
    
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        
    }// </editor-fold>//GEN-END:initComponents
}