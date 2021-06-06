package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.Agent;
import com.litige.dao.GeneralDao;
import com.litige.dao.Delivery;

public class DeliveryBusinesstest {
	private GeneralDao dao;
	private DeliveryBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new DeliveryBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Delivery> listDelivery = business.listAll();
		
		assertFalse(listDelivery.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Delivery> listDelivery = business.listAll();
		int sizeBefore = listDelivery.size();
		
		Delivery delivery = new Delivery();
		Agent agent =  new Agent();
		Date date = new Date();
		
		delivery.setDeliveryId(1);
		delivery.setDateDelivery(date);
		delivery.setTagNumber("AT790674");
		delivery.setWeight(22.8);
		delivery.setName("Oumarou Moussa");
		delivery.setPhone("07098536539");
		delivery.setAddress("Matoto Yimbaya");
		delivery.setAgent(agent);
		
		
		business.save(delivery);
		
		listDelivery = business.listAll();
		int sizeAfter = listDelivery.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Delivery deliveryToDelete = new Delivery();
		
		deliveryToDelete.setDeliveryId(1);	
		
		business.delete(deliveryToDelete);
		
		List<Delivery> listDelivery = business.listAll();
		
		assertFalse(listDelivery.contains(deliveryToDelete));
	}
}
