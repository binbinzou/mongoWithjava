package com.zbb.simple.mongo.pool;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoManager {

	private static MongoClient client;

	private MongoDatabase db;

	static {
		init();
	}
	
	public MongoManager() {
		this(MongoConfig.getDbname(),MongoConfig.getUsername(),MongoConfig.getPassword());
	}

	public MongoManager(String dbname, String username, String password) {
		if(dbname == null || "".equals(dbname)){
			try {
				throw new MongoException("dbname is null");
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		db =  client.getDatabase(dbname);
	}

	public MongoCollection<Document> getDBConnection(String tableName){
		return db.getCollection(tableName);
	}
	
	
	private static void init() {
		// TODO Auto-generated method stub
		if (MongoConfig.getHosts() == null
				|| MongoConfig.getHosts().length == 0) {
			try {
				throw new MongoException("host is null");
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (MongoConfig.getPorts() == null
				|| MongoConfig.getPorts().length == 0) {
			try {
				throw new MongoException("port is null");
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (MongoConfig.getPorts().length != MongoConfig.getHosts().length) {
			try {
				throw new MongoException("host and post length is not match");
			} catch (MongoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
		int hostLength = MongoConfig.getHosts().length;
		for (int i = 0; i < hostLength; i++) {
			serverAddresses.add(new ServerAddress(MongoConfig.getHosts()[i],
					MongoConfig.getPorts()[i]));
		}
		
		final MongoClientOptions.Builder builder = new Builder();
		builder.connectionsPerHost(MongoConfig.getConnectionsPerHost());
		builder.threadsAllowedToBlockForConnectionMultiplier(MongoConfig.getThreadsAllowedToBlockForConnectionMultiplier());
		MongoClientOptions clientOptions = builder.build();
		client = new MongoClient(serverAddresses, clientOptions);
		
	}

}
