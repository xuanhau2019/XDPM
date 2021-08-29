package se.iuh.controller;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class GeneralCRUD<T> {
	public boolean insert(T object, Session session) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(object);
			
			transaction.commit();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
			return false;
		}
	}
	
	public boolean update(T object, Session session) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(object);
			
			transaction.commit();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
			return false;
		}
	}
	
	public boolean delete(T object, Session session) {
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.remove(object);
			
			transaction.commit();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			transaction.rollback();
			return false;
		}
	}
	
	public List<T> getAll(Class<T> type, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		criteria.from(type);
		List<T> data = session.createQuery(criteria).getResultList();
		return data;
	}
}
