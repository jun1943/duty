package com.tianyi.drs.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.PoliceMapper;
import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;
import com.tianyi.util.PaginationData;
/**
 * 警员接口实现
 * @author lq
 *
 */
@Service("policeService")
public class PoliceServiceImpl implements PoliceService {

	@Resource(name="policeMapper")
	private PoliceMapper policeMapper;
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.deleteByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insert(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insert(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insertSelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insertSelective(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.selectByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKeySelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKeySelective(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKey(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKey(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> findBycode(String code) {
		// TODO Auto-generated method stub
		return policeMapper.findBycode(code);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police findByname(String name) {
		// TODO Auto-generated method stub
		return policeMapper.findByname(name);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> selectAll() {
		// TODO Auto-generated method stub
		return policeMapper.selectAll();
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updatePolice(Police police) {
		// TODO Auto-generated method stub
		return policeMapper.updatePolice(police);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police login(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return policeMapper.login(params);
	}

	public int findCount(PoliceVM police) {
		int count = policeMapper.countByExample(police);
		return count;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#findPageList(PoliceVM police, PaginationData page)
	 */
	public List<PoliceVM> findPageList(PoliceVM police, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(police.getNumber() == null || police.getNumber().length() ==0) )
			map.put("number", police.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<PoliceVM> list = policeMapper.selectWithPage(map);
		 
		return list;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMCount(Map<String, Object> map)
	 */
	public int loadVMCount(Map<String, Object> map) {
		int count= policeMapper.loadVMCount(map);
		return count;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMList(Map<String, Object> map)
	 */
	public List<PoliceVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMList(map);
		
		return list;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectPoliceType()
	 */
	public List<PoliceType> selectPoliceType() {
		// TODO Auto-generated method stub
		return policeMapper.selectPoliceType();
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectIntercomGroup()
	 */
	public List<IntercomGroup> selectIntercomGroup() {
		// TODO Auto-generated method stub
		return policeMapper.selectIntercomGroup();
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectGpsId(int orgId)
	 */
	public List<GpsBaseVM> selectGpsId(int orgId) {
		// TODO Auto-generated method stub
		return policeMapper.selectGpsId(orgId);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroup(Map<String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMListWithGroup(map);
		return list;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByidCard(String param)
	 */
	public List<Police> findByidCard(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByidCard(param);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroupList(Map<String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
				List<PoliceVM> list = policeMapper.loadVMListWithGroupList(map);
				return list;
	} 
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#deleteByIds(Map<String, Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.deleteByIds(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#getUserAuthorization(Map<String, Object> map)
	 */
	public UserObjectVM getUserAuthorization(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return policeMapper.getUserAuthorization(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#changePoliceStateByIds(Map)
	 */
	public void changePoliceStateByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.changePoliceStateByIds(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByintercomPerson(String)
	 */
	public List<Police> findByintercomPerson(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByintercomPerson(param);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMListWithOrgId(Integer)
	 */
	public List<PoliceVM> loadVMListWithOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return policeMapper.loadVMListWithOrgId(orgId);
	} 
	
}
