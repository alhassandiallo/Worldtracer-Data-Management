package com.litige.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneralDaoTest {
private GeneralDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new GeneralDao();
		dao.connect();
	}

	@After
	public void tearDown() throws Exception {
		dao.close();
	}

	@Test
	public void testConnect() throws Exception {
		GeneralDao dao = new GeneralDao();
		dao.connect();
		dao.close();
	}

}
