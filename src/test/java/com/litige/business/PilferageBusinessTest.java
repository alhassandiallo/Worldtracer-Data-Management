package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.Agent;
import com.litige.dao.GeneralDao;
import com.litige.dao.Pilferage;

public class PilferageBusinessTest {
	private GeneralDao dao;
	private PilferageBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new PilferageBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Pilferage> listPilferage = business.listAll();
		
		assertFalse(listPilferage.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Pilferage> listPilferage = business.listAll();
		int sizeBefore = listPilferage.size();
		
		Pilferage pilf = new Pilferage();
		Agent agent =  new Agent();
		
		pilf.setCodePilf("AF28935");
		pilf.setPassengerName("Oumarou Moussa");
		pilf.setTagNumber("AT790674");
		pilf.setWeight(22.8);
		pilf.setDeliveredWeight(22.7);
		pilf.setReason("Broken suitcase");
		pilf.setAgent(agent);
		
		
		business.save(pilf);
		
		listPilferage = business.listAll();
		int sizeAfter = listPilferage.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Pilferage pilfToDelete = new Pilferage();
		
		pilfToDelete.setCodePilf("AF28935");	
		
		business.delete(pilfToDelete);
		
		List<Pilferage> listSendBag = business.listAll();
		
		assertFalse(listSendBag.contains(pilfToDelete));
	}
}
