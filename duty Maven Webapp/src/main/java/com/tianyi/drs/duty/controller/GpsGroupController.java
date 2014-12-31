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

import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.model.GpsGroupMember;
import com.tianyi.drs.duty.model.GpsGroupOrg; 
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.GpsGroupService;
import com.tianyi.drs.duty.viewmodel.GpsGroupMemberVM;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult; 
/**
 * 定位设备分组逻辑控制器
 * @author lq
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/gpsGroup")
public class GpsGroupController {

	/**
	 * 初始化服务接口
	 */
	@Resource(name = "gpsGroupService")
	protected GpsGroupService gpsGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;

	/**
	 * 获取左右翼分组列表
	 * @param query 组织结构信息
	 * @param page  当前页
	 * @param rows  每页条数
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list.do")
	public @ResponseBody
	String List(
			@RequestParam(value = "gpsGroup_Query", required = false) String query,
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

		int total = gpsGroupService.loadVMCountByOrgId(map);

		List<GpsGroupVM> pgvms = gpsGroupService.loadVMListByOrgId(map);

		ListResult<GpsGroupVM> rs = new ListResult<GpsGroupVM>(total, pgvms);

		String ss = JSONObject.fromObject(rs).toString();

		return ss;

	}
	/**
	 * 获取当前组织结构以及下级机构
	 * @param gpsGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody
	String getShareOrgs(
			@RequestParam(value = "gpsGroupId", required = false) Integer gpsGroupId,
			HttpServletRequest request) {

		List<GpsGroupOrg> ls = gpsGroupService.loadShareOrgList(gpsGroupId);

		String rs = JSONArray.fromObject(ls).toString();

		return rs;

	}
	/**
	 * 根据分组id获取组成员
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

		int total = gpsGroupService.countMemberByGroupId(groupId);

		List<GpsGroupMemberVM> ms = gpsGroupService.loadMemberByGroupId(map);

		ListResult<GpsGroupMemberVM> rs = new ListResult<GpsGroupMemberVM>(
				total, ms);

		String ss = JSONObject.fromObject(rs).toString();

		return ss;
	}
	/**
	 * 添加组成员
	 * @param members 组成员列表
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

		List<?> list2 = JSONArray.toList(jaMembers, new GpsGroupMember(),
				new JsonConfig());// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据，即List盛装的数据

		List<GpsGroupMember> ls = new ArrayList<GpsGroupMember>();

		for (Object o : list2) {
			GpsGroupMember pgm = (GpsGroupMember) o;
			ls.add(pgm);
		}

		gpsGroupService.appendMemeber(ls);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);

		return rs.toString();
	}
	/**
	 * 保存定位设备分组
	 * @param gpsGroup
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "saveGpsGroup.do")
	public @ResponseBody
	String saveGpsGroup(
			@RequestParam(value = "gpsGroup", required = false) String gpsGroup,
			HttpServletRequest request) {

		JSONObject joPG = JSONObject.fromObject(gpsGroup);

		GpsGroup pg = new GpsGroup();
		pg.setId(joPG.getInt("id"));
		pg.setName(joPG.getString("name"));
		pg.setShareType(joPG.getInt("shareType"));
		pg.setOrgId(joPG.getInt("orgId"));

		JSONArray jaOrgIds = joPG.getJSONArray("shareOrgIds");

		Object[] orgIds = jaOrgIds.toArray();

		gpsGroupService.saveGpsGroup(pg, orgIds);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		rs.accumulate("id", pg.getId());
		return rs.toString();
	}

	/**
	 * 删除定位设备分组
	 * 
	 * @param GpsGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteGpsGroup.do")
	public @ResponseBody
	String deleteGpsGroup(
			@RequestParam(value = "gpsGroupId", required = false) Integer gpsGroupId,
			HttpServletRequest request) {

		gpsGroupService.deleteById(gpsGroupId);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");

		return rs.toString();
	}
	/**
	 * 加载定位设备分组列表
	 * @param gpsGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "loadGpsGroup.do")
	public @ResponseBody
	String loadGpsGroup(
			@RequestParam(value = "gpsGroupId", required = false) Integer gpsGroupId,
			HttpServletRequest request) {

		GpsGroup pg = gpsGroupService.loadById(gpsGroupId);
		JSONObject joPG = JSONObject.fromObject(pg);

		List<GpsGroupOrg> ls = gpsGroupService.loadShareOrgList(gpsGroupId);

		JSONArray joArray = JSONArray.fromObject(ls);

		joPG.accumulate("shareOrgs", joArray.toString());

		String rs = joPG.toString();
		return rs;
	}
	/**
	 * 删除组成员
	 * @param memberId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delMemberById.do")
	public @ResponseBody
	String delMemberById(
			@RequestParam(value = "memberId", required = false) Integer memberId,
			HttpServletRequest request) {

		gpsGroupService.delMemberById(memberId);

		JSONObject rs = new JSONObject();
		rs.accumulate("isSuccess", true);

		return rs.toString();
	}
	/**
	 * 清空组成员
	 * @param gpsGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "cleanMemberByGroupId.do")
	public @ResponseBody
	String cleanMemberByGroupId(
			@RequestParam(value = "gpsGroupId", required = false) Integer gpsGroupId,
			HttpServletRequest request) {

		gpsGroupService.cleanMember(gpsGroupId);

		ObjResult<Object> rs = new ObjResult<Object>(true, null, 0, null);

		return rs.toJson();
	}
	
	/**
	 * 获取定位设备分组，用于GPS资源分组查询
	 * @param orgId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "getGpsGrouplist.do")
	public @ResponseBody
	String getGpsGrouplist(@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", orgId);

			List<GpsGroupVM> pgvms = gpsGroupService.loadVMListByOrgIdShared(map);
			int total = pgvms.size();
			ListResult<GpsGroupVM> rs = new ListResult<GpsGroupVM>(total,
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
			List<GpsGroup> gpsGroup =gpsGroupService.findByNameAndOrg(map);
			if (gpsGroup.size() > 0) {
				return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
			} else {
				return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}
}
