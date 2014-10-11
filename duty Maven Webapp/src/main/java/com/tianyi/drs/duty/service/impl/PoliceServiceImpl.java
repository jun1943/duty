package com.tianyi.drs.duty.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.PoliceMapper;
import com.tianyi.drs.duty.model.Police;
import com.tianyi.drs.duty.service.PoliceService;

@Service("policeService")
public class PoliceServiceImpl implements PoliceService {

	@Resource(name="policeMapper")
	private PoliceMapper policeMapper;

	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.deleteByPrimaryKey(id);
	}

	public int insert(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insert(record);
	}

	public int insertSelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insertSelective(record);
	}

	public Police selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKey(record);
	}
	
}
