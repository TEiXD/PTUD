package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	
	public List<KhachHang> layThongTin() {
	    List<KhachHang> dsKH = new ArrayList<>();
	    try (Connection conn = ConnectDB.getConnection();
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery("SELECT * FROM dbo.KhachHang")) {

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
	        // Handle the exception (e.g., log it or show an error message)
	        e.printStackTrace();
	    }
	    return dsKH;
	}
	
	public String getLastMaKH() {
	    String lastMaKH = null;
	    ConnectDB.getInstance();
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement statement = con.prepareStatement("SELECT MaNV FROM NhanVien ORDER BY MaNV DESC LIMIT 1")) {
	        ResultSet rs = statement.executeQuery();
	        if (rs.next()) {
	            lastMaKH = rs.getString("MaNV");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return lastMaKH;
	}

	
	public String taoMaNV() {
	    String lastMaKH = getLastMaKH(); // Phương thức này cần truy vấn CSDL để lấy mã NV lớn nhất
	    String prefix = lastMaKH.substring(0, 2); // NV
	    int number = Integer.parseInt(lastMaKH.substring(2)) + 1; // 999 + 1 = 1000
	    return prefix + number; 
	}

	
	//them KH
	public boolean themKH(KhachHang kh) {
	    ConnectDB.getInstance();
	    String newMaNV = taoMaNV(); // Sinh mã nhân viên mới
	    try (Connection con = ConnectDB.getConnection();
	         PreparedStatement statement = con.prepareStatement("INSERT INTO NhanVien (MaNV, TenNV, CCCD, GioiTinh, SDT, Email) VALUES (?, ?, ?, ?, ?, ?)")) {
	        statement.setString(1, newMaNV);
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
		         PreparedStatement statement = con.prepareStatement("DELETE FROM KhachHang WHERE MaKH = ?")) {
		        statement.setString(1, maKH);
		        int rowsAff = statement.executeUpdate();
		        return rowsAff > 0;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
}
