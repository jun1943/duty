package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
/*
 * 武器对象映射文件类
 */
@MyBatisRepository
public interface WeaponMapper {
	/*
	 * 根据主键值id删除武器对象
	 */
    int deleteByPrimaryKey(Integer id);
	/*
	 * 插入新的武器对象
	 */
    int insert(Weapon record);
	/*
	 * 插入查询武器对象集
	 */
    int insertSelective(Weapon record);
	/*
	 * 根据主键值id，获取武器对象
	 */
    Weapon selectByPrimaryKey(Integer id);
	/*
	 * 根据主键值更新武器对象
	 */
    int updateByPrimaryKeySelective(Weapon record);
	/*
	 * 更新武器对象
	 */
    int updateByPrimaryKey(Weapon record);
	/*
	 * 获取所有武器列表
	 */
	List<Weapon> selectAll();
	/*
	 * 根据参数，获取武器总记录数 
	 */
	int countByExample(Weapon weapon);
	/*
	 * 获取车辆列表数据集
	 */
	List<Weapon> selectByExample(Map<String, Object> map);
	/*
	 * 获取车辆列表数据集
	 */
	List<WeaponVM> loadVMList(Map<String, Object> map);
	/*
	 * 根据参数，获取武器总记录数 
	 */
	int countByVM(Map<String, Object> map);
	/*
	 * 根据参数，获取武器总记录数 
	 */
	int loadVMCount(Map<String, Object> map);
	/*
	 * 获取武器类型列表集
	 */
	List<WeaponType> selectWeaponType();
	/*
	 * 根据所选分组，查询武器资源列表
	 */
	List<WeaponVM> loadVMListWithGroup(Map<String, Object> map);
	/*
	 * 批量删除武器对象
	 */
	void deleteByIds(Map<String, Object> map);
	/**
	 * 判断是否存在武器
	 * @param param
	 * @return
	 */
	List<Weapon> findByNumber(String param);
	/**
	 * 判断是否武器存在关联数据
	 * @param param
	 * @return
	 */
	List<Weapon> findByIdAndDtyId(String param);
	/**
	 * 根据组织机构id，获取所有成员列表
	 * @param orgId
	 * @return
	 */
	List<Weapon> getWeaponInfo(Integer orgId);
}