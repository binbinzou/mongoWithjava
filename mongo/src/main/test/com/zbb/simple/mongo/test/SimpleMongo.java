package com.zbb.simple.mongo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.conversions.Bson;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.zbb.simlpe.mongo.dto.TUser;
import com.zbb.simple.mongo.dao.MongoDao;
import com.zbb.simple.mongo.util.FiltersFactory;
import com.zbb.simple.mongo.util.FiltersType;

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
	public void findWithFilter(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "zbb");
		Bson filterBson = FiltersFactory.getFilters(map).returnFilters(map);
		List<JSONObject> jsonObjects = dao.findWithFilter("test20160722",filterBson);
		for(JSONObject jsonObject:jsonObjects){
			System.out.println(JSONObject.toJSONString(jsonObject));
		}
	}
	@Test
	public void findWithFilterNot(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "zbb");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		map.clear();
		map.put("type", FiltersType.NOT);
		map.put("one", filterBsonfir);
		Bson filterBsonsec = FiltersFactory.getFilters(map).returnFilters(map);
		List<JSONObject> jsonObjects = dao.findWithFilter("test20160722",filterBsonsec);
		for(JSONObject jsonObject:jsonObjects){
			System.out.println(JSONObject.toJSONString(jsonObject));
		}
	}
	@Test
	public void findOneAndDelete(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "admin");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		Document document = (Document) dao.findOneAndDelete("test20160722",filterBsonfir);
		System.out.println(document.toJson());
	}
	@Test
	public void findOneAndReplace(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "test");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		TUser user = new TUser("test",32);
		Document userDocument = user.toDocument();
		Document document = (Document) dao.findOneAndReplace("test20160722",filterBsonfir,userDocument);
		System.out.println(document.toJson());
	}
	@Test
	public void findOneAndUpdate(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "test1");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		TUser user = new TUser("test",32);
		Document userDocument = user.toDocument();
		Bson[] updates = new Document[1];
		updates[0] = userDocument;
		//Bson bson = Updates.combine(updates);
		Bson bson = Updates.unset("test");
		Document document = (Document) dao.findOneAndUpdate("test20160722",filterBsonfir,bson);
		System.out.println(document.toJson());
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
	public void updateOne(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "testUpdateMany");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		TUser user = new TUser("testUpdate",26);
		long l = dao.updateOne("test20160722", filterBsonfir, user.toUpdate());
		System.out.println(l);
	}
	@Test
	public void updateMany(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "testUpdate");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		TUser user = new TUser("testUpdateMany",28);
		long l = dao.updateMany("test20160722", filterBsonfir, user.toUpdate());
		System.out.println(l);
	}
	@Test
	public void count(){
		MongoDao dao = new MongoDao();
		long l = dao.count("test20160722");
		System.out.println(l);
	}
	@Test
	public void countFilter(){
		MongoDao dao = new MongoDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", FiltersType.EQ);
		map.put("one", "name");
		map.put("two", "testUpdateMany");
		Bson filterBsonfir = FiltersFactory.getFilters(map).returnFilters(map);
		long l = dao.count("test20160722", filterBsonfir);
		System.out.println(l);
	}
	@Test
	public void createIndex(){
		MongoDao dao = new MongoDao();
		List<String> list = new ArrayList<String>();
		list.add("name");
		//Bson index = Indexes.ascending(list);
		Bson index = Indexes.descending(list);
		String indexS = dao.createIndex("test20160722", index);
		System.out.println(indexS);
	}
	@Test
	public void dropIndex(){
		MongoDao dao = new MongoDao();
		List<String> list = new ArrayList<String>();
		list.add("name");
		Bson index = Indexes.ascending(list);
		dao.dropIndex("test20160722", index);
	}
	@Test
	public void dropIndexByName(){
		MongoDao dao = new MongoDao();
		dao.dropIndex("test20160722", "name_-1");
	}
	@Test
	public void listIndexs(){
		MongoDao dao = new MongoDao();
		ListIndexesIterable<Document> listIndexesIterable = dao.listIndexs("test20160722");
		for(Document document:listIndexesIterable){
			System.out.println(document);
		}
	}
	@Test
	public void dropIndexs(){
		MongoDao dao = new MongoDao();
		dao.dropIndexs("test20160722");
	}
	@Test
	public void createIndexs(){
		MongoDao dao = new MongoDao();
		List<String> list = new ArrayList<String>();
		List<Bson> bsons = new ArrayList<Bson>();
		list.add("name");
		//Bson index = Indexes.ascending(list);
		Bson indexName = Indexes.descending(list);
		list.clear();
		list.add("age");
		Bson indexAge = Indexes.ascending(list);
		bsons.add(indexName);
		bsons.add(indexAge);
		List<String> indexS = dao.createIndexs("test20160722", bsons);
		for(String string : indexS){
			System.out.println(string);
		}
	}
	@Test
	public void distinct(){
		MongoDao dao = new MongoDao();
		DistinctIterable<String> iterable = dao.distinct("test20160722", "name", String.class);
		for(String o : iterable){
			System.out.println(o);
		}
	}
	
}
