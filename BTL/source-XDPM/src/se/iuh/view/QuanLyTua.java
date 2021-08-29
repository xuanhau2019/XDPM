package se.iuh.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.awt.event.MouseMotionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.TuaDiaController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.TuaDia;

public class QuanLyTua extends JDialog
		implements ActionListener, MouseListener, KeyListener, FocusListener, MouseMotionListener {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();

	private JTable table;
	private DefaultTableModel tableModel;
	private JButton btnThemTua;
	private JTextField txtTim;
	private JLabel lblTitle;

	private TuaDiaController tuaDiaControl = new TuaDiaController();
	private List<TuaDia> listTua = tuaDiaControl.getAllTuaDia(session);
	private ThemTuaJDialog themTuaDL;

	// private JFrame parent = new JFrame();
	protected int rollOverRowIndex = -1;
	DecimalFormat dmf = new DecimalFormat("#,##0");
	private JButton btnXoa;

	public QuanLyTua(Frame parent) {
		super(parent, true);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 13, 1200, 588);
		setBounds(0, 0, 1200, 588);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel pnTitle = new JPanel();
		pnTitle.setBounds(0, 0, 1200, 35);
		pnTitle.setBackground(Color.CYAN);

		lblTitle = new JLabel("Quản lý Tựa Đĩa");
		lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

		pnTitle.add(lblTitle);

		panel.add(pnTitle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 1180, 469);
		panel.add(scrollPane);

		table = new JTable() {

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				c.setFont(new Font("Segoe UI", Font.BOLD, 15));
				if (row % 2 == 0 && !isCellSelected(row, col)) {
					c.setBackground(Color.white);
				} else if (!isCellSelected(row, col)) {
					c.setBackground(Color.decode("#F1F1F1"));
				} else {
					c.setBackground(Color.decode("#009FFF"));
				}
				if (isRowSelected(row) || (row == rollOverRowIndex)) {
					c.setForeground(Color.black);
					c.setBackground(Color.decode("#A9DFFF"));
				} else {
					c.setBackground(getBackground());
				}
				return c;

			}

		};
		table.setFillsViewportHeight(true);
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 5));

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.decode("#006ECA"));
		header.setForeground(Color.white);
		header.setOpaque(false);

		table.setModel(tableModel = new DefaultTableModel(new Object[][] {

		}, new String[] { "-", "Mã Tựa", "Tên Tựa", "Loại", "Giá thuê (Đồng)", "Hạn thuê (ngày)", "Phí trễ hạn (Đồng)",
				"Mô tả" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int column) {
				return (column == 0) ? Icon.class : Object.class;
			}

		});
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		scrollPane.setViewportView(table);
		table.setRowHeight(40);
