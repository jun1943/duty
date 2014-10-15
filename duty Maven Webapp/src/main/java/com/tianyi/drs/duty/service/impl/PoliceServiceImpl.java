package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.PoliceMapper;
import com.tianyi.drs.duty.model.Police; 
import com.tianyi.drs.duty.service.PoliceService;
import com.tianyi.drs.duty.viewmodel.PoliceViewModel;
import com.tianyi.util.PaginationData;

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
 
	public Police findBycode(String code) {
		// TODO Auto-generated method stub
		return policeMapper.findBycode(code);
	}
 
	public Police findByname(String name) {
		// TODO Auto-generated method stub
		return policeMapper.findByname(name);
	}
 
	public List<Police> selectAll() {
		// TODO Auto-generated method stub
		return policeMapper.selectAll();
	}
 
	public int updatePolice(Police police) {
		// TODO Auto-generated method stub
		return policeMapper.updatePolice(police);
	}
 
	public Police login(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return policeMapper.login(params);
	}

	public int findCount(PoliceViewModel police) {
		int count = policeMapper.countByExample(police);
		return count;
	}

	public List<PoliceViewModel> findPageList(PoliceViewModel police, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(police.getNumber() == null || police.getNumber().length() ==0) )
			map.put("number", police.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<PoliceViewModel> list = policeMapper.selectWithPage(map);
		

		
		
		return list;
	} 
	
}
