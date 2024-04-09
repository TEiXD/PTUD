package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List; 
import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ve;

public class VeDAO {
	public List<Ve> layThongTin() {
	    List<Ve> dsV = new ArrayList<>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT v.MaVe, v.TenVe, v.LoaiVe, v.NgayDi, v.NgayVe, kh.MaKH, nv.MaNV, ct.MaChuyenTau FROM Ve v " +
	                     "INNER JOIN KhachHang kh ON v.MaKH = kh.MaKH " +
	                     "INNER JOIN NhanVien nv ON v.MaNV = nv.MaNV " +
	                     "INNER JOIN ChuyenTau ct ON v.MaChuyenTau = ct.MaChuyenTau";
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(SQL);
	        while (rs.next()) {
	            String maV = rs.getString(1);
	            String tenV = rs.getString(2);
	            String loaiV = rs.getString(3);
	            String ngayDi = rs.getString(4);
	            String ngayVe = rs.getString(5);
	            String maKH = rs.getString(6);
	            String maNV = rs.getString(7);
	            String maCT = rs.getString(8);
	            KhachHang kh = new KhachHang(maKH);
	            NhanVien nv = new NhanVien(maNV);
	            ChuyenTau ct = new ChuyenTau(maCT);
	            Ve v = new Ve(maV, tenV, loaiV, ngayDi, ngayVe, kh, nv, ct);
	            dsV.add(v);
	        }
	    } catch (Exception e) {

	        e.printStackTrace();
	    }
	    return dsV;
	}

	//Thêm vé
	public boolean themVe(Ve v) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    try {
	        ConnectDB.getInstance();
			conn = ConnectDB.getConnection();
	        String SQL = "INSERT INTO dbo.Ve (MaVe, TenVe, LoaiVe, NgayDi, NgayVe, MaKH, MaNV, MaChuyenTau) VALUES (?,?,?,?,?,?,?,?)";
	        st = conn.prepareStatement(SQL);
	        st.setString(1, v.getMaVe().trim());
	        st.setString(2, v.getTenVe().trim());
	        st.setString(3, v.getLoaiVe().trim());
	        st.setTimestamp(4, Timestamp.valueOf(v.getNgayDi()));
	        st.setTimestamp(5, Timestamp.valueOf(v.getNgayVe()));
	        st.setString(6, v.getMaKH().getMaKH().trim());
	        st.setString(7, v.getMaNV().getMaNV().trim());
	        st.setString(8, v.getMaCT().getMaChuyenTau().trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	//xóa vé
	public boolean xoaVe(String maV) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    int rowsAff = 0; // Renamed variable

	    try {
	        ConnectDB.getInstance();
			conn = ConnectDB.getConnection();
	        String SQL = "DELETE FROM Ve WHERE MaVe=?";
	        st = conn.prepareStatement(SQL);
	        st.setString(1, maV);

	        rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	
	//sửa vé
	public boolean suaVe(Ve v) {
	    Connection conn = null;
	    PreparedStatement st = null;
	    int rowsAff = 0; // Renamed variable

	    try {
	        ConnectDB.getInstance();
			conn = ConnectDB.getConnection();
	        String SQL = "UPDATE Ve SET MaVe=?, TenVe=?, LoaiVe=?, NgayDi=?, NgayVe=?, MaKH=?, MaNV=?, MaChuyenTau=? WHERE MaVe=?";
	        st = conn.prepareStatement(SQL);
	        st.setString(1, v.getMaVe());
	        st.setString(2, v.getTenVe());
	        st.setString(3, v.getLoaiVe());
	        st.setTimestamp(4, Timestamp.valueOf(v.getNgayDi()));
	        st.setTimestamp(5, Timestamp.valueOf(v.getNgayVe()));
	        st.setString(6, v.getMaKH().getMaKH());
	        st.setString(7, v.getMaNV().getMaNV());
	        st.setString(8, v.getMaCT().getMaChuyenTau());
	        st.setString(9, v.getMaVe()); // Set maVe for WHERE clause

	        rowsAff = st.executeUpdate();
	        return rowsAff > 0; 
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

}
