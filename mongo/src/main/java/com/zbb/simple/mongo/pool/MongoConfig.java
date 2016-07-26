package com.zbb.simple.mongo.pool;

/**
 * @author zbb
 * mongo的配置
 */
public class MongoConfig {

	private static String username;
	
	private static String password;
	
	private static String[] hosts;
	
	private static int[] ports;
	
	private static String dbname;
	
	private static int connectionsPerHost = 20;//最大连接数
	
	private static int threadsAllowedToBlockForConnectionMultiplier = 10;//线程队列数
	
	private static boolean authentication = false;//是否需要身份验证

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		MongoConfig.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		MongoConfig.password = password;
	}

	public static String[] getHosts() {
		return hosts;
	}

	public static void setHosts(String[] hosts) {
		MongoConfig.hosts = hosts;
	}

	public static int[] getPorts() {
		return ports;
	}

	public static void setPorts(int[] ports) {
		MongoConfig.ports = ports;
	}

	public static String getDbname() {
		return dbname;
	}

	public static void setDbname(String dbname) {
		MongoConfig.dbname = dbname;
	}

	public static int getConnectionsPerHost() {
		return connectionsPerHost;
	}

	public static void setConnectionsPerHost(int connectionsPerHost) {
		MongoConfig.connectionsPerHost = connectionsPerHost;
	}

	public static int getThreadsAllowedToBlockForConnectionMultiplier() {
		return threadsAllowedToBlockForConnectionMultiplier;
	}

	public static void setThreadsAllowedToBlockForConnectionMultiplier(
			int threadsAllowedToBlockForConnectionMultiplier) {
		MongoConfig.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
	}

	public static boolean isAuthentication() {
		return authentication;
	}

	public static void setAuthentication(boolean authentication) {
		MongoConfig.authentication = authentication;
	}
	
}
