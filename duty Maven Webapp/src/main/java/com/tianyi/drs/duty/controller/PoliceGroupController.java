package com.tianyi.drs.duty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
 
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;
import com.tianyi.drs.duty.viewmodel.ListResult;

/**
 * 警员分组逻辑控制器
 * 
 * @author lq
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/policeGroup")
public class PoliceGroupController {

	@Resource(name = "policeGroupService")
	protected PoliceGroupService policeGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;

	/**
	 * 获取警员分组列表
	 * 
	 * @param query
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody
	String List(
			@RequestParam(value = "policeGroup_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {

		JSONObject joQuery = JSONObject.fromObject(query);

		int orgId = joQuery.getInt("orgId");
		String orgCode = joQuery.getString("orgCode");

		Map<String, Object> map = new HashMap<String, Object>();
		page = page == 0 ? 1 : page;
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);
		map.put("orgCode", orgCode);
		map.put("orgId", orgId);

		map.put("inSubOrg", 0);

		int total = policeGroupService.loadVMCountByOrgId(map);

		List<PoliceGroupVM> pgvms = policeGroupService.loadVMListByOrgId(map);

		ListResult<PoliceGroupVM> rs = new ListResult<PoliceGroupVM>(total,
				pgvms);

		String ss = JSONObject.fromObject(rs).toString();

		return ss;

	}

	/**
	 * 获取当前组织结构集下级附属机构
	 * 
	 * @param policeGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody
	String getShareOrgs(
			@RequestParam(value = "policeGroupId", required = false) Integer policeGroupId,
			HttpServletRequest request) {

		List<PoliceGroupOrg> ls = policeGroupService
				.loadShareOrgList(policeGroupId);

		String rs = JSONArray.fromObject(ls).toString();

		return rs;

	}

	/**
	 * 获取警员分组信息
	 * 
	 * @param policeGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadPoliceGroup.do")
	public @ResponseBody
	String loadPoliceGroup(
			@RequestParam(value = "policeGroupId", required = false) Integer policeGroupId,
			HttpServletRequest request) {

		PoliceGroup pg = policeGroupService.loadById(policeGroupId);
		JSONObject joPG = JSONObject.fromObject(pg);

		List<PoliceGroupOrg> ls = policeGroupService
				.loadShareOrgList(policeGroupId);

		JSONArray joArray = JSONArray.fromObject(ls);

		joPG.accumulate("shareOrgs", joArray.toString());

		String rs = joPG.toString();
		return rs;
	}

	/**
	 * 保存警员分组信息
	 * 
	 * @param policeGroup
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "savePoliceGroup.do")
	public @ResponseBody
	String savePoliceGroup(
			@RequestParam(value = "policeGroup", required = false) String policeGroup,
			HttpServletRequest request) {

		JSONObject joPG = JSONObject.fromObject(policeGroup);

		PoliceGroup pg = new PoliceGroup();
		pg.setId(joPG.getInt("id"));
		pg.setName(joPG.getString("name"));
		pg.setShareType(joPG.getInt("shareType"));
		pg.setOrgId(joPG.getInt("orgId"));

		JSONArray jaOrgIds = joPG.getJSONArray("shareOrgIds");

		Object[] orgIds = jaOrgIds.toArray();

		policeGroupService.savePoliceGroup(pg, orgIds);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		rs.accumulate("id", pg.getId());
		return rs.toString();
	}

	/**
	 * 删除警员组
	 * 
	 * @param policeGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deletePoliceGroup.do")
	public @ResponseBody
	String deletePoliceGroup(
			@RequestParam(value = "policeGroupId", required = false) Integer policeGroupId,
			HttpServletRequest request) {

		policeGroupService.deleteById(policeGroupId);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");

		return rs.toString();
	}

	/**
	 * 根据分组id，加载组成员
	 * 
	 * @param query
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadMemberByGroupId.do")
	public @ResponseBody
	String loadMemberByGroupId(
			@RequestParam(value = "member_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {

		JSONObject joQuery = JSONObject.fromObject(query);

		int groupId = joQuery.getInt("groupId");
		page = page == 0 ? 1 : page;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (page - 1) * rows);
		map.put("pageSize", rows);
		map.put("groupId", groupId);

		int total = policeGroupService.countMemberByGroupId(groupId);

		List<PoliceGroupMemberVM> ms = policeGroupService
				.loadMemberByGroupId(map);

		ListResult<PoliceGroupMemberVM> rs = new ListResult<PoliceGroupMemberVM>(
				total, ms);

		String ss = JSONObject.fromObject(rs).toString();

		return ss;
	}

	/**
	 * 增加组成员
	 * 
	 * @param members
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "appendMember.do")
	public @ResponseBody
	String appendMember(
			@RequestParam(value = "members", required = false) String members,
			HttpServletRequest request) {

		JSONArray jaMembers = JSONArray.fromObject(members);

		// Collection<PoliceGroupMember>
		// collection=JSONArray.toCollection(jaMembers,
		// PoliceGroupMember.class);

		List<?> list2 = JSONArray.toList(jaMembers, new PoliceGroupMember(),
				new JsonConfig());// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据

		List<PoliceGroupMember> ls = new ArrayList<PoliceGroupMember>();

		for (Object o : list2) {
			PoliceGroupMember pgm = (PoliceGroupMember) o;
			ls.add(pgm);
		}

		policeGroupService.appendMemeber(ls);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);

		return rs.toString();
	}

	/**
	 * 删除组成员
	 * 
	 * @param memberId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMemberById.do")
	public @ResponseBody
	String delMemberById(
			@RequestParam(value = "memberId", required = false) Integer memberId,
			HttpServletRequest request) {

		policeGroupService.delMemberById(memberId);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);

		return rs.toString();
	}

	/**
	 * 清除组成员
	 * 
	 * @param policeGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cleanMemberByGroupId.do")
	public @ResponseBody
	String cleanMemberByGroupId(
			@RequestParam(value = "policeGroupId", required = false) Integer policeGroupId,
			HttpServletRequest request) {

		policeGroupService.cleanMember(policeGroupId);

		ObjResult<Object> rs = new ObjResult<Object>(true, null, 0, null);

		return rs.toJson();
	}

	/**
	 * 获取警员分组，用于报备资源分组查询
	 * 
	 * @param orgId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getPoliceGrouplist.do")
	public @ResponseBody
	String getPoliceGrouplist(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);

			List<PoliceGroupVM> pgvms = policeGroupService
					.loadVMListByOrgIdShared(map);
			int total = pgvms.size();
			ListResult<PoliceGroupVM> rs = new ListResult<PoliceGroupVM>(total,
					pgvms);

			String ss = JSONObject.fromObject(rs).toString();

			return ss;
		} catch (Exception ex) {
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
			List<PoliceGroup> policeGroup =policeGroupService.findByNameAndOrg(map);
			if (policeGroup.size() > 0) {
				return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
			} else {
				return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}

}
