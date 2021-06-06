package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.GeneralDao;
import com.litige.dao.Destination;

public class DestinationBusinessTest {
	private GeneralDao dao;
	private DestinationBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new DestinationBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Destination> listDestination = business.listAll();
		
		assertFalse(listDestination.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Destination> listDestination = business.listAll();
		int sizeBefore = listDestination.size();
		
		Destination newDestination = new Destination();
		
		newDestination.setId(1);
		newDestination.setCoDest("FNA");
		newDestination.setName("Freetown");
		
		
		business.save(newDestination);
		
		listDestination = business.listAll();
		int sizeAfter = listDestination.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Destination destToDelete = new Destination();
		
		destToDelete.setId(1);	
		
		business.delete(destToDelete);
		
		List<Destination> listResponsable = business.listAll();
		
		assertFalse(listResponsable.contains(destToDelete));
	}

}
