package com.pwc.utilities.tron.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pwc.utilities.tron.model.entity.Patch;
import com.pwc.utilities.tron.services.AdminService;



@RestController
@RequestMapping("/patches")
public class PatchController {
	
	@Autowired  
	AdminService adminService;
		
	
	@RequestMapping(method = RequestMethod.POST)
	public Patch addPatch(@RequestBody Patch patch, HttpServletRequest request) {
		  Patch newPatch = adminService.addPatch(patch);
		  request.getSession().setAttribute("patch", newPatch);
		  return newPatch;
	}	

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Patch> getPatchs() {
		  return adminService.getPatches();
	}		
	
}
