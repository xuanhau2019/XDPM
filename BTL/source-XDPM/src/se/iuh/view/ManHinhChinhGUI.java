package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import se.iuh.utility.GlobalConfig;

public class ManHinhChinhGUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1791912629040857293L;
	private JPanel contentPane;
	private JMenuItem mntmThemDia;
	private JMenuItem mntmXoaDia;
	private JMenuItem mntmThongKeKhachHangTatCa;
	private JMenuItem mntmThongKeKhachHangQuaHan;
	private JMenuItem mntmThongKeKhachHangNoPhi;
	private JMenuItem mntmThongKeTuaDia;
	private JMenuItem mntmThemTuaDia;
	private JMenuItem mntmXoaTuaDia;
	private JMenuItem mntmLapPhieuTra;
	private JMenuItem mntmLapPhieuThue;
	private JMenuItem mntmXemPTHKhachHang;
	private JMenuItem mntmThemTTDatTruoc;
	private JMenuItem mntmHuyPhiTraMuon;
	private JMenuItem mntmDangXuat;

	
	private JMenuItem mntmThemKhachHang;
	private JMenuItem mntmSuaKhachHang;
	private JMenuItem mntmHuyDatTruoc;
	private JMenuItem mntmThongBao;
	/**
	 * Create the frame.
	 */
	public ManHinhChinhGUI() {
		setSize(new Dimension(720, 440));
		setPreferredSize(new Dimension(800, 450));
		setTitle("Quản lý thuê băng đĩa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnThueDia = new JMenu("Thuê đĩa");
		menuBar.add(mnThueDia);

		mntmLapPhieuThue = new JMenuItem("Lập phiếu thuê");
		mntmLapPhieuThue.addActionListener(this);
		mnThueDia.add(mntmLapPhieuThue);

		mntmLapPhieuTra = new JMenuItem("Lập phiếu trả");
		mntmLapPhieuTra.addActionListener(this);
		mnThueDia.add(mntmLapPhieuTra);

		mntmHuyPhiTraMuon = new JMenuItem("Hủy phí trả muộn");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmHuyPhiTraMuon.addActionListener(this);
			mnThueDia.add(mntmHuyPhiTraMuon);
		}

		JMenu mnDatTruoc = new JMenu("Đặt trước");
		menuBar.add(mnDatTruoc);

		mntmThemTTDatTruoc = new JMenuItem("Thêm thông tin đặt trước");
		mntmThemTTDatTruoc.addActionListener(this);
		mnDatTruoc.add(mntmThemTTDatTruoc);

		
		JMenu mnKhachHang = new JMenu("Khách hàng");
		menuBar.add(mnKhachHang);

		mntmXemPTHKhachHang = new JMenuItem("Xem phí trễ hạn");
		mntmXemPTHKhachHang.addActionListener(this);
		mnKhachHang.add(mntmXemPTHKhachHang);

		JMenu mnDia = new JMenu("Đĩa");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			menuBar.add(mnDia);
		}

		mntmThemDia = new JMenuItem("Thêm đĩa");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThemDia.addActionListener(this);
			mnDia.add(mntmThemDia);
		}

		mntmXoaDia = new JMenuItem("Xem danh sách / xóa đĩa");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmXoaDia.addActionListener(this);
			mnDia.add(mntmXoaDia);
		}

		
		mntmThongBao = new JMenuItem("Thông báo tình trạng đĩa");
		mntmThongBao.addActionListener(this);
		mnDia.add(mntmThongBao);
		
		JMenu mnTuaDia = new JMenu("Tựa đĩa");
		menuBar.add(mnTuaDia);

		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThemTuaDia = new JMenuItem("Thêm tựa đĩa");
			mntmThemTuaDia.addActionListener(this);
			mnTuaDia.add(mntmThemTuaDia);

			mntmXoaTuaDia = new JMenuItem("Quản lý / xóa tựa đĩa");
			mntmXoaTuaDia.addActionListener(this);
			mnTuaDia.add(mntmXoaTuaDia);
		}

		JMenuItem mntmYeuCauTuaDiaMoi = new JMenuItem("Yêu cầu thêm tựa đĩa mới");
		mnTuaDia.add(mntmYeuCauTuaDiaMoi);

		JMenu mnThongKe = new JMenu("Thống kê");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			menuBar.add(mnThongKe);
		}

		mntmThongKeKhachHangTatCa = new JMenuItem("Thống kê tất cả khách hàng");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThongKeKhachHangTatCa.addActionListener(this);
			mnThongKe.add(mntmThongKeKhachHangTatCa);
		}

		mntmThongKeKhachHangQuaHan = new JMenuItem("Thống kê khách hàng đang mượn quá hạn");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThongKeKhachHangQuaHan.addActionListener(this);
			mnThongKe.add(mntmThongKeKhachHangQuaHan);
		}

		mntmThongKeKhachHangNoPhi = new JMenuItem("Thống kê khách hàng nợ phí");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThongKeKhachHangNoPhi.addActionListener(this);
			mnThongKe.add(mntmThongKeKhachHangNoPhi);
		}

		mntmThongKeTuaDia = new JMenuItem("Thống kê tựa đĩa");
		if (GlobalConfig.loaiUser == GlobalConfig.USER_ADMIN) {
			mntmThongKeTuaDia.addActionListener(this);
			mnThongKe.add(mntmThongKeTuaDia);
		}

		JMenu mnTaiKhoan = new JMenu("Tài khoản");
		menuBar.add(mnTaiKhoan);

		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.addActionListener(this);
		mnTaiKhoan.add(mntmDangXuat);
		
		
		mntmThemKhachHang = new JMenuItem("Thêm khách hàng");
		mntmThemKhachHang.addActionListener(this);
		mnKhachHang.add(mntmThemKhachHang);
		
		mntmSuaKhachHang = new JMenuItem("Sửa thông tin khách hàng");
		mntmSuaKhachHang.addActionListener(this);
		mnKhachHang.add(mntmSuaKhachHang);
		

		mntmHuyDatTruoc = new JMenuItem("Huỷ đặt trước");
		mntmHuyDatTruoc.addActionListener(this);
		mnDatTruoc.add(mntmHuyDatTruoc);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(mntmThemDia)) {
			ThemDiaGUI themDiaGui = new ThemDiaGUI(this);
			themDiaGui.setVisible(true);
		} else if (e.getSource().equals(mntmXoaDia)) {
			XoaDiaGUI xoaDiaGui = new XoaDiaGUI(this);
			xoaDiaGui.setVisible(true);
		} else if (e.getSource().equals(mntmThongKeKhachHangTatCa)) {
			ThongKeKhachHangGUI frame = new ThongKeKhachHangGUI(this, GlobalConfig.THONGKE_TATCA);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmThongKeKhachHangQuaHan)) {
			ThongKeKhachHangGUI frame = new ThongKeKhachHangGUI(this, GlobalConfig.THONGKE_TREHAN);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmThongKeKhachHangNoPhi)) {
			ThongKeKhachHangGUI frame = new ThongKeKhachHangGUI(this, GlobalConfig.THONGKE_NOPHI);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmThongKeTuaDia)) {
			ThongKeTuaDiaGUI frame = new ThongKeTuaDiaGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmThemTuaDia)) {
			ThemTuaJDialog frame = new ThemTuaJDialog(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmXoaTuaDia)) {
			QuanLyTua frame = new QuanLyTua(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmLapPhieuTra)) {
			LapPhieuTraGUI frame = new LapPhieuTraGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmLapPhieuThue)) {
			LapPhieuThueGUI frame = new LapPhieuThueGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmXemPTHKhachHang)) {
			HienThiPhiTreHanGUI frame = new HienThiPhiTreHanGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmThemTTDatTruoc)) {
			ThemDatTruocGUI frame = new ThemDatTruocGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmHuyPhiTraMuon)) {
			HuyPhiTreHanGUI frame = new HuyPhiTreHanGUI(this);
			frame.setVisible(true);
		} else if (e.getSource().equals(mntmDangXuat)) {
			dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						DangNhapGUI frame = new DangNhapGUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		else if(e.getSource().equals(mntmThemKhachHang)) {
			ThemKhachHangGUI frame = new ThemKhachHangGUI(this);
			frame.setVisible(true);
		}
		else if(e.getSource().equals(mntmSuaKhachHang)) {
			SuaKhachHangGUI frame = new SuaKhachHangGUI(this);
			frame.setVisible(true);
		}
		else if(e.getSource().equals(mntmHuyDatTruoc)) {
			HuyDatTruocGUI frame = new HuyDatTruocGUI(this);
			frame.setVisible(true);
		}
		else if(e.getSource().equals(mntmThongBao)) {
			ThongBaoTinhTrangDiaGUI frame = new ThongBaoTinhTrangDiaGUI(this);
			frame.setVisible(true);
		}
	}

}
