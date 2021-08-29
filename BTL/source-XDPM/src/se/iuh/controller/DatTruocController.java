package se.iuh.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import se.iuh.model.DatTruoc;
import se.iuh.model.Dia;

public class DatTruocController extends GeneralCRUD<DatTruoc> {
	
	public List<DatTruoc> getDatTruocTheoMaTua(String maTua, Session session) {
		String hql = "From DatTruoc d where d.tuaDatTruoc.maTua = :maTua";
		Query<DatTruoc> query = session.createQuery(hql);
		query.setParameter("maTua", maTua);
		List<DatTruoc> list = query.list();
		return list;
	}
	
	public void themDatTruoc(DatTruoc datTruoc, Session session) {
		insert(datTruoc, session);
	}
	public DatTruoc getDatTruoc(String maDatTruoc, Session session) {
		return session.get(DatTruoc.class, maDatTruoc);
	}
}
