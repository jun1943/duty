package com.tianyi.drs.basedata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.PoliceMapper;
import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
import com.tianyi.drs.duty.exportmodel.GpsInfo;
import com.tianyi.drs.duty.exportmodel.ItemInfo; 
import com.tianyi.drs.duty.exportmodel.PoliceExtItem;
import com.tianyi.drs.duty.exportmodel.PoliceInfo; 
import com.tianyi.drs.duty.exportmodel.WeaponInfo;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;
import com.tianyi.util.PaginationData;

/**
 * 警员接口实现
 * 
 * @author lq
 * 
 */
@Service("policeService")
public class PoliceServiceImpl implements PoliceService {

	@Resource(name = "policeMapper")
	private PoliceMapper policeMapper;

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insert(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insert(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insertSelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insertSelective(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.selectByPrimaryKey(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKeySelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKey(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKey(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> findBycode(String code) {
		// TODO Auto-generated method stub
		return policeMapper.findBycode(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police findByname(String name) {
		// TODO Auto-generated method stub
		return policeMapper.findByname(name);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> selectAll() {
		// TODO Auto-generated method stub
		return policeMapper.selectAll();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updatePolice(Police police) {
		// TODO Auto-generated method stub
		return policeMapper.updatePolice(police);
	}

	/**
	 * (non-Javadoc)
	 * 
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

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findPageList(PoliceVM
	 *      police, PaginationData page)
	 */
	public List<PoliceVM> findPageList(PoliceVM police, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!(police.getNumber() == null || police.getNumber().length() == 0))
			map.put("number", police.getNumber());

		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<PoliceVM> list = policeMapper.selectWithPage(map);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMCount(Map<String,
	 *      Object> map)
	 */
	public int loadVMCount(Map<String, Object> map) {
		int count = policeMapper.loadVMCount(map);
		return count;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMList(Map<String,
	 *      Object> map)
	 */
	public List<PoliceVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMList(map);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectPoliceType()
	 */
	public List<PoliceType> selectPoliceType() {
		// TODO Auto-generated method stub
		return policeMapper.selectPoliceType();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectIntercomGroup()
	 */
	public List<IntercomGroup> selectIntercomGroup() {
		// TODO Auto-generated method stub
		return policeMapper.selectIntercomGroup();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectGpsId(int orgId)
	 */
	public List<GpsBaseVM> selectGpsId(int orgId) {
		// TODO Auto-generated method stub
		return policeMapper.selectGpsId(orgId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroup(Map
	 *      <String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMListWithGroup(map);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByidCard(String
	 *      param)
	 */
	public List<Police> findByidCard(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByidCard(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroupList
	 *      (Map<String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMListWithGroupList(map);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#deleteByIds(Map<String,
	 *      Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.deleteByIds(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#getUserAuthorization(Map
	 *      <String, Object> map)
	 */
	public UserObjectVM getUserAuthorization(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return policeMapper.getUserAuthorization(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#changePoliceStateByIds(Map)
	 */
	public void changePoliceStateByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.changePoliceStateByIds(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByintercomPerson(String)
	 */
	public List<Police> findByintercomPerson(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByintercomPerson(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMListWithOrgId(Integer)
	 */
	public List<PoliceVM> loadVMListWithOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return policeMapper.loadVMListWithOrgId(orgId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByIdAndDtyId(String)
	 */
	public List<Police> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByIdAndDtyId(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadListByOrgId(Map)
	 */
	public List<Police> getPoliceInfo(Integer orgId) {
		// TODO Auto-generated method stub
		return policeMapper.getPoliceInfo(orgId);
	}

	public List<PoliceExtItem> getPoliceDutyInfo(Integer orgId, Integer ymd) {

		Map<Integer, ItemInfo<?>> cache = new HashMap<Integer, ItemInfo<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// //
		List<PoliceExtItem> pels = new ArrayList<PoliceExtItem>();
		// 局部缓存，避免大量低效率的循环。Object无意义，都为null

		List<PoliceInfo> ps = new ArrayList<PoliceInfo>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);

		if (ymd == null || ymd == 0) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String date = dateFormat.format(now);
			ymd = Integer.parseInt(date);

		}
		map.put("ymd", ymd);

		List<ExtDbResult> rs = exportMapper.loadDutyItemInfo(map);

		for (ExtDbResult r : rs) {
			if (r.getItemTypeId() == 2) {
				PoliceInfo p = this.createPoliceInfo(r);
				p.setShiftInfo(this.createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(p.getDutyItemId(), p);// 添加到缓存
				cache2.put(p.getData().getId(), null);
				ps.add(p);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					PoliceInfo pp = (PoliceInfo) cache.get(r.getParentId());

					switch (r.getItemTypeId()) {
					case 3:// weapon
						if (pp.getWeaponItems() == null) {
							pp.setWeaponItems(new ArrayList<WeaponInfo>());
						}
						WeaponInfo w = createWeaponInfo(r);
						pp.getWeaponItems().add(w);
						cache.put(r.getDutyItemId(), w);
						break;
					case 4: // gps
						if (pp.getGpsItems() == null) {
							pp.setGpsItems(new ArrayList<GpsInfo>());
						}
						GpsInfo g = createGpsInfo(r);
						pp.getGpsItems().add(g);
						cache.put(r.getDutyItemId(), g);
						break;
					}
				}
			}
		}

		List<Police> mps = policeMapper.getPoliceInfo(orgId);

		for (Police mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				PoliceInfo ep2 = new PoliceInfo();
				ep2.setData(mp);
				ps.add(ep2);
			}
		}
		pels = CreatePoliceExtItem(ps);
		return pels;
	}

	private List<PoliceExtItem> CreatePoliceExtItem(List<PoliceInfo> eps) {
		// TODO Auto-generated method stub
		List<PoliceExtItem> pets = new ArrayList<PoliceExtItem>();
		for (PoliceInfo rs : eps) {
			PoliceExtItem pei = new PoliceExtItem();

			pei.setData(rs.getData());
			pei.setDutyItemId(rs.getDutyItemId());
			pei.setItemTypeId(rs.getItemTypeId());
			pei.setShiftInfo(rs.getShiftInfo());
			if (rs.getGpsItems() != null) {
				if (rs.getGpsItems().size() > 0) {
					for (int i = 0; i < rs.getGpsItems().size(); i++) {
						GpsInfo ep = rs.getGpsItems().get(i);
						List<Integer> gpsid = new ArrayList<Integer>();
						if (ep.getData() != null) {
							Gps g = (Gps) ep.getData();
							gpsid.add(g.getId());
							if (pei.getGpsItems() == null) {
								pei.setGpsItems(gpsid);
							} else {
								pei.getGpsItems().add(g.getId());
							}
						}

					}
				}
			}
			if (rs.getWeaponItems() != null) {
				if (rs.getWeaponItems().size() > 0) {
					for (int i = 0; i < rs.getWeaponItems().size(); i++) {
						WeaponInfo wp = rs.getWeaponItems().get(i);
						List<Integer> wid = new ArrayList<Integer>();
						if (wp.getData() != null) {
							Weapon w = (Weapon) wp.getData();
							wid.add(w.getId());
							if (pei.getWeaponItems() == null) {
								pei.setWeaponItems(wid);
							} else {
								pei.getWeaponItems().add(w.getId());
							}
						}

					}
				}
			}
			pets.add(pei);
		}
		return pets;
	}

	private PoliceInfo createPoliceInfo(ExtDbResult result) {
		PoliceInfo item = new PoliceInfo();
		Police data = this.createPolice(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

		return item;
	}

	private GpsInfo createGpsInfo(ExtDbResult result) {
		GpsInfo item = new GpsInfo();
		Gps data = this.createGsp(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

		return item;
	}

	private WeaponInfo createWeaponInfo(ExtDbResult result) {
		WeaponInfo item = new WeaponInfo();
		Weapon data = this.createWeapon(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

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

	private Police createPolice(ExtDbResult result) {
		Police p = new Police();
		p.setGpsId(result.getPoliceGpsId());
		p.setGpsName(result.getPoliceGpsName());
		p.setId(result.getPoliceId());
		p.setIdcardno(result.getPoliceIdcardno());
		p.setIntercomGroup(result.getPoliceIntercomGroup());
		p.setIntercomPerson(result.getPoliceIntercomPerson());
		p.setMobile(result.getPoliceMobile());
		p.setMobileShort(result.getPoliceMobileShort());
		p.setName(result.getPoliceName());
		p.setNumber(result.getPoliceNumber());
		p.setOrgId(result.getOrgId());

		return p;
	}

	private Weapon createWeapon(ExtDbResult result) {
		Weapon w = new Weapon();
		w.setId(result.getWeaponId());
		w.setNumber(result.getWeaponNumber());
		w.setOrgId(result.getWeaponOrgId());
		w.setStandard(result.getWeaponStandard());
		w.setTypeId(result.getWeaponTypeId());
		return w;
	}

	private Gps createGsp(ExtDbResult result) {
		Gps g = new Gps();
		g.setGpsName(result.getGpsName());
		g.setIconId(null);
		g.setId(result.getGpsId());
		g.setNumber(result.getGpsNumber());
		g.setOrgId(result.getGpsOrgId());
		g.setTypeId(result.getGpsTypeId());
		return g;
	}

}
