package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Tau;
import entity.Toa;

public class ToaDAO {
	public List<Toa> layThongTinToa() {
	    List<Toa> dsToa = new ArrayList<>();
	    try (Connection conn = ConnectDB.getConnection();
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery(
	                 "SELECT t.MaTau, toa.SoToaTau, toa.LoaiTau, toa.SoPhong, toa.MaGhe, toa.SoGhe " +
	                 "FROM Toa toa INNER JOIN Tau t ON toa.MaTau = t.MaTau")) {

	        while (rs.next()) {
	            String maTau = rs.getString(1);
	            String soToaTau = rs.getString(2);
	            String loaiTau = rs.getString(3);
	            int soPhong = rs.getInt(4);
	            String maGhe = rs.getString(5);
	            int soGhe = rs.getInt(6);
	            Tau t = new Tau(maTau);
	            Toa toa = new Toa(t, soToaTau, loaiTau, maGhe, soPhong, soGhe);
	            dsToa.add(toa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsToa;
	}

	//thêm toa
	public boolean addToa(Toa toa) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	         "INSERT INTO Toa (MaTau, SoToaTau, LoaiTau, SoPhong, MaGhe, SoGhe) VALUES (?, ?, ?, ?, ?, ?)")) {

	        st.setString(1, toa.getTau().getMaTau().trim());
	        st.setString(2, toa.getSoToaTau().trim());
	        st.setString(3, toa.getLoaiTau().trim());
	        st.setInt(4, toa.getSoPhong());
	        st.setString(5, toa.getMaGhe().trim());
	        st.setInt(6, toa.getSoGhe());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}


	//xóa toa
	public boolean removeToa(String soToa) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement("DELETE FROM Toa WHERE SoToaTau = ?")) {

	        st.setString(1, soToa.trim());
	        int rowsAffected = st.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	//sửa toa
	public boolean updateToa(Toa toa) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	                 "UPDATE Toa SET MaTau=?, LoaiTau=?, SoPhong=?, MaGhe=?, SoGhe=? WHERE SoToaTau=?")) {

	        st.setString(1, toa.getTau().getMaTau().trim());
	        st.setString(2, toa.getLoaiTau().trim());
	        st.setInt(3, toa.getSoPhong());
	        st.setString(4, toa.getMaGhe().trim());
	        st.setInt(5, toa.getSoGhe());
	        st.setString(6, toa.getSoToaTau().trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
