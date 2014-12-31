package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM;
/**
 * 车辆分组映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface VehicleGroupMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的车辆分组对象
     * @param record
     * @return
     */
    int insert(VehicleGroup record);

    /**
     * 插入新的查询车辆分组对象
     * @param record
     * @return
     */
    int insertSelective(VehicleGroup record);

    /**
     * 查询车辆分组对象
     * @param id
     * @return
     */
    VehicleGroup selectByPrimaryKey(Integer id);

    /**
     * 更新车辆分组对象
     * @param id
     * @return
     */
    int updateByPrimaryKeySelective(VehicleGroup record);

    /**
     * 更新车辆分组对象
     * @param id
     * @return
     */
    int updateByPrimaryKey(VehicleGroup record);


    /**
     * 根据组织机构id查询记录总数
     * @param map
     * @return
     */
	int countByOrgId(Map<String, Object> map);

	/**
	 * 根据组织机构id，获取车辆分组管理对象
	 * @param map
	 * @return
	 */
	List<VehicleGroupVM> loadVMListByOrgId(Map<String, Object> map);

	/**
	 * 根据组织机构id，查询车辆分组对象以及共享下级的对象
	 * @param map
	 * @return
	 */
	List<VehicleGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);

	/**
	 * 判断分组是否存在
	 * @param map
	 * @return
	 */
	List<VehicleGroup> findByNameAndOrg(Map<String, Object> map);
}