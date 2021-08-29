package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.ChiTietPhieuThueController;
import se.iuh.controller.KhachHangController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.controller.PhieuThueController;
import se.iuh.controller.PhieuTraController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.ChiTietPhieuThue;
import se.iuh.model.KhachHang;

public class SuaKhachHangGUI extends JDialog implements ActionListener, MouseListener {
	private JTextField txtTenTua;
	private JTextField txtMaTuaDia_1;
	private JButton btnKiemTraTuaDia;
	private JButton btnKiemTraMaKhachHang;
	private JTextField txtMaKhachHang;
	private JTextField txtMaKhachHang_1;
	private JTextField txtTenKhachHang;
	private JTextField txtSoDienThoai;
	private JTextField txtDiaChi;
	private JTextField txtTenTuaDe1;
	private JPanel contentPane;
	private JTextField txtMaDia;
	private JTextField txtHanTraDia;
	private JTextField txtNgayHomNay;
	private JTextField txtPhiTreHan;
	private JButton btnKiemTraMaDia;
	private JButton btnTraDia;
	private JTable tblDia;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private ChiTietPhieuThueController ctptController = new ChiTietPhieuThueController();
	private PhiTreHanController phiTreHanController = new PhiTreHanController();
	private PhieuTraController phieuTraController = new PhieuTraController();
	private boolean coTreHan = false;
	private ChiTietPhieuThue ctptDangChon;
	private boolean flag1 = true;
	private boolean flag2 = true;
	private KhachHangController khachHangController = new KhachHangController();
	private PhieuThueController phieuThueController = new PhieuThueController();
	private JButton btnXoa;
	public SuaKhachHangGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setTitle("Sửa và xoá thông tin khách hàng");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 586, 700);
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
		txtMaKhachHang.setColumns(10);

		btnKiemTraMaKhachHang = new JButton("Tìm kiếm");
		btnKiemTraMaKhachHang.addActionListener(this);
		panelNorth.add(btnKiemTraMaKhachHang, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		
		Box verticalBox = Box.createVerticalBox();
		panelCenter.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		horizontalBox.add(lblTenKhachHang);

		Component horizontalStrut_2 = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut_2);

		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setEditable(true);
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
		txtSoDienThoai.setEditable(true);
		horizontalBox_1.add(txtSoDienThoai);
		txtSoDienThoai.setColumns(15);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		JLabel lblDiaChi = new JLabel("Địa chỉ: ");
		horizontalBox_1.add(lblDiaChi);

		Component horizontalStrut_5 = Box.createHorizontalStrut(10);
		horizontalBox_1.add(horizontalStrut_5);
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(true);
		horizontalBox_1.add(txtDiaChi);
		txtDiaChi.setColumns(15);

		Component verticalStrut_1 = Box.createVerticalStrut(30);
		verticalBox.add(verticalStrut_1);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		btnTraDia = new JButton("Cập nhật");
		btnTraDia.addActionListener(this);
		horizontalBox_2.add(btnTraDia);
		btnTraDia.setEnabled(false);

		ctptDangChon = null;
		
		JPanel contentPane2 = new JPanel();
		contentPane2.setLayout(new BorderLayout());
		contentPane.add(contentPane2, BorderLayout.SOUTH);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		scrollPane = new JScrollPane();
		contentPane2.add(scrollPane, BorderLayout.CENTER);
		tblDia = new JTable();
		scrollPane.setViewportView(tblDia);
		tblDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDia.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã khách hàng", "Địa chỉ", "Số điện thoại", "Tên khách hàng"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblDia.addMouseListener(this);
		
		btnXoa = new JButton("Xoá khách hàng");
		btnXoa.addActionListener(this);
		btnXoa.setEnabled(false);
		contentPane2.add(btnXoa, BorderLayout.SOUTH);
		
//		JPanel panel = new JPanel();
//		contentPane.add(panel, BorderLayout.NORTH);
//		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		listAll();
	}
	
	private void listAll() {
		while(tableModel.getRowCount() >= 1)
			tableModel.removeRow(0);
		khachHangController.getAll(KhachHang.class, session).forEach(x -> {
			String[] tx = {
					x.getMaKH(),
					x.getDiaChi(),
					x.getSoDT(),
					x.getTenKH(),
			};
			tableModel.addRow(tx);
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource().equals(btnKiemTraMaKhachHang)){
			if(flag1==true)
			{
				if(txtMaKhachHang.getText().toString().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Mã khách hàng không được rỗng");
				}else {
					KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang.getText().toString());
					if(kh!=null) {
					 	txtMaKhachHang.setText(kh.getMaKH());
					txtTenKhachHang.setText(kh.getTenKH());
						txtSoDienThoai.setText(kh.getSoDT());
						txtDiaChi.setText(kh.getDiaChi());
						flag1 = false;
						txtMaKhachHang.setEditable(true);
					}else {
						JOptionPane.showMessageDialog(this, "Mã khách hàng không có trong hệ thống");
						txtMaKhachHang.requestFocus();
					}
				}
			}else {
				txtMaKhachHang.setText("");
				txtTenTua.requestFocus();
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setText("");
				flag1 = true;
				txtMaKhachHang.setEditable(true);
				btnKiemTraMaKhachHang.setText("Kiểm tra ID khách hàng");
			}
		}
		Object o = e.getSource();
		if (o.equals(btnTraDia)) {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang.getText().toString());
			KhachHang khachHang = khachHangController.getKH(kh.getMaKH(), session);
			Session session = sessionFactory.openSession();
			if(khachHang.getMaKH() != null) {
				if (btnTraDia.getText().equals("Cập nhật")) {
					String tenKH = txtTenKhachHang.getText();
					String sodt = txtSoDienThoai.getText();
					String diachi = txtDiaChi.getText();
					khachHang.setTenKH(tenKH);
					khachHang.setSoDT(sodt);
					khachHang.setDiaChi(diachi);
					khachHangController.update(khachHang, session);
					JOptionPane.showMessageDialog(this, "cập nhật thành công");
					listAll();
				}
			}
			else {
				listAll();
				System.out.println("ákfjksafjlkasjflksajflsajflksj");
			}

		}
		if(o.equals(btnXoa)) {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			KhachHang kh = khachHangController.getKH(txtMaKhachHang.getText(), session);
			if(kh != null) {
				khachHangController.delete(kh, session);
				JOptionPane.showMessageDialog(this, "xoá thành công");
				listAll();
				return;
			}
			JOptionPane.showMessageDialog(this, "Chọn để xoá");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource().equals(tblDia)) {
			if(tblDia.getSelectedRow() > -1) {
				txtMaKhachHang.setText((String)tableModel.getValueAt(tblDia.getSelectedRow(), 0));
				txtTenKhachHang.setText((String)tableModel.getValueAt(tblDia.getSelectedRow(), 3));
				txtSoDienThoai.setText((String)tableModel.getValueAt(tblDia.getSelectedRow(), 2));
				txtDiaChi.setText((String)tableModel.getValueAt(tblDia.getSelectedRow(), 1));
			}
		}
		btnTraDia.setEnabled(true);
		btnXoa.setEnabled(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
