package com.tianyi.drs.basedata.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.GpsMapper;
import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.basedata.service.GpsService;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.util.PaginationData;
/**
 * 定位设备接口实现
 * @author lq
 *
 */
@Service("gpsService")
public class GpsServiceImpl implements GpsService{

	@Resource(name="gpsMapper")
	private GpsMapper gpsMapper;
	
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#findCount(Gps gps)
	 */
	public int findCount(Gps gps) {
		
		return gpsMapper.findCount(gps);
	}
	/** (non-Javadoc)
	 *  @see com.tianyi.drs.basedata.service.GpsService#findPageList(Gps query, PaginationData page)
	 */
	public List<Gps> findPageList(Gps query, PaginationData page) {
		// TODO Auto-generated method stub
		return gpsMapper.findPageList(query,page);
	}
	/** (non-Javadoc)
	 *  @see com.tianyi.drs.basedata.service.GpsService#loadVMCount(Map<String, Object> map)
	 */
	public int loadVMCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsMapper.loadVMCount(map);
	}
	/** (non-Javadoc)
	 *  @see com.tianyi.drs.basedata.service.GpsService#loadVMList(Map<String, Object> map)
	 */
	public List<GpsVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsMapper.loadVMList(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#updateByPrimaryKey(Gps gps)
	 */
	public int updateByPrimaryKey(Gps gps) {
		// TODO Auto-generated method stub
		return gpsMapper.updateByPrimaryKey(gps);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#insert(Gps gps)
	 */
	public int insert(Gps gps) {
		// TODO Auto-generated method stub
		return gpsMapper.insert(gps);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#deleteByPrimaryKey(id)
	 */
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return gpsMapper.deleteByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#selectGpsType()
	 */
	public List<GpsType> selectGpsType() {
		// TODO Auto-generated method stub
		return gpsMapper.selectGpsType();
	}
	/** (non-Javadoc)
	 *  @see com.tianyi.drs.basedata.service.GpsService#loadVMListWithGroup(Map<String, Object> map)
	 */
	public List<GpsVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return gpsMapper.loadVMListWithGroup(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#deleteByIds(Map<String, Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		gpsMapper.deleteByIds(map);
	}
}
