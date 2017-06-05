package com.briup.lb.web.controller.sysadmin.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Role;
import com.briup.lb.bean.User;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.service.RoleService;
import com.briup.lb.service.UserService;

@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;

	@RequestMapping("/sysadmin/role/list.action")
	private String list(Model model, Page<Role> page) {

		List<Role> roleList = roleService.findPage(page);
		model.addAttribute("roleList", roleList);
		model.addAttribute("pageLinks", page.pageLinks("list.action"));

		return "/sysadmin/role/roleList.jsp";
	}
	
	@RequestMapping("/sysadmin/role/toSave.action")
	public String toSave(Model model) {
		List<User> userList = userService.find(null);
		model.addAttribute("userList", userList);
		
		return "/sysadmin/role/roleSave.jsp";
	}
	
	@RequestMapping("/sysadmin/role/doInsert.action")
	public String doInsert(Role role,@RequestParam("userId") String[] ids) {
		roleService.insert(role,ids);
		
		return "redirect:/sysadmin/role/list.action";
	}
	
	@RequestMapping("/sysadmin/role/toUpdate.action")
	public String toUpdate(String id, Model model) {
		Role role = roleService.get(id);
		model.addAttribute("role", role);
		model.addAttribute("id", id);
		
		List<User> userList = userService.find(null);
		model.addAttribute("userList", userList);
		

		return "/sysadmin/role/roleUpdate.jsp";
	}

	@RequestMapping("/sysadmin/role/doUpdate.action")
	public String doUpdate(Role role,@RequestParam("userId") String[] ids) {
		roleService.update(role,ids);

		return "redirect:/sysadmin/role/list.action";
	}
	
	@RequestMapping("/sysadmin/role/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		roleService.delete(ids);
		
		return "redirect:/sysadmin/role/list.action";
	}
	
	@RequestMapping("/sysadmin/role/toView.action")
	public String toView(String id, Model model) {

		Role role = roleService.get(id);
		model.addAttribute("role", role);
		
		List<User> userList = userService.getUserList(id);
		model.addAttribute("userList", userList);

		return "/sysadmin/role/roleView.jsp";
	}

}
