package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
/*
 * 定位设备类型映射文件
 */
@MyBatisRepository
public interface GpsTypeMapper {
	/*
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(Integer id);
    /*
	 * 插入新纪录
	 */
    int insert(GpsType record);
    /*
	 * 插入查询集
	 */
    int insertSelective(GpsType record);
    /*
	 * 根据主键值获取对象
	 */
    GpsType selectByPrimaryKey(Integer id);
    /*
	 * 根据主键值，更新对象
	 */
    int updateByPrimaryKeySelective(GpsType record);
    /*
	 * 更行对象
	 */
    int updateByPrimaryKey(GpsType record);
}