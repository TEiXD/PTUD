package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Tau;
import entity.Toa;

public class ToaDAO {
	public ArrayList<Toa> layThongTinToa(){
	    ArrayList<Toa> dsToa = new ArrayList<Toa>();
	    try {
	        ConnectDB.getInstance().connect();
	        Connection conn = ConnectDB.getConnection();
	        String SQL = "SELECT t.maTau, toa.soToaTau, toa.loaiTau, toa.soPhong, toa.maGhe, toa.soGhe" +
	        			"FROM Toa toa" +
	        			"INNER JOIN Tau t ON toa.maTau = t.maTau";
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(SQL);
	        while (rs.next()) {
	            String maTau = rs.getString(1);
	            String soToaTau = rs.getString(2);
	            String loaiTau = rs.getString(3);
	            int soPhong = rs.getInt(4);
	            String maGhe = rs.getString(5);
	            int soGhe = rs.getInt(6);
	            Tau t = new Tau(maTau);

	            Toa toa = new Toa(t, loaiTau, maGhe, soGhe, soPhong, soGhe);
	            dsToa.add(toa);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsToa;
	}
	
	//thêm toa
	public boolean addToa(Toa toa) {
	    ConnectDB.getInstance();
	    Connection conn = ConnectDB.getConnection();
	    PreparedStatement st = null;
	    String SQL = "INSERT INTO Toa (maTau, soToaTau, loaiTau, soPhong, maGhe, soGhe) VALUES (?,?,?,?,?,?)";
	    int n = 0;
	    try {
	        st = conn.prepareStatement(SQL);
	        st.setString(1, toa.getTau().getMaTau().trim());
	        st.setString(2, toa.getSoToaTau().trim());
	        st.setString(3, toa.getLoaiTau().trim());
	        st.setInt(4, toa.getSoPhong());
	        st.setString(5, toa.getMaGhe().trim());
	        st.setInt(6, toa.getSoGhe());

	        n = st.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(st != null) {
	                st.close();
	            }
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}

	//xóa toa
	public boolean removeToa(String soToa) {
	    ConnectDB.getInstance();
	    Connection conn = ConnectDB.getConnection();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        String SQL = "DELETE FROM Toa WHERE soToaTau=?";
	        st = conn.prepareStatement(SQL);
	        st.setString(1, soToa);
	        n = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(st != null) {
	                st.close();
	            }
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}

	//sửa toa
	public boolean updateToa(Toa toa) {
	    ConnectDB.getInstance();
	    Connection conn = ConnectDB.getConnection();
	    PreparedStatement st = null;
	    int n = 0;
	    try {
	        String SQL = "UPDATE Toa SET maTau=?, loaiTau=?, soPhong=?, maGhe=?, soGhe=? WHERE soToaTau=?";
	        st = conn.prepareStatement(SQL);
	        st.setString(1, toa.getTau().getMaTau().trim());
	        st.setString(2, toa.getLoaiTau().trim());
	        st.setInt(3, toa.getSoPhong());
	        st.setString(4, toa.getMaGhe().trim());
	        st.setInt(5, toa.getSoGhe());
	        st.setString(6, toa.getSoToaTau());

	        n = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(st != null) {
	                st.close();
	            }
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return n > 0;
	}

	

}
