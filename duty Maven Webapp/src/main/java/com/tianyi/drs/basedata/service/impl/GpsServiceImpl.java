package com.tianyi.drs.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.GpsMapper;
import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType; 
import com.tianyi.drs.basedata.service.GpsService;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
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

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;
	
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
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#findByNumber(String)
	 */
	public List<Gps> findByNumber(String param) {
		// TODO Auto-generated method stub
		return gpsMapper.findByNumber(param);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.GpsService#findByIdAndDtyId(String)
	 */
	public List<Gps> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return gpsMapper.findByIdAndDtyId(param);
	}
	public List<Gps> getGPSInfo(Integer orgId) {
		// TODO Auto-generated method stub
		return gpsMapper.getGPSInfo(orgId);
	}
	
	
	

	public List<ExtItem<Gps>> getGpsDutyInfo(Integer orgId, Integer ymd) {
		Map<Integer, ExtItem<?>> cache = new HashMap<Integer, ExtItem<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// 局部缓存，避免大量低效率的循环。Object无意义，都为null

		List<ExtItem<Gps>> eps = new ArrayList<ExtItem<Gps>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);

		List<ExtDbResult> rs = exportMapper.loadDutyItemInfo(map);

		for (ExtDbResult r : rs) {
			if (r.getItemTypeId() == 4) {
				@SuppressWarnings("unchecked")
				ExtItem<Gps> ep = (ExtItem<Gps>) this.createItemInfo(r);
				ep.setShiftInfo(this.createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(ep.getDutyItemId(), ep);// 添加到缓存
				cache2.put(ep.getData().getId(), null);
				eps.add(ep);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					@SuppressWarnings("unchecked")
					ExtItem<Gps> pp = (ExtItem<Gps>) cache.get(r.getParentId());
					if (pp.getItems() == null) {
						pp.setItems(new ArrayList<ExtItem<?>>());
					}
					ExtItem<?> cp = (ExtItem<?>) createItemInfo(r);
					pp.getItems().add(cp);
					cache.put(r.getDutyItemId(), cp);

				}
			}
		}

		List<Gps> mps = gpsMapper.getGPSInfo(orgId);

		for (Gps mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				ExtItem<Gps> ep2 = new ExtItem<Gps>();
				ep2.setData(mp);
				eps.add(ep2);
			}
		}

		return eps;
	}

	private Object createItemInfo(ExtDbResult result) {
		ExtItem<Object> item = new ExtItem<Object>();
		Object data = null;

		data = this.createGsp(result);

		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());

		return item;
	}

	private ExtShiftInfo createShiftInfo(ExtDbResult result) {
		ExtShiftInfo info = new ExtShiftInfo();
		info.setBeginTime(result.getBeginTime());
		info.setDutyTypeId(result.getDutyTypeId());
		info.setDutyTypeName(result.getDutyTypeName());
		info.setEndTime(result.getEndTime());
		info.setShiftId(result.getDutyItemId());
		info.setShiftName(result.getName());

		return info;
	}
 

	private Gps createGsp(ExtDbResult result) {
		Gps g = new Gps();
		g.setGpsName(result.getGpsName());
		g.setIconUrl(result.getGpsIconUrl());
		g.setId(result.getGpsId());
		g.setNumber(result.getGpsNumber());
		g.setOrgId(result.getGpsOrgId());
		g.setTypeId(result.getGpsTypeId());
		return g;
	}
}
