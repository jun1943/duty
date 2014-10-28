package com.tianyi.drs.basedata.dao;

import java.util.List;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.util.PaginationData;

import java.util.Map;

@MyBatisRepository
public interface PoliceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Police record);

    int insertSelective(Police record);

    Police selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);
 
	Police findBycode(String code); 
	
	Police findByname(String name); 
	
	List<Police> selectAll();
	 
	int updatePolice(Police police);
	  
	Police login(Map<String, Object> params);

	int findCount(Police police);

	List<Police> findPageList(Police police, PaginationData page);

	int countByExample(PoliceVM police);

	List<PoliceVM> selectWithPage(Map<String, Object> map);

	List<PoliceVM> loadVMList(Map<String, Object> map);

	int countByVM(Map<String, Object> map);

	int loadVMCount(Map<String, Object> map);

	List<PoliceType> selectPoliceType();

	List<IntercomGroup> selectIntercomGroup();

	List<GpsBaseVM> selectGpsId(int orgId);

	List<PoliceVM> loadVMListWithGroup(Map<String, Object> map);
	 
}