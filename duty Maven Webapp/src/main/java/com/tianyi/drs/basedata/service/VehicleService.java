package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.util.PaginationData;
/**
 * 车辆后台逻辑服务层
 * @author lq
 *
 */
public interface VehicleService {

	/**
	 * 查询记录总数
	 * @param vehicle
	 * @return
	 */
	public int findCount(Vehicle vehicle);
	/**
	 * 查询分页记录
	 * 
	 * @param query
	 * @param page
	 * @return
	 */
	public List<Vehicle> findPageList(Vehicle query,PaginationData page);
	
	/**
	 * 查询分页记录总数
	 * @param query
	 * @return
	 */
	public int loadVMCount(Map<String,Object> query);
	/**
	 * 查询分页记录列表对象
	 * @param query
	 * @return
	 */
	public List<VehicleVM> loadVMList(Map<String,Object> query);
	/**
	 * 更新车辆对象
	 * @param vehicle
	 * @return
	 */
	public int updateByPrimaryKey(Vehicle vehicle);
	/**
	 * 插入新的车辆记录
	 * @param vehicle
	 * @return
	 */
	public int insert(Vehicle vehicle);
	/**
	 * 删除车辆对象
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(int id);
	/**
	 * 查询车辆类型列表
	 * @return
	 */
	public List<VehicleType> selectVehicleType();
	/**
	 * 查询对讲及组呼号列表
	 * @return
	 */
	public List<IntercomGroup> selectIntercomGroup();
	/**
	 * 查询车辆分组记录列表
	 * @param map
	 * @return
	 */
	public List<VehicleVM> loadVMListWithGroup(Map<String, Object> map);
	/**
	 * 批量删除车辆对象
	 * @param map
	 */
	public void deleteByIds(Map<String, Object> map);
}
