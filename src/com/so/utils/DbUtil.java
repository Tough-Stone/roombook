package com.so.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
	
	private DbUtil() {
		System.out.println("初始化连接池DB....");
	}

	private volatile static DbUtil dbUtil = null;
	
	public static DbUtil getDbUtil() throws Exception {
		if (dbUtil == null) {
			dbUtil = new DbUtil();
		}
		return dbUtil;
	}

	public Connection getCon()throws Exception{
		Class.forName(PropertiesUtil.getValue("jdbcName"));
		Connection con=DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"), PropertiesUtil.getValue("dbUserName"), PropertiesUtil.getValue("dbPassword"));
		return con;
	}
	
	public static void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	/*public static void main(String[] args) {
		try {
			DbUtil.getDbUtil().getCon();
			System.out.println("connect success");
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}*/
}
