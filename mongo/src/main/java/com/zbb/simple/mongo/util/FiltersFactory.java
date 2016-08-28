package com.zbb.simple.mongo.util;

import java.awt.Event;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.client.model.Filters;
import com.zbb.simple.mongo.event.FiveParamEvent;
import com.zbb.simple.mongo.event.FourParamEvent;
import com.zbb.simple.mongo.event.IEvent;
import com.zbb.simple.mongo.event.ThreeParamEvent;
import com.zbb.simple.mongo.event.TwoParamEvent;
import com.zbb.simple.mongo.event.OneParamEvent;

public class FiltersFactory {

	private static FiltersFactory factory = new FiltersFactory();
	private static Map filterMap = new HashMap();
	
	static {
		filterMap.put(FiltersType.EQ.getParamSize(), new TwoParamEvent());
		filterMap.put(FiltersType.AND.getParamSize(), new OneParamEvent());
		filterMap.put(FiltersType.MOD.getParamSize(), new ThreeParamEvent());
		filterMap.put(FiltersType.GEOWITHINCENTER.getParamSize(), new FourParamEvent());
		filterMap.put(FiltersType.GEOWITHINBOX.getParamSize(), new FiveParamEvent());
	}
	
	private static FiltersFactory getIntance(){
		return factory;
	}
	
	public static IEvent getFilters(Map<String, Object> map){
		String type = ((FiltersType)map.get("type")).getParamSize();
		IEvent event;
		if(filterMap.containsKey(type)){
			event = (IEvent)filterMap.get(type);
		}else{
			event = null;
		}
		return event;
	}
	
	
}
