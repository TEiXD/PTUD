package DAO;

import java.sql.Connection;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Tau;

public class TauDAO {
	public ArrayList<Tau> layThongTin() {
		ArrayList<Tau> dsT = new ArrayList<Tau>();
		try {
			ConnectDB.getInstance().connect();
			Connection conn = ConnectDB.getConnection();
			String SQL = "SELECT t.maTau, ng.NhaGa, t.loaiTau" +
						"FROM Tau" +
						"INNER JOIN NhaGa ng ON t.maNhaGa = ng.maNhaGa";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return dsT;
	}
}
