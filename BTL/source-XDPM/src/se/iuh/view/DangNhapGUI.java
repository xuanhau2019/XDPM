package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.NhanVienController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.NhanVien;
import se.iuh.utility.GlobalConfig;

public class DangNhapGUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtMaNV;
	private JPasswordField txtMatKhau;

	private JButton btnDangNhap;
	private JButton btnDangKy;
	private NhanVienController nhanVienController = new NhanVienController();
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session;

	/**
	 * Create the frame.
	 */
	public DangNhapGUI() {
		setTitle("Đăng nhập");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitle, BorderLayout.NORTH);

		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panelInput = new JPanel();
		panelInput.setPreferredSize(new Dimension(340, 90));
		panelCenter.add(panelInput);
		panelInput.setLayout(new BoxLayout(panelInput, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panelInput.add(verticalStrut);

		Box boxUsername = Box.createHorizontalBox();
		panelInput.add(boxUsername);

		JLabel lblMaNV = new JLabel("Mã nhân viên");
		boxUsername.add(lblMaNV);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		boxUsername.add(horizontalStrut);

		txtMaNV = new JTextField();
		boxUsername.add(txtMaNV);
		txtMaNV.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelInput.add(verticalStrut_1);

		Box horizontalBox = Box.createHorizontalBox();
		panelInput.add(horizontalBox);
		JLabel lblMatKhau = new JLabel("Mật khẩu      ");
		horizontalBox.add(lblMatKhau);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);

		txtMatKhau = new JPasswordField();
		horizontalBox.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		JPanel panelButton = new JPanel();
		panelButton.setPreferredSize(new Dimension(340, 90));
		contentPane.add(panelButton, BorderLayout.SOUTH);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnDangNhap = new JButton("Đăng nhập");
		panelButton.add(btnDangNhap);
		btnDangNhap.addActionListener(this);

		btnDangKy = new JButton("Tạo tài khoản");
		// panelButton.add(btnDangKy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDangNhap)) {
			String maNV = txtMaNV.getText();
//			String maNV = "NV001";
			session = sessionFactory.openSession();
			NhanVien nv = nhanVienController.getNhanVien(maNV, session);
			if(nv == null) {
				JOptionPane.showMessageDialog(this, "Sai mã nhân viên, hãy thử lại");
				return;
			}
			session.close();
			if (nv.getMatKhau().equals(String.copyValueOf(txtMatKhau.getPassword()))) {
//			if (nv.getMatKhau().equals("123")) {
				if (nv.getMaNV().contains("NV")) {
					GlobalConfig.loaiUser = GlobalConfig.USER_NHANVIEN;
				}
				else {
					GlobalConfig.loaiUser = GlobalConfig.USER_ADMIN;
				}
				GlobalConfig.maNV = nv.getMaNV();
				dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ManHinhChinhGUI frame = new ManHinhChinhGUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				JOptionPane.showMessageDialog(this, "Sai mật khẩu, hãy thử lại");
			}
		}
	}

}
