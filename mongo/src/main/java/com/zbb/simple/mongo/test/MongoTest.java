package com.zbb.simple.mongo.test;

import java.util.Iterator;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.zbb.simple.mongo.pool.MongoConfig;
import com.zbb.simple.mongo.pool.MongoManager;

public class MongoTest {

	public static void main(String[] args) {
		String[] hosts = {"127.0.0.1"};
		int[] ports = {27017};
		MongoConfig.setHosts(hosts);
		MongoConfig.setPorts(ports);
		MongoConfig.setDbname("test");
		MongoManager manager = new MongoManager();
		MongoCollection<Document> collection = manager.getDBConnection("test20160722");
		Iterable<Document> iterable = collection.find();
		Iterator<Document> iterator = iterable.iterator();
		while(iterator.hasNext()){
			Document document = iterator.next();
			System.out.println(document.getString("name"));
			System.out.println(document.getDouble("age"));
		}
	}
	
}
