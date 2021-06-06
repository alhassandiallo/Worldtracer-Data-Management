package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.Agent;

public class AgentBusiness {
	private GeneralDao dao;

	public AgentBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Agent> listAll() throws Exception {
		return dao.list(Agent.class);
	}
	
	public void save(Agent agent) throws Exception {
		dao.save(agent);
	}
	
	public void delete(Agent agent) throws Exception {
		dao.delete(agent);
	}

}
