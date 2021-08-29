package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
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

import se.iuh.controller.DiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.Dia;

public class XoaDiaGUI extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8682516532700877527L;
	private JPanel contentPane;
	private JTable tblDia;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JButton btnXoaDia;
	private JButton btnShowAll;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private DiaController diaController;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	
	/**
	 * Create the dialog.
	 */
	public XoaDiaGUI(Frame parent) {
		super(parent, true);
		diaController = new DiaController();
		setTitle("Xem danh sách / xóa đĩa");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 460);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		tblDia = new JTable();
		scrollPane.setViewportView(tblDia);
		tblDia.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDia.setModel(tableModel = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã đĩa", "Mã tựa đĩa", "Tên tựa", "Mô tả", "Giá thuê", "Số ngày thuê" }) {
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
		
		btnXoaDia = new JButton("Xóa đĩa");
		btnXoaDia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnXoaDia.addActionListener(this);
		contentPane.add(btnXoaDia, BorderLayout.SOUTH);
		
		listAll();
	}
	
	private void listAll() {
		while(tableModel.getRowCount() >= 1)
			tableModel.removeRow(0);
		diaController.getAll(Dia.class, session).forEach(x -> {
			String[] tx = {
					x.getMaDia(),
					x.getTuaDia().getMaTua(),
					x.getTuaDia().getTenTua(),
					x.getTuaDia().getMoTa(),
					String.format("%.0f Đ",x.getTuaDia().getGiaThue()),
					String.format("%d ngày", x.getTuaDia().getSoNgayThue())
			};
			tableModel.addRow(tx);
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnShowAll)) {
			listAll();
		}
		else if(e.getSource().equals(btnTimKiem) || e.getSource().equals(txtTimKiem)) {
			while(tableModel.getRowCount() >= 1)
				tableModel.removeRow(0);
			diaController.timKiemTheoTuKhoa(txtTimKiem.getText(), session).forEach(x -> {
				String[] tx = {
						x.getMaDia(),
						x.getTuaDia().getMaTua(),
						x.getTuaDia().getTenTua(),
						x.getTuaDia().getMoTa(),
						String.format("%.0f Đ",x.getTuaDia().getGiaThue()),
						String.format("%d ngày", x.getTuaDia().getSoNgayThue())
				};
				tableModel.addRow(tx);
			});
		}
		else if(e.getSource().equals(btnXoaDia)) {
			if(tblDia.getSelectedRow() >= 0) {
				String maDia = (String) tableModel.getValueAt(tblDia.getSelectedRow(), 0);
				Dia ob = diaController.getDia(maDia, session);
				if(ob == null) {
					JOptionPane.showMessageDialog(this, "Không thể xóa đĩa");
				}
				boolean flag = diaController.delete(ob, session);
				if(flag) {
					JOptionPane.showMessageDialog(this, "Xóa đĩa thành công");
				}
				else {
					ob.setTrangThai("REMOVED");
					diaController.update(ob, session);
					JOptionPane.showMessageDialog(this, "Lưu trạng thái xóa thành công");
				}
				listAll();
			}
			else {
				JOptionPane.showMessageDialog(this, "Chưa chọn đĩa muốn xóa");
			}
		}
	}

}
