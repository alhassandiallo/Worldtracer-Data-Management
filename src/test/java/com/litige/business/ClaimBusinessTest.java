package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.GeneralDao;
import com.litige.dao.Agent;
import com.litige.dao.Claim;
import com.litige.dao.Delivery;

public class ClaimBusinessTest {
	private GeneralDao dao;
	private ClaimBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new ClaimBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Claim> listClaim = business.listAll();
		
		assertFalse(listClaim.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Claim> listClaim = business.listAll();
		int sizeBefore = listClaim.size();
		
		Claim claim = new Claim();
		Date date = new Date();
		Agent agent = new Agent();
		Delivery delivery = new Delivery();
		
		claim.setClaimId(1);
		claim.setPassengerName("Kentaro Fujimaru");
		claim.setDateClaim(date);
		claim.setNumberOfBags(3);
		claim.setPassengerAddress("Suita city");
		claim.setPhone("0786359376");
		claim.setAgent(agent);
		claim.setDelivery(delivery);
		
		
		business.save(claim);
		
		listClaim = business.listAll();
		int sizeAfter = listClaim.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Claim claimToDelete = new Claim();
		
		claimToDelete.setClaimId(1);	
		
		business.delete(claimToDelete);
		
		List<Claim> listClaim = business.listAll();
		
		assertFalse(listClaim.contains(claimToDelete));
	}

}
