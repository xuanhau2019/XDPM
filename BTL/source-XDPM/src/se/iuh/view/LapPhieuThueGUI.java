package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.DatTruocController;
import se.iuh.controller.NhanVienController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.controller.PhieuThueController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.DatTruoc;
import se.iuh.model.Dia;
import se.iuh.model.KhachHang;
import se.iuh.model.NhanVien;
import se.iuh.model.PhiTreHan;
import se.iuh.utility.GlobalConfig;

public class LapPhieuThueGUI extends JDialog implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtMaDia;
	private JTextField txtMaDia_1;
	private JButton btnKiemTraMaDia;
	private JButton btnKiemTraMaKhachHang;
	private JButton btnThemDia;
	private JTextField txtMaKhachHang;
	private JTextField txtMaKhachHang_1;
	private JTextField txtTenKhachHang;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtTenTuaDe;
	private JTextField txtNgayHienTai;
	private JTextField txtThoiHanThue;
	private JTextField txtPhiTreHan;
	private JTextField txtPhiThue;
	private JTable tableDSDia;
	private DefaultTableModel tmDia;
	private PhieuThueController phieuThueController = new PhieuThueController();
	private PhiTreHanController phiTreHanController = new PhiTreHanController();
	private DatTruocController datTruocController = new DatTruocController();
	private Dia dia;
	private DatTruoc datTruoc;
	private int stt = 1;
	private JLabel lblTongTien;
	private double tongTien = 0;
	private JButton btnThueDia;
	private Date outDate;
	private boolean flag1 = true;
	private boolean flag2 = true;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();

	public LapPhieuThueGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setTitle("Lập phiếu thuê đĩa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JLabel lblMaKhachHang = new JLabel("Nhập ID khách hàng:");
		panelNorth.add(lblMaKhachHang);

		txtMaKhachHang = new JTextField();
		panelNorth.add(txtMaKhachHang, BorderLayout.NORTH);
		txtMaKhachHang.setColumns(25);

		btnKiemTraMaKhachHang = new JButton("Kiểm tra ID khách hàng");
		btnKiemTraMaKhachHang.addActionListener(this);
		panelNorth.add(btnKiemTraMaKhachHang, BorderLayout.NORTH);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);

		Box verticalBox = Box.createVerticalBox();
		panelCenter.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JLabel lblMaKhachHang_1 = new JLabel("ID khách hàng:");
		horizontalBox.add(lblMaKhachHang_1);

		Component horizontalStrut_1 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_1);

		txtMaKhachHang_1 = new JTextField();
		txtMaKhachHang_1.setEditable(false);
		horizontalBox.add(txtMaKhachHang_1);
		txtMaKhachHang_1.setColumns(15);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		horizontalBox.add(lblTenKhachHang);

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_2);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(false);
		horizontalBox.add(txtTenKhachHang);
		txtTenKhachHang.setColumns(15);

		Component verticalStrut = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		JLabel lblSoDienThoai = new JLabel("Số điện thoại: ");
		horizontalBox_1.add(lblSoDienThoai);

		Component horizontalStrut_3 = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut_3);

		txtSoDienThoai = new JTextField();
		txtSoDienThoai.setEditable(false);
		horizontalBox_1.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(5);

		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);

		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		horizontalBox_1.add(lblDiaChi);

		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut_5);

		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		horizontalBox_1.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		JLabel lblMaDia = new JLabel("Nhập ID DVD/disk:");
		horizontalBox_2.add(lblMaDia);

		Component horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut_6);

		txtMaDia = new JTextField();
		txtMaDia.setEditable(true);
		horizontalBox_2.add(txtMaDia);
		txtMaDia.setColumns(25);

		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut_7);

		btnKiemTraMaDia = new JButton("Kiểm tra ID DVD/disk");
		btnKiemTraMaDia.addActionListener(this);
		horizontalBox_2.add(btnKiemTraMaDia);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);

		JLabel lblMaDia_1 = new JLabel("ID DVD/disk:");
		horizontalBox_3.add(lblMaDia_1);

		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_8);

		txtMaDia_1 = new JTextField();
		txtMaDia_1.setEditable(false);
		horizontalBox_3.add(txtMaDia_1);
		txtMaDia_1.setColumns(15);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_3);

		Component horizontalStrut_9 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_9);

		JLabel lblTenTuaDe = new JLabel("Tên tựa đề:");
		horizontalBox_3.add(lblTenTuaDe);

		Component horizontalStrut_10 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_10);

		txtTenTuaDe = new JTextField();
		txtTenTuaDe.setEditable(false);
		horizontalBox_3.add(txtTenTuaDe);
		txtTenTuaDe.setColumns(15);

		Box horizontalBox_4 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_4);

		JLabel lblNgayHienTai = new JLabel("Ngày thuê đĩa:");
		horizontalBox_4.add(lblNgayHienTai);

		Component horizontalStrut_11 = Box.createHorizontalStrut(10);
		horizontalBox_4.add(horizontalStrut_11);

		txtNgayHienTai = new JTextField();
		txtNgayHienTai.setEditable(false);
		horizontalBox_4.add(txtNgayHienTai);
		txtNgayHienTai.setColumns(15);

		Component horizontalStrut_12 = Box.createHorizontalStrut(10);
		horizontalBox_4.add(horizontalStrut_12);

		JLabel lblThoiHanThue = new JLabel("Thời hạn thuê:");
		horizontalBox_4.add(lblThoiHanThue);

		Component horizontalStrut_13 = Box.createHorizontalStrut(10);
		horizontalBox_4.add(horizontalStrut_13);

		txtThoiHanThue = new JTextField();
		txtThoiHanThue.setEditable(false);
		horizontalBox_4.add(txtThoiHanThue);
		txtThoiHanThue.setColumns(15);

		Component verticalStrut_4 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_4);

		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_5);

		JLabel lblPhiTreHan = new JLabel("Phí trễ hạn:");
		horizontalBox_5.add(lblPhiTreHan);

		Component horizontalStrut_14 = Box.createHorizontalStrut(10);
		horizontalBox_5.add(horizontalStrut_14);

		txtPhiTreHan = new JTextField();
		txtPhiTreHan.setEditable(false);
		horizontalBox_5.add(txtPhiTreHan);
		txtPhiTreHan.setColumns(15);

		Component horizontalStrut_15 = Box.createHorizontalStrut(10);
		horizontalBox_5.add(horizontalStrut_15);

		JLabel lblPhiThue = new JLabel("Phí thuê:");
		horizontalBox_5.add(lblPhiThue);

		Component horizontalStrut_16 = Box.createHorizontalStrut(10);
		horizontalBox_5.add(horizontalStrut_16);

		txtPhiThue = new JTextField();
		txtPhiThue.setEditable(false);
		horizontalBox_5.add(txtPhiThue);
		txtPhiThue.setColumns(15);

		Component verticalStrut_5 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_5);

		Box horizontalBox_6 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_6);

		btnThemDia = new JButton("Thêm đĩa");
		btnThemDia.addActionListener(this);
		horizontalBox_6.add(btnThemDia);

		JLabel lblDanhSachDiaThue = new JLabel("DANH SÁCH ĐĨA MUỐN THUÊ");
		lblDanhSachDiaThue.setFont(new Font("Courier", Font.BOLD, 12));
		panelCenter.add(lblDanhSachDiaThue);

		Component horizontalStrut0 = Box.createHorizontalStrut(20);
		horizontalStrut0.setPreferredSize(new Dimension(10, 10));
		panelCenter.add(horizontalStrut0);

		JScrollPane scrollPane = new JScrollPane();
		panelCenter.add(scrollPane);

		tableDSDia = new JTable();
		tableDSDia.setModel(tmDia = new DefaultTableModel(new Object[][] {}, new String[] { "STT", "Mã đĩa",
				"Trạng thái", "Số ngày thuê", "Tên tựa", "Mô tả", "Phí trễ hạn", "Giá thuê" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableDSDia.addMouseListener(this);
		scrollPane.setPreferredSize(new Dimension(500, 230));
		scrollPane.setViewportView(tableDSDia);

		lblTongTien = new JLabel("TỔNG TIỀN : ");
		lblTongTien.setFont(new Font("Courier", Font.BOLD, 12));
		panelCenter.add(lblTongTien);

		JPanel pnlFooter = new JPanel();
		contentPane.add(pnlFooter, BorderLayout.SOUTH);
		btnThueDia = new JButton("Thuê đĩa");
		btnThueDia.setPreferredSize(new Dimension(500, 25));
		btnThueDia.addActionListener(this);
		btnThueDia.setEnabled(false);
		pnlFooter.add(btnThueDia);
		btnThemDia.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnKiemTraMaKhachHang)) {
			if (flag1 == true) {
				if (txtMaKhachHang.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this, "Mã khách hàng không được rỗng");
				} else {
					KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang.getText().toString());

					if (kh != null) {
						hienThiThongBaoPTH();
						txtMaKhachHang.setText("");
						txtMaDia.requestFocus();
						txtMaKhachHang_1.setText(kh.getMaKH());
						txtTenKhachHang.setText(kh.getTenKH());
						txtSoDienThoai.setText(kh.getSoDT());
						txtDiaChi.setText(kh.getDiaChi());
						flag1 = false;
					} else {
						JOptionPane.showMessageDialog(this, "Mã khách hàng không có trong hệ thống");
						txtMaKhachHang.requestFocus();
					}
				}
			} else {
				txtMaKhachHang.setText("");
				txtMaKhachHang.requestFocus();
				txtMaKhachHang_1.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setText("");
				flag1 = true;
				txtMaKhachHang.setEditable(true);
				btnKiemTraMaKhachHang.setText("Kiểm tra ID khách hàng");
			}
		} else if (e.getSource().equals(btnKiemTraMaDia)) {
			if (flag2 == true) {
				if (txtMaDia.getText().toString().equals("")) {
					JOptionPane.showMessageDialog(this, "Tựa đĩa không được rỗng");
				} else {
					Date date = new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);

					dia = phieuThueController.getDiaTheoMa(txtMaDia.getText().toString());

					if (dia != null) {
						List<DatTruoc> listDattruoc = datTruocController.getDatTruocTheoMaTua(dia.getTuaDia().getMaTua(), session);

						if (dia.getTrangThai().equalsIgnoreCase("đang được thuê")) {
							JOptionPane.showMessageDialog(this, "Đĩa không có sẵn tại cửa hàng !!!");
						}else if("đang tạm giữ".equals(dia.getTrangThai())) {
							if(listDattruoc != null) {
								DatTruoc  datTruoc1 = listDattruoc.get(0);
								if(!datTruoc1.getKhachHang().getMaKH().equals(txtMaKhachHang_1.getText().toString())) {
									JOptionPane.showMessageDialog(this, "Đĩa này đã có người đặt trước !!!");
								}else {
									txtMaDia.setText("");
									txtMaDia_1.setText(dia.getMaDia());
									txtTenTuaDe.setText(dia.getTuaDia().getTenTua());
									txtNgayHienTai.setText(LocalDate.now().toString());
									cal.add(Calendar.DATE, dia.getTuaDia().getSoNgayThue());
									outDate = cal.getTime();
									String strOutDate = formatter.format(outDate);
									txtThoiHanThue.setText(strOutDate);
									txtPhiTreHan.setText(String.valueOf(dia.getTuaDia().getPhiTreHan()));
									txtPhiThue.setText(String.valueOf(dia.getTuaDia().getGiaThue()));
									btnKiemTraMaDia.setText("Chọn mã đĩa khác");
									btnThemDia.setEnabled(true);
									txtMaDia.setEditable(false);
									flag2 = false;
								}
							}

						} 
						else {
							txtMaDia.setText("");
							txtMaDia_1.setText(dia.getMaDia());
							txtTenTuaDe.setText(dia.getTuaDia().getTenTua());
							txtNgayHienTai.setText(LocalDate.now().toString());
							cal.add(Calendar.DATE, dia.getTuaDia().getSoNgayThue());
							outDate = cal.getTime();
							String strOutDate = formatter.format(outDate);
							txtThoiHanThue.setText(strOutDate);
							txtPhiTreHan.setText(String.valueOf(dia.getTuaDia().getPhiTreHan()));
							txtPhiThue.setText(String.valueOf(dia.getTuaDia().getGiaThue()));
							btnKiemTraMaDia.setText("Chọn mã đĩa khác");
							btnThemDia.setEnabled(true);
							txtMaDia.setEditable(false);
							flag2 = false;
						}
					} else {
						JOptionPane.showMessageDialog(this, "ID đĩa không có trong hệ thống");
						txtMaDia.requestFocus();
					}
				}
			} else {
				txtMaDia_1.setText("");
				txtTenTuaDe.setText("");
				txtNgayHienTai.setText("");
				txtThoiHanThue.setText("");
				txtPhiTreHan.setText("");
				txtPhiThue.setText("");
				txtMaDia.setEditable(true);
				btnThemDia.setEnabled(false);
				btnKiemTraMaDia.setText("Kiểm tra ID DVD/disk");
				flag2 = true;
			}
		} else if (e.getSource().equals(btnThemDia)) {
			loadDiaVaoBang();
			tinhTongTien();
			btnThueDia.setEnabled(true);
		} else if (e.getSource().equals(btnThueDia)) {

			if (txtMaKhachHang_1.getText().toString().equals("")) {
				JOptionPane.showMessageDialog(this, "Chưa có thông tin khách hàng");
			} else {
				KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang_1.getText().toString());
				Session session = HibernateUtil.getSessionFactory().openSession();
				NhanVien nv = new NhanVienController().getNhanVien(GlobalConfig.maNV, session);
				Date dateCurr = new Date();
				for (int count = 0; count < tmDia.getRowCount(); count++) {
					Dia diaTrongTable = phieuThueController.getDiaTheoMa(tmDia.getValueAt(count, 1).toString());
					phieuThueController.insertThongTinThueDia(diaTrongTable, dateCurr, outDate, kh, nv);

				}
				JOptionPane.showMessageDialog(this, "Thuê DVD/disk thành công");
				session.close();
				// reset gui
				tmDia.setRowCount(0);
				txtMaKhachHang.setText("");
				txtMaKhachHang.requestFocus();
				txtMaKhachHang_1.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setText("");
				txtMaDia.setText("");
				txtMaDia_1.setText("");
				txtTenTuaDe.setText("");
				txtNgayHienTai.setText("");
				txtThoiHanThue.setText("");
				txtPhiTreHan.setText("");
				txtPhiThue.setText("");
				btnThueDia.setEnabled(false);
				this.dispose();
			}
		}

	}

	private void tinhTongTien() {
		if (tmDia.getRowCount() > 0) {
			for (int count = 0; count < tmDia.getRowCount(); count++) {
				tongTien = tongTien + Double.parseDouble((String) tmDia.getValueAt(count, 7));
			}
			lblTongTien.setText("TỔNG TIỀN : " + String.format("%.0f Đ", tongTien));
			tongTien = 0;
		}

	}

	private void chiTietTTKhachHang() {
		KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang.getText().toString());
		txtMaKhachHang.setText("");
		txtMaDia.requestFocus();
		txtMaKhachHang_1.setText(kh.getMaKH());
		txtTenKhachHang.setText(kh.getTenKH());
		txtSoDienThoai.setText(kh.getSoDT());
		txtDiaChi.setText(kh.getDiaChi());
	}

	private void loadDiaVaoBang() {
		if (tmDia.getRowCount() < 0) {
			String[] tx = { String.format("%d", stt++), dia.getMaDia(), dia.getTrangThai(),
					String.valueOf(dia.getTuaDia().getSoNgayThue()), dia.getTuaDia().getTenTua(),
					dia.getTuaDia().getMoTa(), String.valueOf(dia.getTuaDia().getPhiTreHan()),
					String.valueOf(dia.getTuaDia().getGiaThue()) };
			tmDia.addRow(tx);
		} else if (kiemTraTrungDiaTrongBang() == false) {
			JOptionPane.showMessageDialog(this, "Mã đĩa đã tồn tại trong bảng");
		} else {
			String[] tx = { String.format("%d", stt++), dia.getMaDia(), dia.getTrangThai(),
					String.valueOf(dia.getTuaDia().getSoNgayThue()), dia.getTuaDia().getTenTua(),
					dia.getTuaDia().getMoTa(), String.valueOf(dia.getTuaDia().getPhiTreHan()),
					String.valueOf(dia.getTuaDia().getGiaThue()) };
			tmDia.addRow(tx);
		}
	}

	private boolean kiemTraTrungDiaTrongBang() {
		for (int count = 0; count < tmDia.getRowCount(); count++) {
			if (tmDia.getValueAt(count, 1).equals(txtMaDia_1.getText().toString())) {

				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void hienThiThongBaoPTH(){

		btnKiemTraMaKhachHang.setText("Nhấn để chọn mã khách hàng khác");
		txtMaKhachHang.setEditable(false);
		List<PhiTreHan> listPTH = phiTreHanController
				.getListPhiTreHanChuaTraTheoMaKH(txtMaKhachHang.getText(), session);
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
	}
}
