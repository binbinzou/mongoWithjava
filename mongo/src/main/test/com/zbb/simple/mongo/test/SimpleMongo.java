package com.zbb.simple.mongo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.zbb.simlpe.mongo.dto.TUser;
import com.zbb.simple.mongo.dao.MongoDao;

public class SimpleMongo {
	
	@Test
	public void find(){
		MongoDao dao = new MongoDao();
		List<JSONObject> jsonObjects = dao.find("test20160722");
		for(JSONObject jsonObject:jsonObjects){
			System.out.println(JSONObject.toJSONString(jsonObject));
		}
	}
	@Test
	public void insert(){
		MongoDao dao = new MongoDao();
		TUser user = new TUser("test",22);
		Document document = user.toDocument();
		dao.insertOne("test20160722", document);
	}
	@Test
	public void insertOneWithOptions(){
		MongoDao dao = new MongoDao();
		TUser user = new TUser("上海虹桥",23);
		Document document = user.toDocument();
		dao.insertOneWithOptions("test20160722", document,true);
	}
	@Test
	public void insertMany(){
		MongoDao dao = new MongoDao();
		TUser user = new TUser("上海南站",24);
		List<Document> list = new ArrayList<Document>();
		Document document = user.toDocument();
		list.add(document);
		user = new TUser("上海浦东", 25);
		document = user.toDocument();
		list.add(document);
		dao.insertMany("test20160722", list);
	}
	@Test
	public void update(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "上海浦东");
		map.put("age", 25);
		TUser user = new TUser("上海浦东机场",26);
		dao.updateOne("test20160722", map, user.toDocument());
		
	}
	
	
	
}
