package com.tianyi.drs.basedata.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.IconsMapper; 
import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.service.IconsService;

@Service("iconsService")
public class IconsServiceImpl implements IconsService  {

	@Resource(name="iconsMapper")
	private IconsMapper iconsMapper;
	
	public int loadCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iconsMapper.loadCount(map);
	}

	public List<Icons> loadList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iconsMapper.loadList(map);
	}

	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return iconsMapper.deleteByPrimaryKey(id);
	}

	public int updateByPrimaryKey(Icons icons) {
		// TODO Auto-generated method stub
		return iconsMapper.updateByPrimaryKey(icons);
	}

	public int insert(Icons icons) {
		// TODO Auto-generated method stub
		return iconsMapper.insert(icons);
	}

	public Icons loadById(int id) {
		// TODO Auto-generated method stub
		return iconsMapper.loadById(id);
	}

	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		iconsMapper.deleteByIds(map);
	}

}
