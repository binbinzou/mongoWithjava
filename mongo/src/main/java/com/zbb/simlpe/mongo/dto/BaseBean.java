package com.zbb.simlpe.mongo.dto;

import org.bson.BSONObject;
import org.bson.Document;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class BaseBean {

	public BaseBean fromDocument(Document document){
		return (BaseBean) JSONObject.parse(JSON.serialize(document));
	} 
	
	public Document toDocument(){
		return new Document(((DBObject) JSON.parse(JSONObject.toJSONString(this))).toMap());
	} 
	
}
