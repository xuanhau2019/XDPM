package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.ChiTietPhieuThueController;
import se.iuh.controller.KhachHangController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.ChiTietPhieuThue;
import se.iuh.model.KhachHang;
import se.iuh.model.PhiTreHan;
import se.iuh.utility.GlobalConfig;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class ThongKeKhachHangGUI extends JDialog implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2072527007403022604L;
	private JPanel contentPane;
	private JTable tableKhachHang;
	private DefaultTableModel tmKhachHang;
	
	private JTable tableDiaDangGiu;
	private DefaultTableModel tmDiaDangGiu;
	
	private JTable tableNoPhi;
	private DefaultTableModel tmNoPhi;
	private Component horizontalStrut2A;
	private JLabel lblThongKeKH;
	private JLabel lblDanhSachDia;
	private Component horizontalStrut1A;
	private Component horizontalStrut1B;
	private JLabel lblDanhSachNoPhi;
	private Component horizontalStrut2B;
	private JLabel lblDanhSachKH;
	private Component horizontalStrut0;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private KhachHangController khachHangController = new KhachHangController();
	private List<KhachHang> listCustomer;
	private ChiTietPhieuThueController ctptController = new ChiTietPhieuThueController();
	private PhiTreHanController pthController = new PhiTreHanController();
	
	/**
	 * Create the frame.
	 */
	public ThongKeKhachHangGUI(Frame parent, int loaiThongKe) {
		super(parent, true);
		setTitle("Th???ng k?? v??? kh??ch h??ng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		lblDanhSachKH = new JLabel("Danh s??ch kh??ch h??ng");
		panel.add(lblDanhSachKH);
		
		horizontalStrut0 = Box.createHorizontalStrut(20);
		horizontalStrut0.setPreferredSize(new Dimension(20, 10));
		panel.add(horizontalStrut0);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tableKhachHang = new JTable();
		tableKhachHang.setModel(tmKhachHang = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M?? kh??ch h??ng", "T??n", "?????a ch???", "S??? ??T", "S??? ????a ??ang gi???", "T???ng n??? ph?? tr??? h???n"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, true, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableKhachHang.addMouseListener(this);
		scrollPane.setViewportView(tableKhachHang);
		
		horizontalStrut1A = Box.createHorizontalStrut(20);
		horizontalStrut1A.setPreferredSize(new Dimension(20, 30));
		panel.add(horizontalStrut1A);
		
		lblDanhSachDia = new JLabel("Danh s??ch ????a ??ang gi??? qu?? h???n");
		panel.add(lblDanhSachDia);
		
		horizontalStrut1B = Box.createHorizontalStrut(20);
		horizontalStrut1B.setPreferredSize(new Dimension(20, 10));
		panel.add(horizontalStrut1B);
		
		
		JScrollPane scrollPane2 = new JScrollPane();
		panel.add(scrollPane2);
		
		tableDiaDangGiu = new JTable();
		tableDiaDangGiu.setModel(tmDiaDangGiu = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M?? ????a", "M?? t???a", "T??n t???a", "H???n thu??", "Ph?? tr??? h???n"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, true, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane2.setViewportView(tableDiaDangGiu);
		
		horizontalStrut2A = Box.createHorizontalStrut(20);
		horizontalStrut2A.setPreferredSize(new Dimension(20, 30));
		panel.add(horizontalStrut2A);
		
		lblDanhSachNoPhi = new JLabel("Danh s??ch kho???n n??? ph?? tr??? h???n theo ????a");
		panel.add(lblDanhSachNoPhi);
		
		horizontalStrut2B = Box.createHorizontalStrut(20);
		horizontalStrut2B.setPreferredSize(new Dimension(20, 10));
		panel.add(horizontalStrut2B);
		
		JScrollPane scrollPane3 = new JScrollPane();
		panel.add(scrollPane3);
		
		tableNoPhi = new JTable();
		tableNoPhi.setModel(tmNoPhi = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M?? ????a", "M?? t???a", "T??n t???a", "H???n thu??", "Ng??y tr???", "Ph?? ??ang n???"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, true, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane3.setViewportView(tableNoPhi);
		
		lblThongKeKH = new JLabel("TH???NG K?? V??? KH??CH H??NG");
		lblThongKeKH.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongKeKH.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(lblThongKeKH, BorderLayout.NORTH);
		
		listCustomer = khachHangController.getAll(KhachHang.class, session);
		addRequestedTableKhachHang(loaiThongKe);
		switch(loaiThongKe) {
		case GlobalConfig.THONGKE_TATCA:
		{
			lblThongKeKH.setText("TH???NG K?? T???T C??? C??C KH??CH H??NG");
			lblDanhSachKH.setText("Danh s??ch kh??ch h??ng");
			setTitle("Th???ng k?? t???t c??? kh??ch h??ng");
		}
		break;
		case GlobalConfig.THONGKE_TREHAN:
		{
			lblThongKeKH.setText("TH???NG K?? C??C KH??CH H??NG C?? GI??? ????A QU?? H???N");
			lblDanhSachKH.setText("Danh s??ch kh??ch h??ng c?? gi??? ????a qu?? h???n");
			setTitle("Th???ng k?? c??c kh??ch h??ng c?? gi??? ????a qu?? h???n");
		}
		break;
		case GlobalConfig.THONGKE_NOPHI:
		{
			lblThongKeKH.setText("TH???NG K?? C??C KH??CH H??NG ??ANG N??? PH?? TR??? H???N");
			lblDanhSachKH.setText("Danh s??ch kh??ch h??ng ??ang n??? ph??");
			setTitle("Th???ng k?? c??c kh??ch h??ng ??ang n??? ph?? tr??? h???n");
		}
		break;
		}
	}
	
	private void addRequestedTableKhachHang(int loaiTK) {
		while(tmKhachHang.getRowCount() >= 1)
			tmKhachHang.removeRow(0);
		int stt = 1;
		for(KhachHang kh : listCustomer) {
			List<ChiTietPhieuThue> ls = ctptController.getCTPTTChuaTraTheoMaKH(kh.getMaKH(), session);
			int tongDia = 0;
			if(ls != null) {
				tongDia = ls.size();
			}
			if (loaiTK == GlobalConfig.THONGKE_TREHAN) {
				boolean koTreHan = true;
				for(ChiTietPhieuThue ct : ls) {
					if(ct.getHanTraDia().after(Date.valueOf(LocalDate.now()))) {
						koTreHan = false;
					}
				}
				if(koTreHan)
					continue;
			}
			
			List<PhiTreHan> ls2 = pthController.getListPhiTreHanChuaTraTheoMaKH(kh.getMaKH(), session);
			double tongNoPhi = 0;
			for(PhiTreHan ph : ls2) {
				tongNoPhi += ph.getPhiTreHan();
			}
			if(loaiTK == GlobalConfig.THONGKE_NOPHI) {
				if(tongNoPhi <= 0)
					continue;
			}
			String[] tx = {
					String.format("%d", stt++),
					kh.getMaKH(),
					kh.getTenKH(),
					kh.getSoDT(),
					kh.getDiaChi(),
					String.format("%d ????a", tongDia),
					String.format("%.0f ??", tongNoPhi)
			};
			tmKhachHang.addRow(tx);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(tableKhachHang)) {
			if(tableKhachHang.getSelectedRow() >= 0) {
				String maKH = (String) tmKhachHang.getValueAt(tableKhachHang.getSelectedRow(), 1);
				List<ChiTietPhieuThue> ls = ctptController.getCTPTTChuaTraTheoMaKH(maKH, session);
				List<ChiTietPhieuThue> lsQH = new ArrayList<ChiTietPhieuThue>();
				ls.forEach(x -> {
					if(x.getHanTraDia().after(Date.valueOf(LocalDate.now()))) {
						
						lsQH.add(x);
					}
				});
				while(tmDiaDangGiu.getRowCount() >= 1)
					tmDiaDangGiu.removeRow(0);
				int stt = 1;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for(ChiTietPhieuThue pg : lsQH) {
					String[] tx = {
							String.format("%d", stt++),
							pg.getDiaThue().getMaDia(),
							pg.getDiaThue().getTuaDia().getMaTua(),
							pg.getDiaThue().getTuaDia().getTenTua(),
							sdf.format(pg.getHanTraDia()),
							String.format("%.0f ??", pg.getPhiTreHan())
					};
					tmDiaDangGiu.addRow(tx);
				}
				
				while(tmNoPhi.getRowCount() >= 1)
					tmNoPhi.removeRow(0);
				stt = 1;
				List<PhiTreHan> ls2 = pthController.getListPhiTreHanChuaTraTheoMaKH(maKH, session);
				for(PhiTreHan pg : ls2) {
					String maDiaPG = pg.getPhieuTra().getDia().getMaDia();
					String maPhieuThuePG = pg.getPhieuTra().getPhieuThue().getMaPhieuThue();
					ChiTietPhieuThue ps = ctptController.timCTPTTheoDiaThuePhieuThue(maDiaPG, maPhieuThuePG, session).get(0);
					String[] tx = {
							String.format("%d", stt++),
							pg.getPhieuTra().getDia().getMaDia(),
							pg.getPhieuTra().getDia().getTuaDia().getMaTua(),
							pg.getPhieuTra().getDia().getTuaDia().getTenTua(),
							sdf.format(ps.getHanTraDia()),
							sdf.format(pg.getPhieuTra().getNgayTra()),
							String.format("%.0f ??", pg.getPhiTreHan())
					};
					tmNoPhi.addRow(tx);
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
