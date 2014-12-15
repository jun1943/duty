package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;

/**
 * 武器分组映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface WeaponGroupMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的武器分组对象
     * @param record
     * @return
     */
    int insert(WeaponGroup record);

    /**
     * 插入新的武器分组对象
     * @param record
     * @return
     */
    int insertSelective(WeaponGroup record);

    /**
     * 根据id查询武器分组对象信息
     * @param id
     * @return
     */
    WeaponGroup selectByPrimaryKey(Integer id);

    /**
     * 更细查询武器分组对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WeaponGroup record);

    /**
     * 更新武器分组对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(WeaponGroup record);

    /**
     * 根据组织机构id，查询武器分组总数
     * @param map
     * @return
     */
	int countByOrgId(Map<String, Object> map);

	/**
	 * 获取当前组织机构及下级机构武器分组对象
	 * @param map
	 * @return
	 */
	List<WeaponGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);

	/**
	 * 根据组织机构id，获取武器分组对象
	 * @param map
	 * @return
	 */
	List<WeaponGroupVM> loadVMListByOrgId(Map<String, Object> map);
}