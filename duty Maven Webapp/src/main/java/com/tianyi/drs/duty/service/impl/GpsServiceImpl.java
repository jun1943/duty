package com.tianyi.drs.duty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.GpsMapper;
import com.tianyi.drs.duty.model.Gps;
import com.tianyi.drs.duty.service.GpsService;
import com.tianyi.util.PaginationData;

@Service("gpsService")
public class GpsServiceImpl implements GpsService{

	@Resource(name="gpsMapper")
	private GpsMapper gpsMapper;

	public long findCount(Gps gps) {
		
		return 0;
	}

	public List<Gps> findPageList(Gps query, PaginationData page) {
		// TODO Auto-generated method stub
		return null;
	}
}
