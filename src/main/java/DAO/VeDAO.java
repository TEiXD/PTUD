package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.KhachHang;
import entity.NhanVien;
import entity.Ve;

public class VeDAO {
	public ArrayList<Ve> layThongTin() {
		ArrayList<Ve> dsV = new ArrayList<Ve>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "SELECT v.maVe, v.tenVe, v.loaiVe, v.ngayDi, v.ngayVe, kh.maKH, nv.maNV, ct.maChuyenTau" +
						"FROM Ve v" +
						"INNER JOIN KhachHang kh ON v.maKH = kh.maKH" +
						"INNER JOIN NhanVien nv ON v.maNV = nv.maNV" +
						"INNER JOIN ChuyenTau ct ON v.maChuyenTau = ct.maChuyenTau";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while (rs.next()) {
				String maV = rs.getString(1);
				String tenV = rs.getString(2);
				String loaiV = rs.getString(3);
				LocalDateTime ngayDi = rs.getTimestamp(4).toLocalDateTime();
                LocalDateTime ngayVe = rs.getTimestamp(5).toLocalDateTime();
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsV;
	}
	
	//Thêm vé
	public boolean addVe(Ve v) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		String SQL = "INSERT INTO dbo.Ve (maVe, tenVe, loaiVe, ngayDi, ngayVe, maKH, maNV, maChuyenTau) VALUES (?,?,?,?,?,?,?,?)";
		int n = 0;
		try {
			st = conn.prepareStatement(SQL);
			st.setString(1, v.getMaVe().trim());
			st.setString(2, v.getTenVe().trim());
			st.setString(3, v.getLoaiVe().trim());
			LocalDateTime ngayDi = v.getNgayDi();
	        Timestamp sqlNgayDi = Timestamp.valueOf(ngayDi);
	        st.setTimestamp(4, sqlNgayDi);
	        LocalDateTime ngayVe = v.getNgayVe();
	        Timestamp sqlNgayVe = Timestamp.valueOf(ngayVe);
	        st.setTimestamp(5, sqlNgayVe);
	        st.setString(6, v.getMaKH().getMaKH().trim());
	        st.setString(7, v.getMaNV().getMaNV().trim());
	        st.setString(8, v.getMaCT().getMaChuyenTau().trim());
	        
			n = st.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(st != null) {
					st.close();
				}conn.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	//xóa vé
	public boolean removeVe(String maV) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			String SQL = "DELETE FROM Ve WHERE maVe=?";
			st = conn.prepareStatement(SQL);
			st.setString(1, maV);
			n=st.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	
	//sửa vé
	public boolean updateVe(Ve v) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			String SQL = "UPDATE Ve SET maVe=?, tenVe=?, loaiVe=?, ngayDi=?, ngayVe=?, maKH=?, maNV=?, maChuyenTau=? WHERE maVe=?";
			st = conn.prepareStatement(SQL);
			st.setString(1, v.getMaVe());
			st.setString(2, v.getTenVe());
			st.setString(3, v.getLoaiVe());
			LocalDateTime ngayDi = v.getNgayDi();
	        Timestamp sqlNgayDi = Timestamp.valueOf(ngayDi);
	        st.setTimestamp(4, sqlNgayDi);
	        LocalDateTime ngayVe = v.getNgayVe();
	        Timestamp sqlNgayVe = Timestamp.valueOf(ngayVe);
	        st.setTimestamp(5, sqlNgayVe);
	        st.setString(6, v.getMaKH().getMaKH());
	        st.setString(7, v.getMaNV().getMaNV());
	        st.setString(8, v.getMaCT().getMaChuyenTau());
	        
	        n=st.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
