package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.NhaGa;
import entity.Tau;

public class TauDAO {
    
    public ArrayList<Tau> layThongTin() {
    	ArrayList<Tau> dsT = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getInstance().connect();
            String SQL = "SELECT t.MaTau, ng.MaNhaGa, t.LoaiTau, t.SoLuongToa, t.SoLuongGhe " +
                         "FROM Tau t " +
                         "INNER JOIN NhaGa ng ON t.MaNhaGa = ng.MaNhaGa";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maTau = rs.getString(1);
                String maNhaGa = rs.getString(2);
                String loaiTau = rs.getString(3);
                int slToa = rs.getInt(4);
                int slGhe = rs.getInt(5);
                
                NhaGa ng = new NhaGa(maNhaGa);
                Tau t = new Tau(maTau, ng, loaiTau, slToa, slGhe);
                dsT.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsT;
    }
    
    // Lấy danh sách mã nhà ga
    public List<String> layDanhSachMaNhaGa() {
        List<String> danhSachMaNhaGa = new ArrayList<>();
        String sql = "SELECT MaNhaGa FROM NhaGa";

        try (Connection conn = ConnectDB.getInstance().getConnection();
             PreparedStatement st = conn.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                danhSachMaNhaGa.add(rs.getString("MaNhaGa"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return danhSachMaNhaGa;
    }
    
    // Thêm tàu
    public boolean addTau(Tau tau) {
    	ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String SQL = "INSERT INTO Tau (MaTau, MaNhaGa, LoaiTau, SoLuongToa, SoLuongGhe) VALUES (?, ?, ?, ?, ?)";
        int n = 0;
        try {
        	st = conn.prepareStatement(SQL);
        	st.setString(1, tau.getMaTau().trim());
        	st.setString(2, tau.getNhaGa().getMaNhaGa().trim());
        	st.setString(3, tau.getLoaiTau().trim());
        	st.setInt(4, tau.getSoLuongToa());
        	st.setInt(5, tau.getSoLuongGhe());
            
        	n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return n>0;
    }

    //Xóa tàu
    public boolean xoaTau(String maTau) {
        Connection conn = null;
        PreparedStatement st = null;
        int n = 0;
        try {
            conn = ConnectDB.getInstance().connect();
            
            String checkVeSQL = "SELECT COUNT(*) FROM Ve WHERE MaChuyenTau IN (SELECT MaChuyenTau FROM ChuyenTau WHERE MaTau = ?)";
            PreparedStatement checkVeStmt = conn.prepareStatement(checkVeSQL);
            checkVeStmt.setString(1, maTau);
            ResultSet rsVe = checkVeStmt.executeQuery();
            rsVe.next();
            int veCount = rsVe.getInt(1);
            
            if (veCount > 0) {
                int option = JOptionPane.showConfirmDialog(null, "Bạn có thật sự muốn xóa?", "Xóa", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    String deleteVeSQL = "DELETE FROM Ve WHERE MaChuyenTau IN (SELECT MaChuyenTau FROM ChuyenTau WHERE MaTau = ?)";
                    PreparedStatement deleteVeStmt = conn.prepareStatement(deleteVeSQL);
                    deleteVeStmt.setString(1, maTau);
                    deleteVeStmt.executeUpdate();
                } else {
                    return false; 
                }
            }
            
            String deleteChuyenTauSQL = "DELETE FROM ChuyenTau WHERE MaTau = ?";
            PreparedStatement deleteChuyenTauStmt = conn.prepareStatement(deleteChuyenTauSQL);
            deleteChuyenTauStmt.setString(1, maTau);
            deleteChuyenTauStmt.executeUpdate();
            
            String deleteTauSQL = "DELETE FROM Tau WHERE MaTau = ?";
            st = conn.prepareStatement(deleteTauSQL);
            st.setString(1, maTau);
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }



    // Sửa tàu
    public boolean suaTau(Tau tau) {
        Connection conn = null;
        PreparedStatement st = null;
        int n = 0;
        try {
            conn = ConnectDB.getInstance().connect();
            String SQL = "UPDATE Tau SET MaNhaGa = ?, LoaiTau = ?, SoLuongToa = ?, SoLuongGhe = ? WHERE MaTau = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, tau.getNhaGa().getMaNhaGa().trim());
            st.setString(2, tau.getLoaiTau().trim());
            st.setString(3, tau.getMaTau().trim());
            st.setInt(4, tau.getSoLuongToa());
        	st.setInt(5, tau.getSoLuongGhe());
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return n > 0;
    }
    
    // Tìm kiếm tàu
    public List<Tau> timKiemTau(Tau tau) {
        List<Tau> dsTau = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
        	conn = ConnectDB.getInstance().connect();
            String SQL = "SELECT t.MaTau, ng.MaNhaGa, t.LoaiTau, t.SoLuongToa, t.SoLuongGhe " +
                         "FROM Tau t " +
                         "INNER JOIN NhaGa ng ON t.MaNhaGa = ng.MaNhaGa " +
                         "WHERE t.MaTau = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, tau.getMaTau());
            rs = st.executeQuery();
            while (rs.next()) {
                String maTau = rs.getString(1);
                String maNhaGa = rs.getString(2);
                String loaiTau = rs.getString(3);
                int soLuongToa = rs.getInt(4);
                int soLuongGhe = rs.getInt(5);
                        
                NhaGa ng = new NhaGa(maNhaGa);
                Tau foundTau = new Tau(maTau, ng, loaiTau, soLuongToa, soLuongGhe);
                dsTau.add(foundTau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsTau;
    }

    
}
