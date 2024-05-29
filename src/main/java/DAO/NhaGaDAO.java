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

public class NhaGaDAO {
    
    public ArrayList<NhaGa> layThongTin() {
        ArrayList<NhaGa> dsNG = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT * FROM NhaGa";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String maNhaGa = rs.getString("maNhaGa");
                String tenNhaGa = rs.getString("tenNhaGa");
                String diaChi = rs.getString("diaChi");
                NhaGa nhaGa = new NhaGa(maNhaGa, tenNhaGa, diaChi);
                dsNG.add(nhaGa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNG;
    }
    
    public List<String> layHetTenGa() {
        try {
            List<String> dsTenGa = layThongTin().stream()
                    .map(ga -> ga.getTenNhaGa())
                    .toList();

            return dsTenGa;
        } catch (Exception e) {
            e.printStackTrace();;
        }
        return new ArrayList<>();
    }
    
    public NhaGa layTheoTen(String name) {
        try {
            String sql = "SELECT * FROM NhaGa WHERE TenNhaGa=?";
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String maGa = rs.getString(1);
                String tenGa = rs.getString(2);
                String diaChi = rs.getString(3);

                return new NhaGa(maGa, tenGa, diaChi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//
    public boolean addNhaGa(NhaGa nhaGa) {
        try {
            Connection conn = ConnectDB.getConnection();
            String sql = "INSERT INTO NhaGa (MaNhaGa, TenNhaGa, DiaChi) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nhaGa.getMaNhaGa());
            stmt.setString(2, nhaGa.getTenNhaGa());
            stmt.setString(3, nhaGa.getDiaChi());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean suaNhaGa(NhaGa nhaGa) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;

        try {
            String SQL = "UPDATE dbo.NhaGa SET TenNhaGa = ?, DiaChi = ? WHERE MaNhaGa = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, nhaGa.getTenNhaGa());
            st.setString(2, nhaGa.getDiaChi());
            st.setString(3, nhaGa.getMaNhaGa());
            n = st.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa thông tin nhà ga trong cơ sở dữ liệu!");
        } finally {
            try {
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return n > 0;
    }

    public boolean xoaNhaGa(String maNhaGa) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;

        try {
            String SQL = "DELETE FROM dbo.NhaGa WHERE MaNhaGa = ?";
            st = conn.prepareStatement(SQL);
            st.setString(1, maNhaGa);
            n = st.executeUpdate();
            return n > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa nhà ga khỏi cơ sở dữ liệu!");
        } finally {
            try {
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return n > 0;
    }
//    public ArrayList<NhaGa> layTenNhaGaTheoTuyen(String tuyen) {
//        ArrayList<NhaGa> dsNG = new ArrayList<>();
//        try {
//            ConnectDB.getInstance().connect();
//            Connection conn = ConnectDB.getConnection();
//            
//            // Find the index of " – "
//            int index = tuyen.indexOf(" – ");
//            
//            // Check if " – " is found
//            if (index != -1) {
//                // Extract substrings before and after " – "
//                String part1 = tuyen.substring(0, index).trim();
//                String part2 = tuyen.substring(index + 3).trim();
//                
//                // Create SQL query to retrieve NhaGa records based on diaChi
//                String SQL = "SELECT * FROM NhaGa WHERE diaChi LIKE '%" + part1 + "%' OR diaChi LIKE '%" + part2 + "%'";
//                
//                Statement st = conn.createStatement();
//                ResultSet rs = st.executeQuery(SQL);
//                
//                // Iterate over the result set and add NhaGa objects to the list
//                while (rs.next()) {
//                    String maNhaGa = rs.getString("maNhaGa");
//                    String tenNhaGa = rs.getString("tenNhaGa");
//                    String diaChi = rs.getString("diaChi");
//                    NhaGa nhaGa = new NhaGa(maNhaGa, tenNhaGa, diaChi);
//                    dsNG.add(nhaGa);
//                }
//            } else {
//                // Handle case when " – " is not found
//                System.out.println("Separator ' – ' not found in the input string.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dsNG;
//    }
}
