package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.Destination;

public class DestinationBusiness {
	private GeneralDao dao;

	public DestinationBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Destination> listAll() throws Exception {
		return dao.list(Destination.class);
	}
	
	public void save(Destination dest) throws Exception {
		dao.save(dest);
	}
	
	public void delete(Destination dest) throws Exception {
		dao.delete(dest);
	}

}
