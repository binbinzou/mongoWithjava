package com.zbb.simlpe.mongo.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.BSONObject;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.mongodb.client.model.Updates;
import com.mongodb.util.JSON;
import com.zbb.simple.mongo.util.TransFormUtil;

public class BaseBean {

	public BaseBean fromDocument(Document document){
		return (BaseBean) JSONObject.parse(JSON.serialize(document));
	} 
	
	public Document toDocument(){
		return new Document(((DBObject) JSON.parse(JSONObject.toJSONString(this))).toMap());
	} 
	
	public Bson toUpdate(){
		List<Bson> bsons = new ArrayList<Bson>();
		Map<String,Object> map = TransFormUtil.beanToMap(this);
		Set set = map.keySet();
		Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
			//System.out.println(iterator.next());
			String name = iterator.next();
			if(!"class".equals(name)){
				Bson bson = Updates.set(name, map.get(name));
				bsons.add(bson);
			}
		}
		Bson endBson = Updates.combine(bsons);
		return endBson;
	}
	
	public BaseBean fromUpdate(BsonDocument document){
		return (BaseBean) JSONObject.parse(JSON.serialize(document));
	}
	
	
}
