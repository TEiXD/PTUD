package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import entity.Tau;
import entity.Toa;

public class ToaDAO {
	public List<Toa> layThongTinToa() {
	    List<Toa> dsToa = new ArrayList<>();
	    try {
	    	ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT toa.MaToa, t.TenTau, toa.TenToa, toa.TinhTrang, toa.SoPhong, toa.MaGhe, toa.SoGhe " +
            			"FROM Toa toa" +
            			"INNER JOIN Tau t ON toa.MaTau = t.MaTau";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
	        while (rs.next()) {
	            String maToa = rs.getString(1);
	            String tau = rs.getString(2);
	            String tenToa = rs.getString(3);
	            int tinhTrang = rs.getInt(4);
	            int soPhong = rs.getInt(5);
	            String maGhe = rs.getString(6);
	            int soGhe = rs.getInt(7);
	            Tau t = new Tau(tau);
	            Toa toa = new Toa(maToa, t, tenToa, tinhTrang, soPhong, maGhe, soGhe);
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
        String SQL = "INSERT INTO Toa (MaToa, MaTau, TenToa, TinhTrang, SoPhong, MaGhe, SoGhe) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int n = 0;
	    try {
	    	st = conn.prepareStatement(SQL);
	    	st.setString(1, toa.getMaToa().trim());
	        st.setString(2, toa.getTau().getMaTau().trim());
	        st.setString(3, toa.getTenToa().trim());
	        st.setInt(4, toa.getTinhTrang());
	        st.setInt(5, toa.getSoPhong());
	        st.setString(6, toa.getMaGhe().trim());
	        st.setInt(7, toa.getSoGhe());
	        n = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        return n > 0;
	}


	//xóa toa
	public boolean removeToa(String maToaT) {
		ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n = 0;
	    try {
	    	String SQL = "DELETE FROM Toa WHERE MaToa = ?";
	    	st = conn.prepareStatement(SQL);
	        st.setString(1, maToaT);
	        n = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
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
	    	String SQL = "UPDATE Toa SET MaNhaGa = ?, TenToa = ?, TinhTrang = ?, SoPhong = ?, MaGhe = ?, SoGhe = ? WHERE MaToa =? ";
	    	st = conn.prepareStatement(SQL);
	        st.setString(1, toa.getTau().getMaTau().trim());
	        st.setString(2, toa.getTenToa().trim());
	        st.setInt(3, toa.getTinhTrang());
	        st.setInt(4, toa.getSoPhong());
	        st.setString(5, toa.getMaGhe().trim());
	        st.setInt(6, toa.getSoGhe());
	        st.setString(7, toa.getMaToa().trim());
	        n = st.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        return n > 0;
	}
	
	public List<Toa> layToaTheoMaTau(String ma) {
		ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
		List<Toa> list = new ArrayList<Toa>();
		try {
			String SQL = "SELECT * FROM Toa toa LEFT JOIN Tau t ON toa.MaTau = t.MaTau WHERE t.MaTau =? ORDER BY MaToa";
			st = conn.prepareStatement(SQL);
			st.setString(1, ma);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String maToa = rs.getString(1);
	            String tau = rs.getString(2);
	            String tenToa = rs.getString(3);
	            int tinhTrang = rs.getInt(4);
	            int soPhong = rs.getInt(5);
	            String maGhe = rs.getString(6);
	            int soGhe = rs.getInt(7);
	            
	            list.add(new Toa(maToa, new Tau(tau), tenToa, tinhTrang, soPhong, maGhe, soGhe));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
