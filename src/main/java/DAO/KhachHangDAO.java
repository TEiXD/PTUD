package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.KhachHang;

public class KhachHangDAO {
	
	public ArrayList<KhachHang> layThongTin(){
		ArrayList<KhachHang> dsKH = new ArrayList<KhachHang>();
		ConnectDB.getInstance().connect();
		Connection conn = ConnectDB.getConnection();
		String SQL = "Select * from dbo.KhachHang";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(SQL);
		while (rs.next()) {
			String maKH = rs.get;
		}
		return dsKH;
	}
}
