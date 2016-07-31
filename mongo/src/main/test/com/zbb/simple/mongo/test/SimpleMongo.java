package com.zbb.simple.mongo.test;

import org.junit.Test;

import com.zbb.simple.mongo.dao.MongoDao;

public class SimpleMongo {
	
	@Test
	public void find(){
		MongoDao dao = new MongoDao();
		dao.find("test20160722", 0, 1);
	}
	
}
