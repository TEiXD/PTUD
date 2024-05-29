package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;


import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.NhaGa;
import entity.Tau;

public class ChuyenTauDAO {

    public ArrayList<ChuyenTau> layThongTin() {
        ArrayList<ChuyenTau> dsCT = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT ct.MaChuyenTau, t.TenTau, ngDi.TenNhaGa AS GaDi, ngDen.TenNhaGa AS GaDen, ct.NgayDi, ct.GioDi, ct.NgayDen, ct.GioDen " +
            			"FROM ChuyenTau ct " +
            			"INNER JOIN Tau t ON ct.MaTau = t.MaTau " +
                        "INNER JOIN NhaGa ngDi ON ct.GaDi = ngDi.MaNhaGa " +
                        "INNER JOIN NhaGa ngDen ON ct.GaDen = ngDen.MaNhaGa " +
                        "WHERE ngDi.TenNhaGa <> ngDen.TenNhaGa";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String maChuyenTau = rs.getString(1);
                String tau = rs.getString(2);
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                Date ngayDi = rs.getDate("NgayDi");
                Time gioDi = rs.getTime("GioDi");
                Date ngayDen = rs.getDate("NgayDen");
                Time gioDen = rs.getTime("GioDen");

                ChuyenTau ct = new ChuyenTau(maChuyenTau, new Tau(tau), new NhaGa(gaDi), new NhaGa(gaDen), ngayDi, gioDi, ngayDen, gioDen);
                dsCT.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin chuyến tàu từ cơ sở dữ liệu!");
        }
        return dsCT;
    }

    
    public boolean themChuyenTau(ChuyenTau ct) {
        ConnectDB.getInstance().connect();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String sql = "INSERT INTO ChuyenTau(MaChuyenTau, MaTau, GaDi, GaDen, NgayDi, GioDi, NgayDen, GioDen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int n = 0;
        try {
        	st = conn.prepareStatement(sql);
            st.setString(1, ct.getMaChuyenTau());
            st.setString(2, ct.getTau().getMaTau());
            st.setString(3, ct.getGaDi().getMaNhaGa());
            st.setString(4, ct.getGaDen().getMaNhaGa());

            // Convert java.util.Date to java.sql.Date
            st.setDate(5, new java.sql.Date(ct.getNgayDi().getTime()));
            st.setTime(6, ct.getGioDi());
            st.setDate(7, new java.sql.Date(ct.getNgayDen().getTime()));
            st.setTime(8, ct.getGioDen());
            
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm chuyến tàu vào cơ sở dữ liệu!");
        }
        return n > 0;
    }

    public boolean suaChuyenTau(ChuyenTau ct) {
        ConnectDB.getInstance().connect();
        Connection conn = ConnectDB.getConnection();
        String sql = "UPDATE ChuyenTau SET MaTau = ?, GaDi = ?, GaDen = ?, NgayDi = ?, GioDi = ?, NgayDen = ?, GioDen = ? WHERE MaChuyenTau = ?";
        int n = 0;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, ct.getTau().getMaTau());
            st.setString(2, ct.getGaDi().getMaNhaGa());
            st.setString(3, ct.getGaDen().getMaNhaGa());
            st.setDate(4, new java.sql.Date(ct.getNgayDi().getTime()));
            st.setTime(5, ct.getGioDi());
            st.setDate(6, new java.sql.Date(ct.getNgayDen().getTime()));
            st.setTime(7, ct.getGioDen());
            st.setString(8, ct.getMaChuyenTau());
            
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi sửa chuyến tàu trong cơ sở dữ liệu!");
        }
        return n > 0;
    }

    public boolean xoaChuyenTau(String maCT) {
        ConnectDB.getInstance().connect();
        Connection conn = ConnectDB.getConnection();
        String sql = "DELETE FROM ChuyenTau WHERE MaChuyenTau = ?";
        int n = 0;
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, maCT);
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa chuyến tàu từ cơ sở dữ liệu!");
        }
        return n > 0;
    }
    
    public List<ChuyenTau> timChuyenTauTheoGa(String tenGaDi, String tenGaDen, Date ngayDi) {
        List<ChuyenTau> dsChuyenTau = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String sql = "SELECT ct.MaChuyenTau, t.TenTau, ngDi.TenNhaGa, ngDen.TenNhaGa, ct.GioDi, ct.GioDen " +
                         "FROM ChuyenTau ct " +
                         "JOIN Tau t ON ct.MaTau = t.MaTau " +
                         "JOIN NhaGa ngDi ON ct.GaDi = ngDi.MaNhaGa " +
                         "JOIN NhaGa ngDen ON ct.GaDen = ngDen.MaNhaGa " +
                         "WHERE ngDi.TenNhaGa = ? AND ngDen.TenNhaGa = ? AND ct.NgayDi = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, tenGaDi);
	          pst.setString(2, tenGaDen);
	          pst.setDate(3, ngayDi);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
            	String maChuyen = rs.getString(1);
            	String maTau = rs.getString(2);
                String maNGDi = rs.getString(3);
                String maNGDen = rs.getString(4);
                Time timeDi = rs.getTime(5);
                Time timeDen = rs.getTime(6);

                dsChuyenTau.add(new ChuyenTau(maChuyen, new Tau(maTau), new NhaGa(maNGDi), new NhaGa(maNGDen), null, timeDi, null, timeDen));

               
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm chuyến tàu từ cơ sở dữ liệu!");
        }
        return dsChuyenTau;
        
    }
}
