package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.Tau;

public class ChuyenTauDAO {
    
    public void layComboBox(JComboBox<String> cboMaTau) {
        try {
            Connection conn = ConnectDB.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MaTau FROM ChuyenTau");

            // Remove all existing items
            cboMaTau.removeAllItems();

            // Add new items
            while (rs.next()) {
                cboMaTau.addItem(rs.getString("MaTau"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public ArrayList<ChuyenTau> layThongTin(){
        ArrayList<ChuyenTau> dsCT = new ArrayList<ChuyenTau>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT ct.MaChuyenTau, t.MaTau, ct.GaDi, ct.GaDen, ct.GioDi, ct.GioDen " +
                         "FROM ChuyenTau ct " +
                         "INNER JOIN Tau t ON ct.MaTau = t.MaTau";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String maChuyenTau = rs.getString(1);
                String MaTau = rs.getString(2);
                String gaDi = rs.getString(3);
                String gaDen = rs.getString(4);
                String gioDi = rs.getString(5);
                String gioDen = rs.getString(6);
                Tau t = new Tau(MaTau);
                
                ChuyenTau ct = new ChuyenTau(maChuyenTau, t, gaDi, gaDen, gioDi, gioDen);
                dsCT.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsCT;
    }
    
    public boolean addCT(ChuyenTau chuyenTau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String SQL = "INSERT  INTO dbo.ChuyenTau (MaChuyenTau, MaTau, GaDi, GaDen, GioDi, GioDen) VALUES (?,?,?,?,?,?)";
        int n = 0;
        try {
            st = conn.prepareStatement(SQL);
            st.setString(1, chuyenTau.getMaChuyenTau().trim());
            st.setString(2, chuyenTau.getMaTau().getMaTau().trim());
            st.setString(3, chuyenTau.getGaDi().trim());
            st.setString(4, chuyenTau.getGaDen().trim());
            st.setString(5, chuyenTau.getGioDi().trim());
            st.setString(6, chuyenTau.getGioDen().trim());
            
            n = st.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return false;
    }
    
    public boolean removeCT(String maCT) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n=0;
        try {
            String SQL = "DELETE FROM ChuyenTau WHERE MaChuyenTau=?";
            st = conn.prepareStatement(SQL);
            st.setString(1, maCT);
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public boolean updateCT(ChuyenTau chuyenTau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n=0;
        try {
            String SQL = "UPDATE ChuyenTau SET MaTau=?, GaDi=?, GaDen=?, GioDi=?, GioDen=? WHERE MaChuyenTau=?";
            st = conn.prepareStatement(SQL);

            st.setString(1, chuyenTau.getMaTau().getMaTau().trim());
            st.setString(2, chuyenTau.getGaDi().trim());
            st.setString(3, chuyenTau.getGaDen().trim());
            st.setString(4, chuyenTau.getGioDi().trim());
            st.setString(5, chuyenTau.getGioDen().trim());
            st.setString(6, chuyenTau.getMaChuyenTau().trim());
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n>0;
    }
}
