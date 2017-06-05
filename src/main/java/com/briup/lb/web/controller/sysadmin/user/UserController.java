package com.briup.lb.web.controller.sysadmin.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Dept;
import com.briup.lb.bean.Role;
import com.briup.lb.bean.User;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.service.DeptService;
import com.briup.lb.service.RoleService;
import com.briup.lb.service.UserService;
import com.briup.lb.web.controller.DateController;

@Controller
public class UserController extends DateController {
	@Autowired
	private UserService userService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/sysadmin/user/list.action")
	public String list(Model model, Page<User> page,String message) {
		List<User> userList = userService.findPage(page);
		model.addAttribute("userList", userList);
		model.addAttribute("pageLinks", page.pageLinks("list.action"));
		model.addAttribute("message", message);

		return "/sysadmin/user/userList.jsp";
	}

	@RequestMapping("/sysadmin/user/toSave.action")
	public String toSave(Model model) {
		List<Dept> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);

		List<Role> roleList = roleService.find(null);
		model.addAttribute("roleList", roleList);

		return "/sysadmin/user/userSave.jsp";
	}

	@RequestMapping("/sysadmin/user/doInsert.action")
	public String doInsert(User user, @RequestParam("roleId") String[] ids) {
		userService.insert(user, ids);

		return "redirect:/sysadmin/user/list.action";
	}

	@RequestMapping("/sysadmin/user/toUpdate.action")
	public String toUpdate(String id, Model model) {

		User user = userService.get(id);
		model.addAttribute("user", user);
		model.addAttribute("id", id);

		List<Dept> deptList = deptService.getDeptList();
		model.addAttribute("deptList", deptList);

		List<Role> roleList = roleService.find(null);
		model.addAttribute("roleList", roleList);

		return "/sysadmin/user/userUpdate.jsp";
	}

	@RequestMapping("/sysadmin/user/doUpdate.action")
	public String doUpdate(User user, @RequestParam("roleId") String[] ids) {

		userService.update(user, ids);

		return "redirect:/sysadmin/user/list.action";
	}

	@RequestMapping("/sysadmin/user/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {

		userService.delete(ids);

		return "redirect:/sysadmin/user/list.action";
	}

	@RequestMapping("/sysadmin/user/toView.action")
	public String toView(String id, Model model) {

		User user = userService.get(id);
		model.addAttribute("user", user);

		return "/sysadmin/user/userView.jsp";
	}

	@RequestMapping("/sysadmin/user/doDisable.action")
	public String doDisable(@RequestParam("id") String[] ids, Model model) {

		try {
			userService.disable(ids);
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/sysadmin/user/list.action";
		}

		return "redirect:/sysadmin/user/list.action";
	}

	@RequestMapping("/sysadmin/user/doCancel.action")
	public String doCancel(@RequestParam("id") String[] ids) {

		userService.cancel(ids);

		return "redirect:/sysadmin/user/list.action";
	}

}
