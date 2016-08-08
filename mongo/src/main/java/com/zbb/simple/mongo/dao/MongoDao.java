package com.zbb.simple.mongo.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.bson.BSON;
import org.bson.Document;
import org.bson.codecs.Encoder;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.CountOptions;
import com.mongodb.client.model.DeleteManyModel;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndDeleteOptions;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.diagnostics.logging.Logger;
import com.mongodb.operation.FindAndReplaceOperation;
import com.zbb.simlpe.mongo.dto.TUser;
import com.zbb.simple.mongo.pool.MongoConfig;
import com.zbb.simple.mongo.pool.MongoManager;
import com.zbb.simple.mongo.util.ResourceUtil;

public class MongoDao {

	private static MongoManager manager;
	
	private static MongoConfig config = new MongoConfig();
	
	static{
		String[] hosts = ResourceUtil.getPorperty("jdbc", "mongoHost").split(",");
		String[] Sports = ResourceUtil.getPorperty("jdbc", "mongoPost").split(",");
		String dbName = ResourceUtil.getPorperty("jdbc", "mongoDbName");
		int portSize = Sports.length;
		int[] ports = new int[portSize];
		for(int i = 0;i < portSize;i++){
			ports[i] = Integer.parseInt(Sports[i]);
		}
		config.setHosts(hosts);
		config.setPorts(ports);
		config.setDbname(dbName);
		manager = new MongoManager();
	}
	
	
	
	public List<JSONObject> find(String tableName){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		List<Document> documents = collection.find().into(new ArrayList<Document>());
		List<JSONObject> jsonpObjects = new ArrayList<JSONObject>();
		for(Document document : documents){
			String documentString = document.toJson();
			JSONObject jsonObject = JSONObject.parseObject(documentString);
			jsonpObjects.add(jsonObject);
		}
		return jsonpObjects;
	}
	
	public List<JSONObject> findWithFilter(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		List<Document> documents = collection.find(filter).into(new ArrayList<Document>());
		List<JSONObject> jsonpObjects = new ArrayList<JSONObject>();
		for(Document document : documents){
			String documentString = document.toJson();
			JSONObject jsonObject = JSONObject.parseObject(documentString);
			jsonpObjects.add(jsonObject);
		}
		return jsonpObjects;
	}
	
	public List<JSONObject> findWithFilterLimit(String tableName,Map<String, Object> map,int fromIndex,int toIndex){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		List<Document> documents = collection.find(filter).into(new ArrayList<Document>()).subList(fromIndex, toIndex);
		List<JSONObject> jsonpObjects = new ArrayList<JSONObject>();
		for(Document document : documents){
			String documentString = document.toJson();
			JSONObject jsonObject = JSONObject.parseObject(documentString);
			jsonpObjects.add(jsonObject);
		}
		return jsonpObjects;
	}
	
	public List<Document> findWithFilterClass(String tableName,Map<String, Object> map, Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		List<Document> tUser = collection.find(document.getClass()).filter(filter).into(new ArrayList<Document>());
		return tUser;
	}
	
	public Document findOneAndDelete(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		Document document = collection.findOneAndDelete(filter);
		return document;
	}
	
	public Document findOneAndDeleteWithOptions(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		FindOneAndDeleteOptions options = new FindOneAndDeleteOptions();
		Document document = collection.findOneAndDelete(filter,options);
		return document;
	}
	
	public Document findOneAndReplace(String tableName,Map<String, Object> map,Document replacement){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		Document document = collection.findOneAndReplace(filter, replacement);
		return document;
	}
	
	public Document findOneAndReplaceOptions(String tableName,Map<String, Object> map,Document replacement){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		FindOneAndReplaceOptions options = new FindOneAndReplaceOptions();
		Document document = collection.findOneAndReplace(filter, replacement,options);
		return document;
	}
	
	public Document findOneAndUpdate(String tableName,Map<String, Object> map,Document replacement){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		Bson bson = replacement;
		Document document = collection.findOneAndUpdate(filter, bson);
		return document;
	}
	
