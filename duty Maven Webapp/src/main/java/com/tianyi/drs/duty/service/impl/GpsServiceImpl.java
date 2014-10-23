package com.tianyi.drs.duty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.GpsMapper;
import com.tianyi.drs.duty.model.Gps;
import com.tianyi.drs.duty.model.GpsType;
import com.tianyi.drs.duty.service.GpsService;
import com.tianyi.drs.duty.viewmodel.GpsVM;
import com.tianyi.util.PaginationData;

@Service("gpsService")
public class GpsServiceImpl implements GpsService{

	@Resource(name="gpsMapper")
	private GpsMapper gpsMapper;

	public int findCount(Gps gps) {
		
		return gpsMapper.findCount(gps);
	}

	public List<Gps> findPageList(Gps query, PaginationData page) {
		// TODO Auto-generated method stub
		return gpsMapper.findPageList(query,page);
	}

	public int loadVMCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsMapper.loadVMCount(map);
	}

	public List<GpsVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsMapper.loadVMList(map);
	}

	public int updateByPrimaryKey(Gps gps) {
		// TODO Auto-generated method stub
		return gpsMapper.updateByPrimaryKey(gps);
	}

	public int insert(Gps gps) {
		// TODO Auto-generated method stub
		return gpsMapper.insert(gps);
	}

	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return gpsMapper.deleteByPrimaryKey(id);
	}

	public List<GpsType> selectGpsType() {
		// TODO Auto-generated method stub
		return gpsMapper.selectGpsType();
	}
}
