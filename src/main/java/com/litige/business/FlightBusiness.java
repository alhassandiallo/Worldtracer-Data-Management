package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.Flight;

public class FlightBusiness {
	private GeneralDao dao;

	public FlightBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Flight> listAll() throws Exception {
		return dao.list(Flight.class);
	}
	
	public void save(Flight flight) throws Exception {
		dao.save(flight);
	}
	
	public void delete(Flight flight) throws Exception {
		dao.delete(flight);
	}

}
