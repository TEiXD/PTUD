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
import entity.NhaGa;
import entity.Tau;

public class ChuyenTauDAO {
	public ArrayList<ChuyenTau> layThongTin(){
		ArrayList<ChuyenTau> dsCT = new ArrayList<ChuyenTau>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "SELECT ct.maChuyenTau, t.Tau, ng.NhaGa, ct.gaDi, ct.gaDen, ct.gioDi, ct.gioDen" +
					"FROM ChuyenTau ct" +
					"INNER JOIN Tau t ON ct.maTau=t.maTau" +
					"INNER JOIN NhaGa ng ON ct.maNhaGa = ng.maNhaGa";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
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
				
				ChuyenTau ct = new ChuyenTau(maChuyenTau, t, ng, gaDi, gaDen, null, null);
				dsCT.add(ct);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCT;
	}
	
	//thêm chuyến tàu
	public boolean addCT(ChuyenTau chuyenTau) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		String SQL = "INSERT  INTO dbo.ChuyenTau (maChuyenTau, maTau, maNhaGa, gaDi, gaDen, gioDi, gioDen) VALUES (?,?,?,?,?,?,?)";
		int n = 0;
		try {
			st = conn.prepareStatement(SQL);
			st.setString(1, chuyenTau.getMaChuyenTau().trim());
			st.setString(2, chuyenTau.getmTau().getMaTau().trim());
			st.setString(3, chuyenTau.getNhaGa().getMaNhaGa().trim());
			st.setString(4, chuyenTau.getGaDi().trim());
			st.setString(5, chuyenTau.getGaDen().trim());
			LocalDateTime gioDi = chuyenTau.getGioDi();
	        Timestamp sqlGioDi = Timestamp.valueOf(gioDi);
	        st.setTimestamp(6, sqlGioDi);
	        LocalDateTime gioDen = chuyenTau.getGioDen();
	        Timestamp sqlGioDen = Timestamp.valueOf(gioDen);
	        st.setTimestamp(7, sqlGioDen);
			
	        n = st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
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
	
	//xóa chuyến tàu
	public boolean removeCT(String maCT) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			String SQL = "DELETE FROM ChuyenTau WHERE maChuyenTau=?";
			st = conn.prepareStatement(SQL);
			st.setString(1, maCT);
			n = st.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n > 0;
	}
	
	//sửa phiếu
	public boolean updateCT(ChuyenTau chuyenTau) {
		ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			String SQL = "UPDATE ChuyenTau SET maChuyenTau=?, maTau=?, maNhaGa=?, gaDi=?, gaDen=?, gioDi=?, gioDen=? WHERE maChuyenTau=?";
			st = conn.prepareStatement(SQL);
			st.setString(1, chuyenTau.getMaChuyenTau());
			st.setString(2, chuyenTau.getmTau().getMaTau().trim());
			st.setString(3, chuyenTau.getNhaGa().getMaNhaGa().trim());
			st.setString(4, chuyenTau.getGaDi().trim());
			st.setString(5, chuyenTau.getGaDen().trim());
			LocalDateTime gioDi = chuyenTau.getGioDi();
	        Timestamp sqlGioDi = Timestamp.valueOf(gioDi);
	        st.setTimestamp(6, sqlGioDi);
	        LocalDateTime gioDen = chuyenTau.getGioDen();
	        Timestamp sqlGioDen = Timestamp.valueOf(gioDen);
	        st.setTimestamp(7, sqlGioDen);
			
	        n = st.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
}
