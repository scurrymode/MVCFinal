package com.mvc.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConfigManager {
	private static ConfigManager instance;
	SqlSessionFactory factory;
	
	
	private  ConfigManager() {
		String resource = "com/mvc/mybatis/config.xml";
		InputStream is = null;
		
		try {
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		factory = new SqlSessionFactoryBuilder().build(is);
		
	}
	
	public static ConfigManager getInstance(){
		if(instance==null){
			instance = new ConfigManager();
		}
		return instance;
	}
	
	public SqlSession getSession(){
		SqlSession session =null;
		session = factory.openSession();
		return session;
	}
	
	public void close(SqlSession session){
		session.close();
	}

}
