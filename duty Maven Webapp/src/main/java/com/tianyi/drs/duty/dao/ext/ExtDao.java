package com.tianyi.drs.duty.dao.ext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.alibaba.druid.pool.DruidDataSource;
import com.tianyi.drs.duty.model.ext.Org;


@Repository("extDao")
public class ExtDao {
	
	@Resource(name="dataSource")
	private DruidDataSource dataSource;
	
	public DruidDataSource getDataSource(){
		return this.dataSource;
	}
	
	public Connection getSqlConnection() throws SQLException{
		 return this.dataSource.getConnection();
	}
	
	public Org loadOrgById(int id) throws SQLException{
		ResultSet rs= this.getSqlConnection().createStatement().executeQuery("select id,code,path,parent_id from organ where id =" + id);
		
		Org org = null;
		
		if(rs.next()){
			org=new Org();
			org.setId(rs.getInt("id"));
			org.setCode(rs.getString("code"));
			org.setPath(rs.getString("path"));
			org.setParentId(rs.getInt("parent_id"));
		}
		
		return org;
	}
	
}
