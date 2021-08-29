package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.PhiTreHanController;
import se.iuh.controller.ThanhToanTreHanController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.PhiTreHan;

public class GhiNhanTraPhiTreHanGUI extends JDialog implements ActionListener{
	private JPanel contentPane1;
	private JTable tablePTH1;
	private DefaultTableModel tmPTH1;
	private JButton btnXacNhanThanhToan;
	private JLabel lblTongTien1;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private ThanhToanTreHanController thanhToanTreHanController = new ThanhToanTreHanController();
	private PhiTreHanController phiTreHanController = new PhiTreHanController();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GhiNhanTraPhiTreHanGUI(List<PhiTreHan> listPTH, String tongTien, Frame parent) {
		super(parent, true);
		setBounds(100, 100, 586, 450);
		setPreferredSize(new Dimension(1100, 750));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Xác nhận thanh toán phí trễ hạn");
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane1);
		
		JPanel panel = new JPanel();
		contentPane1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 4));
		panel.add(verticalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tablePTH1 = new JTable();
		
		tablePTH1.setModel(tmPTH1 = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã phí trễ hạn", "Mã phiếu trả", "Tên tựa đĩa", "Ngày thuê", "Ngày trả", "Phí trễ hạn"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
        
		scrollPane.setViewportView(tablePTH1);
		
		JPanel pnlFooter = new JPanel();
		contentPane1.add(pnlFooter, BorderLayout.SOUTH);
		
		lblTongTien1 = new JLabel();
		lblTongTien1.setFont(new Font("Courier", Font.BOLD,12));
		pnlFooter.add(lblTongTien1);
		
		pnlFooter.setBorder( BorderFactory.createEmptyBorder(0,0,30,30) );
		
		btnXacNhanThanhToan = new JButton("Xác nhận thanh toán");
		btnXacNhanThanhToan.setPreferredSize(new Dimension(500, 25));
		btnXacNhanThanhToan.addActionListener(this);
		btnXacNhanThanhToan.setEnabled(true);
		pnlFooter.add(btnXacNhanThanhToan);
		loadDataVaoTable(listPTH);
		
		lblTongTien1.setText(tongTien);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnXacNhanThanhToan)) {
			for(int count = 0; count < tmPTH1.getRowCount(); count++)
			{
				PhiTreHan phiTreHan = phiTreHanController.getPhiTreHan(String.valueOf(tmPTH1.getValueAt(count, 1)), session);
				thanhToanTreHanController.ghiNhanThanhToanPTH(phiTreHan);
			}
			JOptionPane.showMessageDialog(this, "Thanh toán phí trễ hạn thành công");
			tmPTH1.setRowCount(0);
			btnXacNhanThanhToan.setEnabled(false);
			lblTongTien1.setText("TỔNG TIỀN: ");
		}
		
	}
	private void loadDataVaoTable(List<PhiTreHan> listPTH)	{
		int stt = 1;
		tmPTH1.setRowCount(0);
		for (PhiTreHan pth : listPTH) {
			Object[] tx = {
					String.format("%d", stt++),
					pth.getMaPhiTreHan(),
					pth.getPhieuTra().getMaPhieuTra(),
					pth.getPhieuTra().getDia().getTuaDia().getTenTua(),
					pth.getPhieuTra().getPhieuThue().getNgayThue().toString(),
					pth.getPhieuTra().getNgayTra().toString(),
					String.valueOf(pth.getPhiTreHan())
			};
			tmPTH1.addRow(tx);
		}
	}
}
