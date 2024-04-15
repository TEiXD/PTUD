package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaGa;
import entity.NhanVien;

public class NhanVienDAO {
	//Add DS
	public List<NhanVien> layThongTin() {
	    List<NhanVien> dsNhanVien = new ArrayList<>();
	    try {
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT nv.MaNV, nv.HoTen, nv.CCCD, nv.GioiTinh, nv.SDT, nv.Email, nv.NgaySinh, nv.TrinhDo, ng.MaNhaGa " +
	                     "FROM NhanVien nv INNER JOIN NhaGa ng ON nv.MaNhaGa = ng.MaNhaGa";
	        try (PreparedStatement ps = conn.prepareStatement(SQL);
	             ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                String maNV = rs.getString(1);
	                String hoTen = rs.getString(2);
	                String CCCD = rs.getString(3);
	                String gioiTinh = rs.getString(4);
	                String SDT = rs.getString(5);
	                String email = rs.getString(6);
	                String ngaySinh = rs.getString(7);
	                String trinhDo = rs.getString(8);
	                String maNhaGa = rs.getString(9);
	                NhaGa ng = new NhaGa(maNhaGa);
	                NhanVien nv = new NhanVien(maNV, hoTen, CCCD, gioiTinh, SDT, email, ngaySinh, trinhDo, ng);
	                dsNhanVien.add(nv);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}

	
	//Them NV
	public boolean addNV(NhanVien nhanVien) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	         "INSERT INTO dbo.NhanVien (MaNV, HoTen, CCCD, GioiTinh, SDT, Email, NamSinh, TrinhDo, MaNhaGa)"
	         + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
	        
	        st.setString(1, nhanVien.getMaNV().trim());
	        st.setString(2, nhanVien.getHoTen().trim());
	        st.setString(3, nhanVien.getCCCD().trim());
	        st.setString(4, nhanVien.getGioiTinh().trim());
	        st.setString(5, nhanVien.getSDT().trim());
	        st.setString(6, nhanVien.getEmail().trim());
	        st.setObject(7, nhanVien.getNgaySinh());
	        st.setString(8, nhanVien.getTrinhDo().trim());
	        st.setString(9, nhanVien.getNhaGa().getMaNhaGa().trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	//xoa NV
	public boolean xoaNV(String maNV) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement("DELETE FROM dbo.NhanVien WHERE MaNV = ?")) {
	        st.setString(1, maNV);
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	//Sua NV
	public boolean SuaNV(NhanVien nhanVien) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	         "UPDATE dbo.NhanVien SET MaNV = ?, HoTen = ?, CCCD = ?, GioiTinh = ?, SDT = ?, Email = ?, NgaySinh = ?, TrinhDo = ?, MaNhaGa = ?"
	         + " WHERE MaNV = ?")) {
	        st.setString(1, nhanVien.getMaNV());
	        st.setString(2, nhanVien.getHoTen());
	        st.setString(3, nhanVien.getCCCD());
	        st.setString(4, nhanVien.getGioiTinh());
	        st.setString(5, nhanVien.getSDT());
	        st.setString(6, nhanVien.getEmail());
	        st.setObject(7, nhanVien.getNgaySinh());
	        st.setString(8, nhanVien.getTrinhDo());
	        st.setString(9, nhanVien.getNhaGa().getMaNhaGa().trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	//TimkiemNV
	public List<NhanVien> timKiemNhanVien(NhanVien nv) {
	    List<NhanVien> dsNhanVien = new ArrayList<>();
	    try {
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT nv.MaNV, nv.HoTen, nv.CCCD, nv.GioiTinh, nv.SDT, nv.Email, nv.NgaySinh, nv.TrinhDo, ng.MaNhaGa " +
	                     "FROM NhanVien nv INNER JOIN NhaGa ng ON nv.MaNhaGa = ng.MaNhaGa " +
	                     "WHERE nv.MaNV = ? OR nv.HoTen = ?";
	        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
	            ps.setString(1, nv.getMaNV());
	            ps.setString(2, nv.getHoTen());
	            
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    String maNV = rs.getString(1);
	                    String hoTen = rs.getString(2);
	                    String CCCD = rs.getString(3);
	                    String gioiTinh = rs.getString(4);
	                    String SDT = rs.getString(5);
	                    String email = rs.getString(6);
	                    String ngaySinh = rs.getString(7);
	                    String trinhDo = rs.getString(8);
	                    String maNhaGa = rs.getString(9);
	                    NhaGa ng = new NhaGa(maNhaGa);
	                    NhanVien nhanVien = new NhanVien(maNV, hoTen, CCCD, gioiTinh, SDT, email, ngaySinh, trinhDo, ng);
	                    dsNhanVien.add(nhanVien);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}


}
