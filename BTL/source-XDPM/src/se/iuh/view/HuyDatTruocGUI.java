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

import se.iuh.controller.DatTruocController;
import se.iuh.controller.DiaController;
import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.DatTruoc;
import se.iuh.model.Dia;

public class HuyDatTruocGUI extends JDialog implements ActionListener{
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
	private DatTruocController datTruocController;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	
	public HuyDatTruocGUI(Frame parent) {
		super(parent, true);
		diaController = new DiaController();
		datTruocController = new DatTruocController();
		setTitle("Danh sách đĩa đặt trước");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 460);
		setLocationRelativeTo(null);
		
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
				new String[] { "Mã đặt trước", "Khách hàng", "Tên đĩa đặt trước", "Thời gian đặt"}) {
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

		
		btnXoaDia = new JButton("Huỷ đặt trước");
		btnXoaDia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnXoaDia.addActionListener(this);
		contentPane.add(btnXoaDia, BorderLayout.SOUTH);
		
		listAll();
	}
	
	private void listAll() {
		while(tableModel.getRowCount() >= 1)
			tableModel.removeRow(0);
		datTruocController.getAll(DatTruoc.class, session).forEach(x -> {
			System.out.println("aaaaaaaaaaaa"+x);
			String[] tx = {
					x.getMaDatTruoc(),
					x.getKhachHang().getTenKH(),
					x.getTuaDatTruoc().getTenTua(),
					x.getThoiGianLap().toString(),
			};
			tableModel.addRow(tx);
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource().equals(btnXoaDia)) {
			if(tblDia.getSelectedRow() >= 0) {
				String maDatTruoc = (String) tableModel.getValueAt(tblDia.getSelectedRow(), 0);
				DatTruoc ob = datTruocController.getDatTruoc(maDatTruoc, session);
				if(ob == null) {
					JOptionPane.showMessageDialog(this, "Không thể xóa đĩa đặt trước");
				}
				boolean flag = datTruocController.delete(ob, session);
				if(flag) {
					JOptionPane.showMessageDialog(this, "Huỷ đặt trước thành công");
				}
				listAll();
			}
		}
	}

}
