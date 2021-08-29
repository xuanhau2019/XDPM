package se.iuh.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.KhachHangController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.KhachHang;

public class ThemKhachHangGUI extends JDialog implements ActionListener{
	private final JPanel contentPanel = new JPanel();
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtDiaChi;
	private JTextField txtSDT;

	private JButton btnThem;
	private JButton btnHuy;

	public ThemKhachHangGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setForeground(Color.WHITE);
		setTitle("Thêm Khách Hàng");
		setSize(327, 256);
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);

		contentPanel.setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 174, 309, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnThem = new JButton("Thêm");
				btnThem.setActionCommand("Thêm");
				buttonPane.add(btnThem);
				getRootPane().setDefaultButton(btnThem);
			}
			{
				btnHuy = new JButton("Hủy");
				btnHuy.setActionCommand("Hủy");
				buttonPane.add(btnHuy);
			}
		}

		JLabel lblThmKhchHng = new JLabel("Thêm khách hàng");
		lblThmKhchHng.setBounds(92, 10, 102, 16);
		getContentPane().add(lblThmKhchHng);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng");
		lblMKhchHng.setBounds(12, 39, 102, 16);
		getContentPane().add(lblMKhchHng);
		{
			JLabel lblMKhchHng_1 = new JLabel("Tên khách hàng");
			lblMKhchHng_1.setBounds(12, 73, 102, 16);
			getContentPane().add(lblMKhchHng_1);
		}
		{
			JLabel lblMKhchHng_1 = new JLabel("Địa chỉ");
			lblMKhchHng_1.setBounds(12, 102, 102, 16);
			getContentPane().add(lblMKhchHng_1);
		}
		{
			JLabel lblMKhchHng_1 = new JLabel("Số điện thoại");
			lblMKhchHng_1.setBounds(12, 131, 102, 16);
			getContentPane().add(lblMKhchHng_1);
		}

		txtMaKH = new JTextField();
		txtMaKH.setBounds(159, 36, 116, 22);
		getContentPane().add(txtMaKH);
		txtMaKH.setColumns(10);

		txtTenKH = new JTextField();
		txtTenKH.setColumns(10);
		txtTenKH.setBounds(159, 70, 116, 22);
		getContentPane().add(txtTenKH);

		txtDiaChi = new JTextField();
		txtDiaChi.setColumns(10);
		txtDiaChi.setBounds(159, 99, 116, 22);
		getContentPane().add(txtDiaChi);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(159, 128, 116, 22);
		getContentPane().add(txtSDT);
		btnHuy.addActionListener(this);
		btnThem.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		KhachHangController khachHangController = new KhachHangController();
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			String maKH =	txtMaKH.getText();
			String tenKH = txtTenKH.getText();
			String sodt = txtSDT.getText();
			String diachi = txtDiaChi.getText();
			KhachHang khachHang = new KhachHang(maKH, diachi, sodt, tenKH);
			if(maKH.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mã khách hàng");
			}else if(tenKH.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng");
			}else if (btnThem.getText().equals("Thêm")) {
				khachHangController.themKH(khachHang, session);
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				dispose();
			}
		}
		if (o.equals(btnHuy)) {
			dispose();
		}

	}
}
