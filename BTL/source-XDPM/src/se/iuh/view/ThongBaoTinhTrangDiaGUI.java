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

public class ThongBaoTinhTrangDiaGUI  extends JDialog implements ActionListener{
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
	
	public ThongBaoTinhTrangDiaGUI(Frame parent) {
		super(parent, true);
		diaController = new DiaController();
		setTitle("Danh sách đĩa");
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
				new String[] { "Mã đĩa", "Tựa đĩa", "Trạng thái"}) {
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


		btnXoaDia = new JButton("Cập nhật trạng thái đĩa");
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
					x.getTuaDia().getTenTua(),
					x.getTrangThai(),
			};
			tableModel.addRow(tx);
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if(e.getSource().equals(btnXoaDia)) {
				if(tblDia.getSelectedRow() >= 0) {
					String maDia = (String) tableModel.getValueAt(tblDia.getSelectedRow(), 0);
					Dia ob = diaController.getDia(maDia, session);
					if(ob.getTrangThai().equals("Có sẵn")) {
						ob.setTrangThai("đang được thuê");
					}
					else if(ob.getTrangThai().equals("đang được thuê")) {
						
					}
					else {
						ob.setTrangThai("Có sẵn");
					}
						diaController.update(ob, session);
						JOptionPane.showMessageDialog(this, "Trạng thái được cập nhật thành công");
						listAll();
				}
			}
	}
	
	
}
