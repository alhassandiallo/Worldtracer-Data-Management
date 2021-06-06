package com.litige.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.litige.dao.GeneralDao;
import com.litige.dao.Agent;

public class AgentBusinessTest {
	private GeneralDao dao;
	private AgentBusiness business;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
		business = new AgentBusiness(dao);
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testListAll() throws Exception {
		List<Agent> listAgent = business.listAll();
		
		assertFalse(listAgent.isEmpty());
	}

	@Test
	public void testSave() throws Exception {
		List<Agent> listAgent = business.listAll();
		int sizeBefore = listAgent.size();
		
		Agent agent = new Agent();
		
		agent.setId(1);
		agent.setName("Toure Caramba");
		agent.setPhone("669085368");
		agent.setAddress("Ratoma Cosa");
		agent.setPassword("heishs53");
		
		
		business.save(agent);
		
		listAgent = business.listAll();
		int sizeAfter = listAgent.size();
		
		assertTrue(sizeAfter == sizeBefore + 1);
	}

	@Test
	public void testDelete() throws Exception {
		Agent agentToDelete = new Agent();
		
		agentToDelete.setId(1);	
		
		business.delete(agentToDelete);
		
		List<Agent> listAgent = business.listAll();
		
		assertFalse(listAgent.contains(agentToDelete));
	}


}
