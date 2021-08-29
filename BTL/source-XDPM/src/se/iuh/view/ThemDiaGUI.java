package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.DiaController;
import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.Dia;
import se.iuh.model.TuaDia;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

public class ThemDiaGUI extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5497763627563289681L;
	private JPanel contentPane;
	private JTable tblTuaDia;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnThemDia;
	private JButton btnShowAll;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private TuaDiaController tuaDiaController;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private static final int MAX_TRIES = 15;

	/**
	 * Create the frame.
	 */
	public ThemDiaGUI(Frame parent) {
		super(parent, true);
		tuaDiaController = new TuaDiaController();
		setTitle("Thêm đĩa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tblTuaDia = new JTable();
		scrollPane.setViewportView(tblTuaDia);
		tblTuaDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTuaDia.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã tựa đĩa", "Tên tựa", "Mô tả", "Phí trễ hạn", "Số ngày thuê", "Loại tựa đĩa" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		txtTimKiem = new JTextField();
		panel.add(txtTimKiem);
		txtTimKiem.setColumns(10);
		txtTimKiem.addActionListener(this);

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnTimKiem.addActionListener(this);
		panel.add(btnTimKiem);
		
		btnShowAll = new JButton("Hiện tất cả");
		btnShowAll.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		btnShowAll.addActionListener(this);
		panel.add(btnShowAll);

		btnThemDia = new JButton("Thêm đĩa");
		btnThemDia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnThemDia.addActionListener(this);
		contentPane.add(btnThemDia, BorderLayout.SOUTH);
		
		listAll();
	}
	
	private void listAll() {
		while(tableModel.getRowCount() >= 1)
			tableModel.removeRow(0);
		tuaDiaController.getAll(TuaDia.class, session).forEach(x -> {
			String[] tx = {
					x.getMaTua(),
					x.getTenTua(),
					x.getMoTa(),
					String.format("%.0f Đ", x.getPhiTreHan()),
					String.format("%d", x.getSoNgayThue()),
					String.format("%d", x.getLoaiTua())
			};
			tableModel.addRow(tx);
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnTimKiem) || e.getSource().equals(txtTimKiem)) {
			while(tableModel.getRowCount() >= 1)
				tableModel.removeRow(0);
			tuaDiaController.timKiemTheoTuKhoa(txtTimKiem.getText(), session).forEach(x -> {
				String[] tx = {
						x.getMaTua(),
						x.getTenTua(),
						x.getMoTa(),
						String.format("%.0f Đ", x.getPhiTreHan()),
						String.format("%d", x.getSoNgayThue()),
						String.format("%d", x.getLoaiTua())
				};
				tableModel.addRow(tx);
			});
		}
		else if(e.getSource().equals(btnThemDia)) {
			DiaController diaController = new DiaController();
			if(tblTuaDia.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn tựa đĩa, không thể thêm");
				return;
			}
			String maTua = (String) tableModel.getValueAt(tblTuaDia.getSelectedRow(), 0);
			if(!maTua.equals("")) {
				String id = "D_PLACEHOLDER";
				boolean passed = false;
				Random random = new Random();
				int times = 0;
				while(!passed) {
					id = "D"+String.format("%012d", Math.abs(random.nextLong())%1000000000000L);
					Dia test = diaController.getDia(id, session);
					if(test == null)
						passed = true;
					times++;
					if(times > MAX_TRIES) {
						JOptionPane.showMessageDialog(this, "Có lỗi. Không thể thêm đĩa mới");
						return;
					}
				}
				TuaDia td = tuaDiaController.getTuaDia(maTua, session);
				Dia d = new Dia(id, td, "OPEN");
				boolean success = diaController.insert(d, session);
				if(success) {
					JOptionPane.showMessageDialog(this, "Thêm thành công, ID của đĩa là "+id);
				}
				else {
					JOptionPane.showMessageDialog(this, "Không thể thêm đĩa");
				}
			}
		}
		else if(e.getSource().equals(btnShowAll)) {
			listAll();
		}
	}
}
