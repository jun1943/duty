package com.tianyi.drs.duty.controller;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.VehicleGroupService;

@Scope("prototype")
@Controller
@RequestMapping("/vehicleGroup")
public class VehicleGroupController {

	@Resource(name = "vehicleGroupService")
	protected VehicleGroupService vehicleGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;
	
}
