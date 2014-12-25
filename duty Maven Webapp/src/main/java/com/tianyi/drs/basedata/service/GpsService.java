package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.util.PaginationData;
/**
 * 定位设备后台逻辑服务层
 * auther：李强
 */
public interface GpsService {

	/**
	 * 查询记录数
	 * @param gps
	 * @return
	 */
	public int findCount(Gps gps);
	/**
	 * 查询分页
	 * @param query
	 * @param page
	 * @return
	 */
	public List<Gps> findPageList(Gps query, PaginationData page);
	/**
	 * 查询记录总数
	 * @param map
	 * @return
	 */
	public int loadVMCount(Map<String, Object> map);
	/**
	 * 查询记录并分页
	 * @param map
	 * @return
	 */
	public List<GpsVM> loadVMList(Map<String, Object> map);
	/**
	 * 更新对象
	 * @param gps
	 * @return
	 */
	public int updateByPrimaryKey(Gps gps);
	/**
	 * 插入新纪录
	 * @param gps
	 * @return
	 */
	public int insert(Gps gps);
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(int id);
	/**
	 * 查询对象集
	 * @return
	 */
	public List<GpsType> selectGpsType();
	/**
	 * 查询分页记录
	 * @param map
	 * @return
	 */
	public List<GpsVM> loadVMListWithGroup(Map<String, Object> map);
	/**
	 * 批量删除对象
	 * @param map
	 */
	public void deleteByIds(Map<String, Object> map);
	
	/**
	 * 判断是否存在编号的定位设备
	 * @param param
	 * @return
	 */
	public List<Gps> findByNumber(String param);
}
