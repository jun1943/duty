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

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.service.VehicleService;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.ListResult;

/*
 * 车辆管理逻辑控制器；
 */
@Scope("prototype")
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@Resource(name = "vehicleService")
	protected VehicleService vehicleService;

	/*
	 * 获取车辆列表信息
	 * 
	 * vehicle_Query：查询条件包 sort：排序列 order：排序方式 page：当前页 rows：每页条数
	 */
	@RequestMapping(value = "getVehicleList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleList(
			@RequestParam(value = "vehicle_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number");

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<VehicleVM> list = new ArrayList<VehicleVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number);
			if (sort != null) {
				if (!sort.equals("")) {
					map.put("sort", "v." + sort);
				}
			} else {
				map.put("sort", "v.id");
			}
			if (order != null) {
				if (!order.equals("")) {
					map.put("order", order);
				}
			} else {
				map.put("order", "desc");
			}
			int total = vehicleService.loadVMCount(map);
			list = vehicleService.loadVMList(map);

			ListResult<VehicleVM> rs = new ListResult<VehicleVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 
	 * 保存车辆信息；
	 */
	@RequestMapping(value = "saveVehicle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveVehicle(Vehicle vehicle) throws Exception {
		try {
			vehicle.setPlatformId(1);
			vehicle.setSyncState(true);
			int result = 0;
			if (vehicle.getId() > 0) {
				int vid = vehicle.getId();
				vehicle.setId(vid);
				result = vehicleService.updateByPrimaryKey(vehicle);
			} else {
				result = vehicleService.insert(vehicle);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 
	 * 删除车辆，
	 * 
	 * id：传入选择车辆的id；
	 */
	@RequestMapping(value = "deleteVehicle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteVehicle(String id) throws Exception {
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
					List<Vehicle> list = vehicleService.findByIdAndDtyId(s[i]);
					if (list.size() == 0) {
						ids[i] = Integer.parseInt(String.valueOf(s[i]));
					}else{
						m++;
					}
					// ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				if (s.length > m) {
					Message = "部分数据删除成功！部分车辆数据已关联报备数据，不能删除！";
				}else if(s.length==m){
					Message = "删除失败！选择资源数据已关联报备数据，不能删除";
				}
				map.put("ids", ids);

				vehicleService.deleteByIds(map);
			}
			return "{\"success\":true,\"Message\":\"" + Message + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 
	 * 获取车辆类型列表，以下拉框的形式展现；
	 */
	@RequestMapping(value = "getVehicleType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleType() throws Exception {
		try {
			List<VehicleType> list = vehicleService.selectVehicleType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/*
	 * 
	 * 获取车辆类型列表数据，以数据列表的形式展现
	 * 
	 * 用于报备中车辆资源的过滤条件筛选；
	 */
	@RequestMapping(value = "getvehicleTypelist.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getvehicleTypelist() throws Exception {
		try {
			List<VehicleType> list = vehicleService.selectVehicleType();
			int total = list.size();
			ListResult<VehicleType> rs = new ListResult<VehicleType>(total,
					list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 
	 * 获取车辆对应的组呼号；
	 */
	@RequestMapping(value = "getintercomGroup.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getintercomGroup() throws Exception {
		try {
			List<IntercomGroup> list = vehicleService.selectIntercomGroup();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	/*
	 * 获取车辆资源类表
	 * 
	 * 用于报备页面资源元素的展示
	 */
	@RequestMapping(value = "getVehicleSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleSource(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "groupId", required = false) String groupId,
			HttpServletRequest request) throws Exception {
		try {

			number = number.replace(",", "");
			List<VehicleVM> list = new ArrayList<VehicleVM>();
			Map<String, Object> map = new HashMap<String, Object>();
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
			map.put("orgId", orgId);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			map.put("number", number);

			list = vehicleService.loadVMListWithGroup(map);
			int total = list.size();
			ListResult<VehicleVM> rs = new ListResult<VehicleVM>(total, list);

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
	@RequestMapping(value = "isExistVehicle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isExistVehicle(
			@RequestParam(value = "param", required = false) String param)
			throws Exception {
		try {

			if (!param.equals("")) {
				List<Vehicle> vehicle = vehicleService.findByNumber(param);
				if (vehicle.size() > 0) {
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
			@RequestParam(value = "vehicle_Query", required = false) String query,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number");

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<VehicleVM> list = new ArrayList<VehicleVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", 0);
			map.put("pageSize", 65530);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number);
			map.put("sort", "v.id");
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
			list = vehicleService.loadVMList(map);
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

	private boolean initExcelData(List<VehicleVM> list, String realPath) {
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
				Sheet sheet = workbook.createSheet("车辆基础数据");
				Row row0 = sheet.createRow(0);
				String title = "车辆基础数据汇总信息";
				if (list.size() > 0) {
					title = list.get(0).getOrgName() + title;
				}
				Cell cell_0 = row0.createCell(0, Cell.CELL_TYPE_STRING);
				CellStyle style = ExcelPortUtil.getStyle(workbook);
				cell_0.setCellStyle(style);

				cell_0.setCellValue(title);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 0));

				Row row1 = sheet.createRow(1);
				Cell cell_1 = row1.createCell(0, Cell.CELL_TYPE_STRING);
				cell_1.setCellStyle(style);
				cell_1.setCellValue("车辆类型");
				sheet.autoSizeColumn(0);

				Cell cell_2 = row1.createCell(1, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("车牌号码");
				sheet.autoSizeColumn(1);

				Cell cell_3 = row1.createCell(2, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("车辆用途");
				sheet.autoSizeColumn(2);

				Cell cell_4 = row1.createCell(3, Cell.CELL_TYPE_STRING);
				cell_4.setCellStyle(style);
				cell_4.setCellValue("车辆品牌");
				sheet.autoSizeColumn(3);

				Cell cell_5 = row1.createCell(4, Cell.CELL_TYPE_STRING);
				cell_5.setCellStyle(style);
				cell_5.setCellValue("座位数");
				sheet.autoSizeColumn(4);

				Cell cell_6 = row1.createCell(5, Cell.CELL_TYPE_STRING);
				cell_6.setCellStyle(style);
				cell_6.setCellValue("GPS设备ID");
				sheet.autoSizeColumn(5);

				Cell cell_7 = row1.createCell(6, Cell.CELL_TYPE_STRING);
				cell_7.setCellStyle(style);
				cell_7.setCellValue("GPS名称");
				sheet.autoSizeColumn(6);

				Cell cell_8 = row1.createCell(7, Cell.CELL_TYPE_STRING);
				cell_8.setCellStyle(style);
				cell_8.setCellValue("组呼号");
				sheet.autoSizeColumn(7);

				Cell cell_9 = row1.createCell(8, Cell.CELL_TYPE_STRING);
				cell_9.setCellStyle(style);
				cell_9.setCellValue("个呼号");
				sheet.autoSizeColumn(8);

				for (int rowNum = 2; rowNum <= list.size()+1; rowNum++) {
					Row row = sheet.createRow(rowNum);
					VehicleVM vehicle = new VehicleVM();
					vehicle = list.get(rowNum - 2);
					Cell cella = row.createCell(0, Cell.CELL_TYPE_STRING);
					cella.setCellValue(vehicle.getTypeName() == null ? ""
							: vehicle.getTypeName());
					Cell cellb = row.createCell(1, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(vehicle.getNumber() == null ? ""
							: vehicle.getNumber());
					Cell cellc = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellc.setCellValue(vehicle.getPurpose() == null ? ""
							: vehicle.getPurpose());
					Cell celle = row.createCell(3, Cell.CELL_TYPE_STRING);
					celle.setCellValue(vehicle.getBrand() == null ? ""
							: vehicle.getBrand());
					Cell celld = row.createCell(4, Cell.CELL_TYPE_STRING);
					celld.setCellValue(vehicle.getSiteQty() == null ? ""
							: vehicle.getSiteQty());
					Cell cellf = row.createCell(5, Cell.CELL_TYPE_STRING);
					cellf.setCellValue(vehicle.getGpsNumber() == null ? ""
							: vehicle.getGpsNumber());

					Cell cellg = row.createCell(6, Cell.CELL_TYPE_STRING);
					cellg.setCellValue(vehicle.getGpsName() == null ? ""
							: vehicle.getGpsName());
					Cell cellh = row.createCell(7, Cell.CELL_TYPE_STRING);
					cellh.setCellValue(vehicle.getIntercomGroup() == null ? ""
							: vehicle.getIntercomGroup());
					Cell celli = row.createCell(8, Cell.CELL_TYPE_STRING);
					celli.setCellValue(vehicle.getIntercomPerson() == null ? ""
							: vehicle.getIntercomPerson());

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
