package com.tianyi.drs.duty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.model.Duty;
import com.tianyi.drs.duty.model.DutyProperty;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.model.PoliceTarget;
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.service.DutyTaskService;
import com.tianyi.drs.duty.service.ExportService;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

/**
 * 报备详细操作逻辑控制器
 * 
 * @author lq
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/duty")
public class DutyController {
	/**
	 * 初始化需要的服务层接口
	 */
	@Resource(name = "dutyService")
	protected DutyService dutyService;

	@Resource(name = "dutyTaskService")
	protected DutyTaskService dutyTaskService;

	@Resource(name = "exportService")
	protected ExportService exportService;
	
	/**
	 * 根据组织机构id和日期，获取详细的报备数据，以树形结构显示
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param ymd
	 *            日期
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "load.do")
	public @ResponseBody
	String load(@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "ymd", required = false) Integer ymd,
			HttpServletRequest request) {
		return null;
	}

	/**
	 * 报备模板的加载获取赋值报备数据信息
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param ymd
	 *            日期
	 * @param id
	 *            传入的id值，若id不等于空，则是通过模板选择来加载报备数据，若id为空，则通过报备复制加载报备数据
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadDutyByOrgIdAndYMD.do")
	public @ResponseBody
	String loadDutyByOrgIdAndYMD(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "ymd", required = false) Integer ymd,
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request) {
		
		//test!
		//test1();
		
		DutyVM dvm = null;

		if (id == null) {
			dvm = dutyService.loadVMByOrgIdAndYmd(orgId, ymd);
		} else {
			dvm = dutyService.loadById(id);
		}

		ObjResult<DutyVM> rs = new ObjResult<DutyVM>(true, null,
				dvm == null ? 0 : dvm.getId(), dvm);

		String s = rs.toJson();

		return s;
	}

	/**
	 * 保存报备明细数据
	 * 
	 * @param dvm
	 *            前台构建对象
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save.do")
	public @ResponseBody
	String save(@RequestParam(value = "duty", required = false) String dvm,
			HttpServletRequest request) {

		JSONObject jobj = JSONObject.fromObject(dvm);

		// jobj.remove("beginTime");
		// jobj.remove("endTime2");

		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpher(new String[] { "yyyy-MM-dd HH:mm" }));

		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		classMap.put("items", DutyItemVM.class);
		classMap.put("children", DutyItemVM.class);
		classMap.put("targets", PoliceTarget.class);

		DutyVM d = (DutyVM) JSONObject.toBean(jobj, DutyVM.class, classMap);

		dutyService.save(d);

		ObjResult<DutyVM> rs = new ObjResult<DutyVM>(true, null, d.getId(),
				null);// 暂时不

		return rs.toJson();
	}

	/**
	 * 加载当前组织机构下已保存的模板
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadTemplateByOrgId.do")
	public @ResponseBody
	String loadTemplateByOrgId(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {

		List<Duty> dvms = dutyService.loadTemplatesWithOutItem(orgId);

		ListResult<Duty> rs = new ListResult<Duty>(dvms.size(), dvms, true);

		return rs.toJson();
	}

	@RequestMapping(value = "deleteDutyTemplateAction.do")
	public @ResponseBody
	String deleteDutyTemplateAction(
			@RequestParam(value = "temId", required = false) Integer param,
			HttpServletRequest request) { 
		try {
			Duty duty = new Duty();
			duty = dutyService.loadTempById(param);
			if(duty!=null){
				int dutyId = duty.getId();
				dutyService.deleteByDutyId(dutyId);
				dutyService.deleteTempById(param);
			}
			 return "{\"isSuccess\":true,\"Message\":\"模板删除成功\"}";
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}

	}

	/**
	 * 加载当前勤务类型的关联任务属性
	 * 
	 * @param orgId
	 * @param orgCode
	 * @param taskType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadTaskTargetByOrg.do")
	public @ResponseBody
	String loadTaskTargetByOrg(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "taskType", required = false) Integer taskType,
			HttpServletRequest request) {

		Org org = new Org();

		org.setId(orgId);
		org.setCode(orgCode);

		List<TaskTargetVM> ls = dutyTaskService.loadTaskTargetVMList(taskType,
				org);

		ListResult<TaskTargetVM> rs = new ListResult<TaskTargetVM>(ls.size(),
				ls, true);

		return rs.toJson();
	}

	/**
	 * 获取勤务类型关联属性
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getdutyProperty.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getdutyProperty() throws Exception {
		try {
			List<DutyProperty> list = dutyService.selectdutyProperty();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}
	
//	private void test1(){
//		List<ExtItem<Police>> ls=exportService.loadPoliceDutyInfo(15, 20141209);
////		List<ExtItem<Vehicle>> ls=exportService.loadVehicleDutyInfo(15, 20141209);
////		List<ExtItem<Weapon>> ls=exportService.loadWeaponDutyInfo(15, 20141209);
////		List<ExtItem<Gps>> ls=exportService.loadGpsDutyInfo(15, 20141209);
////		if(ls.size()>0){
////			int s = ls.size();
////		}
//	}

}
