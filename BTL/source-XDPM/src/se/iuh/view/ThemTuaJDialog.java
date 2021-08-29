package se.iuh.view;

import java.awt.Color;
import java.awt.Cursor;
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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.TuaDia;

public class ThemTuaJDialog extends JDialog implements ActionListener, FocusListener, KeyListener, MouseListener {
	private JTextField txtMaTua;
	private JTextField txtTenTua;

	private JTextField txtSoNgayThue;
	private JLabel lblGiaThue;

	private JSpinner spnPhiTreHan;
	private JSpinner JSpGiaThue;

	private JTextArea txtMota;

	private JButton btnThem;

	private JRadioButton rdPhim;

	private JRadioButton rdGame;

	private JLabel lblPhiTreHan;

	private JLabel lblSoNgayThue;

	private JLabel lblTenTua;

	private JLabel lblMaTua;

	private JButton btnHuy;

	public ThemTuaJDialog(Frame parent) {
		super(parent, true);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setForeground(Color.WHITE);
		setTitle("Thêm Tựa Đĩa");
		setSize(640, 640);
		setResizable(false);
		getContentPane().setLayout(null);
		setLocationRelativeTo(parent);
		

		JPanel panel = new JPanel();
		panel.setBounds(0, 48, 634, 557);
		panel.setBackground(Color.white);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel pnTittle = new JPanel();
		pnTittle.setBounds(0, 0, 634, 44);
		getContentPane().add(pnTittle);
		pnTittle.setBackground(Color.cyan);

		JLabel lblTitle = new JLabel("Thêm Tựa Đĩa");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 25));
		pnTittle.add(lblTitle);

		lblMaTua = new JLabel("Mã Tựa");
		lblMaTua.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblMaTua.setBounds(7, 5, 83, 41);
		panel.add(lblMaTua);

		txtMaTua = new JTextField();
		txtMaTua.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtMaTua.setColumns(10);
		txtMaTua.setForeground(Color.LIGHT_GRAY);
		txtMaTua.setBounds(114, 5, 285, 30);
		panel.add(txtMaTua);

		lblTenTua = new JLabel("Tên Tựa");
		lblTenTua.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblTenTua.setBounds(7, 61, 83, 41);
		panel.add(lblTenTua);

		txtTenTua = new JTextField();
		txtTenTua.setToolTipText("Bắt buộc nhập");
		txtTenTua.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtTenTua.setColumns(10);
		txtTenTua.setForeground(Color.LIGHT_GRAY);
		txtTenTua.setBounds(114, 59, 285, 30);
		panel.add(txtTenTua);

		lblPhiTreHan = new JLabel("Phí trễ hạn(VND)");
		lblPhiTreHan.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblPhiTreHan.setBounds(7, 161, 127, 41);
		panel.add(lblPhiTreHan);

		lblGiaThue = new JLabel("Giá Thuê");
		lblGiaThue.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblGiaThue.setBounds(7, 199, 127, 41);
		panel.add(lblGiaThue);

		lblSoNgayThue = new JLabel("Số Ngày Thuê");
		lblSoNgayThue.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblSoNgayThue.setBounds(7, 105, 127, 41);
		panel.add(lblSoNgayThue);

		txtSoNgayThue = new JTextField();
		txtSoNgayThue.setToolTipText("Bắt buộc nhập");
		txtSoNgayThue.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtSoNgayThue.setColumns(10);
		txtSoNgayThue.setForeground(Color.LIGHT_GRAY);
		txtSoNgayThue.setBounds(114, 107, 285, 30);
		panel.add(txtSoNgayThue);

		spnPhiTreHan = new JSpinner();
		spnPhiTreHan.setToolTipText("Phí trễ hạn từ 1.000 đồng đến 1.000.000 đồng");
		spnPhiTreHan.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		spnPhiTreHan.setBounds(114, 159, 285, 30);
		panel.add(spnPhiTreHan);

		spnPhiTreHan.setModel(new SpinnerNumberModel(1000.0, 1000.0, 1000000.0, 1000.0));

		txtMota = new JTextArea(12, 14);
		JScrollPane scrollPane = new JScrollPane(txtMota);
		txtMota.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		txtMota.setColumns(10);
		txtMota.setForeground(Color.LIGHT_GRAY);
		scrollPane.setBounds(114, 250, 285, 76);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane);

		btnThem = new JButton("Thêm");
		btnThem.setContentAreaFilled(false);
		btnThem.setOpaque(true);
		btnThem.setForeground(Color.white);
		btnThem.setBorder(null);
		btnThem.setBackground(Color.decode("#4CAF50"));
		btnThem.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThem.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThem.setBounds(133, 455, 188, 51);
		panel.add(btnThem);

		rdPhim = new JRadioButton("Phim");
		rdPhim.setFont(new Font("Segoe UI", Font.BOLD, 15));
		rdPhim.setBounds(133, 335, 117, 36);
		rdPhim.setBackground(Color.white);
		rdPhim.setToolTipText("Tựa này là Phim");
		panel.add(rdPhim);

		rdGame = new JRadioButton("Game");
		rdGame.setFont(new Font("Segoe UI", Font.BOLD, 15));
		rdGame.setBounds(252, 335, 117, 36);
		rdGame.setToolTipText("Tựa này là Game");
		rdGame.setBackground(Color.white);
		panel.add(rdGame);

		ButtonGroup groupbtn = new ButtonGroup();
		groupbtn.add(rdGame);
		groupbtn.add(rdPhim);
		rdPhim.setSelected(true);

		JLabel lblMoTa = new JLabel("Mô tả");
		lblMoTa.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblMoTa.setBounds(7, 250, 59, 30);
		panel.add(lblMoTa);

		btnHuy = new JButton("Hủy");
		btnHuy.setContentAreaFilled(false);
		btnHuy.setOpaque(true);
		btnHuy.setForeground(Color.white);
		btnHuy.setBorder(null);
		btnHuy.setBackground(Color.decode("#C14008"));
		btnHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnHuy.setBounds(341, 455, 188, 51);
		panel.add(btnHuy);

		JSpGiaThue = new JSpinner();
		JSpGiaThue.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		JSpGiaThue.setBounds(114, 203, 285, 30);
		panel.add(JSpGiaThue);

		JSpGiaThue.setModel(new SpinnerNumberModel(1000.0, 1000.0, 1000000.0, 1000.0));

		btnThem.addActionListener(this);
		btnThem.addMouseListener(this);

		btnHuy.addActionListener(this);
		btnHuy.addMouseListener(this);
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TuaDiaController tuaDiaController = new TuaDiaController();

		Object o = e.getSource();

		if (o.equals(btnThem)) {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			if (txtTenTua.getText().equals("") || txtTenTua.getText().equals("Nhập Tên Tựa Đĩa..."))
				JOptionPane.showMessageDialog(this, "Chưa nhập tên tựa!!");
			else {
				String matua = txtMaTua.getText();
				String tenTua = txtTenTua.getText();
				String mota = txtMota.getText();
				int songaythue = Integer.parseInt(txtSoNgayThue.getText());
				double phitre = Double.parseDouble((spnPhiTreHan.getValue() + ""));
				double giaThue = Double.parseDouble((JSpGiaThue.getValue() + ""));

				int maloaidia;
				if (rdGame.isSelected()) {
					maloaidia = 2;
				} else {
					maloaidia = 1;
				}
				TuaDia tuaDia = new TuaDia(matua, tenTua, mota, maloaidia, songaythue, phitre, giaThue);

				if (btnThem.getText().equals("Thêm")) {
					tuaDiaController.themTua(tuaDia, session);
					JOptionPane.showMessageDialog(this, "Thêm tựa thành công");
					dispose();
				}

			}
		}
		if (o.equals(btnHuy)) {
			dispose();
		}
	}
}
