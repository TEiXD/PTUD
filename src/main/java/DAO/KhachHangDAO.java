package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	
	public ArrayList<KhachHang> layThongTin(){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "Select * from dbo.KhachHang";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				String maKH = rs.getString(1);
				String hoTen = rs.getString(2);
	            String CCCD = rs.getString(3);
	            String gioiTinh = rs.getString(4);
	            String SDT = rs.getString(5);
	            String email = rs.getString(6);
	            KhachHang kh = new KhachHang(maKH, hoTen, CCCD, gioiTinh, SDT, email);
                dsKH.add(kh);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dsKH;
	}
	//them KH
		public boolean themKH(KhachHang kh) {
			ConnectDB.getInstance();
			try (Connection con = ConnectDB.getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO NhanVien (MaNV, TenNV, CCCD, GioiTinh, SDT, Email) VALUES (?, ?, ?, ?, ?, ?)")) {
	        	statement.setString(1, kh.getMaKH());
	        	statement.setString(2, kh.getHoTen());
	        	statement.setString(3, kh.getCCCD());
	        	statement.setString(4, kh.getGioiTinh());
	        	statement.setString(5, kh.getSDT());
	        	statement.setString(6, kh.getEmail());
	        int rowsAff = statement.executeUpdate();
	        return rowsAff > 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}

	//xoa KH
		public boolean xoaKH(String maKH) {
		    ConnectDB.getInstance();
			try (Connection con = ConnectDB.getConnection();
		         PreparedStatement statement = con.prepareStatement("DELETE FROM KhachHang WHERE maKH = ?")) {
		        statement.setString(1, maKH);
		        int rowsAff = statement.executeUpdate();
		        return rowsAff > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
}
