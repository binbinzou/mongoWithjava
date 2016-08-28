package com.zbb.simple.mongo.util;

public enum FiltersType {

	EQ("two","eq"),
	NE("two","ne"),
	GT("two","gt"),
	LT("two","lt"),
	GTE("two","gte"),
	LTE("two","lte"),
	IN("two","in"),
	NIN("two","nin"),
	AND("one","and"),
	OR("one","or"),
	NOT("one","not"),
	NOR("one","nor"),
	EXISTS("one","exists"),
	TYPE("two","type"),
	MOD("three","mod"),
	REGEX("two","regex"),
	TEXT("one","text"),
	WHERE("one","where"),
	ALL("two","all"),
	ELEMMATCH("two","elemMatch"),
	SIZE("two","size"),
	BITSALLCLEAR("two","bitsAllClear"),
	BITSALLSET("two","bitsAllSet"),
	BITSANYCLEAR("two","bitsAnyClear"),
	BITSANYSET("two","bitsAnySet"),
	GEOWITHIN("two","geoWithin"),
	GEOWITHINBOX("five","geoWithinBox"),
	GEOWITHINPOLYGON("two","geoWithinPolygon"),
	GEOWITHINCENTER("four","geoWithinCenter"),
	GEOWITHINCENTERSPHERE("four","geoWithinCenterSphere"),
	GEOINTERSECTS("two","geoIntersects"),
	NEAR("four","near"),
	NEARSPHERE("four","nearSphere");
	
	
	
	
	private String paramSize;

	private String methodName;
	
	public String getParamSize() {
		return paramSize;
	}
	public void setParamSize(String paramSize) {
		this.paramSize = paramSize;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	private FiltersType(){
		this.paramSize="";
		this.methodName="";
	}
	private FiltersType(String paramType,String methodName){
		this.paramSize=paramType;
		this.methodName=methodName;
	}
	
}
