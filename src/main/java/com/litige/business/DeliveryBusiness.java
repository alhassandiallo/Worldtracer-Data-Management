package com.litige.business;

import java.util.List;

import com.litige.dao.Delivery;
import com.litige.dao.GeneralDao;


public class DeliveryBusiness {
	private GeneralDao dao;

	public DeliveryBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Delivery> listAll() throws Exception {
		return dao.list(Delivery.class);
	}
	
	public void save(Delivery delivery) throws Exception {
		dao.save(delivery);
	}
	
	public void delete(Delivery delivery) throws Exception {
		dao.delete(delivery);
	}

}
