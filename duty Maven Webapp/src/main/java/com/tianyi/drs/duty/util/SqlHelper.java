package com.tianyi.drs.duty.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SqlHelper {

	@Autowired
	private static SqlSessionFactory sqlSessionFactory;
	
	
	public static SqlSessionFactory getSqlSessionFactory()
	{
		return sqlSessionFactory;
	}
}
