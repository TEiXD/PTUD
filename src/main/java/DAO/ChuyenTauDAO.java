package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChuyenTau;
import entity.NhaGa;
import entity.Tau;

public class ChuyenTauDAO {
	public List<ChuyenTau> layThongTin() {
	    List<ChuyenTau> dsCT = new ArrayList<>();
	    try (Connection conn = ConnectDB.getConnection();
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery("SELECT ct.MaChuyenTau, t.Tau, ng.NhaGa, ct.GaDi, ct.GaDen, ct.GioDi, ct.GioDen " +
	         "FROM ChuyenTau ct INNER JOIN Tau t ON ct.MaTau = t.MaTau INNER JOIN NhaGa ng ON ct.MaNhaGa = ng.MaNhaGa")) {
	        while (rs.next()) {
	            String maChuyenTau = rs.getString(1);
	            String maTau = rs.getString(2);
	            String maNhaGa = rs.getString(3);
	            String gaDi = rs.getString(4);
	            String gaDen = rs.getString(5);
	            LocalDateTime gioDi = rs.getTimestamp(6).toLocalDateTime();
	            LocalDateTime gioDen = rs.getTimestamp(7).toLocalDateTime();
	            Tau t = new Tau(maTau);
	            NhaGa ng = new NhaGa(maNhaGa);
	            ChuyenTau ct = new ChuyenTau(maChuyenTau, t, ng, gaDi, gaDen, gioDi, gioDen);
	            dsCT.add(ct);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsCT;
	}

	//thêm chuyến tàu
	public boolean addCT(ChuyenTau chuyenTau) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	                 "INSERT INTO dbo.ChuyenTau (MaChuyenTau, MaTau, MaNhaGa, GaDi, GaDen, GioDi, GioDen) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)")) {
	    	
	        st.setString(1, chuyenTau.getMaChuyenTau().trim());
	        st.setString(2, chuyenTau.getmTau().getMaTau().trim());
	        st.setString(3, chuyenTau.getNhaGa().getMaNhaGa().trim());
	        st.setString(4, chuyenTau.getGaDi().trim());
	        st.setString(5, chuyenTau.getGaDen().trim());
	        st.setTimestamp(6, Timestamp.valueOf(chuyenTau.getGioDi()));
	        st.setTimestamp(7, Timestamp.valueOf(chuyenTau.getGioDen()));
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	//xóa chuyến tàu
	public boolean removeCT(String maCT) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement("DELETE FROM ChuyenTau WHERE MaChuyenTau = ?")) {
	        st.setString(1, maCT.trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	//sửa phiếu
	public boolean updateCT(ChuyenTau chuyenTau) {
	    try (Connection conn = ConnectDB.getConnection();
	         PreparedStatement st = conn.prepareStatement(
	         "UPDATE ChuyenTau SET MaChuyenTau=?, MaTau=?, MaNhaGa=?, GaDi=?, GaDen=?, GioDi=?, GioDen=? WHERE MaChuyenTau=?")) {

	        st.setString(1, chuyenTau.getMaChuyenTau().trim());
	        st.setString(2, chuyenTau.getmTau().getMaTau().trim());
	        st.setString(3, chuyenTau.getNhaGa().getMaNhaGa().trim());
	        st.setString(4, chuyenTau.getGaDi().trim());
	        st.setString(5, chuyenTau.getGaDen().trim());
	        st.setTimestamp(6, Timestamp.valueOf(chuyenTau.getGioDi()));
	        st.setTimestamp(7, Timestamp.valueOf(chuyenTau.getGioDen()));
	        st.setString(8, chuyenTau.getMaChuyenTau().trim());
	        int rowsAff = st.executeUpdate();
	        return rowsAff > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
