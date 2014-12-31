package com.tianyi.drs.duty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
  
import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.model.VehicleGroupMember;
import com.tianyi.drs.duty.model.VehicleGroupOrg;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.VehicleGroupService;
import com.tianyi.drs.duty.viewmodel.ListResult;  
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.VehicleGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM; 

/**
 * 车辆分组逻辑控制器
 * @author lq
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/vehicleGroup")
public class VehicleGroupController {

	@Resource(name = "vehicleGroupService")
	protected VehicleGroupService vehicleGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;

	/**
	 * 查询车辆分组列表
	 * @param query
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody String List(
			@RequestParam(value = "vehicleGroup_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {
	
		JSONObject joQuery =JSONObject.fromObject(query);

		int orgId=joQuery.getInt("orgId");
		String orgCode=joQuery.getString("orgCode");
		
		Map<String, Object> map = new HashMap<String, Object>();
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page-1) * rows);
		map.put("pageSize", rows);
		map.put("orgCode", orgCode);
		map.put("orgId", orgId);
		
		map.put("inSubOrg", 0);
		
		int total=vehicleGroupService.loadVMCountByOrgId(map);

		List<VehicleGroupVM> pgvms=vehicleGroupService.loadVMListByOrgId(map);
		
		ListResult<VehicleGroupVM> rs=new ListResult<VehicleGroupVM>(total,pgvms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
		
	}
	
	/**
	 * 获取当前组织机构及下级机构
	 * @param vehicleGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody String getShareOrgs(
			@RequestParam(value = "vehicleGroupId", required = false) Integer vehicleGroupId,
			HttpServletRequest request
			){
		
		
		List<VehicleGroupOrg> ls = vehicleGroupService.loadShareOrgList(vehicleGroupId);
		
		String rs=JSONArray.fromObject(ls).toString();
		
		return rs;
		
	}
	
	/**
	 * 根据分组id，获取组成员列表
	 * @param query
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadMemberByGroupId.do")
	public @ResponseBody String loadMemberByGroupId(
			@RequestParam(value = "member_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {
		
		JSONObject joQuery =JSONObject.fromObject(query);

		int groupId=joQuery.getInt("groupId");
		page=page==0?1:page;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (page-1) * rows);
		map.put("pageSize", rows);
		map.put("groupId", groupId);
		
		int total=vehicleGroupService.countMemberByGroupId(groupId);

		List<VehicleGroupMemberVM> ms=vehicleGroupService.loadMemberByGroupId(map);
	
		ListResult<VehicleGroupMemberVM> rs=new ListResult<VehicleGroupMemberVM>(total,ms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
	}

	/**
	 * 增加组成员
	 * @param members
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "appendMember.do")
	public @ResponseBody String appendMember(
			@RequestParam(value = "members", required = false) String members,
			HttpServletRequest request
			){
		
		JSONArray jaMembers=JSONArray.fromObject(members);
				
		//Collection<PoliceGroupMember> collection=JSONArray.toCollection(jaMembers, PoliceGroupMember.class);

		List<?> list2 = JSONArray.toList(jaMembers, new VehicleGroupMember(), new JsonConfig());//参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据
		
		List<VehicleGroupMember> ls=new ArrayList<VehicleGroupMember>();
		
		for(Object o:list2){
			VehicleGroupMember pgm=(VehicleGroupMember)o;
			ls.add(pgm);
		}
		
		vehicleGroupService.appendMemeber(ls);

		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		
		return rs.toString();
	}
	
	/**
	 * 保存车辆分组信息
	 * @param vehicleGroup
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveVehicleGroup.do")
	public @ResponseBody String saveVehicleGroup(
			@RequestParam(value = "vehicleGroup", required = false) String vehicleGroup,
			HttpServletRequest request
			){
		
		JSONObject joPG =JSONObject.fromObject(vehicleGroup);
		
		VehicleGroup pg=new VehicleGroup();
		pg.setId(joPG.getInt("id"));
		pg.setName(joPG.getString("name"));
		pg.setShareType(joPG.getInt("shareType"));
		pg.setOrgId(joPG.getInt("orgId"));
		
		JSONArray jaOrgIds=joPG.getJSONArray("shareOrgIds");
		
		Object[] orgIds=jaOrgIds.toArray();
		
		vehicleGroupService.saveVehicleGroup(pg, orgIds);
		
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		rs.accumulate("id", pg.getId());
		return rs.toString();
	}
	/**
	 * 删除车辆组
	 * @param vehicleGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteVehicleGroup.do")
	 public @ResponseBody String deleteVehicleGroup(
			 @RequestParam(value="vehicleGroupId",required=false) Integer vehicleGroupId,
				HttpServletRequest request
				){
		 
		 vehicleGroupService.deleteById(vehicleGroupId);
		 
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		 	
		return rs.toString();
	 }
	
	/**
	 * 获取车辆分组信息
	 * @param vehicleGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadVehicleGroup.do")
		public  @ResponseBody String loadVehicleGroup(
				@RequestParam(value="vehicleGroupId",required=false) Integer vehicleGroupId,
				HttpServletRequest request
				){
			
			VehicleGroup pg=	vehicleGroupService.loadById(vehicleGroupId);
			JSONObject joPG=JSONObject.fromObject(pg);
			
			List<VehicleGroupOrg> ls = vehicleGroupService.loadShareOrgList(vehicleGroupId);
			
			JSONArray joArray=JSONArray.fromObject(ls);
			
			joPG.accumulate("shareOrgs", joArray.toString());
			
			String rs=joPG.toString();
			return rs;
		}
	
	/**
	 * 删除组成员
	 * @param memberId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMemberById.do")
	public @ResponseBody String delMemberById(
			@RequestParam(value = "memberId", required = false) Integer memberId,
			HttpServletRequest request
			){
		
		vehicleGroupService.delMemberById(memberId);
		
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		
		return rs.toString();
	}
	
	/**
	 * 清空组成员列表
	 * @param vehicleGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cleanMemberByGroupId.do")
	public @ResponseBody String cleanMemberByGroupId(
			@RequestParam(value = "vehicleGroupId", required = false) Integer vehicleGroupId,
			HttpServletRequest request
			){
		
		vehicleGroupService.cleanMember(vehicleGroupId);

		ObjResult<Object> rs=new ObjResult<Object>(true,null,0,null);
		
		return rs.toJson();
	}

	/**
	 * 获取车辆分组列表，用于报备资源分组条件筛选
	 * @param orgId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getVehicleGrouplist.do")
	public @ResponseBody
	String getVehicleGrouplist(@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);

			List<VehicleGroupVM> pgvms = vehicleGroupService.loadVMListByOrgIdShared(map);
			int total = pgvms.size();
			ListResult<VehicleGroupVM> rs = new ListResult<VehicleGroupVM>(total,
					pgvms);

			String ss = JSONObject.fromObject(rs).toString();

			return ss;
		}catch(Exception ex){
			return "{'total':0,rows:[]}";
		}
		
	}

	/**
	 * 判断是否有有分组存在
	 * 
	 * 判断是否分组名称重复；
	 */
	@RequestMapping(value = "isExistGroup.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isExistGroup(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "orgId", required = false) Integer orgId)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("orgId", orgId);
			List<VehicleGroup> vehicleGroup =vehicleGroupService.findByNameAndOrg(map);
			if (vehicleGroup.size() > 0) {
				return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
			} else {
				return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}

}
