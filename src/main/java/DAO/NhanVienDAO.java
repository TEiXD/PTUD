package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import connectDB.ConnectDB;

public class NhanVienDAO {
	public ArrayList<NhanVien> layThongTin(){
		ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "Select * from dbo.NhanVien";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next()) {
				String maNV = rs.getString(1);
				String hoten = rs.getString(2);
				String  = rs.getString(1);
				String hoten = rs.getString(2);
				String maNV = rs.getString(1);
				String hoten = rs.getString(2);
				String maNV = rs.getString(1);
				String hoten = rs.getString(2);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
