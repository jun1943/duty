package com.tianyi.drs.duty.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianyi.drs.duty.model.Police;
import com.tianyi.drs.duty.service.PoliceService;

@Scope("prototype")
@Controller
@RequestMapping("/police")
public class policecontroller {
	@Resource(name = "policeService")
	protected PoliceService policeService;// = ServiceFactory.getPoliceServiceInit();

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "savePolice.do", produces = "application/json;charset=UTF-8")
	public void savePolice() throws Exception {
		try {
			Police police = new Police();
			police.setName("王五");
			police.setGpsId(5);
			police.setGpsName("一号手机定位");
			police.setMobile("13568865179");
			police.setIntercomGroup("ssss");
			police.setIntercomPerson("22222");
			police.setIdcardno("512301198506234875");
			police.setOrgId(2);
			police.setMobileShort("6179");
			police.setNumber("51007816");
			police.setPoliceTypeId(3);
			police.setTitle("科长");
			System.out.println(policeService.insert(police));
		} catch (Exception ex) {
			System.out.println("insert failed");
		}
	}
}
