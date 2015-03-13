package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Icons; 
/**
 * 图片图标后台逻辑服务层
 * @author lq
 *
 */
public interface IconsService {
	/**
	 * 查询记录总数
	 * @param map
	 * @return
	 */
	int loadCount(Map<String, Object> map);
	/**
	 * 查询图标列表数据集，并分页
	 * @param map
	 * @return
	 */
	List<Icons> loadList(Map<String, Object> map);
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(int id);
	/**
	 * 更新图标对象
	 * @param icons
	 * @return
	 */
	int updateByPrimaryKey(Icons icons);
	/**
	 * 插入新图标对象
	 * @param icons
	 * @return
	 */
	int insert(Icons icons);
	/**
	 * 根据id获取图标对象
	 * @param id
	 * @return
	 */
	Icons loadById(int id);
	/**
	 * 批量删除图标对象
	 * @param map
	 */
	void deleteByIds(Map<String, Object> map);

	/**
	 * 读取图标基础信息
	 * @return
	 */
	List<Icons> getIconsInfo();
	
}
