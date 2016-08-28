package com.zbb.simple.mongo.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

import org.bson.conversions.Bson;

import com.mongodb.client.model.Filters;
import com.zbb.simple.mongo.util.FiltersType;

public class OneParamEvent implements IEvent{

	public Bson returnFilters(Map<String, Object> map) {
		FiltersType type = (FiltersType)map.get("type");
		Method[] methods = null;
		Method method = null;
		try {
			methods = Filters.class.getDeclaredMethods();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Method m:(new ArrayList<Method>()).toArray(methods)){
			if(m==null){
				continue;
			}
			if(m.getName().equals(type.getMethodName())){
				if(m.getParameterTypes().length==1){
					method = m;
					break;
				}
			}
		}
		if(method == null){
			return null;
		}
		Object[] args = (Object[]) new Object[1];
		args[0] = map.get("one");
		try {
			return (Bson) method.invoke(null, args);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
