 package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.util.PaginationData;
/*
 * 定位设备映射文件
 */
@MyBatisRepository
public interface GpsMapper {
	/*
	 * 根据主键删除对象
	 * return：int
	 */
    int deleteByPrimaryKey(Integer id);
    /*
	 * 插入新对象
	 * return record_id
	 */
    int insert(Gps record);
    /*
	 * 插入查询结果对象
	 */
    int insertSelective(Gps record);
    /*
	 * 根据对象id获取对象详细信息
	 */
    Gps selectByPrimaryKey(Integer id);
    /*
	 * 根据主键更新对象集信息
	 */
    int updateByPrimaryKeySelective(Gps record);
    /*
	 * 更新对象信息
	 */
    int updateByPrimaryKey(Gps record);
    /*
	 * 查询对象的个数
	 */
	int findCount(Gps gps);
	/*
	 * 查询对象列表
	 */
	List<Gps> findPageList(Gps query, PaginationData page);
	/*
	 * 查询符合条件的对象总数
	 */
	int loadVMCount(Map<String, Object> map);
	/*
	 * 查询符合条件的所有数据集
	 */
	List<GpsVM> loadVMList(Map<String, Object> map);
	/*
	 * 查询类型列表
	 */
	List<GpsType> selectGpsType();
	/*
	 * 根据条件查询分组对象
	 */
	List<GpsVM> loadVMListWithGroup(Map<String, Object> map);
	/*
	 * 根据id集，批量删除对象
	 */
	void deleteByIds(Map<String, Object> map);
}


