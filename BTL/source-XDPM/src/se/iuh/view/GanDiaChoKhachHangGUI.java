package se.iuh.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import se.iuh.model.KhachHang;

public class GanDiaChoKhachHangGUI extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane1;
	private JTable tableKH;
	private DefaultTableModel tmKH;
	private JLabel lblTenTuaDia;
	
	public GanDiaChoKhachHangGUI(List<KhachHang> listKH, String tenTuaDia) {
		setBounds(100, 100, 586, 450);
		setPreferredSize(new Dimension(1100, 750));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Thông tin đặt trước");
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane1.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane1);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		contentPane1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 4));
		panel.add(verticalStrut);
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		tableKH = new JTable();
		
		
		tableKH.setModel(tmKH = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Địa chỉ"}) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3023736029136595975L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
        
        tableKH.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                System.out.println();
                hienThiDiaglog(tableKH.getValueAt(tableKH.getSelectedRow(), 1).toString(),tenTuaDia);
            }
        });
        
		scrollPane.setViewportView(tableKH);
		
		lblTenTuaDia = new JLabel();
		lblTenTuaDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenTuaDia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane1.add(lblTenTuaDia, BorderLayout.NORTH);
		lblTenTuaDia.setText("TÊN TỰA ĐĨA : "+ tenTuaDia);
		
		loadDataVaoTable(listKH);
		
	}

	protected void hienThiDiaglog(String Kh, String tenTua) {
		int option = JOptionPane.showConfirmDialog(this,
				"Xác nhận khách hàng "+ tableKH.getValueAt(tableKH.getSelectedRow(), 1).toString() + " đồng ý đặt trước tiêu đề này", "Thông báo",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Giữ đĩa cho khách hàng thành công");
			this.dispose();
		}
	}
	
	private void loadDataVaoTable(List<KhachHang> listKH)	{
		int stt = 1;
		tmKH.setRowCount(0);
		for (KhachHang kh : listKH) {
			Object[] tx = {
					String.format("%d", stt++),
					kh.getMaKH(),
					kh.getTenKH(),
					kh.getSoDT(),
					kh.getDiaChi()
			};
			tmKH.addRow(tx);
		}
	}
}
