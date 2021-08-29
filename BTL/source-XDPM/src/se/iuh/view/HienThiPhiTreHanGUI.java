package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.controller.KhachHangController;
import se.iuh.controller.PhiTreHanController;
import se.iuh.controller.PhieuTraController;
import se.iuh.database.HibernateUtil;
import se.iuh.model.PhiTreHan;
import se.iuh.model.PhieuTra;

public class HienThiPhiTreHanGUI extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tablePTH;
	private DefaultTableModel tmPTH;
	private JLabel lblMaKhachHang;
	private JTextField txtMaKhachHang;
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.openSession();
	private List<PhiTreHan> listPTH = new ArrayList<PhiTreHan>();
	private List<PhiTreHan> listPTHChecked;
	private PhiTreHanController phiTreHanController = new PhiTreHanController();
	private JButton btnKiemTraPTH;
	private KhachHangController khachHangController = new KhachHangController();
	private PhieuTraController phieuTraController = new PhieuTraController();
	private JButton btnThanhToan;
	private double tongTien = 0;
	private JLabel lblTongTien;
	private static final int BOOLEAN_COLUMN = 0;
	
	public HienThiPhiTreHanGUI(Frame parent) {
		super(parent, true);
		setModal(true);
		setBounds(100, 100, 680, 450);
		setPreferredSize(new Dimension(1100, 750));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thông tin phí trễ hạn");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 4));
		panel.add(verticalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		
		tmPTH = new DefaultTableModel(null, new String [] {"", "Mã phí trễ hạn", "Mã phiếu trả", "Tên tựa đĩa", "Ngày thuê", "Ngày trả", "Phí trễ hạn"}) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public Class<?> getColumnClass(int c) {
				 return c == BOOLEAN_COLUMN ? Boolean.class : String.class;
            } };
        tablePTH = new JTable(tmPTH);
		scrollPane.setViewportView(tablePTH);
		tablePTH.getModel().addTableModelListener(new CheckBoxModelListener());
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		lblMaKhachHang = new JLabel("Nhập ID khách hàng:");
		panelNorth.add(lblMaKhachHang);

		txtMaKhachHang = new JTextField();
		panelNorth.add(txtMaKhachHang, BorderLayout.NORTH);
		txtMaKhachHang.setColumns(25);

		btnKiemTraPTH = new JButton("Kiểm tra phí trễ hạn");
		btnKiemTraPTH.addActionListener(this);
		panelNorth.add(btnKiemTraPTH, BorderLayout.NORTH);
		
		
		
		JPanel pnlFooter = new JPanel();
		contentPane.add(pnlFooter, BorderLayout.SOUTH);
		
		lblTongTien = new JLabel("TỔNG TIỀN : ");
		lblTongTien.setFont(new Font("Courier", Font.BOLD,12));
		pnlFooter.add(lblTongTien);
		
		pnlFooter.setBorder( BorderFactory.createEmptyBorder(0,0,30,30) );
		
		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setPreferredSize(new Dimension(500, 25));
		btnThanhToan.addActionListener(this);
		btnThanhToan.setEnabled(false);
		pnlFooter.add(btnThanhToan);
	}

	private void loadDataVaoTable()	{
		tmPTH.setRowCount(0);
		listPTH = phiTreHanController.getListPhiTreHanChuaTraTheoMaKH(txtMaKhachHang.getText().toString(), session);
		if(txtMaKhachHang.getText().toString().equals(""))
		{
			JOptionPane.showMessageDialog(this, "Chưa có mã khách hàng");
			btnThanhToan.setEnabled(false);
		}else if(khachHangController.getKhachHang(txtMaKhachHang.getText().toString(), session)==null)
		{
			JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại");
			btnThanhToan.setEnabled(false);
		}else if(listPTH.isEmpty()){
			JOptionPane.showMessageDialog(this, "Khách hàng ID "+txtMaKhachHang.getText().toString()+" không nợ phí trễ hạn" );
			btnThanhToan.setEnabled(false);
		}else{
			for(PhiTreHan pth : listPTH) {
				Object[] tx = {
						false,
						pth.getMaPhiTreHan(),
						pth.getPhieuTra().getMaPhieuTra(),
						pth.getPhieuTra().getDia().getTuaDia().getTenTua(),
						pth.getPhieuTra().getPhieuThue().getNgayThue().toString(),
						pth.getPhieuTra().getNgayTra().toString(),
						String.valueOf(pth.getPhiTreHan())
				};
				tmPTH.addRow(tx);
		}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnKiemTraPTH))
		{
			
			loadDataVaoTable();
			
		}
		else if(e.getSource().equals(btnThanhToan))
		{
			dispose();
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GhiNhanTraPhiTreHanGUI frame = new GhiNhanTraPhiTreHanGUI(listPTHChecked, lblTongTien.getText().toString(), null);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
		}
	}
	public class CheckBoxModelListener implements TableModelListener {

        public void tableChanged(TableModelEvent e) {
        	listPTHChecked = new ArrayList<PhiTreHan>();
            int row = tmPTH.getRowCount();
            for(int num = 0 ; num < row; num++)
            {
            	PhieuTra phieuTra = phieuTraController.getPhieuTraTheoMa( String.valueOf(tmPTH.getValueAt(num, 2)), session);
            	PhiTreHan phiTreHan = new PhiTreHan(String.valueOf(tmPTH.getValueAt(num, 1)), phieuTra, Double.parseDouble(String.valueOf(tmPTH.getValueAt(num, 6))));
            	int column = e.getColumn();
                if (column == BOOLEAN_COLUMN) {
                    TableModel model = (TableModel) e.getSource();
                    Boolean checked = (Boolean) model.getValueAt(num, column);
                    if (checked) {
                    	tongTien = tongTien + Double.parseDouble((String) tmPTH.getValueAt(num, 6));
                    	listPTHChecked.add(phiTreHan);
                    	btnThanhToan.setEnabled(true);
                    } else {
                    	if(listPTHChecked!=null)
                    	{
                    		listPTHChecked.remove(phiTreHan);
                    		btnThanhToan.setEnabled(false);
                    	}
                    	else {
                    		tongTien = tongTien - Double.parseDouble((String) tmPTH.getValueAt(num, 6));
                    		tongTien=0;
                    		listPTHChecked = null;
                    		System.out.println("List rỗng");
                    		btnThanhToan.setEnabled(false);
                    	}
                    }
                }
            }
            lblTongTien.setText("TỔNG TIỀN : "+String.format("%.0f Đ", tongTien));
            tongTien = 0;
//				for (PhiTreHan phiTreHan : listPTHChecked) {
//					System.out.println(phiTreHan);
//			}
//				System.out.println("--------------------------------------------------");
        }
    }
	
}
