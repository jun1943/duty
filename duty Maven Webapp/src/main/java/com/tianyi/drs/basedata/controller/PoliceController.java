package com.tianyi.drs.basedata.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList; 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;

import sun.misc.BASE64Decoder;

/*
 * 警员管理逻辑控制器
 * 
 */
@SuppressWarnings("restriction")
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
	 * police_Query：查询条件包 sort：排序列 order：排序方式 page：当前页 rows：每页条数
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
			// String s = HttpclientUtils.postXML(
			// "http://25.30.5.105:8080/drs/home/cacheMonitor",
			// "<><><><><>");
			// s = s + "";
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
	 * 判断是否有分组id传入，如果有分组，则从分组里面选择警员 若传入分组id为空，则从polie表里面抽取数据
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
					List<Police> police = policeService.findByidCard(param);
					if (police.size() > 0) {
						return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
					} else {
						return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
					}
				} else {
					return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
				}
			} else if (paramType.equals("number")) {
				if (!param.equals("")) {
					List<Police> police = policeService.findBycode(param);
					if (police.size() > 0) {
						return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
					} else {
						return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
					}
				} else {
					return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
				}
			} else if (paramType.equals("intercomPerson")) {
				if (!param.equals("")) {
					List<Police> police = policeService
							.findByintercomPerson(param);
					if (police.size() > 0) {
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
	 * 启用为true 锁定为false；
	 */
	@RequestMapping(value = "changePoliceState.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String changePoliceState(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "isUsed", required = false) Integer isUsed)
			throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String message = "";
			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
				map.put("isUsed", isUsed);
				policeService.changePoliceStateByIds(map);
				message = "状态修改成功";
			} else {
				message = "传入后台id为空";
			}
			// Police police = new Police();
			// police = policeService.selectByPrimaryKey(id);
			// int result = id;

			// if (police != null) {
			// if (isUsed == 0) {
			// police.setIsused(true);
			// } else {
			// police.setIsused(false);
			// }
			// // 更新状态
			// police.setId(id);
			// result = policeService.updateByPrimaryKey(police);
			// message = "状态修改成功,result is " + result;
			// } else {
			// message = "状态修改失败,根据Id获取对象为空";
			// }
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
			Date date = new Date();
			// Calendar canlandar = Calendar.getInstance();
			// canlandar.setTime(date);
			int years = date.getYear() + 1900;
			int month = date.getMonth() + 1;
			uvm.setServerYears(years);
			uvm.setServerMonth(month);
			ObjResult<UserObjectVM> rs = new ObjResult<UserObjectVM>(true,
					null, uvm == null ? 0 : uvm.getId(), uvm);

			String s = rs.toJson();

			return s;

		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Data\":null}";
		}
	}

	/**
	 * 导出数据到excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "exportDataToExcle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String exportDataToExcle(
			@RequestParam(value = "police_Query", required = false) String query,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", 1);
			map.put("pageSize", 65530);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("sort", "p.id");
			map.put("order", "asc");
			boolean isSuccess = false;
			// 取服务器地址，默认指向bin目录
			String realPath = getClass().getResource("/").getFile().toString();
			// 将地址截取，指向resouse目录
			realPath = realPath.substring(0, (realPath.length() - 16));
			realPath += "resource";
			String exlPath = "/resource";
			File sF = new File(realPath);
			if (!sF.exists()) {
				sF.mkdir();
			}
			realPath += "/" + "excels";
			exlPath += "/" + "excels";
			File iconF = new File(realPath);
			if (!iconF.exists()) {
				iconF.mkdir();
			}
			String s = UUID.randomUUID().toString();
			s = s.replace("-", "");
			realPath += "/" + s + ".xls";
			exlPath += "/" + s + ".xls";
			List<PoliceVM> list = new ArrayList<PoliceVM>();
			list = policeService.loadVMList(map);
			if (list.size() > 0) {
				isSuccess = initExcelData(list, realPath);
				if (isSuccess) {
					return "{\"isSuccess\":true,\"Message\":\"" + exlPath
							+ "\",\"Data\":\"" + exlPath + "\"}";
				} else {
					return "{\"isSuccess\":false,\"Message\":\"创建Excel表格失败\",\"Data\":\"\"}";
				}
			} else {
				return "{\"isSuccess\":false,\"Message\":\"获取报备详细信息子失败\",\"Data\":\"\"}";
			}
		} catch (Exception e) {
			return "{\"isSuccess\":false,\"Message\":\"" + e.getMessage()
					+ "\",\"Data\":\"\"}";
		}
	}

	private boolean initExcelData(List<PoliceVM> list, String realPath) {
		File file = new File(realPath);
		if (!file.exists()) {
			file.delete();
		}
		boolean isCreateSuccess = false;
		Workbook workbook = null;
		try {
			// XSSFWork used for .xslx (>= 2007), HSSWorkbook for 03 .xsl
			workbook = new XSSFWorkbook();// HSSFWorkbook();//WorkbookFactory.create(inputStream);
			if (workbook != null) {
				Sheet sheet = workbook.createSheet("警员基础数据");
				Row row0 = sheet.createRow(0);
				String title = "警员基础数据汇总信息";
				if (list.size() > 0) {
					title = list.get(0).getOrgName() + title;
				}
				Cell cell_0 = row0.createCell(0, Cell.CELL_TYPE_STRING);
				CellStyle style = ExcelPortUtil.getStyle(workbook);
				cell_0.setCellStyle(style);

				cell_0.setCellValue(title);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 9, 0));

				Row row1 = sheet.createRow(1);
				Cell cell_1 = row1.createCell(0, Cell.CELL_TYPE_STRING);
				cell_1.setCellStyle(style);
				cell_1.setCellValue("职务");
				sheet.autoSizeColumn(0);

				Cell cell_2 = row1.createCell(1, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("姓名");
				sheet.autoSizeColumn(1);

				Cell cell_3 = row1.createCell(2, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("警号");
				sheet.autoSizeColumn(2);

				Cell cell_4 = row1.createCell(3, Cell.CELL_TYPE_STRING);
				cell_4.setCellStyle(style);
				cell_4.setCellValue("GPS名称");
				sheet.autoSizeColumn(3);

				Cell cell_5 = row1.createCell(4, Cell.CELL_TYPE_STRING);
				cell_5.setCellStyle(style);
				cell_5.setCellValue("手机号");
				sheet.autoSizeColumn(4);

				Cell cell_6 = row1.createCell(5, Cell.CELL_TYPE_STRING);
				cell_6.setCellStyle(style);
				cell_6.setCellValue("公安短号");
				sheet.autoSizeColumn(5);

				Cell cell_7 = row1.createCell(6, Cell.CELL_TYPE_STRING);
				cell_7.setCellStyle(style);
				cell_7.setCellValue("身份证号码");
				sheet.autoSizeColumn(6);

				Cell cell_8 = row1.createCell(7, Cell.CELL_TYPE_STRING);
				cell_8.setCellStyle(style);
				cell_8.setCellValue("警员类别");
				sheet.autoSizeColumn(7);

				Cell cell_9 = row1.createCell(8, Cell.CELL_TYPE_STRING);
				cell_9.setCellStyle(style);
				cell_9.setCellValue("组呼号");
				sheet.autoSizeColumn(8);

				Cell cell_t = row1.createCell(9, Cell.CELL_TYPE_STRING);
				cell_t.setCellStyle(style);
				cell_t.setCellValue("个呼号");
				sheet.autoSizeColumn(9);

				for (int rowNum = 2; rowNum <= list.size(); rowNum++) {
					Row row = sheet.createRow(rowNum);
					PoliceVM police = new PoliceVM();
					police = list.get(rowNum - 2);
					Cell cella = row.createCell(0, Cell.CELL_TYPE_STRING);
					cella.setCellValue(police.getTitle() == null ? "" : police
							.getTitle());
					Cell cellb = row.createCell(1, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(police.getName() == null ? "" : police
							.getName());
					Cell cellc = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellc.setCellValue(police.getNumber() == null ? "" : police
							.getNumber());
					Cell celle = row.createCell(3, Cell.CELL_TYPE_STRING);
					celle.setCellValue(police.getGpsName() == null ? ""
							: police.getGpsName());
					Cell celld = row.createCell(4, Cell.CELL_TYPE_STRING);
					celld.setCellValue(police.getMobile() == null ? "" : police
							.getMobile());
					Cell cellf = row.createCell(5, Cell.CELL_TYPE_STRING);
					cellf.setCellValue(police.getMobileShort() == null ? ""
							: police.getMobileShort());

					Cell cellg = row.createCell(6, Cell.CELL_TYPE_STRING);
					cellg.setCellValue(police.getIdcardno() == null ? ""
							: police.getIdcardno());
					Cell cellh = row.createCell(7, Cell.CELL_TYPE_STRING);
					cellh.setCellValue(police.getTypeName() == null ? ""
							: police.getTypeName());
					Cell celli = row.createCell(8, Cell.CELL_TYPE_STRING);
					celli.setCellValue(police.getIntercomGroup() == null ? ""
							: police.getIntercomGroup());
					Cell cellj = row.createCell(9, Cell.CELL_TYPE_STRING);
					cellj.setCellValue(police.getIntercomPerson() == null ? ""
							: police.getIntercomPerson());
				}
			}
			try {
				FileOutputStream outputStream = new FileOutputStream(realPath);
				workbook.write(outputStream);
				outputStream.flush();
				outputStream.close();
				isCreateSuccess = true;
			} catch (Exception e) {
				isCreateSuccess = false;
			}
			return isCreateSuccess;
		} catch (Exception ex) {
			return isCreateSuccess;
		}
	}
}