	public Document findOneAndUpdateWithOptions(String tableName,Map<String, Object> map,Document replacement){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		Bson bson = replacement;
		FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
		Document document = collection.findOneAndUpdate(filter, bson, options);
		return document;
	}
	
	public long deleteOne(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson bson = new Document(map);
		DeleteResult result = collection.deleteOne(bson);
		return result.getDeletedCount();
	}
	
	public long deleteMany(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson bson = new Document(map);
		DeleteResult result = collection.deleteMany(bson);
		return result.getDeletedCount();
	}
	
	public void insertMany(String tableName,List<Document> list){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		collection.insertMany(list);
	}
	
	public void insertManyWithOptions(String tableName,List<Document> list,boolean isValidation){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		InsertManyOptions options = new InsertManyOptions();
		options.bypassDocumentValidation(isValidation);
		collection.insertMany(list, options);
	}
	
	public void insertOne(String tableName,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		collection.insertOne(document);
	}
	
	public void insertOneWithOptions(String tableName,Document document,boolean isValidation){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		InsertOneOptions options = new InsertOneOptions();
		options.bypassDocumentValidation(isValidation);
		collection.insertOne(document, options);
	}
	
	public long updateOne(String tableName,Map<String, Object> map,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson bson = Filters.eq("qqq", (String)map.get("qqq"));
		UpdateResult result = collection.updateOne(bson,document);
		return result.getModifiedCount();
	}
	
	public long updateOneWithOptions(String tableName,Map<String, Object> map,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		UpdateOptions options = new UpdateOptions();
		UpdateResult result = collection.updateOne(filter, document,options);
		return result.getModifiedCount();
	}
	
	public long updateMany(String tableName,Map<String, Object> map,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		UpdateResult result = collection.updateMany(filter, document);
		return result.getModifiedCount();
	}
	
	public long updateManyWithOptions(String tableName,Map<String, Object> map,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		UpdateOptions options = new UpdateOptions();
		UpdateResult result = collection.updateMany(filter, document,options);
		return result.getModifiedCount();
	}
	
	public long count(String tableName){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		return collection.count();
	}
	
	public long count(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		return collection.count(filter);
	}
	
	public long countWithOptions(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson filter = new Document(map);
		CountOptions options = new CountOptions();
		return collection.count(filter,options);
	}
	
	public String createIndex(String tableName,String key,int sort){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson keys = new Document(key,sort);
		return collection.createIndex(keys);
	}
	
	public String createIndexWithOption(String tableName,String key,int sort){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson keys = new Document(key,sort);
		IndexOptions options = new IndexOptions();
		return collection.createIndex(keys, options);
	}
	
	public void dropIndex(String tableName,Map<String, Object> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson bson = new Document(map);
		collection.dropIndex(bson);
	}
	
	public void dropIndex(String tableName,String indexName){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		collection.dropIndex(indexName);
	}

	/**
	 * 删除掉所有的索引。除了_id的索引
	 * @param tableName
	 */
	public void dropIndexs(String tableName){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		collection.dropIndexes();
	}
	
	public ListIndexesIterable<Document> listIndexs(String tableName){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		return collection.listIndexes();
	}
	
	public ListIndexesIterable<Document> listIndexs(String tableName,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		return (ListIndexesIterable<Document>) collection.listIndexes(document.getClass());
	}
	
	public List<String> createIndexs(String tableName,Map<String, Integer> map){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Iterator<String> iterator = (Iterator) map.entrySet();
		List<IndexModel> indexes = new ArrayList<IndexModel>();
		IndexOptions options = new IndexOptions();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Bson bson = new Document(key,map.get(key));
			IndexModel indexModel = new IndexModel(bson,options);
			indexes.add(indexModel);
		}
		return collection.createIndexes(indexes);
	}
	
	public DistinctIterable<Document> distinct(String tableName,String key,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		return (DistinctIterable<Document>) collection.distinct(key,document.getClass());
	}
	
	public DistinctIterable<Document> distinct(String tableName,Map<String, Object> map,String key,Document document){
		MongoCollection<Document> collection = manager.getDBConnection(tableName);
		Bson bson = new Document(map);
		return (DistinctIterable<Document>) collection.distinct(key,bson,document.getClass());
	}

	
}
