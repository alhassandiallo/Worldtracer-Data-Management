package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.Agent;
import com.litige.dao.Claim;
import com.litige.dao.Delivery;
import com.litige.dao.Flight;
import com.litige.dao.GeneralDao;
import com.litige.dao.Luggage;
import com.litige.dao.Pilferage;
import com.litige.dao.SendBag;

public class LuggageBusinessTest {
	private GeneralDao dao;
	private LuggageBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new LuggageBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Luggage> listBagage = business.listAll();
		
		assertFalse(listBagage.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Luggage> listBagage = business.listAll();
		int sizeBefore = listBagage.size();
		
		Luggage newBag = new Luggage(1, "AF768934", "Diallo", 23.5, "suitcase", "black", "good state");
		//Claim claim = new Claim(1, "Barry", new Date(), 1, "Suita", "654783924");
		//Pilferage pilf = new Pilferage("AT2341", "Nakamura", "AT758964", 23.0, 22.8, "the bag was open");
		//Flight flight =  new Flight("AF596", new Date(), 1);
		//Delivery delivery =  new Delivery(1, new Date(), "AF273859", 23.0, "Diallo", );
		//Agent agent =  new Agent();
		//SendBag sendBag = new SendBag();
		
		//newBag.setNumBag(1);
		//newBag.setTagNumber("AF768934");
		//newBag.setNameOnTag("Diallo");
		//newBag.setReceivedWeight(23.5);
		//newBag.setType("suitcase");
		//newBag.setColor("black");
		//newBag.setState("good state");
		//newBag.setClaim(claim);
		//newBag.setFlight(flight);
		//newBag.setPilferage(pilf);
		//newBag.setAgent(agent);
		//newBag.setDelivery(delivery);
		//newBag.setSendBag(sendBag);
		
		business.save(newBag);
		
		listBagage = business.listAll();
		int sizeAfter = listBagage.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Luggage bagToDelete = new Luggage();
		
		bagToDelete.setNumBag(1);	
		
		business.delete(bagToDelete);
		
		List<Luggage> listBagage = business.listAll();
		
		assertFalse(listBagage.contains(bagToDelete));
	}

}
