package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.DutyType;
import com.tianyi.drs.duty.util.ResultMsg;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;

/**
 * 报备类型管理接口层
 * @author lq
 *
 */
public interface DutyTypeService {
	
	/**
	 * 加载勤务类型属性列表，用于下拉列表使用
	 * @return
	 */
	List<DutyTypePropertyVM>  loadProperties();
	
	/**
	 * 获取启用状态下的勤务类型数据
	 * @param isUsed
	 * @return
	 */
	List<DutyTypeVM> loadDutyTypeVM(Boolean isUsed);
	
	/**
	 * 保存勤务类型数据
	 * @param vm
	 */
	void save(DutyTypeVM vm);
	
	/**
	 * 更新勤务报备类型状态，启用或锁定
	 * 
	 * @param id
	 * @param isUsed
	 */
	void updateUseStateByFullPath(Integer id,Boolean isUsed);
	
	/**
	 * 删除勤务类型节点数据信息
	 * @param id
	 * @return
	 */
	ResultMsg deleteNode(Integer id);
 
	/**
	 * 加载勤务类型列表数据，并分页
	 * @param map
	 * @return
	 */
	List<DutyType> loadDutyType(Map<String, Object> map);

	/**
	 * 获取文物类型子节点数据统计汇总
	 * @param map
	 * @return
	 */
	List<DutyItemCountVM> loadDutyItemCount(Map<String, Object> map);
 
}
