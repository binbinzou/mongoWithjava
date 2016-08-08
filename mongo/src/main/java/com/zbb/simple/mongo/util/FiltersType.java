package com.zbb.simple.mongo.util;

public enum FiltersType {

	EQ("str_obj","eq"),
	NE("str_obj","ne"),
	GT("str_obj","gt"),
	LT("str_obj","lt"),
	GTE("str_obj","gte"),
	LTE("str_obj","lte"),
	IN("str_obj[]","in"),
	NIN("str_obj[]","nin"),
	AND("bson[]","and"),
	OR("bson[]","or"),
	NOT("bson","not"),
	NOR("bson[]","nor"),
	EXISTS("str","exists"),
	TYPE("str_bsonTypeOrStr","type"),
	MOD("str_lon_lon","mod"),
	REGEX("str_strOrPat","regex"),
	TEXT("str","text"),
	WHERE("str","where"),
	ALL("str_obj[]","all"),
	ELEMMATCH("str_bson","elemMatch"),
	SIZE("str_int","size"),
	BITSALLCLEAR("str_lon","bitsAllClear"),
	BITSALLSET("str_lon","bitsAllSet"),
	BITSANYCLEAR("str_lon","bitsAnyClear"),
	BITSANYSET("str_lon","bitsAnySet"),
	GEOWITHIN("str_geoOrBson","geoWithin"),
	GEOWITHINBOX("str_4_dou","geoWithinBox"),
	GEOWITHINPOLYGON("str_list","geoWithinPolygon"),
	GEOWITHINCENTER("str_3_dou","geoWithinCenter"),
	GEOWITHINCENTERSPHERE("str_3_dou","geoWithinCenterSphere"),
	GEOINTERSECTS("str_geoOrBson","geoIntersects"),
	NEAR(),
	NEARSPHERE();
	
	
	
	
	private String paramType;

	private String methodName;
	
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	private FiltersType(){
		this.paramType="";
		this.methodName="";
	}
	private FiltersType(String paramType,String methodName){
		this.paramType=paramType;
		this.methodName=methodName;
	}
	
}
