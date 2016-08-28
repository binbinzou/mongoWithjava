package com.zbb.simple.mongo.event;

import java.util.Map;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;

public interface IEvent {

	Bson returnFilters(Map<String, Object> map);
	
}
