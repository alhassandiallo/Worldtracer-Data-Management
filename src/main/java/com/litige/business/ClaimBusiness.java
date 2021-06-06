package com.litige.business;

import java.util.List;

import com.litige.dao.GeneralDao;
import com.litige.dao.Claim;

public class ClaimBusiness {
	private GeneralDao dao;

	public ClaimBusiness(GeneralDao dao) {
		this.dao = dao;
	}

	public List<Claim> listAll() throws Exception {
		return dao.list(Claim.class);
	}
	
	public void save(Claim claim) throws Exception {
		dao.save(claim);
	}
	
	public void delete(Claim claim) throws Exception {
		dao.delete(claim);
	}

}
