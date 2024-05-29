package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.Ghe;
import entity.Toa;

public class GheDAO {
	public List<Ghe> laySlotTheoMaToaTauVaDsChoNgoi(String maToaTau, List<Integer> dsCho) {
		ConnectDB.getInstance().connect();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        List<Ghe> dsSlot = new ArrayList<>();

        if (dsSlot.isEmpty())
            return null;

        StringBuilder dsChoParam = new StringBuilder(dsCho.toString());
        dsChoParam.setCharAt(0,'(');
        dsChoParam.setCharAt(dsCho.toString().length() - 1,')');
        try {
			String SQL = "SELECT * FROM Slot s" +
						"LEFT JOIN Toa toa ON s.MaToa = toa.MaToa" +
						"WHERE s.SoSlot IN" + dsChoParam +
						"ORDER BY s.SoSlot ASC";
			st = conn.prepareStatement(SQL);
			st.setString(1, maToaTau);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
                String maSlot = rs.getString(1);
                String maToa = rs.getString(2);
                int tinhTrang = rs.getInt(3);
                int soSlot = rs.getInt(4);
                
                dsSlot.add(new Ghe(maSlot, soSlot, new Toa(maToa), tinhTrang));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return dsSlot;
	}
	
	
	public boolean capNhatHetSlot(Connection conn,  List<Ghe> dsChoDaChon) throws SQLException {
        PreparedStatement st = null;
        for (Ghe slot : dsChoDaChon) {
            String sql = "UPDATE Slot s" +
                    	"SET s.TinhTrang = 0" +
                    	"WHERE s.MaSlot = ?";
            st =  conn.prepareStatement(sql);
            st.setString(1, slot.getMaSlot());
            if (st.executeUpdate() > 0)
                continue;
            else return false;
        }
        return true;
    }
	
	
	public boolean capNhatConSlot(Connection conn,  List<Ghe> dsChoDaChon) throws SQLException {
        for (Ghe slot : dsChoDaChon) {
            String sql = "UPDATE Slot s" +
                    "SET s.TinhTrang = 1" +
                    "WHERE s.MaSlot = ?";
            PreparedStatement pst =  conn.prepareStatement(sql);
            pst.setString(1, slot.getMaSlot());

            if (pst.executeUpdate() > 0)
                continue;
            else return false;
        }
        return true;
    }

	public List<Ghe> layTinhTrangChoNgoiTheoToaTau(String maToaTau) {
		ConnectDB.getInstance().connect();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        List<Ghe> dsSlot = new ArrayList<>();
        try {
            String sql = "SELECT slot.MaSlot, slot.SoSlot, slot.TinhTrang, slot.MaToa FROM Slot slot" +
                    "WHERE khoang.MaToa = ?" +
                    "ORDER BY slot.SoSlot ASC";

            st = conn.prepareStatement(sql);
            st.setString(1, maToaTau);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSlot = rs.getString("slot.MaSlot");
                int soSlot = rs.getInt("slot.SoSlot");
                int tinhTrang = rs.getInt("slot.TinhTrang");
                String maKhoang = rs.getString("slot.MaKhoang");
                dsSlot.add(new Ghe( maSlot,  soSlot,  new Toa(maToaTau),  tinhTrang));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsSlot;
    }
}
