package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.DatTruocController;
import se.iuh.controller.KhachHangController;
import se.iuh.controller.PhieuThueController;
import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.DatTruoc;
import se.iuh.model.Dia;
import se.iuh.model.KhachHang;
import se.iuh.model.TuaDia;

public class ThemDatTruocGUI extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
	private JButton btnDatTruoc;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private PhieuThueController phieuThueController = new PhieuThueController();
	private TuaDiaController tuaDiaController = new TuaDiaController();
	private DatTruocController datTruocController = new DatTruocController();
	private KhachHangController khachHangController = new KhachHangController();
	private boolean flag1 = true;
	private boolean flag2 = true;

	public ThemDatTruocGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setTitle("Thêm thông tin đặt trước");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 300);
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

		JLabel lblTuaDia = new JLabel("Nhập tên tựa đĩa:");
		horizontalBox_2.add(lblTuaDia);

		Component horizontalStrut_6 = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut_6);

		txtTenTua = new JTextField();
		txtTenTua.setEditable(true);
		horizontalBox_2.add(txtTenTua);
		txtTenTua.setColumns(25);

		Component horizontalStrut_7 = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut_7);

		btnKiemTraTuaDia = new JButton("Kiểm tra tựa đĩa");
		btnKiemTraTuaDia.addActionListener(this);
		horizontalBox_2.add(btnKiemTraTuaDia);

		Component verticalStrut_2 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_2);

		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);

		JLabel lblMaTuaDia_1 = new JLabel("Mã tựa đĩa:");
		horizontalBox_3.add(lblMaTuaDia_1);

		Component horizontalStrut_8 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_8);

		txtMaTuaDia_1 = new JTextField();
		txtMaTuaDia_1.setEditable(false);
		horizontalBox_3.add(txtMaTuaDia_1);
		txtMaTuaDia_1.setColumns(15);

		Component verticalStrut_3 = Box.createVerticalStrut(10);
		verticalBox.add(verticalStrut_3);

		Component horizontalStrut_9 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_9);

		JLabel lblTenTuaDe1 = new JLabel("Tên tựa đề:");
		horizontalBox_3.add(lblTenTuaDe1);

		Component horizontalStrut_10 = Box.createHorizontalStrut(10);
		horizontalBox_3.add(horizontalStrut_10);

		txtTenTuaDe1 = new JTextField();
		txtTenTuaDe1.setEditable(false);
		horizontalBox_3.add(txtTenTuaDe1);
		txtTenTuaDe1.setColumns(15);

		JPanel pnlFooter = new JPanel();
		contentPane.add(pnlFooter, BorderLayout.SOUTH);
		btnDatTruoc = new JButton("Đặt trước");
		btnDatTruoc.setPreferredSize(new Dimension(500, 25));
		btnDatTruoc.addActionListener(this);
		pnlFooter.add(btnDatTruoc);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnKiemTraMaKhachHang)){
			if(flag1==true)
			{
				if(txtMaKhachHang.getText().toString().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Mã khách hàng không được rỗng");
				}else {
					KhachHang kh = phieuThueController.getKhachHangTheoMa(txtMaKhachHang.getText().toString());
					if(kh!=null) {
						txtMaKhachHang.setText("");
						txtTenTua.requestFocus();
						txtMaKhachHang_1.setText(kh.getMaKH());
						txtTenKhachHang.setText(kh.getTenKH());
						txtSoDienThoai.setText(kh.getSoDT());
						txtDiaChi.setText(kh.getDiaChi());
						flag1 = false;
						txtMaKhachHang.setEditable(false);
						btnKiemTraMaKhachHang.setText("Chọn mã khách hàng khác");
					}else {
						JOptionPane.showMessageDialog(this, "Mã khách hàng không có trong hệ thống");
						txtMaKhachHang.requestFocus();
					}
				}
			}else {
				txtMaKhachHang.setText("");
				txtTenTua.requestFocus();
				txtMaKhachHang_1.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setText("");
				flag1 = true;
				txtMaKhachHang.setEditable(true);
				btnKiemTraMaKhachHang.setText("Kiểm tra ID khách hàng");
			}
		}
		if(e.getSource().equals(btnKiemTraTuaDia)) {
			if(flag2==true)
			{
				if(txtTenTua.getText().toString().equals(""))
				{
					JOptionPane.showMessageDialog(this, "Tựa đĩa không được rỗng");
				}else {
					List<TuaDia> listTd = new ArrayList<TuaDia>();
					listTd = tuaDiaController.getTuaDiaTheoTenTua(txtTenTua.getText().toString(), session);

					if(!listTd.isEmpty()) {
						TuaDia tuaDia = listTd.get(0);
						List<DatTruoc> listDattruoc = datTruocController.getDatTruocTheoMaTua(tuaDia.getMaTua(), session);
						if(listDattruoc != null && listDattruoc.size()>0) {
							DatTruoc  datTruoc1 = listDattruoc.get(0);
							if(datTruoc1.getKhachHang().getMaKH().equals(txtMaKhachHang_1.getText().toString())) {
								JOptionPane.showMessageDialog(this, "Bạn đã đặt tựa đĩa này rồi !!!");
							}else {
								txtMaTuaDia_1.setText(tuaDia.getMaTua());
								txtTenTuaDe1.setText(tuaDia.getTenTua());
								txtTenTua.setText("");
								txtTenTua.setEditable(false);
								btnKiemTraTuaDia.setText("Chọn tên tựa đề khác");
								flag2 = false;
							}
						}else {
							txtMaTuaDia_1.setText(tuaDia.getMaTua());
							txtTenTuaDe1.setText(tuaDia.getTenTua());
							txtTenTua.setText("");
							txtTenTua.setEditable(false);
							btnKiemTraTuaDia.setText("Chọn tên tựa đề khác");
							flag2 = false;
						}

					}else {
						JOptionPane.showMessageDialog(this, "Tựa đĩa không có trong hệ thống");
						txtTenTua.requestFocus();
					}
				}
			}else {
				txtMaTuaDia_1.setText("");
				txtTenTuaDe1.setText("");
				txtTenTua.setText("");
				txtTenTua.setEditable(true);
				btnKiemTraTuaDia.setText("Kiểm tra tựa đĩa");
				flag2 = true;
			}
		}
		if(e.getSource().equals(btnDatTruoc))
		{
			if(txtMaKhachHang_1.getText().toString().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Chưa có mã khách hàng");
			}else if(txtMaTuaDia_1.getText().toString().equals(""))
			{
				JOptionPane.showMessageDialog(this, "Chưa có tên tựa đề");
			}else {
				KhachHang khachHang = khachHangController.getKhachHang(txtMaKhachHang_1.getText().toString(), session);
				List<TuaDia> listTd = new ArrayList<TuaDia>();
				listTd = tuaDiaController.getTuaDiaTheoTenTua(txtTenTuaDe1.getText().toString(), session);
				TuaDia tuaDatTruoc = listTd.get(0);
				Date thoiGianLap = new Date();
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);

				int month = c.get(Calendar.MONTH)+1;
				int day = c.get(Calendar.DAY_OF_MONTH);
				int hour = c.get(Calendar.HOUR_OF_DAY);
				int minute = c.get(Calendar.MINUTE);
				//				    int second = c.get(Calendar.SECOND);
				int mils = c.get(Calendar.MILLISECOND);
				String maDatTruoc = "DT"+year+month+day+hour+minute+mils;
				DatTruoc datTruoc = new DatTruoc(maDatTruoc, tuaDatTruoc, thoiGianLap, khachHang);
				datTruocController.themDatTruoc(datTruoc, session);
				JOptionPane.showMessageDialog(this, "Đặt trước thành công");

				txtMaTuaDia_1.setText("");
				txtTenTuaDe1.setText("");
				txtTenTua.setText("");
				txtTenTua.setEditable(true);
				btnKiemTraTuaDia.setText("Kiểm tra tựa đĩa");
				flag2 = true;

				txtMaKhachHang.setText("");
				txtTenTua.requestFocus();
				txtMaKhachHang_1.setText("");
				txtTenKhachHang.setText("");
				txtSoDienThoai.setText("");
				txtDiaChi.setText("");
				flag1 = true;
				txtMaKhachHang.setEditable(true);
				btnKiemTraMaKhachHang.setText("Kiểm tra ID khách hàng");
				this.dispose();
			}
		}
	}

}
