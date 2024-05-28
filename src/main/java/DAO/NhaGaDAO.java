package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaGa;

public class NhaGaDAO {
    public List<NhaGa> layThongTin() {
        List<NhaGa> dsNG = new ArrayList<>();
        try {
            ConnectDB.getInstance().connect();
            Connection conn = ConnectDB.getConnection();
            String SQL = "SELECT MaNhaGa, TenNhaGa, DiaDiem FROM NhaGa";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                String maNhaGa = rs.getString("MaNhaGa");
                String tenNhaGa = rs.getString("TenNhaGa");
                String diaDiem = rs.getString("DiaDiem");
                NhaGa nhaGa = new NhaGa(maNhaGa, tenNhaGa, diaDiem);
                dsNG.add(nhaGa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsNG;
    }
}

