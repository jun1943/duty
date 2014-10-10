package com.tianyi.drs.duty.service.impl;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;
 
import com.tianyi.drs.duty.dao.PoliceManMapper;
import com.tianyi.drs.duty.model.PoliceMan;
import com.tianyi.drs.duty.service.PoliceManService;

@Service("policemanService")
public class PoliceManServiceImpl implements PoliceManService{
	
	@Resource(name = "policemanMapper")
    protected PoliceManMapper policemanMapper;
 
	public int deleteByPrimaryKey(Integer id) {
		return policemanMapper.deleteByPrimaryKey(id);
	}
 
	public int insert(PoliceMan record) {
		return policemanMapper.insert(record);
	}
 
	public int insertSelective(PoliceMan record) {
		return policemanMapper.insertSelective(record);
	}
 
	public PoliceMan selectByPrimaryKey(Integer id) {
		return policemanMapper.selectByPrimaryKey(id);
	}
 
	public int updateByPrimaryKeySelective(PoliceMan record) {
		return policemanMapper.updateByPrimaryKey(record);
	}
 
	public int updateByPrimaryKey(PoliceMan record) {
		return policemanMapper.updateByPrimaryKeySelective(record);
	}
}