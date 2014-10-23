package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.Gps;
import com.tianyi.drs.duty.model.GpsType;
import com.tianyi.drs.duty.viewmodel.GpsVM;
import com.tianyi.util.PaginationData;

public interface GpsService {

	public int findCount(Gps gps);

	public List<Gps> findPageList(Gps query, PaginationData page);

	public int loadVMCount(Map<String, Object> map);

	public List<GpsVM> loadVMList(Map<String, Object> map);

	public int updateByPrimaryKey(Gps gps);

	public int insert(Gps gps);

	public int deleteByPrimaryKey(int id);

	public List<GpsType> selectGpsType();
}
