package se.iuh.controller;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import se.iuh.database.HibernateUtil;
import se.iuh.model.NhanVien;
import se.iuh.model.PhiTreHan;
import se.iuh.model.ThanhToanTreHan;
import se.iuh.utility.GlobalConfig;

public class ThanhToanTreHanController extends GeneralCRUD<ThanhToanTreHan> {
	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	Session session = sessionFactory.openSession();
	
	public void ghiNhanThanhToanPTH(PhiTreHan phiTreHan)
	{
		
		session = sessionFactory.openSession();
		Calendar c = Calendar.getInstance();
		Date ngayThanhToan = new Date();
		int year = c.get(Calendar.YEAR);
		 
	    int month = c.get(Calendar.MONTH)+1;
	    int day = c.get(Calendar.DAY_OF_MONTH);
	    int hour = c.get(Calendar.HOUR_OF_DAY);
	    int minute = c.get(Calendar.MINUTE);
//	    int second = c.get(Calendar.SECOND);
	    int mils = c.get(Calendar.MILLISECOND);
	    String maThanhToan = "TT"+year+month+day+hour+minute+mils;
	    // tạo nhân viên để test
	    NhanVien nhanVien = new NhanVienController().getNhanVien(GlobalConfig.maNV, session);
		ThanhToanTreHan thanhToanTreHan = new ThanhToanTreHan(maThanhToan, ngayThanhToan, nhanVien, phiTreHan);
		
		ThanhToanTreHanController thanhToanTreHanController = new ThanhToanTreHanController();
		thanhToanTreHanController.insert(thanhToanTreHan, session);
		session.close();
	}
}
