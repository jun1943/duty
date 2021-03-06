package com.tianyi.drs.basedata.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.service.IconsService;
import com.tianyi.drs.duty.viewmodel.ListResult;

/*'
 *图标管理逻辑控制器
 *
 */

@Scope("prototype")
@Controller
@RequestMapping("/icons")
public class IconsController extends CommonsMultipartResolver {

	@Resource(name = "iconsService")
	protected IconsService iconsService;

	public IconsController() {
		super();
	}

	/*
	 * 获取图标列表信息
	 * 
	 * param：query查询条件包
	 * 
	 * page：当前页 rows：每页条数
	 */
	@RequestMapping(value = "getIconsList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getIconsList(
			@RequestParam(value = "icons_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			String name = joQuery.getString("name");

			int typeid = Integer.parseInt(joQuery.getString("typeid"));

			List<Icons> list = new ArrayList<Icons>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("name", name);
			map.put("typeid", typeid);

			int total = iconsService.loadCount(map);
			list = iconsService.loadList(map);

			ListResult<Icons> rs = new ListResult<Icons>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	/*
	 * 获取图标列表信息
	 * 
	 * param：query查询条件包
	 * 
	 * page：当前页 rows：每页条数
	 */
	@RequestMapping(value = "saveIconBaseInfo.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveIconBaseInfo(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "name", required = false) String name,
			HttpServletRequest request) throws Exception {
		try {

			if (id > 0) {
				Icons icons = new Icons();
				icons = iconsService.loadById(id);
				if(icons!=null){
					icons.setTypeId(typeId);
					icons.setName(name);
					iconsService.updateByPrimaryKey(icons);
					return "{\"isSuccess\":true,\"Message\":\"修改图标信息成功!\"}";
				}else{
					return "{\"isSuccess\":false,\"Message\":\"修改图标信息失败，获取对象信息失败!\"}";
				}
			} else {
				return "{\"isSuccess\":true,\"Message\":\"修改图标信息失败，传入后台主键值为空!\"}";
			}
			
			 
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"修改图标信息失败!\"}";
		}
	}

	/*
	 * 删除图标信息
	 * 
	 * id：删除对象的id
	 */
	@RequestMapping(value = "deleteIcons.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteIcons(String id) throws Exception {
		try {
			int result = 0;
			String realPath = getClass().getResource("/").getFile().toString();
			realPath = realPath.substring(0, (realPath.length() - 17));
			Map<String, Object> map = new HashMap<String, Object>();

			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
					Icons icon = new Icons();
					icon = iconsService.loadById(ids[i]);
					if (icon != null) {
						String iconUrl = realPath + icon.getIconUrl();
						File pc = new File(iconUrl);

						if (pc.exists()) {
							pc.delete();
						}
					}
				}
				map.put("ids", ids);

				iconsService.deleteByIds(map);
			}
			// if (id > 0) {
			// Icons icon = new Icons();
			// icon = iconsService.loadById(id);
			// if (icon != null) {
			// String iconUrl = realPath + icon.getIconUrl();
			// File pc = new File(iconUrl);
			//
			// if (pc.exists()) {
			// pc.delete();
			// }
			// }
			// result = iconsService.deleteByPrimaryKey(id);
			// }
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	/*
	 * 上传控件后台接受地址 file：上传控件的输入流数据
	 */
	@RequestMapping(value = "uploadIcon.do", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String uploadIcon(
			@RequestParam("file") CommonsMultipartFile cmFile, // 请求参数一定要与form中的参数名对应
			Icons icons, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String realPath = getClass().getResource("/").getFile().toString();
			String result = "";
			realPath = realPath.substring(0, (realPath.length() - 16));
			realPath = realPath + "resource";
			String picPath = "/resource";
			File sF = new File(realPath);
			if (!sF.exists()) {
				sF.mkdir();
			}
			realPath = realPath + "/" + "icons";
			picPath += "/" + "icons";
			File iconF = new File(realPath);
			if (!iconF.exists()) {
				iconF.mkdir();
			}

			int iconId = 0;
			if (icons != null) {

				iconId = icons.getId();
				if (icons.getTypeId() == 1) {
					realPath = realPath + "/police";
					picPath += "/police";
				} else if (icons.getTypeId() == 2) {
					realPath = realPath + "/vehicle";
					picPath += "/vehicle";
				} else if (icons.getTypeId() == 3) {
					realPath = realPath + "/weapon";
					picPath += "/weapon";
				} else if (icons.getTypeId() == 4) {
					realPath = realPath + "/gpsdevice";
					picPath += "/gpsdevice";
				} else {
					realPath = realPath + "others";
					picPath += "/others";
				}

				File rF = new File(realPath);
				if (!rF.exists()) {
					rF.mkdir();
				}
				realPath = realPath + "/";
				picPath += "/";
			}

			if (!cmFile.isEmpty()) {
				// int size = (int) cmFile.getFileItem().getSize();
				String name = cmFile.getFileItem().getName();
				Icons iconObj = new Icons();
				iconObj.setId(icons.getId());
				iconObj.setTypeId(icons.getTypeId());
				if (equals(icons.getName() == "") || icons.getName() == null) {
					iconObj.setName(name);
					result = name;
				} else {
					iconObj.setName(icons.getName());
					result = icons.getName();
				}
				iconObj.setSyncState(true);
				iconObj.setPlatformId(1);

				if (iconId == 0) {
					iconsService.insert(iconObj);
					iconId = iconObj.getId();
					String dirUrl = realPath + iconId;
					picPath += iconId + "/";
					File filedir = new File(dirUrl);

					if (!filedir.exists()) {
						filedir.mkdir();
					}
					dirUrl = dirUrl + "/";
					String iconUrl = dirUrl + name;
					picPath += name;
					result = result + ";" + iconId + ";" + picPath;
					iconObj.setIconUrl(picPath);
					iconObj.setId(iconId);
					iconsService.updateByPrimaryKey(iconObj);

					InputStream ops = cmFile.getFileItem().getInputStream();
					BufferedImage iconPic = ImageIO.read(ops);
					// String
					// rootPath=getClass().getResource("/").getFile().toString();

					File pc = new File(iconUrl);

					if (pc.exists()) {
						pc.delete();
					}

					ImageIO.write(iconPic, "png", pc);// 不管输出什么格式图片，此处不需改动
				} else {
					String dirUrl = realPath + iconId;
					picPath += iconId + "/";
					File filedir = new File(dirUrl);

					if (!filedir.exists()) {
						filedir.mkdir();
					}
					dirUrl = dirUrl + "/";
					String iconUrl = dirUrl + name;

					picPath += name;
					result = result + ";" + iconId + ";" + picPath;
					iconObj.setIconUrl(picPath);
					iconObj.setId(iconId);
					iconsService.updateByPrimaryKey(iconObj);

					InputStream ops = cmFile.getFileItem().getInputStream();
					BufferedImage iconPic = ImageIO.read(ops);
					// String
					// rootPath=getClass().getResource("/").getFile().toString();

					File pc = new File(iconUrl);

					if (pc.exists()) {
						pc.delete();
					}

					ImageIO.write(iconPic, "png", pc);// 不管输出什么格式图片，此处不需改动
				}
			}
			// ObjResult<IconsVM> rs = new ObjResult<DutyTypeVM>(true, null,
			// dtvm.getId(), null);

			return result;

		} catch (Exception ex) {
			return "File Upload Failed;0;";
		}

	}
}
