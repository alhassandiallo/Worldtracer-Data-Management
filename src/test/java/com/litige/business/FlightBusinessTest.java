package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.GeneralDao;
import com.litige.dao.Flight;

public class FlightBusinessTest {
	private GeneralDao dao;
	private FlightBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new FlightBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Flight> listFlight = business.listAll();
		
		assertFalse(listFlight.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Flight> listFlight = business.listAll();
		int sizeBefore = listFlight.size();
		
		Flight newFlight = new Flight();
		Date date =  new Date();
		
		newFlight.setCodeFlight("AF596");
		newFlight.setDateFlight(date);
		newFlight.setNumberOfBags(5);
		
		
		business.save(newFlight);
		
		listFlight = business.listAll();
		int sizeAfter = listFlight.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Flight flightToDelete = new Flight();
		
		flightToDelete.setCodeFlight("AF596");	
		
		business.delete(flightToDelete);
		
		List<Flight> listFlight = business.listAll();
		
		assertFalse(listFlight.contains(flightToDelete));
	}


}
