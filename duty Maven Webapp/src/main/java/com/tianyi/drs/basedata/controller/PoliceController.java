package com.tianyi.drs.basedata.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.util.HttpclientUtils;
import com.tianyi.drs.duty.viewmodel.DutyVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;

import sun.misc.BASE64Decoder;
/*
 * 警员管理逻辑控制器
 * 
 */
@Scope("prototype")
@Controller
@RequestMapping("/police")
public class PoliceController {
	@Resource(name = "policeService")
	protected PoliceService policeService;// =
											// ServiceFactory.getPoliceServiceInit();

	@RequestMapping("index")
	public String index() {
		return "index";
	}


	/*
	 * 获取警员列表信息
	 * 
	 * police_Query：查询条件包
	 * sort：排序列
	 * order：排序方式
	 * page：当前页
	 * rows：每页条数
	 */
	@RequestMapping(value = "getPoliceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceList(
			@RequestParam(value = "police_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			HttpServletRequest request) throws Exception {
		try {
//
//			String s = HttpclientUtils.postXML(
//					"http://25.30.5.105:8080/drs/home/cacheMonitor",
//					"<><><><><>");
//			s = s + "";
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String name = joQuery.getString("name");
			int typeid = Integer.parseInt(joQuery.getString("typeid"));
			int[] ids = new int[1];
			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<PoliceVM> list = new ArrayList<PoliceVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("name", name);
			if (sort != null) {
				if (!sort.equals("")) {
					map.put("sort", "p." + sort);
				}
			} else {
				map.put("sort", "p.isUsed");
			}
			if (order != null) {
				if (!order.equals("")) {
					map.put("order", order);
				}
			} else {
				map.put("order", "asc");
			}
			if (typeid > 0) {
				ids[0] = typeid;
				map.put("ids", ids);
			}
			int total = policeService.loadVMCount(map);
			list = policeService.loadVMList(map);

			ListResult<PoliceVM> rs = new ListResult<PoliceVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	/*
	 * 获取警员资源列表
	 * 
	 * 判断是否有分组id传入，如果有分组，则从分组里面选择警员
	 * 若传入分组id为空，则从polie表里面抽取数据
	 */
	@RequestMapping(value = "getPoliceSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceSource(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "groupId", required = false) String groupId)
			throws Exception {
		try {
			List<PoliceVM> list = new ArrayList<PoliceVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			name = name.replace(",", "");
			map.put("orgId", orgId);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			map.put("name", name);

			// map.put("typeId", typeId);
			// map.put("groupId", groupId);

			if (typeId != null && typeId != "") {
				String[] s = {};
				s = typeId.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			}

			if (groupId != null && groupId != "") {
				String[] gs = {};
				gs = groupId.split(",");
				int[] gids = new int[gs.length];
				for (int i = 0; i < gs.length; i++) {
					gids[i] = Integer.parseInt(String.valueOf(gs[i]));
				}
				map.put("gids", gids);
				list = policeService.loadVMListWithGroupList(map);
			} else {
				list = policeService.loadVMListWithGroup(map);
			}

			// list = policeService.loadVMListWithGroup(map);

			int total = list.size();
			ListResult<PoliceVM> rs = new ListResult<PoliceVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 保存警员信息逻辑
	 */
	@RequestMapping(value = "savePolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String savePolice(Police police) throws Exception {
		try {
			police.setPlatformId(1);
			police.setIsused(true);
			police.setSyncState(true);
			int result = 0;
			if (police.getId() > 0) {
				int pid = police.getId();
				police.setId(pid);
				result = policeService.updateByPrimaryKey(police);
			} else {
				result = policeService.insert(police);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 删除警员id
	 * 
	 * 批量删除
	 */
	@RequestMapping(value = "deletePolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deletePolice(String id) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int result = 0;
			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);

				policeService.deleteByIds(map);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 判断是否有有警员存在
	 * 
	 * 判断是否身份证号重复；
	 * 
	 * 判断是否警号重复；
	 */
	@RequestMapping(value = "isExistPolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isExistPolice(
			@RequestParam(value = "param", required = false) String param,
			@RequestParam(value = "paramType", required = false) String paramType)
			throws Exception {
		try {
			if (paramType.equals("idCard")) {
				if (!param.equals("")) {
					Police police = policeService.findByidCard(param);
					if (police != null) {
						return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
					} else {
						return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
					}
				} else {
					return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
				}
			} else if (paramType.equals("number")) {
				if (!param.equals("")) {
					Police police = policeService.findBycode(param);
					if (police != null) {
						return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
					} else {
						return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
					}
				} else {
					return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
				}
			} else {
				return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}

	/*
	 * 获取警员类型列表，以下拉列表形式呈现；
	 */
	@RequestMapping(value = "getPoliceType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceType() throws Exception {
		try {
			List<PoliceType> list = policeService.selectPoliceType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/*
	 * 获取警员类型列表，一数据列表的形式展现；
	 */
	@RequestMapping(value = "getPoliceTypeList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceTypeList() throws Exception {
		try {
			List<PoliceType> list = policeService.selectPoliceType();
			int total = list.size();
			ListResult<PoliceType> rs = new ListResult<PoliceType>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	
	/*
	 * 获取警员分组列表，以数据列表的形式展现
	 * 
	 * 用于报备类型警员过滤条件筛选；
	 */
	@RequestMapping(value = "getintercomGroup.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getintercomGroup() throws Exception {
		try {
			List<IntercomGroup> list = policeService.selectIntercomGroup();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/*
	 * 获取当前组织机构下面所属的所有GPS列表
	 * 
	 * 以下拉列表的形式展现；
	 */
	
	@RequestMapping(value = "getGpsId.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsId(int orgId) throws Exception {
		try {
			List<GpsBaseVM> list = policeService.selectGpsId(orgId);
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	
	/*
	 * 
	 * 修改警员的启用与锁定状态；
	 * 
	 * 启用为true
	 * 锁定为false；
	 */
	@RequestMapping(value = "changePoliceState.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String changePoliceState(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "isUsed", required = false) Integer isUsed)
			throws Exception {
		try {
			Police police = new Police();
			police = policeService.selectByPrimaryKey(id);
			int result = id;
			String message = "";
			if (police != null) {
				if (isUsed == 0) {
					police.setIsused(true);
				} else {
					police.setIsused(false);
				}
				// 更新状态
				police.setId(id);
				result = policeService.updateByPrimaryKey(police);
				message = "状态修改成功,result is " + result;
			} else {
				message = "状态修改失败,根据Id获取对象为空";
			}
			return "{\"success\":true,\"Message\":\"" + message + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"状态修改失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 查询警员列表
	 */
	@RequestMapping(value = "selectPolice.do", produces = "application/json;charset=UTF-8")
	public void selectPolice() throws Exception {
		try {
			Police police = policeService.selectByPrimaryKey(2);
			String name = "list is empty!";
			if (police != null) {
				name = police.getName();
			}
			System.out
					.println("total count is :" + 1 + " , First is : " + name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}

	@RequestMapping(value = "selectPoliceList.do", produces = "application/json;charset=UTF-8")
	public void selectPoliceList() throws Exception {
		try {
			List<Police> list = policeService.selectAll();
			String name = "list is empty!";
			int count = list.size();
			if (count > 0) {
				name = list.get(0).getName();
			}
			System.out.println("total count is :" + count + " , First is : "
					+ name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}

	/*
	 * 用户信息验证 userName：用户名 password：用户密码
	 */
	@RequestMapping(value = "batchGetUserAuthorization.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String batchGetUserAuthorization(
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "password", required = false) String password)
			throws Exception {
		try {
			UserObjectVM uvm = new UserObjectVM();
			Map<String, Object> map = new HashMap<String, Object>();
			userName = new String(new BASE64Decoder().decodeBuffer(userName));
			if (userName.endsWith("-")) {
				userName = userName.substring(0, userName.length() - 1);
			}
			map.put("userName", userName);
			map.put("password", password);
			uvm = policeService.getUserAuthorization(map);

			ObjResult<UserObjectVM> rs = new ObjResult<UserObjectVM>(true,
					null, uvm == null ? 0 : uvm.getId(), uvm);

			String s = rs.toJson();

			return s;

		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Data\":null}";
		}
	}

}
