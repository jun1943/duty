package com.tianyi.drs.basedata.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.basedata.service.GpsService;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.ListResult;

/*
 * 定位设备控制器，mark by liqinag
 */
@Scope("prototype")
@Controller
@RequestMapping("/gpsdevice")
public class GpsController {
	@Resource(name = "gpsService")
	protected GpsService gpsService;

	/*
	 * 获取定位设备列表 参数： query：前台查询条件打包参数，包括组织机构信息，名称 page：当前页 rows：每页数据量
	 */
	@RequestMapping(value = "getGpsdeviceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsdeviceList(
			@RequestParam(value = "gpsdevice_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String gpsname = joQuery.getString("gpsname");

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("gpsname", gpsname);

			int total = gpsService.loadVMCount(map);
			list = gpsService.loadVMList(map);

			ListResult<GpsVM> rs = new ListResult<GpsVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 获取定位设备資源列表，用于报备页面显示； 参数： orgId、orgCode、orgPath ：前台查询条件打包参数，包括组织机构信息
	 * typeId：类型集 groupId：所属群组集
	 */
	@RequestMapping(value = "getGpsdeviceSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsdeviceSource(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "gpsname", required = false) String gpsname,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "groupId", required = false) String groupId,
			HttpServletRequest request) throws Exception {
		try {
			gpsname = gpsname.replace(",", "");
			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("orgId", orgId);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			map.put("gpsname", gpsname);
			// map.put("typeId", typeId);
			if (groupId != null && groupId != "") {
				String[] gs = {};
				gs = groupId.split(",");
				int[] gids = new int[gs.length];
				for (int i = 0; i < gs.length; i++) {
					gids[i] = Integer.parseInt(String.valueOf(gs[i]));
				}
				map.put("gids", gids);
			}
			if (typeId != null && typeId != "") {
				String[] s = {};
				s = typeId.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			}

			// int total = gpsService.loadVMCount(map);
			list = gpsService.loadVMListWithGroup(map);
			int total = list.size();
			ListResult<GpsVM> rs = new ListResult<GpsVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 保存定位设备列表 参数：gps:传入后台保存对象
	 */
	@RequestMapping(value = "saveGpsdevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveGpsdevice(Gps gps) throws Exception {
		try {
			gps.setPlatformId(1);
			gps.setSyncState(true);
			int result = 0;
			if (gps.getId() > 0) {
				int vid = gps.getId();
				gps.setId(vid);
				result = gpsService.updateByPrimaryKey(gps);
			} else {
				result = gpsService.insert(gps);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 删除定位设备 参数： id:前台选择的数据id集
	 */
	@RequestMapping(value = "deleteGpsdevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteGpsdevice(String id) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int result = 0;
			String Message = "";
			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				int m = 0;
				for (int i = 0; i < s.length; i++) {
					List<Gps> list = gpsService.findByIdAndDtyId(s[i]);
					if (list.size() == 0) {
						ids[i] = Integer.parseInt(String.valueOf(s[i]));
					} else {
						m++;
					}
					// ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				if (s.length > m) {
					Message = "部分数据删除成功！ 部分定位设备数据已关联报备数据，不能删除！";
				}else if(s.length==m){
					Message = "删除失败！ 选择资源数据已关联报备数据，不能删除";
				}
				map.put("ids", ids);

				gpsService.deleteByIds(map);
			}
			return "{\"success\":true,\"Message\":\"" + Message + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 获取定位设备类型 参数： id:前台选择的数据id集
	 */
	@RequestMapping(value = "getGpsType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsType() throws Exception {
		try {
			List<GpsType> list = gpsService.selectGpsType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/*
	 * 获取定位设备类型，列表形式 参数： id:前台选择的数据id集
	 */
	@RequestMapping(value = "getGpsTypelist.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsTypelist() throws Exception {
		try {
			List<GpsType> list = gpsService.selectGpsType();
			int total = list.size();
			ListResult<GpsType> rs = new ListResult<GpsType>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/**
	 * 判断是否有有车辆存在
	 * 
	 * 判断是否车牌号码重复；
	 */
	@RequestMapping(value = "isExistGpsDevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isExistGpsDevice(
			@RequestParam(value = "param", required = false) String param)
			throws Exception {
		try {

			if (!param.equals("")) {
				List<Gps> gps = gpsService.findByNumber(param);
				if (gps.size() > 0) {
					return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
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

	@RequestMapping(value = "exportDataToExcle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String exportDataToExcle(
			@RequestParam(value = "gpsdevice_Query", required = false) String query,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String gpsname = joQuery.getString("gpsname");

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", 0);
			map.put("pageSize", 65530);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("gpsname", gpsname);
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
			list = gpsService.loadVMList(map);
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

	private boolean initExcelData(List<GpsVM> list, String realPath) {
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
				Sheet sheet = workbook.createSheet("定位设备基础数据");
				Row row0 = sheet.createRow(0);
				String title = "定位设备基础数据汇总信息";
				if (list.size() > 0) {
					title = list.get(0).getOrgName() + title;
				}
				Cell cell_0 = row0.createCell(0, Cell.CELL_TYPE_STRING);
				CellStyle style = ExcelPortUtil.getStyle(workbook);
				cell_0.setCellStyle(style);

				cell_0.setCellValue(title);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 0));

				Row row1 = sheet.createRow(1);

				Cell cell_1 = row1.createCell(0, Cell.CELL_TYPE_STRING);
				cell_1.setCellStyle(style);
				cell_1.setCellValue("定位设备类型");
				sheet.autoSizeColumn(0);

				Cell cell_2 = row1.createCell(1, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("显示名称");
				sheet.autoSizeColumn(1);

				Cell cell_3 = row1.createCell(2, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("设备编号");
				sheet.autoSizeColumn(2);

				Cell cell_4 = row1.createCell(3, Cell.CELL_TYPE_STRING);
				cell_4.setCellStyle(style);
				cell_4.setCellValue("图片链接地址");
				sheet.autoSizeColumn(3);

				for (int rowNum = 2; rowNum <= list.size()+1; rowNum++) {
					Row row = sheet.createRow(rowNum);
					GpsVM gps = new GpsVM();
					gps = list.get(rowNum - 2);
					Cell cella = row.createCell(0, Cell.CELL_TYPE_STRING);
					cella.setCellValue(gps.getTypeName() == null ? "" : gps
							.getTypeName());
					Cell cellb = row.createCell(1, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(gps.getGpsName() == null ? "" : gps
							.getGpsName());
					Cell cellc = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellc.setCellValue(gps.getNumber() == null ? "" : gps
							.getNumber());
					Cell celle = row.createCell(3, Cell.CELL_TYPE_STRING);
					celle.setCellValue(gps.getIconUrl() == null ? "" : gps
							.getIconUrl());
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
