package com.tianyi.drs.duty.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.duty.model.DutyType;
import com.tianyi.drs.duty.service.DutyTypeService;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.util.ResultMsg;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;

/**
 * 勤务类型管理逻辑控制器
 * 
 * @author lq
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/dutyType")
public class DutyTypeController {

	/**
	 * 初始化服务层接口
	 */
	@Resource(name = "dutyTypeService")
	protected DutyTypeService dutyTypeService;

	@Resource(name = "policeService")
	protected PoliceService policeService;

	@Resource(name = "policeGroupService")
	protected PoliceGroupService policeGroupService;

	/**
	 * 获取勤务类型列表，非模板，并且是启用状态的模板
	 * 
	 * @param isUsed
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody
	String List(
			@RequestParam(value = "isUsed", required = false) Boolean isUsed,
			HttpServletRequest request) {

		List<DutyTypeVM> ls = dutyTypeService.loadDutyTypeVM(isUsed);

		ListResult<DutyTypeVM> rs = new ListResult<DutyTypeVM>(ls.size(), ls);

		String result = rs.toJson();
		return result;
	}

	@RequestMapping(value = "loadProperties.do")
	public @ResponseBody
	String loadProperties(HttpServletRequest request) {

		List<DutyTypePropertyVM> ls = dutyTypeService.loadProperties();

		ListResult<DutyTypePropertyVM> rs = new ListResult<DutyTypePropertyVM>(
				ls.size(), ls);

		String result = rs.toJson();
		return result;
	}

	/**
	 * 保存勤务类型；
	 * 
	 * @param dutyType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveDutyType.do")
	public @ResponseBody
	String saveDutyType(
			@RequestParam(value = "dutyType", required = false) String dutyType,
			HttpServletRequest request) {

		JSONObject jsObj = JSONObject.fromObject(dutyType);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		classMap.put("properties", DutyTypePropertyVM.class);
		DutyTypeVM dtvm = (DutyTypeVM) JSONObject.toBean(jsObj,
				DutyTypeVM.class, classMap);

		dutyTypeService.save(dtvm);

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(true, null,
				dtvm.getId(), null);

		String result = rs.toJson();
		return result;
	}

	/**
	 * 修改勤务类型的启用与锁定状态
	 * 
	 * @param id
	 * @param isUsed
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "changeDutyTypeUseState.do")
	public @ResponseBody
	String changeDutyTypeUseState(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "isUsed", required = false) String isUsed,
			HttpServletRequest request) {

		Integer did = Integer.parseInt(id);
		Boolean dIsUsed = Boolean.parseBoolean(isUsed);

		dutyTypeService.updateUseStateByFullPath(did, dIsUsed);

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(true, null, 0,
				null);

		String result = rs.toJson();
		return result;
	}

	/**
	 * 删除勤务类型
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteDutyType.do")
	public @ResponseBody
	String deleteDutyType(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "parentid", required = false) Integer pid,
			@RequestParam(value = "isleaf", required = false) Integer isleaf,
			HttpServletRequest request) {

		Integer did = Integer.valueOf(id);
		ResultMsg rm = dutyTypeService.deleteNode(did);

		if (pid > 0) { 
			if (isleaf == 1) {
				DutyType dt = new DutyType();
				dt = dutyTypeService.selectByPrimaryKey(pid);
				if (dt != null) {
					dt.setIsLeaf(true);
					dutyTypeService.updateByPrimaryKey(dt);
				}
			}
		}

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(rm.getIsSuccess(),
				rm.getMessage(), 0, null);

		String result = rs.toJson();
		return result;
	}
}
