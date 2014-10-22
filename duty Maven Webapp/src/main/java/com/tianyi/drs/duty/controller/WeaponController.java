package com.tianyi.drs.duty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
    
import com.tianyi.drs.duty.model.Weapon;
import com.tianyi.drs.duty.service.WeaponService;
import com.tianyi.drs.duty.viewmodel.ListResult; 
import com.tianyi.drs.duty.viewmodel.WeaponVM; 

@Scope("prototype")
@Controller
@RequestMapping("/weapon")
public class WeaponController {
		
	@Resource(name="weaponService")
	protected WeaponService weaponService;

	@RequestMapping(value = "getWeaponList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getWeaponList(
			@RequestParam(value = "weapon_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number");
			//int typeid = Integer.parseInt(joQuery.getString("typeid"));

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<WeaponVM> list = new ArrayList<WeaponVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number);
			//map.put("typeid", typeid);

			int total = weaponService.loadVMCount(map);
			list = weaponService.loadVMList(map);

			ListResult<WeaponVM> rs = new ListResult<WeaponVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@RequestMapping(value = "saveWeapon.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveWeapon(Weapon weapon) throws Exception {
		try {
			weapon.setPlatformId(1);
			weapon.setSyncState(true);
			int result = 0;
			if (weapon.getId() > 0) {
				int pid = weapon.getId();
				weapon.setId(pid);
				result = weaponService.updateByPrimaryKey(weapon);
			} else {
				result = weaponService.insert(weapon);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}
	

	@RequestMapping(value = "deleteWeapon.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteWeapon(int id) throws Exception {
		try {
			int result =0;
			if(id>0){
				result = weaponService.deleteByPrimaryKey(id);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}
}
