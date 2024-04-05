package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.NhaGa;
import entity.NhanVien;

public class NhanVienDAO {
	//Add DS
	public ArrayList<NhanVien> layThongTin() {
	    ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
	    try {
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT nv.maNV, nv.hoTen, nv.CCCD, nv.gioiTinh, nv.SDT, nv.email, nv.ngaySinh, nv.trinhDo, ng.NhaGa " +
	                	 "FROM NhanVien nv " +"INNER JOIN NhaGa ng ON nv.maNhaGa = ng.maNhaGa";

	        PreparedStatement ps = conn.prepareStatement(SQL);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            String maNV = rs.getString("maNV");
	            String hoten = rs.getString("hoTen");
	            String CCCD = rs.getString("CCCD");
	            String gioiTinh = rs.getString("gioiTinh");
	            String SDT = rs.getString("SDT");
	            String email = rs.getString("email");
	            Date ngaySinh = rs.getDate("ngaySinh");
	            String trinhDo = rs.getString("trinhDo");
	            String maNhaGa = rs.getString("NhaGa");

	            NhaGa ng = new NhaGa(maNhaGa);
	            NhanVien nv = new NhanVien(maNV, hoten, CCCD, gioiTinh, SDT, email, ngaySinh, trinhDo, ng);
	            dsNhanVien.add(nv);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}
	
	//Them NV
	public boolean addNV(NhanVien nhanVien) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		String SQL = "INSERT INTO dbo.NhanVien (MaNV, HoTen, CCCD, GioiTinh, SDT, Email, NamSinh, TrinhDo, MaNhaGa) VALUES(?,?,?,?,?,?,?,?,?)";
		int n = 0;
		try {
			st = conn.prepareStatement(SQL);
			st.setString(1, nhanVien.getMaNV().trim());
			st.setString(2, nhanVien.getHoTen().trim());
			st.setString(3, nhanVien.getCCCD().trim());
			st.setString(4, nhanVien.getGioiTinh().trim());
			st.setString(5, nhanVien.getSDT().trim());
			st.setString(6, nhanVien.getEmail().trim());
			st.setObject(7, nhanVien.getNgaySinh());
			st.setString(8, nhanVien.getTrinhDo().trim());
			st.setString(9, nhanVien.getNhaGa().getMaNhaGa().trim());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	
	//xoa NV
	public boolean xoaNV(String maNV) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement("DELETE FROM dbo.NhanVien WHERE maNV = ?")) {
	        st.setString(1, maNV);
	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	
	//Sua NV
	public boolean SuaNV(NhanVien nhanVien) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	                 "UPDATE dbo.NhanVien SET maNV = ?, HoTen = ?, CCCD = ?, GioiTinh = ?, SDT = ?, Email = ?, NgaySinh = ?, TrinhDo = ?, MaNhaGa = ? WHERE maNV = ?")) {
	        st.setString(1, nhanVien.getMaNV());
	        st.setString(2, nhanVien.getHoTen());
	        st.setString(3, nhanVien.getCCCD());
	        st.setString(4, nhanVien.getGioiTinh());
	        st.setString(5, nhanVien.getSDT());
	        st.setString(6, nhanVien.getEmail());
	        st.setObject(7, nhanVien.getNgaySinh());
	        st.setString(8, nhanVien.getTrinhDo());
	        st.setString(9, nhanVien.getNhaGa().getMaNhaGa().trim());

	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
