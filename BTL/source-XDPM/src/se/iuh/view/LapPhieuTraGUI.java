package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.ChiTietPhieuThueController;
import se.iuh.controller.DatTruocController;
import se.iuh.controller.DiaController;
import se.iuh.controller.KhachHangController;
import se.iuh.controller.NhanVienController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.controller.PhieuTraController;
import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.ChiTietPhieuThue;
import se.iuh.model.DatTruoc;
import se.iuh.model.Dia;
import se.iuh.model.KhachHang;
import se.iuh.model.PhiTreHan;
import se.iuh.model.PhieuTra;
import se.iuh.model.TuaDia;
import se.iuh.utility.GlobalConfig;

public class LapPhieuTraGUI extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMaDia;
	private JTextField txtHanTraDia;
	private JTextField txtNgayHomNay;
	private JTextField txtPhiTreHan;
	private JButton btnKiemTraMaDia;
	private JButton btnTraDia;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private ChiTietPhieuThueController ctptController = new ChiTietPhieuThueController();
	private PhiTreHanController phiTreHanController = new PhiTreHanController();
	private PhieuTraController phieuTraController = new PhieuTraController();
	private DatTruocController datTruocController = new DatTruocController();
	private TuaDiaController tuaDiaController = new TuaDiaController();
	private DiaController diaController = new DiaController();
	private KhachHangController khachHangController = new KhachHangController();
	private boolean coTreHan = false;
	private ChiTietPhieuThue ctptDangChon;
	private double tongTien;

	/**
	 * Create the frame.
	 */
	public LapPhieuTraGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setTitle("Lập phiếu trả đĩa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JLabel lblNhapMaDia = new JLabel("Nhập ID đĩa/disk");
		panelNorth.add(lblNhapMaDia);

		txtMaDia = new JTextField();
		panelNorth.add(txtMaDia, BorderLayout.NORTH);
		txtMaDia.setColumns(25);

		btnKiemTraMaDia = new JButton("Kiểm tra ID");
		btnKiemTraMaDia.addActionListener(this);
		panelNorth.add(btnKiemTraMaDia, BorderLayout.NORTH);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);

		Box verticalBox = Box.createVerticalBox();
		panelCenter.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JLabel lblHanTraDia = new JLabel("Hạn trả đĩa:");
		horizontalBox.add(lblHanTraDia);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_1);

		txtHanTraDia = new JTextField();
		txtHanTraDia.setEditable(false);
		horizontalBox.add(txtHanTraDia);
		txtHanTraDia.setColumns(15);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		JLabel lblNgayHomNay = new JLabel("Ngày hôm nay:");
		horizontalBox.add(lblNgayHomNay);

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_2);

		txtNgayHomNay = new JTextField();
		txtNgayHomNay.setEditable(false);
		horizontalBox.add(txtNgayHomNay);
		txtNgayHomNay.setColumns(15);

		Component verticalStrut = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		JLabel lblPhiTreHan = new JLabel("Phí trễ hạn: ");
		horizontalBox_1.add(lblPhiTreHan);

		txtPhiTreHan = new JTextField();
		txtPhiTreHan.setEditable(false);
		horizontalBox_1.add(txtPhiTreHan);
		txtPhiTreHan.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		btnTraDia = new JButton("Trả đĩa");
		btnTraDia.addActionListener(this);
		horizontalBox_2.add(btnTraDia);

		ctptDangChon = null;
		tongTien = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnKiemTraMaDia) && ctptDangChon == null) {
			List<ChiTietPhieuThue> list = ctptController.getCTPTTChuaTraTheoMaDia(txtMaDia.getText(), session);
			ChiTietPhieuThue ls = null;
			if (list != null && list.size() > 0) {
				btnTraDia.setEnabled(true);
				btnKiemTraMaDia.setText("Nhấn để chọn mã đĩa khác");
				txtMaDia.setEditable(false);
				ls = list.get(0);
				ctptDangChon = ls;
				List<PhiTreHan> listPTH = phiTreHanController
						.getListPhiTreHanChuaTraTheoMaKH(ls.getPhieuThue().getKhachHang().getMaKH(), session);
				if (listPTH != null) {
					if (listPTH.size() > 0) {
						int option = JOptionPane.showConfirmDialog(this,
								"Khách hàng này đang nợ phí trễ hạn, muốn chuyển qua thanh toán?", "Thông báo",
								JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							for (PhiTreHan phiTreHan : listPTH) {
								tongTien += phiTreHan.getPhiTreHan();
							}
							GhiNhanTraPhiTreHanGUI frame = new GhiNhanTraPhiTreHanGUI(listPTH,String.valueOf(tongTien), (Frame) SwingUtilities.getWindowAncestor(this));
							frame.setVisible(true);
						}
					}
				}

				txtHanTraDia.setText(ls.getHanTraDia().toString());
				txtNgayHomNay.setText(LocalDate.now().toString());
				if (Date.valueOf(LocalDate.now()).after(ls.getHanTraDia())) {
					coTreHan = true;
					txtPhiTreHan.setText(String.format("%.0f", ls.getPhiTreHan()));
					btnTraDia.setEnabled(true);
				} else {
					coTreHan = false;
					txtPhiTreHan.setText("Không áp dụng vì chưa trễ hạn");
					btnTraDia.setEnabled(true);
				}
			} else {
				ctptDangChon = null;
				JOptionPane.showMessageDialog(this, "Không tìm thấy đĩa trong các đĩa đang cho thuê");
				btnTraDia.setEnabled(false);
			}
		} else if (e.getSource().equals(btnKiemTraMaDia) && ctptDangChon != null) {
			btnKiemTraMaDia.setText("Kiểm tra ID");
			ctptDangChon = null;
			txtMaDia.setEditable(true);
			txtHanTraDia.setText("");
			txtNgayHomNay.setText("");
			txtPhiTreHan.setText("");
		} else if (e.getSource().equals(btnTraDia)) {
			if (ctptDangChon != null) {
				LocalDateTime nowTime = LocalDateTime.now();
				String maPhieuTra = "PT" + nowTime.getYear() + String.format("%02d", nowTime.getMonthValue())
						+ String.format("%02d", nowTime.getDayOfMonth()) + String.format("%02d", nowTime.getHour())
						+ String.format("%02d", nowTime.getMinute()) + String.format("%02d", nowTime.getSecond());
				
				PhieuTra phieuTra = new PhieuTra(maPhieuTra, ctptDangChon.getDiaThue(), ctptDangChon.getPhieuThue(),
						new NhanVienController().getNhanVien(GlobalConfig.maNV, session), Date.valueOf(LocalDate.now()));

				phieuTraController.insert(phieuTra, session);
				if (coTreHan) {
					String maPhiTreHan = "TH" + nowTime.getYear() + String.format("%02d", nowTime.getMonthValue())
					+ String.format("%02d", nowTime.getDayOfMonth()) + String.format("%02d", nowTime.getHour())
					+ String.format("%02d", nowTime.getMinute()) + String.format("%02d", nowTime.getSecond());
					PhiTreHan pth = new PhiTreHan(maPhiTreHan, phieuTra, ctptDangChon.getPhiTreHan());
					
					int option = JOptionPane.showConfirmDialog(this,
							"Bạn có muốn trả luôn phí trễ hạn không", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						JOptionPane.showMessageDialog(this, "Trả đĩa thành công");
						Dia dia = diaController.getDia(txtMaDia.getText().toString(), session);
						PhieuTraController phieuTraController = new PhieuTraController();
						phieuTraController.updateSauTraDia(dia);
					}else {
						phiTreHanController.insert(pth, session);
						JOptionPane.showMessageDialog(this, "Trả đĩa thành công. Thông tin phí trễ hạn đã được lưu");
					}
					
					// gan trang thai dia la on-hold
					Dia dia = diaController.getDia(txtMaDia.getText().toString(), session);
					TuaDia tuaDia = tuaDiaController.getTuaDia(dia.getTuaDia().getMaTua(), session);
					List<DatTruoc> listDT = new ArrayList<DatTruoc>();
					listDT = datTruocController.getDatTruocTheoMaTua(tuaDia.getMaTua(), session);
					if (listDT != null) {
						if (listDT.size() > 0) {
							phieuTraController.updateOnHold(dia);
						}
					}
					this.dispose();
				}
				else {
					JOptionPane.showMessageDialog(this, "Trả đĩa thành công");
					
					Dia dia = diaController.getDia(txtMaDia.getText().toString(), session);
					PhieuTraController phieuTraController = new PhieuTraController();
					phieuTraController.updateSauTraDia(dia);
					
					// gan trang thai dia la on-hold
					TuaDia tuaDia = tuaDiaController.getTuaDia(dia.getTuaDia().getMaTua(), session);
					List<DatTruoc> listDT = new ArrayList<DatTruoc>();
					listDT = datTruocController.getDatTruocTheoMaTua(tuaDia.getMaTua(), session);
					if (listDT != null) {
						if (listDT.size() > 0) {
							phieuTraController.updateOnHold(dia);
						}
					}
					btnTraDia.setEnabled(false);
					btnKiemTraMaDia.setText("Kiểm tra ID");
					txtMaDia.setText("");
					txtMaDia.setEditable(true);
					txtHanTraDia.setText("");
					txtNgayHomNay.setText("");
					txtPhiTreHan.setText("");
					ctptDangChon = null;
				}
			}
		}
	}

}
