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
   
import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.model.WeaponGroupMember;
import com.tianyi.drs.duty.model.WeaponGroupOrg;
import com.tianyi.drs.duty.service.OrgService; 
import com.tianyi.drs.duty.service.WeaponGroupService; 
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult; 
import com.tianyi.drs.duty.viewmodel.WeaponGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;
/**
 * 武器设备分组逻辑控制器
 * @author lq
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/weaponGroup")
public class WeaponGroupController {


	@Resource(name = "weaponGroupService")
	protected WeaponGroupService weaponGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;
	
	/**
	 * 获取武器分组列表
	 * @param query
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody String List(
			@RequestParam(value = "weaponGroup_Query", required = false) String query,
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
		
		int total=weaponGroupService.loadVMCountByOrgId(map);

		List<WeaponGroupVM> pgvms=weaponGroupService.loadVMListByOrgId(map);
		
		ListResult<WeaponGroupVM> rs=new ListResult<WeaponGroupVM>(total,pgvms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
		
	}
	
	/**
	 * 获取当前组织机构及下级机构
	 * @param weaponGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody String getShareOrgs(
			@RequestParam(value = "weaponGroupId", required = false) Integer weaponGroupId,
			HttpServletRequest request
			){
		
		
		List<WeaponGroupOrg> ls = weaponGroupService.loadShareOrgList(weaponGroupId);
		
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
		
		int total=weaponGroupService.countMemberByGroupId(groupId);

		List<WeaponGroupMemberVM> ms=weaponGroupService.loadMemberByGroupId(map);
	
		ListResult<WeaponGroupMemberVM> rs=new ListResult<WeaponGroupMemberVM>(total,ms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
	}
	/**
	 * 添加组成员
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

		List<?> list2 = JSONArray.toList(jaMembers, new WeaponGroupMember(), new JsonConfig());//参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据
		
		List<WeaponGroupMember> ls=new ArrayList<WeaponGroupMember>();
		
		for(Object o:list2){
			WeaponGroupMember pgm=(WeaponGroupMember)o;
			ls.add(pgm);
		}
		
		weaponGroupService.appendMemeber(ls);

		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		
		return rs.toString();
	}
	
	/**
	 * 保存武器分组信息
	 * @param weaponGroup
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveWeaponGroup.do")
	public @ResponseBody String saveWeaponGroup(
			@RequestParam(value = "weaponGroup", required = false) String weaponGroup,
			HttpServletRequest request
			){
		
		JSONObject joPG =JSONObject.fromObject(weaponGroup);
		
		WeaponGroup pg=new WeaponGroup();
		pg.setId(joPG.getInt("id"));
		pg.setName(joPG.getString("name"));
		pg.setShareType(joPG.getInt("shareType"));
		pg.setOrgId(joPG.getInt("orgId"));
		
		JSONArray jaOrgIds=joPG.getJSONArray("shareOrgIds");
		
		Object[] orgIds=jaOrgIds.toArray();
		
		weaponGroupService.saveWeaponGroup(pg, orgIds);
		
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		rs.accumulate("id", pg.getId());
		return rs.toString();
	}
	/**
	 * 删除武器分组
	 * @param weaponGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteWeaponGroup.do")
	 public @ResponseBody String deleteWeaponGroup(
			 @RequestParam(value="weaponGroupId",required=false) Integer weaponGroupId,
				HttpServletRequest request
				){
		 
		weaponGroupService.deleteById(weaponGroupId);
		 
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		 	
		return rs.toString();
	 }
	/**
	 * 获取武器分组详细信息
	 * @param weaponGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadWeaponGroup.do")
		public  @ResponseBody String loadWeaponGroup(
				@RequestParam(value="weaponGroupId",required=false) Integer weaponGroupId,
				HttpServletRequest request
				){
			
			WeaponGroup pg=	weaponGroupService.loadById(weaponGroupId);
			JSONObject joPG=JSONObject.fromObject(pg);
			
			List<WeaponGroupOrg> ls = weaponGroupService.loadShareOrgList(weaponGroupId);
			
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
		
		weaponGroupService.delMemberById(memberId);
		
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		
		return rs.toString();
	}
	/**
	 * 清空组成员列表
	 * @param weaponGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cleanMemberByGroupId.do")
	public @ResponseBody String cleanMemberByGroupId(
			@RequestParam(value = "weaponGroupId", required = false) Integer weaponGroupId,
			HttpServletRequest request
			){
		
		weaponGroupService.cleanMember(weaponGroupId);

		ObjResult<Object> rs=new ObjResult<Object>(true,null,0,null);
		
		return rs.toJson();
	}

	/**
	 * 获取武器分组列表，用于报备资源费祖筛选查询
	 * @param orgId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getWeaponGrouplist.do")
	public @ResponseBody
	String getWeaponGrouplist(@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);

			List<WeaponGroupVM> pgvms = weaponGroupService.loadVMListByOrgIdShared(map);
			int total = pgvms.size();
			ListResult<WeaponGroupVM> rs = new ListResult<WeaponGroupVM>(total,
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
			List<WeaponGroup> weaponGroup =weaponGroupService.findByNameAndOrg(map);
			if (weaponGroup.size() > 0) {
				return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
			} else {
				return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}
}
