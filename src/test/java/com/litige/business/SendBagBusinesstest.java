package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.SendBag;
import com.litige.dao.Agent;
import com.litige.dao.Destination;
import com.litige.dao.GeneralDao;

public class SendBagBusinesstest {
	private GeneralDao dao;
	private SendBagBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new SendBagBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<SendBag> listSendBag = business.listAll();
		
		assertFalse(listSendBag.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<SendBag> listSendBag = business.listAll();
		int sizeBefore = listSendBag.size();
		
		SendBag sendBag = new SendBag();
		Date date =  new Date();
		Destination destination =  new Destination();
		Agent agent =  new Agent();
		
		sendBag.setCoSendBag("AT10564");
		sendBag.setDateSent(date);
		sendBag.setTagRush("AT990674");
		sendBag.setWeight(22.8);
		sendBag.setDestination(destination);
		sendBag.setAgent(agent);
		
		
		business.save(sendBag);
		
		listSendBag = business.listAll();
		int sizeAfter = listSendBag.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		SendBag sendBagToDelete = new SendBag();
		
		sendBagToDelete.setCoSendBag("AT10564");	
		
		business.delete(sendBagToDelete);
		
		List<SendBag> listSendBag = business.listAll();
		
		assertFalse(listSendBag.contains(sendBagToDelete));
	}

}
