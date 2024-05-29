package DAO;

import entity.*;
import connectDB.ConnectDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VeDAO {

    public Ve layTheoMa(String id) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "SELECT * FROM Ve v " +
                         "LEFT JOIN KhachHang kh ON v.MaKH = kh.MaKH " +
                         "LEFT JOIN NhanVien nv ON v.MaNV = nv.MaNV " +
                         "WHERE v.MaVe=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maVe = rs.getString("MaVe");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                Date ngayDi = rs.getDate("NgayDi");
                String gioDi = rs.getString("GioDi");
                Date ngayVe = rs.getDate("NgayVe");
                String gioVe = rs.getString("GioVe");
                String toa = rs.getString("Toa");
                String loaiCho = rs.getString("LoaiCho");
                int choNgoi = rs.getInt("ChoNgoi");
                float giaVe = rs.getFloat("GiaVe");

                // Khach Hang
                String maKH = rs.getString("MaKH");
                KhachHang khachHang = new KhachHang(maKH);

                // Nhan Vien
                String maNV = rs.getString("MaNV");
                NhanVien nhanVien = new NhanVien(maNV);

                return new Ve(maVe, gaDi, gaDen, ngayDi, gioDi, ngayVe, gioVe, toa, loaiCho, choNgoi, giaVe, khachHang, nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Ve> layHet() {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        List<Ve> dsV = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Ve v " +
                         "LEFT JOIN KhachHang kh ON v.MaKH = kh.MaKH " +
                         "LEFT JOIN NhanVien nv ON v.MaNV = nv.MaNV";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String maVe = rs.getString("MaVe");
                String gaDi = rs.getString("GaDi");
                String gaDen = rs.getString("GaDen");
                Date ngayDi = rs.getDate("NgayDi");
                String gioDi = rs.getString("GioDi");
                Date ngayVe = rs.getDate("NgayVe");
                String gioVe = rs.getString("GioVe");
                String toa = rs.getString("Toa");
                String loaiCho = rs.getString("LoaiCho");
                int choNgoi = rs.getInt("ChoNgoi");
                float giaVe = rs.getFloat("GiaVe");

                // Khach Hang
                String maKH = rs.getString("MaKH");
                KhachHang khachHang = new KhachHang(maKH);

                // Nhan Vien
                String maNV = rs.getString("MaNV");
                NhanVien nhanVien = new NhanVien(maNV);

                dsV.add(new Ve(maVe, gaDi, gaDen, ngayDi, gioDi, ngayVe, gioVe, toa, loaiCho, choNgoi, giaVe, khachHang, nhanVien));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsV;
    }

    public boolean them(Ve v) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "INSERT INTO Ve (MaVe, GaDi, GaDen, NgayDi, GioDi, NgayVe, GioVe, Toa, LoaiCho, ChoNgoi, GiaVe, MaKH, MaNV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, v.getMaVe().trim());
            statement.setString(2, v.getGaDi().trim());
            statement.setString(3, v.getGaDen().trim());
            statement.setDate(4, v.getNgayDi());
            statement.setString(5, v.getGioDi().trim());
            statement.setDate(6, v.getNgayVe());
            statement.setString(7, v.getGioVe().trim());
            statement.setString(8, v.getToa().trim());
            statement.setString(9, v.getLoaiCho().trim());
            statement.setInt(10, v.getChoNgoi());
            statement.setFloat(11, v.getGiaVe());
            statement.setString(12, v.getKhachHang().getMaKH().trim());
            statement.setString(13, v.getNhanVien().getMaNV().trim());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoa(String id) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "DELETE FROM Ve WHERE MaVe=?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean sua(Ve v) {
        ConnectDB.getInstance();
        Connection conn = ConnectDB.getConnection();
        try {
            String sql = "UPDATE Ve SET GaDi=?, GaDen=?, NgayDi=?, GioDi=?, NgayVe=?, GioVe=?, Toa=?, LoaiCho=?, ChoNgoi=?, GiaVe=?, MaKH=?, MaNV=? WHERE MaVe=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, v.getGaDi().trim());
            stmt.setString(2, v.getGaDen().trim());
            stmt.setDate(3, v.getNgayDi());
            stmt.setString(4, v.getGioDi().trim());
            stmt.setDate(5, v.getNgayVe());
            stmt.setString(6, v.getGioVe().trim());
            stmt.setString(7, v.getToa().trim());
            stmt.setString(8, v.getLoaiCho().trim());
            stmt.setInt(9, v.getChoNgoi());
            stmt.setFloat(10, v.getGiaVe());
            stmt.setString(11, v.getKhachHang().getMaKH().trim());
            stmt.setString(12, v.getNhanVien().getMaNV().trim());
            stmt.setString(13, v.getMaVe().trim());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
