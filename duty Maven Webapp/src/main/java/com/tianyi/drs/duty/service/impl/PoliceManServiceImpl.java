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
		return policeDao.deleteByPrimaryKey(id);
	}
 
	public int insert(PoliceMan record) {
		return policeDao.insert(record);
	}
 
	public int insertSelective(PoliceMan record) {
		return policeDao.insertSelective(record);
	}
 
	public PoliceMan selectByPrimaryKey(Integer id) {
		return policeDao.selectByPrimaryKey(id);
	}
 
	public int updateByPrimaryKeySelective(PoliceMan record) {
		return policeDao.updateByPrimaryKey(record);
	}
 
	public int updateByPrimaryKey(PoliceMan record) {
		return policeDao.updateByPrimaryKeySelective(record);
	}
}