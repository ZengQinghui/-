package com.briup.lb.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.briup.lb.bean.Message;
import com.briup.lb.bean.User;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.exception.PermissionException;
import com.briup.lb.service.MessageService;
import com.briup.lb.service.RoleService;
import com.briup.lb.service.UserService;

@Controller
@SessionAttributes(value = { "user" })
public class BaseController extends DateController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MessageService messageService;

	// 跳转到登录页面
	@RequestMapping("/toLogin.action")
	public String login(HttpServletRequest request,User user) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null && user !=null){
			for (Cookie c : cookies) {
				if (c.getName().equals("username") && c.getValue().equals(user.getLoginName())) {
					return "/home/home.jsp";
				}
			}
		}
		return "/login.jsp";
	}

	@RequestMapping(value = "/doLogin.action")
	public String doLogin(User user, Model model,HttpServletResponse response) {
		User u = null;
		try {
			u = userService.login(user.getLoginName(), user.getPassword());
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "/login.jsp";
		}
		userService.changeState(u.getId(), 1);
		userService.saveLoginLog(u.getLoginName());
		
		Cookie c = new Cookie("username", user.getLoginName());
		c.setMaxAge(60);
		response.addCookie(c);
		
		model.addAttribute("user", u);
		return "/home/home.jsp";
	}

	@RequestMapping(value = "/home.action")
	public String home(String message, Model model) {
		model.addAttribute("message", message);
		return "/home/home.jsp";
	}

	@RequestMapping(value = "/title.action")
	public String title() {
		return "/home/title.jsp";
	}

	@RequestMapping(value = "/left.action")
	public String left() {
		return "/home/left.jsp";
	}

	@RequestMapping(value = "/main.action")
	public String main(User user,String message, Model model) {
		model.addAttribute("message", message);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 0);
		map.put("userId", user.getId());
		
		List<Message> messageList = messageService.find(map);
		model.addAttribute("messageList", messageList);
		
		return "/home/olmsgList.jsp"; // 首页转向留言板
	}

	// 系统管理模块
	@RequestMapping("/sysadminMain.action")
	public String sysadminMain(User user, Model model) {

		try {
			roleService.checkPermission(user.getId());
		} catch (PermissionException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/main.action";
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/main.action";
		}

		return "/sysadmin/main.jsp";
	}

	@RequestMapping("/sysadminLeft.action")
	public String sysadminLeft(User user, Model model) {
		try {
			roleService.checkPermission(user.getId());
		} catch (PermissionException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/left.action";
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/left.action";
		}
		return "/sysadmin/left.jsp";
	}

	// 基础信息模块
	@RequestMapping("/baseinfoMain.action")
	public String baseinfoMain() {
		return "/baseinfo/main.jsp";
	}

	@RequestMapping("/baseinfoLeft.action")
	public String baseinfoLeft() {
		return "/baseinfo/left.jsp";
	}

	@RequestMapping("/basicinfo/sysCodeList.action")
	public String sysCodeList() {
		return "/basicinfo/sysCode.jsp";
	}

	// 统计分析
	@RequestMapping("/statMain.action")
	public String statMain() {
		return "/stat/main.jsp";
	}

	@RequestMapping("/statLeft.action")
	public String statLeft() {
		return "/stat/left.jsp";
	}

	// 货运管理模块
	@RequestMapping("/freightMain.action")
	public String cargoMain(User user, Model model) {
		try {
			roleService.checkPermission(user.getId());
		} catch (PermissionException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/main.action";
		}
		return "/freight/main.jsp";
	}

	@RequestMapping("/freightLeft.action")
	public String cargoLeft(User user, Model model) {
		try {
			roleService.checkPermission(user.getId());
		} catch (PermissionException e) {
			e.printStackTrace();
		} catch (CommonException e) {
			model.addAttribute("message", e.getMessage());
			return "redirect:/left.action";
		}
		return "/freight/left.jsp";
	}

	@RequestMapping("/logout.action")
	public String logout(User user,Model model) {

		Serializable[] ids = { user.getId() };

		userService.cancel(ids);
		
		user = new User();
		model.addAttribute("user", user);

		return "/login.jsp";
	}
	
	@RequestMapping("/toUpdate.action")
	public String toUpdate(User user,Model model){
		model.addAttribute("user", user);
		return "/home/userUpdate.jsp";
	}
	
	@RequestMapping("/doUpdate.action")
	public String doUpdate(User user,Model model){
		User u1 = userService.get(user.getId());
		if(u1.getPassword().equals(user.getPassword())){
			userService.updateUser(user);
			User u2 = userService.get(user.getId());
			model.addAttribute("user", u2);
			return "redirect:/main.action";
		}
		userService.updateUser(user);
		model.addAttribute("message", "密码已经更改，请记住自己的密码！");
		return "redirect:/main.action";
		
	}

}
