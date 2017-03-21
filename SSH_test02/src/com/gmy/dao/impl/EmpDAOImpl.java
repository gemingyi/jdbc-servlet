package com.gmy.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gmy.dao.IEmpDAO;
import com.gmy.factory.HibernateSessionFactory;
import com.gmy.vo.Emp;

@Repository("empDAO")
public class EmpDAOImpl implements IEmpDAO {
	
	private SessionFactory sessionFactory ;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public boolean doCreate(Emp vo) throws Exception {
		boolean result = false;
		Session session = this.getSession() ;
		Transaction tx = session.beginTransaction() ;
		session.save(vo);
		tx.commit();
		result = true;
		HibernateSessionFactory.closeSession();
		return result;
	}

	@Override
	public boolean doUpdate(Emp vo) throws Exception {
		boolean result = false;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		session.update(vo);
		tx.commit();
		result = true;
		HibernateSessionFactory.closeSession();
		return result;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		if(ids.size() == 0) {
			return false;
		}
		boolean result = false;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM Emp WHERE id IN(");
		Iterator<Integer> iter = ids.iterator();
		while (iter.hasNext()) {
			sb.append(iter.next()).append(",");
		}
		sb.delete(sb.length()-1, sb.length()).append(")");
		session.createQuery(sb.toString()).executeUpdate();
		tx.commit();
		result = true;
		HibernateSessionFactory.closeSession();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Emp> findAll(Integer currentPage, Integer lineSize)
			throws Exception {
		List<Emp> empList = null;
		Session session = this.getSession();
		Transaction tx = session.beginTransaction();
		String hql = "FROM Emp" ;
		Query query = session.createQuery(hql) ;
		query.setFirstResult((currentPage - 1) * lineSize).setMaxResults(lineSize);
        empList = query.list();
        tx.commit();
        HibernateSessionFactory.closeSession();
		return empList;
	}

	@Override
	public Integer getAllCount() throws Exception {
		Session session = this.getSession();
		String hql = "SELECT COUNT(id) FROM Emp" ;
		Query query = session.createQuery(hql) ;
		Number count = (Number) query.uniqueResult();
		return count.intValue();
	}

}
