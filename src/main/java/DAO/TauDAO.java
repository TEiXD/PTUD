package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                String loaiTau = rs.getString(3);
                NhaGa ng = new NhaGa(maNhaGa);
                Tau t = new Tau(maTau, ng, loaiTau);
                dsT.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsT;
    }
    
 // Thêm tàu
    public boolean addTau(Tau tau) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        String SQL = "INSERT INTO Tau (MaTau, MaNhaGa, LoaiTau) VALUES (?, ?, ?)";
        int n = 0;
        try {
            // Kiểm tra xem MaNhaGa đã tồn tại trong bảng NhaGa hay không
            if (kiemTraMaNhaGaTonTai(conn, tau.getNhaGa().getMaNhaGa())) {
                st = conn.prepareStatement(SQL);
                st.setString(1, tau.getMaTau().trim());
                st.setString(2, tau.getNhaGa().getMaNhaGa().trim());
                st.setString(3, tau.getLoaiTau().trim());
                n = st.executeUpdate();
            } else {
                System.out.println("MaNhaGa không tồn tại trong bảng NhaGa.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Kiểm tra xem mã nhà ga đã tồn tại trong bảng NhaGa hay chưa
    private boolean kiemTraMaNhaGaTonTai(Connection conn, String maNhaGa) throws SQLException {
        String SQL = "SELECT COUNT(*) FROM NhaGa WHERE MaNhaGa = ?";
        PreparedStatement stmt = conn.prepareStatement(SQL);
        stmt.setString(1, maNhaGa);
        ResultSet rs = stmt.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        return count > 0;
    }


    // Xóa tàu
    public boolean xoaTau(String maTau) {
    	ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        PreparedStatement st = null;
        int n=0;
		try {
			String SQL =  "DELETE FROM Tau WHERE MaTau = ?";
			st = conn.prepareStatement(SQL);
            st.setString(1, maTau);
           
            n = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return n>0;
    }

    // Sửa tàu
    public boolean suaTau(Tau tau) {
    	ConnectDB.getInstance();
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = null;
		int n=0;
		try {
			String SQL = "UPDATE Tau SET MaNhaGa = ?, LoaiTau = ? WHERE MaTau = ?";
			st = conn.prepareStatement(SQL);
            st.setString(1, tau.getNhaGa().getMaNhaGa().trim());
            st.setString(2, tau.getLoaiTau().trim());
            st.setString(3, tau.getMaTau().trim());
           
            n = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return n>0;
    }
}
