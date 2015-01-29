package com.tianyi.drs.basedata.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.service.GpsService;
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.service.VehicleService;
import com.tianyi.drs.basedata.service.WeaponService;

/*
 * 定位设备控制器，mark by liqinag
 */
@Scope("prototype")
@Controller
@RequestMapping("/excelUpload")
public class ExcelUploadController {

	/**
	 * 分隔符
	 */
	private final static String SEPARATOR = "|";

	@Resource(name = "policeService")
	protected PoliceService policeService;// =
	@Resource(name = "weaponService")
	protected WeaponService weaponService;// =
	@Resource(name = "gpsService")
	protected GpsService gpsService;// =
	@Resource(name = "vehicleService")
	protected VehicleService vehicleService;// =

	@RequestMapping(value = "exportDataToDatabase.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String exportDataToDatabase(
			@RequestParam(value = "orgid", required = false) Integer orgId,
			@RequestParam(value = "fileName", required = false) String fileName,
			@RequestParam(value = "sourcetype", required = false) String sourcetype,
			HttpServletRequest request) throws Exception {
		try {

			String filepath = getClass().getResource("/").getFile().toString();
			// String result = "";
			filepath = filepath.substring(0, (filepath.length() - 16));
			filepath = filepath + "resource/tempfile/";
			filepath += fileName;

			File pc = new File(filepath);
			if (pc.exists()) {
				InputStream is = new FileInputStream(pc);
				HSSFWorkbook rwb = new HSSFWorkbook(is); // Workbook.getWorkbook(is);

				HSSFSheet sheet = rwb.getSheet(sourcetype);
				List<String> list = new ArrayList<String>();

				// 解析公式结果
				FormulaEvaluator evaluator = rwb.getCreationHelper()
						.createFormulaEvaluator();
				int minRowIx = sheet.getFirstRowNum();
				int maxRowIx = sheet.getLastRowNum();
				for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
					Row row = sheet.getRow(rowIx);
					StringBuilder sb = new StringBuilder();

					short minColIx = row.getFirstCellNum();
					short maxColIx = row.getLastCellNum();

					for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
						Cell cell = row.getCell(new Integer(colIx));
						CellValue cellValue = evaluator.evaluate(cell);
						if (cellValue == null) {
							continue;
						}
						// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
						// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
						switch (cellValue.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							// sb.append(SEPARATOR +
							// cellValue.getBooleanValue());
							sb.append(SEPARATOR + cellValue.getBooleanValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							// 这里的日期类型会被转换为数字类型，需要判别后区分处理
							if (DateUtil.isCellDateFormatted(cell)) {
								sb.append(SEPARATOR + cell.getDateCellValue());
							} else {
								sb.append(SEPARATOR
										+ cellValue.getNumberValue());
							}
							// sb.append(cellValue.getBooleanValue());
							break;
						case Cell.CELL_TYPE_STRING:
							// sb.append(SEPARATOR +
							// cellValue.getStringValue());
							sb.append(SEPARATOR + cellValue.getStringValue());
							break;
						case Cell.CELL_TYPE_FORMULA:
							sb.append(SEPARATOR + cellValue.getStringValue());
							break;
						case Cell.CELL_TYPE_BLANK:
							sb.append(SEPARATOR + cellValue.getStringValue());
							break;
						case Cell.CELL_TYPE_ERROR:
							sb.append(SEPARATOR + cellValue.getStringValue());
							break;
						default:
							sb.append(SEPARATOR + cellValue.getStringValue());
							break;
						}
					}
					list.add(sb.toString());
				}

				String result = "导入数据成功";
				String message = "";
				if (sourcetype.equals("PoliceInfo")) {
					message = InsertPoliceIntoDatabase(list, orgId);
				} else if (sourcetype.equals("CarInfo")) {
					message = InsertVehicleIntoDatabase(list, orgId);
				} else if (sourcetype.equals("WeaponInfo")) {
					message = InsertWeaponIntoDatabase(list, orgId);
				} else if (sourcetype.equals("GpsInfo")) {
					message = InsertGpsIntoDatabase(list, orgId);
				} else {
					message = "";
				}
				if (message.length() > 0) {
					result = message;
				}
				return "{\"isSuccess\":\"true\",\"Message\":\"" + result
						+ "\"}";
			} else {
				return "{\"isSuccess\":\"false\",\"Message\":\"加载服务器文件出错,请查看上传当是否是选定资源类型\"}";
			}
		} catch (Exception ex) {
			return "{\"isSuccess\":\"false\",\"Message\":\"加载服务器文件出错\"}";
		}

	}

	private String InsertGpsIntoDatabase(List<String> list, Integer orgId) {
		// TODO Auto-generated method stub
		String object = list.toString();
		object = object.substring(0, object.length() - 1);
		String[] datable = object.split(",");
		List<Gps> gpslist = new ArrayList<Gps>();
		if (datable.length > 0) {
			if (datable.length > 3) {
				for (int row = 2; row < datable.length; row++) {
					Gps pol = new Gps();
					String[] entity = datable[row].split("\\|");

					pol.setId(0);
					pol.setTypeId(getGpsTypeId(entity[1].trim()));
					pol.setNumber(entity[3]);
					pol.setGpsName(entity[2]);

					pol.setIconUrl(null);
					pol.setOrgId(orgId);
					pol.setPlatformId(1);
					pol.setSyncState(true);
					gpslist.add(pol);

				}
			}
		}
		String result = "";
		if (gpslist.size() > 0) {
			for (Gps p : gpslist) {
				List<Gps> lst = new ArrayList<Gps>();
				if (p.getNumber() != null) {
					lst = gpsService.findByNumber(p.getNumber());
					if (lst.size() > 0) {
						result += " 编号为：" + p.getNumber() + "的定位设备信息已经存在   ; ";
						continue;
					}
				}
				gpsService.insert(p);
			}
		}
		return result;
	}

	private String InsertWeaponIntoDatabase(List<String> list, Integer orgId) {
		// TODO Auto-generated method stub
		String object = list.toString();
		object = object.substring(0, object.length() - 1);
		String[] datable = object.split(",");
		List<Weapon> weaponlist = new ArrayList<Weapon>();
		if (datable.length > 0) {
			if (datable.length > 3) {
				for (int row = 2; row < datable.length; row++) {
					Weapon pol = new Weapon();
					String[] entity = datable[row].split("\\|");

					pol.setId(0);
					pol.setTypeId(getWeaponTypeId(entity[2].trim()));
					pol.setNumber(entity[1]);

					String standard = entity[3].trim().equals("0.0") ? null
							: entity[3].trim().contains(".0") == true ? entity[3]
									.trim().substring(0,
											entity[3].trim().length() - 2)
									: entity[3].trim();

					pol.setStandard(standard);

					pol.setOrgId(orgId);
					pol.setPlatformId(1);
					pol.setSyncState(true);
					weaponlist.add(pol);

				}
			}
		}
		String result = "";
		if (weaponlist.size() > 0) {
			for (Weapon p : weaponlist) {
				List<Weapon> lst = new ArrayList<Weapon>();
				if (p.getNumber() != null) {
					lst = weaponService.findByNumber(p.getNumber());
					if (lst.size() > 0) {
						result += " 编号为：" + p.getNumber() + "的武器信息已经存在   ; ";
						continue;
					}
				}
				weaponService.insert(p);
			}
		}
		return result;
	}

	private String InsertVehicleIntoDatabase(List<String> list, Integer orgId) {
		// TODO Auto-generated method stub
		String object = list.toString();
		object = object.substring(0, object.length() - 1);
		String[] datable = object.split(",");
		List<Vehicle> vehiclelist = new ArrayList<Vehicle>();
		if (datable.length > 0) {
			if (datable.length > 3) {
				for (int row = 2; row < datable.length; row++) {
					Vehicle pol = new Vehicle();
					String[] entity = datable[row].split("\\|");

					pol.setId(0);
					pol.setVehicleTypeId(getVehicleTypeId(entity[1].trim()));
					pol.setNumber(entity[2].trim());
					String purpose = entity[3].trim().equals("0.0") ? null
							: entity[3].trim();
					pol.setPurpose(purpose == null ? purpose : purpose
							.contains(".0") == false ? purpose : purpose
							.substring(0, purpose.length() - 2));
					String brand = entity[4].trim().equals("0.0") ? null
							: entity[4].trim();
					pol.setBrand(brand == null ? brand
							: brand.contains(".0") == false ? brand : brand
									.substring(0, brand.length() - 2));
					String siteQty = entity[5].trim().equals("0.0") ? null
							: entity[5].trim();
					pol.setSiteQty(siteQty == null ? siteQty : siteQty
							.contains(".0") == false ? siteQty : siteQty
							.substring(0, siteQty.length() - 2));
					String intercomGroup = entity[6].trim().equals("0.0") ? null
							: entity[6].trim();
					pol.setIntercomGroup(intercomGroup == null ? intercomGroup
							: intercomGroup.contains(".0") == false ? intercomGroup
									: intercomGroup.substring(0,
											intercomGroup.length() - 2));

					String intercomPerson = entity[7].trim().equals("0.0") ? null
							: entity[7].trim();
					pol.setIntercomPerson(intercomPerson == null ? intercomPerson
							: intercomPerson.contains(".0") == false ? intercomPerson
									: intercomPerson.substring(0,
											intercomPerson.length() - 2));

					pol.setOrgId(orgId);
					pol.setPlatformId(1);
					pol.setSyncState(true);
					vehiclelist.add(pol);
				}
			}
		}
		String result = "";
		if (vehiclelist.size() > 0) {
			for (Vehicle p : vehiclelist) {
				List<Vehicle> lst = new ArrayList<Vehicle>();
				if (p.getNumber() != null) {
					lst = vehicleService.findByNumber(p.getNumber());
					if (lst.size() > 0) {
						result += " 车牌号码为：" + p.getNumber() + "的车辆信息已经存在   ; ";
						continue;
					}
				}
				vehicleService.insert(p);
			}
		}
		return result;
	}

	private String InsertPoliceIntoDatabase(List<String> list, int orgId) {
		// TODO Auto-generated method stub
		String object = list.toString();
		object = object.substring(0, object.length() - 1);
		String[] datable = object.split(",");
		List<Police> policelist = new ArrayList<Police>();
		if (datable.length > 0) {
			if (datable.length > 3) {
				for (int row = 2; row < datable.length; row++) {
					Police pol = new Police();
					String[] entity = datable[row].split("\\|");
					pol.setId(0);
					pol.setName(entity[1].trim() == "" ? "" : entity[1].trim());
					pol.setTypeId(getPoliceTypeId(entity[2].trim() == "" ? "民警"
							: entity[2]));
					String number = entity[3].trim() == "0.0" ? "" : entity[3]
							.trim().contains(".0") == true ? entity[3].trim()
							.substring(0, entity[3].trim().length() - 2)
							: entity[3].trim();
					pol.setNumber(number.equals("0") ? null : number);
					String idno = entity[4].trim() == "0.0" ? "" : entity[4]
							.trim().contains(".0") == true ? entity[4].trim()
							.substring(0, entity[4].trim().length() - 2)
							: entity[4].trim();
					pol.setIdcardno(idno.equals("0") ? null : idno);
					pol.setTitle(entity[5].trim().equals("0.0") ? null
							: entity[5].trim());
					String mobile = entity[6].trim() == "0.0" ? "" : entity[6]
							.trim().contains(".0") == true ? entity[6].trim()
							.substring(0, entity[6].trim().length() - 2)
							: entity[6].trim();

					pol.setMobile(mobile.equals("0") ? null : mobile);
					String shortmobile = entity[7].trim() == "0.0" ? ""
							: entity[7].trim().contains(".0") == true ? entity[7]
									.trim().substring(0,
											entity[7].trim().length() - 2)
									: entity[7].trim();
					pol.setMobileShort(shortmobile.equals("0") ? null
							: shortmobile);

					String icomgroup = entity[8].trim() == "0.0" ? ""
							: entity[8].trim().equals("5") ? "4" : entity[8]
									.trim().contains(".0") == true ? entity[8]
									.trim().substring(0,
											entity[8].trim().length() - 2)
									: entity[8].trim();

					pol.setIntercomGroup(icomgroup.equals("0") ? null
							: icomgroup);
					String icomperson = entity[9].trim() == "0.0" ? ""
							: entity[9].trim().contains(".0") == true ? entity[9]
									.trim().substring(0,
											entity[9].trim().length() - 2)
									: entity[9].trim();

					pol.setIntercomPerson(icomperson.equals("0") ? null
							: icomperson);
					pol.setOrgId(orgId);
					pol.setIsused(true);
					pol.setPlatformId(1);
					pol.setSyncState(true);
					policelist.add(pol);
				}
			}
		}
		String result = "";
		if (policelist.size() > 0) {
			for (Police p : policelist) {
				List<Police> lst = new ArrayList<Police>();
				if (p.getNumber() != null) {
					lst = policeService.findBycode(p.getNumber());
					if (lst.size() > 0) {
						result += " 警号为：" + p.getNumber() + "的警员信息已经存在   ; ";
						continue;
					}
				}
				if (p.getIdcardno() != null) {
					lst = policeService.findByidCard(p.getIdcardno());
					if (lst.size() > 0) {
						result += " 身份证号码为：" + p.getIdcardno()
								+ "的警员信息已经存在   ; ";
						continue;
					}
				}
				if (p.getIntercomPerson() != null) {
					lst = policeService.findByintercomPerson(p
							.getIntercomPerson());
					if (lst.size() > 0) {
						result += " 个呼号为：" + p.getIntercomPerson()
								+ "的警员信息已经存在   ; ";
						continue;
					}
				}
				policeService.insert(p);
			}
		}
		return result;
	}

	private Integer getPoliceTypeId(String typename) {
		int tId = 1;
		if (typename.equals("民警") || typename.equals("1")) {
			tId = 1;
		} else if (typename.equals("交警") || typename.equals("2")) {
			tId = 2;
		} else if (typename.equals("特警") || typename.equals("3")) {
			tId = 3;
		}
		return tId;
	}

	private Integer getGpsTypeId(String typename) {
		int tId = 3;
		if (typename.equals("800M") || typename.equals("800m")) {
			tId = 1;
		} else if (typename.equals("350M") || typename.equals("350m")) {
			tId = 2;
		} else if (typename.equals("Qchat")) {
			tId = 3;
		} else if (typename.equals("其他") || typename.equals("其它")) {
			tId = 4;
		}
		return tId;
	}

	private Integer getWeaponTypeId(String typename) {
		int tId = 1;
		if (typename.equals("65式手枪") || typename.contains("65式")) {
			tId = 1;
		} else if (typename.equals("77式手枪") || typename.contains("77式")) {
			tId = 2;
		} else if (typename.equals("97式突击步枪") || typename.contains("97式")) {
			tId = 3;
		} else if (typename.equals("79式冲锋枪") || typename.contains("79式")) {
			tId = 4;
		}
		return tId;
	}

	private int getVehicleTypeId(String typename) {
		int tId = 1;
		if (typename.equals("汽车") || typename.contains("汽")) {
			tId = 1;
		} else if (typename.equals("警摩") || typename.contains("摩")) {
			tId = 2;
		} else if (typename.equals("自行车") || typename.contains("电")) {
			tId = 3;
		}
		return tId;
	}

	@RequestMapping(value = "excelUploadAction.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String ExcelUploadAction(@RequestParam("file") CommonsMultipartFile cmFile, // 请求参数一定要与form中的参数名对应
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			String filepath = getClass().getResource("/").getFile().toString();
			// String result = "";
			filepath = filepath.substring(0, (filepath.length() - 16));
			filepath = filepath + "resource/tempfile/";
			String realPath = "";// "resource/tempfile/";
			if (!cmFile.isEmpty()) {
				String name = cmFile.getFileItem().getName();
				InputStream ips = cmFile.getFileItem().getInputStream();

				filepath += name;
				realPath = name;
				File pc = new File(filepath);

				// if (pc.exists()) {
				// pc.delete();
				// }
				if (!pc.exists()) {// 如果文件不存在，则创建该文件
					pc.createNewFile();
				}

				// 文件缓存区
				byte[] b = new byte[1024];
				FileOutputStream fos = new FileOutputStream(pc);
				while (ips.read(b) != -1) {
					// 将缓存区中的内容写到pc文件中
					fos.write(b);
					fos.flush();
				}
				fos.close();
				ips.close();

				return "1;" + realPath;
			} else {
				return "0;上传excel文件到服务器出错";
			}

		} catch (Exception ex) {
			return "0;上传excel文件出错";
		}

	}

}
