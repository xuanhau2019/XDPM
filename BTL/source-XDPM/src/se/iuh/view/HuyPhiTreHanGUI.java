package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.ChiTietPhieuThueController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.ChiTietPhieuThue;
import se.iuh.model.PhiTreHan;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;

import javax.swing.Box;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HuyPhiTreHanGUI extends JDialog implements ActionListener, MouseListener {

	private final JPanel panelCenter = new JPanel();
	private JTextField txtMaKH;
	private JTextField txtMaDia;
	private JTextField txtNgay;
	private JTextField txtThang;
	private JTextField txtNam;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtTenKhacHangSelected;
	private JTextField txtMaCTPTSelected;
	private JButton btnHuyPhiTreHan;
	private JButton btnTim;
	private PhiTreHanController pthController = new PhiTreHanController();
	private ChiTietPhieuThueController ctptController = new ChiTietPhieuThueController();
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	/**
	 * Create the dialog.
	 */
	public HuyPhiTreHanGUI(Frame parent) {
		super(parent, true);
		setTitle("Hủy phí trễ hạn");
		setSize(new Dimension(1125, 540));
		getContentPane().setLayout(new BorderLayout());
		panelCenter.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.X_AXIS));
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		panelCenter.add(scrollPane);

		table = new JTable();
		table.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã phí trễ hạn", "Mã phiếu trả tương ứng", "Mã khách hàng", "Mã đĩa", "Tên đĩa", "Phí trễ hạn", "Hạn trả", "Ngày trả" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.addMouseListener(this);
		scrollPane.setViewportView(table);

		JPanel panelNorth = new JPanel();
		getContentPane().add(panelNorth, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panelNorth.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 5));

		JLabel lblNewLabel_1 = new JLabel(
				"Nhập vào các trường dưới đây là nhấn tìm kiếm, càng nhiều thông tin sẽ giúp tìm chính xác khoản phí trễ hạn cần tìm:");
		panel.add(lblNewLabel_1);

		Box horizontalBox = Box.createHorizontalBox();
		panel.add(horizontalBox);

		JLabel lblTimKiemKhachHang = new JLabel("Nhập mã khách hàng");
		horizontalBox.add(lblTimKiemKhachHang);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		txtMaKH = new JTextField();
		horizontalBox.add(txtMaKH);
		txtMaKH.setColumns(20);

		Box horizontalBox_1 = Box.createHorizontalBox();
		panel.add(horizontalBox_1);

		JLabel lblTimKiemMaDia = new JLabel("Nhập mã đĩa");
		horizontalBox_1.add(lblTimKiemMaDia);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);

		txtMaDia = new JTextField();
		horizontalBox_1.add(txtMaDia);
		txtMaDia.setColumns(10);

		Box horizontalBox_2 = Box.createHorizontalBox();
		panel.add(horizontalBox_2);

		JLabel lblTimKiemNgayTra = new JLabel("Ngày trả (ngày - tháng - năm):");
		horizontalBox_2.add(lblTimKiemNgayTra);

		txtNgay = new JTextField();
		horizontalBox_2.add(txtNgay);
		txtNgay.setColumns(10);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);

		txtThang = new JTextField();
		horizontalBox_2.add(txtThang);
		txtThang.setColumns(10);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_3);

		txtNam = new JTextField();
		horizontalBox_2.add(txtNam);
		txtNam.setColumns(10);

		btnTim = new JButton("Tìm khoản nợ phí");
		btnTim.addActionListener(this);
		panel.add(btnTim);
		
		JPanel panelSouth = new JPanel();
		getContentPane().add(panelSouth, BorderLayout.SOUTH);
		
		JLabel lblTenKhachHangSelected = new JLabel("Tên khách hàng đang chọn:");
		panelSouth.add(lblTenKhachHangSelected);
		
		txtTenKhacHangSelected = new JTextField();
		txtTenKhacHangSelected.setEditable(false);
		panelSouth.add(txtTenKhacHangSelected);
		txtTenKhacHangSelected.setColumns(20);
		
		JLabel lblMaCTPTSelected = new JLabel("Mã chi tiết phiếu thuê đang chọn");
		panelSouth.add(lblMaCTPTSelected);
		
		txtMaCTPTSelected = new JTextField();
		txtMaCTPTSelected.setEditable(false);
		panelSouth.add(txtMaCTPTSelected);
		txtMaCTPTSelected.setColumns(18);
		
		btnHuyPhiTreHan = new JButton("Hủy khoản phí trễ hạn đang chọn");
		btnHuyPhiTreHan.addActionListener(this);
		panelSouth.add(btnHuyPhiTreHan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnTim)) {
			while(tableModel.getRowCount() >= 1)
				tableModel.removeRow(0);
			String maKH = txtMaKH.getText().trim();
			String maDia = txtMaDia.getText().trim();
			String ngayTra1 = txtNgay.getText().trim();
			String ngayTra2 = txtThang.getText().trim();
			String ngayTra3 = txtNam.getText().trim();
			Date date = null;
			boolean checkNgayTra = true;
			if(ngayTra1.equals("") && ngayTra2.equals("") && ngayTra3.equals(""))
				checkNgayTra = false;
			if(checkNgayTra) {
				String dateStr = ngayTra3 + "-" + ngayTra2 + "-" + ngayTra1;
				try {
					date = Date.valueOf(dateStr);
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(this, "Ngày tháng năm không hợp lệ, hãy kiểm tra lại");
					return;
				}
			}
			if(maKH.equals(""))
				maKH = null;
			if(maDia.equals(""))
				maDia = null;
			if(!checkNgayTra)
				date = null;
			if(maKH == null && maDia == null && date == null) {
				JOptionPane.showMessageDialog(this, "Ít nhất phải có 1 điều kiện để tìm, hãy kiểm tra lại");
				return;
			}
			session = sessionFactory.openSession();
			List<PhiTreHan> pthList = pthController.getListPhiTreHanChuaTraTheoNhieuDieuKien(maKH, maDia, date, session);
			for(PhiTreHan pt : pthList) {
				ChiTietPhieuThue ct = ctptController.timCTPTTheoDiaThuePhieuThue(pt.getPhieuTra().getDia().getMaDia(), pt.getPhieuTra().getPhieuThue().getMaPhieuThue(), session).get(0);
				String []rowData = {
						pt.getMaPhiTreHan(), pt.getPhieuTra().getMaPhieuTra(), pt.getPhieuTra().getPhieuThue().getKhachHang().getMaKH(),
						pt.getPhieuTra().getDia().getMaDia(), pt.getPhieuTra().getDia().getTuaDia().getTenTua(), 
						String.format("%.0f Đ", pt.getPhiTreHan()), ct.getHanTraDia().toString(), pt.getPhieuTra().getNgayTra().toString()
				};
				tableModel.addRow(rowData);
			}
			session.close();
		}
		else if(e.getSource().equals(btnHuyPhiTreHan)) {
			if(table.getSelectedRow() > -1) {
				session = sessionFactory.openSession();
				String maPhiTreHan = (String) tableModel.getValueAt(table.getSelectedRow(), 0);
				PhiTreHan pth = pthController.getPhiTreHan(maPhiTreHan, session);
				int confirm = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa khoản phí trễ hạn này?", "Lưu ý", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.YES_OPTION) {
					pthController.delete(pth, session);
					while(tableModel.getRowCount() >= 1)
						tableModel.removeRow(0);
				}
				session.close();
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn khoản phí trễ hạn muốn xóa");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().equals(table)) {
			if(table.getSelectedRow() > -1) {
				session = sessionFactory.openSession();
				PhiTreHan pt = pthController.getPhiTreHan((String) tableModel.getValueAt(table.getSelectedRow(), 0), session);
				ChiTietPhieuThue ct = ctptController.timCTPTTheoDiaThuePhieuThue(pt.getPhieuTra().getDia().getMaDia(), pt.getPhieuTra().getPhieuThue().getMaPhieuThue(), session).get(0);
				session.close();
				
				txtMaCTPTSelected.setText(ct.getMaCTPT());
				txtTenKhacHangSelected.setText(ct.getPhieuThue().getKhachHang().getTenKH());
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
