package com.pwc.utilities.tron.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.utilities.tron.model.entity.Dashboard;
import com.pwc.utilities.tron.services.AdminService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

	private Logger logger = Logger.getLogger(DashboardController.class);
	
	@Autowired
	private AdminService adminService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Dashboard> getDashboard() {
		return adminService.getDashboard();
	}

}
