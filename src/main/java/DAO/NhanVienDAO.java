package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVienDAO{
	
	//Them DS
	public List<NhanVien> layThongTin() {
	    List<NhanVien> dsNhanVien = new ArrayList<>();
	    ConnectDB.getInstance();
		try (Connection conn = ConnectDB.getConnection();
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM dbo.NhanVien")) {
	        while (rs.next()) {
	        	String maNV = rs.getString(1);
	            String hoTen = rs.getString(2);
	            String CCCD = rs.getString(3);
	            String gioiTinh = rs.getString(4);
	            String SDT = rs.getString(5);
	            String email = rs.getString(6);
	            Date ngaySinh = rs.getDate(7);
	            String trinhDo = rs.getString(8);
	            String maNhaGa = rs.getString(9);
	            NhanVien nv = new NhanVien(maNV, hoTen, CCCD, gioiTinh, SDT, email, ngaySinh, trinhDo, maNhaGa);
	            dsNhanVien.add(nv);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}
	
	//Them NV
	public boolean themNhanVien(NhanVien nhanVien) {
	    ConnectDB.getInstance();
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement statement = conn.prepareStatement("INSERT INTO dbo.NhanVien (maNV, hoTen, CCCD, gioiTinh, SDT, email, namSinh, trinhDo, maNhaGa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
	        statement.setString(1, nhanVien.getMaNV().trim());
	        statement.setString(2, nhanVien.getHoTen().trim());
	        statement.setString(3, nhanVien.getCCCD().trim());
	        statement.setString(4, nhanVien.getGioiTinh().trim());
	        statement.setString(5, nhanVien.getSDT().trim());
	        statement.setString(6, nhanVien.getEmail().trim());
	        statement.setObject(7, nhanVien.getNgaySinh());
	        statement.setString(8, nhanVien.getTrinhDo().trim());
	        statement.setString(9, nhanVien.getMaNhaGa().trim());
	        int rowsAff = statement.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//xoa NV
	public boolean xoaNhanVien(String maNV) {
		ConnectDB.getInstance();
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement statement = conn.prepareStatement("DELETE FROM dbo.NhanVien WHERE maNV = ?")) {
	        statement.setString(1, maNV);
	        int rowsAff = statement.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//Sua NV
	public boolean suaNhanVien(NhanVien nhanVien) {
	    ConnectDB.getInstance();
		try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement statement = conn.prepareStatement("UPDATE dbo.NhanVien SET HoTen = ?, CCCD = ?, GioiTinh = ?, SDT = ?, Email = ?, NgaySinh = ?, TrinhDo = ?, MaNhaGa = ? WHERE maNV = ?")) {
	        statement.setString(1, nhanVien.getHoTen());
	        statement.setString(2, nhanVien.getCCCD());
	        statement.setString(3, nhanVien.getGioiTinh());
	        statement.setString(4, nhanVien.getSDT());
	        statement.setString(5, nhanVien.getEmail());
	        statement.setObject(6, nhanVien.getNgaySinh());
	        statement.setString(7, nhanVien.getTrinhDo());
	        statement.setString(8, nhanVien.getMaNhaGa());
	        statement.setString(9, nhanVien.getMaNV());
	        int rowsAff = statement.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
