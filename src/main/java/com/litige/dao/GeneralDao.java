package com.litige.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class GeneralDao {
	private Session session;
	private SessionFactory sessionFactory;
	
	public void connect() throws Exception {
		StandardServiceRegistry registry
			= new StandardServiceRegistryBuilder().configure().build();
		
		try {
			sessionFactory
				= new MetadataSources(registry).buildMetadata().buildSessionFactory();
			
			session = sessionFactory.openSession();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public void close() throws Exception {
		if (session != null) {
			session.close();
			sessionFactory.close();
		}
	}

	public int save(Object obj) throws Exception {
		session.beginTransaction();
		int id = (int) session.save(obj);
		session.getTransaction().commit();
		
		return id;
	}
	
	public void saveOrUpdate(Object obj) throws Exception {
		session.beginTransaction();
		session.saveOrUpdate(obj);;
		session.getTransaction().commit();
	}
	
	public void update(Object obj) throws Exception {
		session.beginTransaction();
		session.update(obj);;
		session.getTransaction().commit();
		
	}
	
	public void delete(Object obj) throws Exception {
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
	}
	
	public <T> List<T> list(Class<T> type) throws Exception {
		CriteriaBuilder builder = session.getCriteriaBuilder();
	    CriteriaQuery<T> criteria = builder.createQuery(type);
	    criteria.from(type);
	    List<T> result = session.createQuery(criteria).getResultList();
	    return result;
	}
	
	public <T> T get(Class<T> type, Serializable id) {		
		return (T) session.get(type, id);
	}

}