//		table.getColumnModel().getColumn(0).setPreferredWidth(45);
//		table.removeColumn(table.getColumnModel().getColumn(6));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);

		btnThemTua = new JButton("Thêm Tựa");
		btnThemTua.setContentAreaFilled(false);
		btnThemTua.setOpaque(true);
		btnThemTua.setForeground(Color.white);
		btnThemTua.setBorder(null);
		btnThemTua.setBackground(Color.decode("#4CAF50"));
		btnThemTua.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnThemTua.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnThemTua.setBounds(889, 56, 121, 41);
		panel.add(btnThemTua);

		btnXoa = new JButton("Xóa tựa");
		btnXoa.setContentAreaFilled(false);
		btnXoa.setOpaque(true);
		btnXoa.setForeground(Color.white);
		btnXoa.setBorder(null);
		btnXoa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnXoa.setBackground(Color.decode("#FF2F50"));
		btnXoa.setBounds(1048, 56, 121, 41);
		panel.add(btnXoa);

		txtTim = new JTextField();
		txtTim.setBounds(10, 60, 690, 34);
		txtTim.setText("Nhập thông tin để tìm kiếm...");
		txtTim.setFont(new Font("Segoe UI", Font.BOLD, 15));
		txtTim.setColumns(10);
		txtTim.setForeground(Color.decode("#9E9E9E"));
		panel.add(txtTim);

		JLabel lblTimKhachHang = new JLabel(">>Tìm Tựa");
		lblTimKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTimKhachHang.setForeground(new Color(255, 0, 255));
		lblTimKhachHang.setBounds(10, 31, 600, 30);
		panel.add(lblTimKhachHang);

		btnThemTua.addActionListener(this);
		btnXoa.addActionListener(this);

		btnThemTua.addMouseListener(this);
		btnXoa.addMouseListener(this);

		txtTim.addKeyListener(this);
		txtTim.addFocusListener(this);

		table.addMouseMotionListener(this);
		table.addMouseListener(this);

		showAllTuaDia(listTua);
	}

	public void showAllTuaDia(List<TuaDia> list) {
		while (tableModel.getRowCount() >= 1)
			tableModel.removeRow(0);

		list.forEach(x -> {
			String loai = "Phim";
			if (x.getLoaiTua() == 2)
				loai = "Game";

			Object[] tx = { x.getTenTua(), x.getMaTua(), x.getTenTua(), loai, dmf.format(x.getGiaThue()),
					x.getSoNgayThue() + "", dmf.format(x.getPhiTreHan()), x.getMoTa() };
			tableModel.addRow(tx);
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		if (o.equals(btnThemTua)) {
			themTuaDL = new ThemTuaJDialog((Frame) SwingUtilities.getWindowAncestor(this));
			themTuaDL.setVisible(true);
			System.out.println("Funko");
			listTua = tuaDiaControl.getAllTuaDia(session);
			showAllTuaDia(listTua);
		}
		// if (o.equals(btnSuaTua)) {
		// if (table.getSelectedRow()>=0) {
		// String matuat =
		// table.getModel().getValueAt(table.getSelectedRow(),6).toString();
		// TuaDia t = tuaDiaControl.getTuaByID(matuat) ;
		// themTuaDL = new ThemTuaJDialog(parent,t);
		// themTuaDL.setVisible(true);
		// listTua = tuaDiaControl.getAllTuaDia();
		// showAllTuaDia(listTua);
		// if(table.getRowCount()>0) {
		// for (int i = 0; i < table.getRowCount(); i++) {
		// if(table.getModel().getValueAt(i, 6).equals(t.getMatua()))
		// table.setRowSelectionInterval(i, i);
		// }
		// }
		// }
		// else {
		// JOptionPane.showMessageDialog(this, "Chọn tựa để sửa");
		// }
		// }
		else if (o.equals(btnXoa)) {
			if (table.getSelectedRow() >= 0) {
				int c = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn xóa tựa đĩa này?");
				if (c == JOptionPane.YES_OPTION) {
					tuaDiaControl.xoaTua(table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), session);
					JOptionPane.showMessageDialog(this, "Xoá tựa thành công!");
					listTua = tuaDiaControl.getAllTuaDia(session);
					showAllTuaDia(listTua);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn tựa để xóa!");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemTua))
			btnThemTua.setBackground(Color.decode("#66D856"));
		if (o.equals(btnXoa))
			btnXoa.setBackground(Color.decode("#FF6B83"));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThemTua))
			btnThemTua.setBackground(Color.decode("#4CAF50"));

		if (o.equals(btnXoa))
			btnXoa.setBackground(Color.decode("#FF2F50"));

		if (o.equals(table)) {
			rollOverRowIndex = -1;
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object o = e.getSource();
		if (o.equals(txtTim)) {
			if (txtTim.getText().length() > 100) {
				JOptionPane.showMessageDialog(this, "Bạn đã nhập quá nhiều!");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object o = e.getSource();
		if (o.equals(txtTim)) {
			List<TuaDia> list = tuaDiaControl.timKiemTheoTuKhoa(txtTim.getText(), session);
			// seach
			showAllTuaDia(list);
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object o = e.getSource();
		if (o.equals(txtTim) && txtTim.getText().equals("Nhập thông tin để tìm kiếm...")) {
			txtTim.setText("");
			txtTim.setForeground(Color.black);
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object o = e.getSource();
		if (o.equals(txtTim) && txtTim.getText().equals("")) {
			txtTim.setText("Nhập thông tin để tìm kiếm...");
			txtTim.setForeground(Color.LIGHT_GRAY);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int row = table.rowAtPoint(e.getPoint());
		if (row != rollOverRowIndex) {
			rollOverRowIndex = row;
			repaint();
		}
	}
}
