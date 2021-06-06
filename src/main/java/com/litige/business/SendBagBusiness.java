package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.SendBag;

public class SendBagBusiness {
	private GeneralDao dao;

	public SendBagBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<SendBag> listAll() throws Exception {
		return dao.list(SendBag.class);
	}
	
	public void save(SendBag sendBag) throws Exception {
		dao.save(sendBag);
	}
	
	public void delete(SendBag sendBag) throws Exception {
		dao.delete(sendBag);
	}

}
