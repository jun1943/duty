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

@Scope("prototype")
@Controller
@RequestMapping("/weaponGroup")
public class WeaponGroupController {


	@Resource(name = "weaponGroupService")
	protected WeaponGroupService weaponGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;
	

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
	

	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody String getShareOrgs(
			@RequestParam(value = "weaponGroupId", required = false) Integer weaponGroupId,
			HttpServletRequest request
			){
		
		
		List<WeaponGroupOrg> ls = weaponGroupService.loadShareOrgList(weaponGroupId);
		
		String rs=JSONArray.fromObject(ls).toString();
		
		return rs;
		
	}
	

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
	 * 删除警员组
	 * @param policeGroupId
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
	
	@RequestMapping(value = "cleanMemberByGroupId.do")
	public @ResponseBody String cleanMemberByGroupId(
			@RequestParam(value = "weaponGroupId", required = false) Integer weaponGroupId,
			HttpServletRequest request
			){
		
		weaponGroupService.cleanMember(weaponGroupId);

		ObjResult<Object> rs=new ObjResult<Object>(true,null,0,null);
		
		return rs.toJson();
	}
}
