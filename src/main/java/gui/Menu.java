package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.*;

public class Menu extends JFrame {
    private JPanel panelChucNang;
    private JLabel lblBg;
    private JMenuBar jMenuBar1;
    private JMenu Home;
    private JMenu NV;
    private JMenuItem timNV;
    private JMenuItem ThongTinNV;
    private JMenu Ve;
    private JMenuItem DatVe;
    private JMenuItem ThongTInVe;
    private JMenu Tau;
    private JMenuItem ThongTinTAu;
    private JMenuItem TimKiemTau;
    private JMenu ChuyenTau;
    private JMenuItem ThongTInChuyenTau;
    private JMenu Tk;
    private JMenuItem DangNhap;
    private DangNhap dangNhapPanel;
    private JMenuItem Dangxuat;
    private JMenu NhaGa;
    private JMenuItem ThongTinNhaGa;

    

    public Menu() {
        initComponents();
    }

    private void initComponents() {
        panelChucNang = new JPanel();
        lblBg = new JLabel();
        jMenuBar1 = new JMenuBar();
        Home = new JMenu();
        NV = new JMenu();
        timNV = new JMenuItem();
        ThongTinNV = new JMenuItem();
        Ve = new JMenu();
        DatVe = new JMenuItem();
        ThongTInVe = new JMenuItem();
        Tau = new JMenu();
        ThongTinTAu = new JMenuItem();
        TimKiemTau = new JMenuItem();
        ChuyenTau = new JMenu();
        ThongTInChuyenTau = new JMenuItem();
        Tk = new JMenu();
        DangNhap = new JMenuItem();
        Dangxuat = new JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1150, 600));

        panelChucNang.setLayout(new CardLayout());

        lblBg.setIcon(new ImageIcon(getClass().getResource("/images/dangnhapimg.jpg")));
        panelChucNang.add(lblBg, "card2");

        jMenuBar1.setFont(new Font("Segoe UI", 0, 18));
        jMenuBar1.setMargin(new Insets(0, 0, 0, 30));

        Home.setText("Home");
        Home.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        jMenuBar1.add(Home);

        NV.setText("Nhân viên");
        NV.setMargin(new Insets(10, 10, 10, 10));
        NV.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                NVMouseEntered(evt);
            }
        });
        NV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                NVActionPerformed(evt);
            }
        });

        timNV.setIcon(new ImageIcon(getClass().getResource("/images/magnifier.png")));
        timNV.setText("Tìm nhân viên");
        timNV.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                timNVMouseClicked(evt);
            }
        });
        timNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timNVActionPerformed(evt);
            }
        });
        NV.add(timNV);

        ThongTinNV.setIcon(new ImageIcon(getClass().getResource("/images/man.png")));
        ThongTinNV.setText("Thông tin nhân viên");
        ThongTinNV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					ThongTinNVActionPerformed(evt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
        NV.add(ThongTinNV);

        jMenuBar1.add(NV);

        Ve.setText("Vé");
        
        
        DatVe.setIcon(new ImageIcon(getClass().getResource("/images/ticket.png")));
        DatVe.setText("Đặt vé");
        DatVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DatVeActionPerformed(evt);
            }
        });
        Ve.add(DatVe);

        ThongTInVe.setIcon(new ImageIcon(getClass().getResource("/images/tickets.png")));
        ThongTInVe.setText("Thông tin vé");
        ThongTInVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ThongTInVeActionPerformed(evt);
            }
        });
        Ve.add(ThongTInVe);

        jMenuBar1.add(Ve);

        Tau.setText("Tàu");

        ThongTinTAu.setIcon(new ImageIcon(getClass().getResource("/images/train1.png")));
        ThongTinTAu.setText("Thông tin tàu");
        ThongTinTAu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ThongTinTAuActionPerformed(evt);
            }
        });
        Tau.add(ThongTinTAu);

        TimKiemTau.setIcon(new ImageIcon(getClass().getResource("/images/magnifier.png")));
        TimKiemTau.setText("Tìm kiếm tàu");
        TimKiemTau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timKiemTauActionPerformed(evt);
            }
        });
        Tau.add(TimKiemTau);

        jMenuBar1.add(Tau);

        ChuyenTau.setText("Chuyến Tàu");

        ThongTInChuyenTau.setIcon(new ImageIcon(getClass().getResource("/images/train.png")));
        ThongTInChuyenTau.setText("Thông tin chuyến tàu");
        ThongTInChuyenTau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ThongTInChuyenTauActionPerformed(evt);
            }
        });
        ChuyenTau.add(ThongTInChuyenTau);

        jMenuBar1.add(ChuyenTau);

        Tk.setText("Tài khoản");

        DangNhap.setText("Đăng nhập");
        DangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DangNhapActionPerformed(evt);
            }
        });
        Tk.add(DangNhap);

        Dangxuat.setText("Đăng xuất");
        Dangxuat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DangxuatActionPerformed(evt);
            }
        });
        
        NhaGa = new JMenu("Nhà ga");
        jMenuBar1.add(NhaGa);
        
        ThongTinNhaGa = new JMenuItem("Thông tin nhà ga");
        ThongTinNhaGa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	try {
				ThongTinNhaGaActionPerformed(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	}
        });
        NhaGa.add(ThongTinNhaGa);
        Tk.add(Dangxuat);

        jMenuBar1.add(Tk);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelChucNang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelChucNang, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelChucNang.getAccessibleContext().setAccessibleName("Panel");

        pack();
    }

    private void NVMouseEntered(MouseEvent evt) {//GEN-FIRST:event_NVMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_NVMouseEntered

    private void ThongTinNhaGaActionPerformed(ActionEvent evt) throws SQLException {
    	panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        ThongTinNG thongTinNG = new ThongTinNG();
        panelChucNang.add(thongTinNG);
        thongTinNG.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }
    
    private void ThongTinNVActionPerformed(ActionEvent evt) throws SQLException {//GEN-FIRST:event_ThongTinNVActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        ThongTinNV thongTinNV = new ThongTinNV();
        panelChucNang.add(thongTinNV);
        thongTinNV.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_ThongTinNVActionPerformed

    private void DatVeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_DatVeActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        TimKiemVe timVe = new TimKiemVe();
        panelChucNang.add(timVe);
        timVe.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_DatVeActionPerformed

    private void DangxuatActionPerformed(ActionEvent evt) {//GEN-FIRST:event_DangxuatActionPerformed
        this.dispose();
    }//GEN-LAST:event_DangxuatActionPerformed

    private void ThongTInVeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ThongTInVeActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        ThongTinVe thongTinVe = new ThongTinVe();
        panelChucNang.add(thongTinVe);
        thongTinVe.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();           
    }//GEN-LAST:event_ThongTInVeActionPerformed

    private void HomeActionPerformed(ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
     
    }//GEN-LAST:event_HomeActionPerformed

    private void DangNhapActionPerformed(ActionEvent evt) {
        if (dangNhapPanel == null) {
            dangNhapPanel = new DangNhap();
            panelChucNang.add(dangNhapPanel);
        }
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        dangNhapPanel.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }

    private void ThongTinTAuActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ThongTinTAuActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        ThongTinTau thongTintau = new ThongTinTau();
        panelChucNang.add(thongTintau);
        thongTintau.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_ThongTinTAuActionPerformed

    private void ThongTInChuyenTauActionPerformed(ActionEvent evt) {//GEN-FIRST:event_ThongTInChuyenTauActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        ThongTinChuyenTau thongtinchuyentau = new ThongTinChuyenTau();
        panelChucNang.add(thongtinchuyentau);
        thongtinchuyentau.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_ThongTInChuyenTauActionPerformed

    private void HomeMouseClicked(MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked
        panelChucNang.removeAll();
        panelChucNang.add(lblBg);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_HomeMouseClicked

    private void NVActionPerformed(ActionEvent evt) {//GEN-FIRST:event_NVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NVActionPerformed

    private void timNVActionPerformed(ActionEvent evt) {//GEN-FIRST:event_timNVActionPerformed
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        TimKiemNV TimKiemNV = new TimKiemNV();
        panelChucNang.add(TimKiemNV);
        TimKiemNV.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }//GEN-LAST:event_timNVActionPerformed

    private void timNVMouseClicked(MouseEvent evt) {//GEN-FIRST:event_timNVMouseClicked
        // TODO add your handling code here:
    }

    private void timKiemTauActionPerformed(ActionEvent evt) {
        panelChucNang.removeAll();
        panelChucNang.repaint();
        panelChucNang.revalidate();
        TimKiemTau timKiemTau = new TimKiemTau();
        panelChucNang.add(timKiemTau);
        timKiemTau.setVisible(true);
        panelChucNang.repaint();
        panelChucNang.revalidate();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
}
