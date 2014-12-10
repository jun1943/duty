package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
/*
 * 武器类型对象映射文件类
 */
@MyBatisRepository
public interface WeaponTypeMapper {
	/*
	 * 根据主键值id，删除武器类型对象
	 */
	int deleteByPrimaryKey(Integer id);
	/*
	 * 插入新的武器类型对象
	 */
    int insert(WeaponType record);
    /*
	 * 查询武器对象查询集
	 */
    int insertSelective(WeaponType record);
    /*
	 * 根据主键值id，获取武器对象
	 */
    WeaponType selectByPrimaryKey(Integer id);
    /*
	 * 更新武器类型对象
	 */
    int updateByPrimaryKeySelective(WeaponType record);
    /*
	 * 更新武器类型对象
	 */
    int updateByPrimaryKey(WeaponType record);
}