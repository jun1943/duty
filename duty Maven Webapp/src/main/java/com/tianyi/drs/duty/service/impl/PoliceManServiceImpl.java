package com.tianyi.drs.duty.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.PoliceManMapper;
import com.tianyi.drs.duty.model.PoliceMan;
import com.tianyi.drs.duty.service.PoliceManService;

@Service
public class PoliceManServiceImpl implements PoliceManService{
	
    @Autowired
    private PoliceManMapper policeDao;
 
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeDao.deleteByPrimaryKey(id);
	}
 
	public int insert(PoliceMan record) {
		// TODO Auto-generated method stub
		return policeDao.insert(record);
	}
 
	public int insertSelective(PoliceMan record) {
		// TODO Auto-generated method stub
		return policeDao.insertSelective(record);
	}
 
	public PoliceMan selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeDao.selectByPrimaryKey(id);
	}
 
	public int updateByPrimaryKeySelective(PoliceMan record) {
		// TODO Auto-generated method stub
		return policeDao.updateByPrimaryKey(record);
	}
 
	public int updateByPrimaryKey(PoliceMan record) {
		// TODO Auto-generated method stub
		return policeDao.updateByPrimaryKeySelective(record);
	}
}