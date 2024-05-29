package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.NhaGa;
import entity.Tau;
import entity.Toa;

public class TauDAO {
    
    public ArrayList<Tau> layThongTin() {
        ArrayList<Tau> dsT = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT t.MaTau, t.TenTau, ng.TenNhaGa, t.LoaiTau " +
                    "FROM Tau t " +
                    "INNER JOIN NhaGa ng ON t.MaNhaGa = ng.MaNhaGa";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String nhaGa = rs.getString(3);
                String loaiTau = rs.getString(4);
                
                NhaGa ng = new NhaGa(nhaGa);
                Tau t = new Tau(maTau, tenTau, ng, loaiTau);
                dsT.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin tàu từ cơ sở dữ liệu!");
        }
        return dsT;
    }
    
    // Thêm tàu
    public boolean addTau(Tau tau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String SQL = "INSERT INTO Tau (MaTau, TenTau, MaNhaGa, LoaiTau) VALUES (?, ?, ?, ?)";
        int n = 0;
        try {
            st = conn.prepareStatement(SQL);
            st.setString(1, tau.getMaTau().trim());
            st.setString(2, tau.getTenTau().trim());
            st.setString(3, tau.getNhaGa().getMaNhaGa());
            st.setString(4, tau.getLoaiTau().trim());
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm tàu vào cơ sở dữ liệu!");
        }
        return n > 0;
    }

    //Xóa tàu
    public boolean xoaTau(String maTau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;
        try {            
            String SQL = "DELETE FROM Tau WHERE MaTau = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, maTau);
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa tàu từ cơ sở dữ liệu!");
        }
        return n > 0;
    }

    // Sửa tàu
    public boolean suaTau(Tau tau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;
        try {
            String SQL = "UPDATE Tau SET TenTau = ?, MaNhaGa = ?, LoaiTau = ? WHERE MaTau = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, tau.getTenTau().trim());
            st.setString(2, tau.getNhaGa().getMaNhaGa().trim());
            st.setString(3, tau.getLoaiTau().trim());
            st.setString(4, tau.getMaTau().trim());
            
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa tàu trong cơ sở dữ liệu!");
        } 
        return n > 0;
    }
    
    // Tìm kiếm tàu
    public List<Tau> timKiemTau(Tau tau) {
        List<Tau> dsTau = new ArrayList<Tau>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = ConnectDB.getConnection();
            String SQL = "SELECT t.MaTau, t.TenTau, ng.TenNhaGa, t.LoaiTau " +
                        "FROM Tau t " +
                        "INNER JOIN NhaGa ng ON t.MaNhaGa = ng.MaNhaGa " +
                        "WHERE t.MaTau = ? OR t.TenTau = ? OR ng.TenNhaGa = ? OR t.LoaiTau = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, tau.getMaTau());
            st.setString(2, tau.getTenTau());
            st.setString(3, tau.getNhaGa().getMaNhaGa());
            st.setString(4, tau.getLoaiTau());
            
            rs = st.executeQuery();

            while (rs.next()) {
                String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String tenNhaGa = rs.getString(3);
                String loaiTau = rs.getString(4);
                NhaGa ng = new NhaGaDAO().layTheoTen(tenNhaGa);

                Tau foundTau = new Tau(maTau, tenTau, ng, loaiTau);
                dsTau.add(foundTau);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm tàu trong cơ sở dữ liệu!");
            }
        return dsTau;
    }
    
    public Tau layTheoMa(String id) {
    	ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        try {
            String sql = "SELECT * FROM Tau WHERE MaTau=?";
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String nhaGa = rs.getString(3);
                String loaiTau = rs.getString(4);

                return new Tau(maTau, tenTau, new NhaGa(nhaGa), loaiTau);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Tau> layTheoTenTau(String ten) {
    	ArrayList<Tau> dsTau = new ArrayList<Tau>();
        try {
        	ConnectDB.getInstance();
            Connection conn = ConnectDB.getConnection();
            PreparedStatement st = null;
            String sql = "SELECT * FROM Tau WHERE TenTau=?";
            st = conn.prepareStatement(sql);
            st.setString(1, ten);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
            	String maTau = rs.getString(1);
                String tenTau = rs.getString(2);
                String nhaGa = rs.getString(3);
                String loaiTau = rs.getString(4);
                Tau t = new Tau(maTau, tenTau, new NhaGa(nhaGa), loaiTau);
                dsTau.add(t);
                return dsTau;
                		
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
