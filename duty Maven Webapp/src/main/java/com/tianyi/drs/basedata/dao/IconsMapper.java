package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
/*
 * 系统图标映射文件
 */
@MyBatisRepository
public interface IconsMapper {
	/*
	 * 根据追歼删除图标
	 */
    int deleteByPrimaryKey(Integer id);
    /*
	 * 插入新的数据
	 */
    int insert(Icons record);
    /*
	 * 插入查询图表集
	 */
    int insertSelective(Icons record);
    /*
	 * 根据id值查询图标对象
	 */
    Icons selectByPrimaryKey(Integer id);
    /*
	 * 更新图表对象
	 */
    int updateByPrimaryKeySelective(Icons record);
    /*
	 * 更新图表对象
	 */
    int updateByPrimaryKeyWithBLOBs(Icons record);
    /*
	 * 更新图表对象
	 */
    int updateByPrimaryKey(Icons record);
    /*
	 * 根据参数获取图标列表对象
	 */
	List<Icons> loadList(Map<String, Object> map);
	/*
	 * 根据参数获取图标总记录数
	 */
	int loadCount(Map<String, Object> map);
	/*
	 * 根据id获取图标对象
	 */
	Icons loadById(int id);
	/*
	 * 批量删除图标对象
	 */
	void deleteByIds(Map<String, Object> map);
}