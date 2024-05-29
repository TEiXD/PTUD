package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.NhaGa;
import entity.NhanVien;

public class NhanVienDAO {
	public List<NhanVien> layThongTin() {
	    List<NhanVien> dsNhanVien = new ArrayList<>();
	    try {
	    	ConnectDB.getInstance().connect();
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT nv.MaNV, nv.HoTen, nv.CCCD, nv.GioiTinh, nv.SDT, nv.Email, nv.NgaySinh, nv.TrinhDo, ng.TenNhaGa " +
	                     "FROM NhanVien nv INNER JOIN NhaGa ng ON nv.MaNhaGa = ng.MaNhaGa";
	        Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
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
	            NhaGa ng = new NhaGa(maNhaGa);
	            NhanVien nv = new NhanVien(maNV, hoTen, CCCD, gioiTinh, SDT, email, ngaySinh, trinhDo, ng);
	            dsNhanVien.add(nv);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return dsNhanVien;
	}

	public String layMaMoiNhat() {
	    String ma = null;
	    try {
	        Connection connection = ConnectDB.getInstance().getConnection();
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery("SELECT MAX(MaNV) AS ma FROM NhanVien");
	        if (resultSet.next()) {
	            ma = resultSet.getString(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return ma;
	}
	
	//Them NV
	public boolean addNV(NhanVien nhanVien) {
		ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String SQL = "INSERT INTO dbo.NhanVien (MaNV, HoTen, CCCD, GioiTinh, SDT, Email, NgaySinh, TrinhDo, MaNhaGa)" +
        	    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int n = 0;
	    try {
	        st = conn.prepareStatement(SQL);
	        st.setString(1, nhanVien.getMaNV().trim());
	        st.setString(2, nhanVien.getHoTen().trim());
	        st.setString(3, nhanVien.getCCCD().trim());
	        st.setString(4, nhanVien.getGioiTinh().trim());
	        st.setString(5, nhanVien.getSDT().trim());
	        st.setString(6, nhanVien.getEmail().trim());
	        st.setDate(7, new Date(nhanVien.getNgaySinh().getTime()));
	        st.setString(8, nhanVien.getTrinhDo().trim());
	        st.setString(9, nhanVien.getNhaGa().getMaNhaGa().trim());
	        n = st.executeUpdate();
	        return n > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi thêm nhân viên vào cơ sở dữ liệu!");
	    }
	    return n > 0;
	}
	//xoa NV
	public boolean xoaNV(String maNV) {
		ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;
	    try {
	    	String SQL = "DELETE FROM dbo.NhanVien WHERE MaNV = ?";
	        st.setString(1, maNV);
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi xóa nhân viên khỏi cơ sở dữ liệu!");
	    }
	    return n > 0;
	}

	
	//Sua NV
	public boolean SuaNV(NhanVien nhanVien) {
		ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;
	    try {
	    	String SQL = "UPDATE dbo.NhanVien SET HoTen = ?, CCCD = ?, GioiTinh = ?, SDT = ?, Email = ?, NgaySinh = ?, TrinhDo = ?, MaNhaGa = ? WHERE MaNV = ?";
	    	st = conn.prepareStatement(SQL);
	    	st.setString(1, nhanVien.getHoTen());
	        st.setString(2, nhanVien.getCCCD());
	        st.setString(3, nhanVien.getGioiTinh());
	        st.setString(4, nhanVien.getSDT());
	        st.setString(5, nhanVien.getEmail());
	        st.setDate(6, new Date(nhanVien.getNgaySinh().getTime()));
	        st.setString(7, nhanVien.getTrinhDo());
	        st.setString(8, nhanVien.getNhaGa().getMaNhaGa().trim());
	        st.setString(9, nhanVien.getMaNV());
	        n = st.executeUpdate();
	        return n > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi khi sửa nhân viên trên cơ sở dữ liệu!");
	    }
	    return n > 0;
	}

	//TimkiemNV
	public List<NhanVien> timKiemNhanVien(NhanVien nv) {
	    List<NhanVien> dsNhanVien = new ArrayList<>();
	    try {
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT nv.MaNV, nv.HoTen, nv.CCCD, nv.GioiTinh, nv.SDT, nv.Email, nv.NgaySinh, nv.TrinhDo, ng.MaNhaGa " +
	                     "FROM NhanVien nv INNER JOIN NhaGa ng ON nv.MaNhaGa = ng.MaNhaGa " +
	                     "WHERE nv.MaNV = ? OR nv.HoTen = ? OR nv.GioiTinh = ? OR nv.SDT = ? OR nv.Email = ? OR nv.TrinhDo = ?";
	        try (PreparedStatement ps = conn.prepareStatement(SQL)) {
	            ps.setString(1, nv.getMaNV());
	            ps.setString(2, nv.getHoTen());
		        ps.setString(3, nv.getGioiTinh());
		        ps.setString(4, nv.getSDT());
		        ps.setString(5, nv.getEmail());
		        ps.setString(6, nv.getTrinhDo());
	            
	            try (ResultSet rs = ps.executeQuery()) {
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
