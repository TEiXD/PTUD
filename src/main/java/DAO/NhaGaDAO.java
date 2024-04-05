package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhaGa;

public class NhaGaDAO {
	public ArrayList<NhaGa> layThongTin(){
		ArrayList<NhaGa> dsNG = new ArrayList<NhaGa>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "SELECT ng.maNhaGa, ng.tenNhaGa";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(SQL);
			while(rs.next()) {
				String maNhaGa = rs.getString(1);
				String tenNhaGa = rs.getString(2);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNG;

	}
}
