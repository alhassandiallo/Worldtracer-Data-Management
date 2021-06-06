package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.Pilferage;


public class PilferageBusiness {
	private GeneralDao dao;

	public PilferageBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Pilferage> listAll() throws Exception {
		return dao.list(Pilferage.class);
	}
	
	public void save(Pilferage pilf) throws Exception {
		dao.save(pilf);
	}
	
	public void delete(Pilferage pilf) throws Exception {
		dao.delete(pilf);
	}

}
