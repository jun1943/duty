package com.tianyi.drs.basedata.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.IconsMapper; 
import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.service.IconsService;
/**
 * 图标接口层实现
 * @author lq
 *
 */
@Service("iconsService")
public class IconsServiceImpl implements IconsService  {

	@Resource(name="iconsMapper")
	private IconsMapper iconsMapper;
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#loadCount(Map<String, Object> map)
	 */
	public int loadCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iconsMapper.loadCount(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#loadList(Map<String, Object> map)
	 */
	public List<Icons> loadList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return iconsMapper.loadList(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#deleteByPrimaryKey(int id)
	 */
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return iconsMapper.deleteByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#updateByPrimaryKey(Icons icons)
	 */
	public int updateByPrimaryKey(Icons icons) {
		// TODO Auto-generated method stub
		return iconsMapper.updateByPrimaryKey(icons);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#insert(Icons icons)
	 */
	public int insert(Icons icons) {
		// TODO Auto-generated method stub
		return iconsMapper.insert(icons);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#loadById(int id)
	 */
	public Icons loadById(int id) {
		// TODO Auto-generated method stub
		return iconsMapper.loadById(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.IconsService#deleteByIds(Map<String, Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		iconsMapper.deleteByIds(map);
	}
	public List<Icons> getIconsInfo() {
		// TODO Auto-generated method stub
		return iconsMapper.getIconsInfo();
	}
}
