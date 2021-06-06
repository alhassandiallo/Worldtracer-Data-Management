package com.litige.business;

import java.util.List;

import com.litige.dao.Luggage;
import com.litige.dao.GeneralDao;

public class LuggageBusiness {
	private GeneralDao dao;

	public LuggageBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Luggage> listAll() throws Exception {
		return dao.list(Luggage.class);
	}
	
	public void save(Luggage bag) throws Exception {
		dao.save(bag);
	}
	
	public void delete(Luggage bag) throws Exception {
		dao.delete(bag);
	}

}
