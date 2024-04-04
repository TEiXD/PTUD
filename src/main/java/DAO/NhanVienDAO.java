package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.NhanVien;

public class NhanVienDAO {
	//Add DS
	public ArrayList<NhanVien> layThongTin(){
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "Select * from dbo.NhanVien";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next()) {
				String maNV = rs.getString(1);
				String hoten = rs.getString(2);
				String CCCD = rs.getString(3);
				String gioiTinh = rs.getString(4);
				String SDT = rs.getString(5);
				String email = rs.getString(6);
				Date ngaySinh = rs.getDate(7);
				String trinhDo = rs.getString(8);
				String maNhaGa = rs.getString(9);
				NhanVien nv = new NhanVien(maNV, hoten, CCCD, gioiTinh, SDT, email, null, trinhDo, maNhaGa);
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
			st.setString(9, nhanVien.getMaNhaGa().trim());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	//xoa NV
	public boolean xoaNV(String maNV) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n = 0;
		try {
			String SQL = "Delete from dbo.NhanVien where maNV = ?";
			st = conn.prepareStatement(SQL);
			st.setString(1, maNV);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
	//Sua NV
	public boolean SuaNV(NhanVien nhanVien){
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n = 0;
		try {
			String SQL = "Update dbo.NhanVien set SET maNV = ?, HoTen = ?, CCCD = ?, GioiTinh = ?, SDT = ?, Email = ?, NgaySinh = ?, TrinhDo = ?, MaNhaGa = ? WHERE maNV = ?";
			st = conn.prepareStatement(SQL);
			st.setString(1, nhanVien.getMaNV());
			st.setString(2, nhanVien.getHoTen());
			st.setString(3, nhanVien.getCCCD());
			st.setString(4, nhanVien.getGioiTinh());
			st.setString(5, nhanVien.getSDT());
			st.setString(6, nhanVien.getEmail());
			st.setObject(7, nhanVien.getNgaySinh());
			st.setString(8, nhanVien.getTrinhDo());
			st.setString(9, nhanVien.getMaNhaGa());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
	}
}
