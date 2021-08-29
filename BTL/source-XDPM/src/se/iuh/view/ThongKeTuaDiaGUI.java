package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.ChiTietPhieuThueController;
import se.iuh.controller.DatTruocController;
import se.iuh.controller.DiaController;
import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.TuaDia;

import java.awt.Rectangle;
import java.util.List;

public class ThongKeTuaDiaGUI extends JDialog {

	private JPanel contentPane;
	private JTable tableTuaDia;
	private DefaultTableModel tmTuaDia;
	private JLabel lblDanhSachTD;
	private JLabel lblThongKeTuaDia;
	private Component verticalStrut;
	
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private List<TuaDia> listTuaDia;
	private TuaDiaController tuaDiaController = new TuaDiaController();
	private ChiTietPhieuThueController ctptController = new ChiTietPhieuThueController();
	private DiaController diaController = new DiaController();
	private DatTruocController datTruocController = new DatTruocController();

	/**
	 * Create the frame.
	 */
	public ThongKeTuaDiaGUI(Frame parent) {
		super(parent, true);
		setBounds(new Rectangle(0, 0, 1100, 640));
		setPreferredSize(new Dimension(1100, 640));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thống kê thông tin các tựa đĩa");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		lblDanhSachTD = new JLabel("Danh sách tựa đĩa");
		panel.add(lblDanhSachTD);
		
		verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 4));
		panel.add(verticalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tableTuaDia = new JTable();
		tableTuaDia.setModel(tmTuaDia = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã tựa", "Tên tựa", "Mô tả", "Số đĩa đang cho thuê", "Số đĩa đang on-hold", "Số đĩa có ở tiệm", "Số lượng đặt trước"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tableTuaDia);
		
		lblThongKeTuaDia = new JLabel("THỐNG KÊ THÔNG TIN CÁC TỰA ĐĨA");
		lblThongKeTuaDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblThongKeTuaDia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(lblThongKeTuaDia, BorderLayout.NORTH);
		
		loadDataVaoTable();
	}

	private void loadDataVaoTable()	{
		listTuaDia = tuaDiaController.getAll(TuaDia.class, session);
		int stt = 1;
		for(TuaDia td : listTuaDia) {
			int soDiaDangThue = ctptController.getCTPTTChuaTraTheoTuaDia(td.getMaTua(), session).size();
			int soDiaOnHold = 0;
			int soDiaTaiCuaHang = diaController.getAllDiaTheoMaTua(td.getMaTua(), session).size() - soDiaOnHold - soDiaDangThue;
			int soLuongDatTruoc = datTruocController.getDatTruocTheoMaTua(td.getMaTua(), session).size();
			
			String[] tx = {
					String.format("%d", stt++),
					td.getMaTua(),
					td.getTenTua(),
					td.getMoTa(),
					String.format("%d", soDiaDangThue),
					String.format("%d", soDiaOnHold),
					String.format("%d", soDiaTaiCuaHang),
					String.format("%d", soLuongDatTruoc)
			};
			tmTuaDia.addRow(tx);
		}
	}
}
