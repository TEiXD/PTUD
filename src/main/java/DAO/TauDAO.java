package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaGa;
import entity.Tau;

public class TauDAO {
    public List<Tau> layThongTin() {
        List<Tau> dsT = new ArrayList<>();
        try {
            Connection conn = ConnectDB.getInstance().connect();
            String SQL = "SELECT t.MaTau, ng.MaNhaGa, t.LoaiTau " +
                         "FROM Tau t " +
                         "INNER JOIN NhaGa ng ON t.MaNhaGa = ng.MaNhaGa";
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	String maTau = rs.getString(1);
                String maNhaGa = rs.getString(2);
                String LoaiTau = rs.getString(3);
                NhaGa ng = new NhaGa(maNhaGa);
                Tau t = new Tau(maTau, ng, LoaiTau);
                dsT.add(t);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsT;
    }
}

