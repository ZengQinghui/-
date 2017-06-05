package com.briup.lb.web.controller.home;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.briup.lb.bean.Message;
import com.briup.lb.bean.User;
import com.briup.lb.service.MessageService;
import com.briup.lb.service.UserService;
import com.briup.lb.web.controller.DateController;

@Controller
@SessionAttributes(value = { "user" })
public class MessageController extends DateController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;

	@RequestMapping("/home/toSave.action")
	public String toSave(Model model) {
		List<User> userList = userService.listUser();
		model.addAttribute("userList", userList);

		return "/home/messageSave.jsp";
	}

	@RequestMapping("/home/doInsert.action")
	public String doInsert(Message message, User user) {
		message.setMessageAuthor(user.getUserName());

		messageService.insert(message);

		return "redirect:/main.action";
	}

	@RequestMapping("/home/changeState.action")
	public String changeState(String id) {
		Serializable[] ids = {id};
		messageService.changeState(ids,1);
		return "redirect:/main.action";
	}

	@RequestMapping("/home/doDelete.action")
	public String doDelete(String id) {
		messageService.deleteById(id);
		return "redirect:/main.action";
	}
	
	@RequestMapping("/home/doDeleteHisMsg.action")
	public String doDeleteHisMsg(@RequestParam("id") String[] ids) {
		messageService.delete(ids);
		return "redirect:/home/toShowHisMsg.action";
	}
	
	@RequestMapping("/home/toShowHisMsg.action")
	public String toShowHisMsg(Model model,User user){
		List<Message> hisMsgList = messageService.getHisMsg(user.getId());
		model.addAttribute("hisMsgList", hisMsgList);
		
		return "/home/showHisMsg.jsp";
	}
	
	@RequestMapping("/home/toView.action")
	public String toView(Model model,String id){
		Message message = messageService.getById(id);
		model.addAttribute("message", message);
		
		return "/home/viewHisMsg.jsp";
	}
	
	@RequestMapping("/home/updateState.action")
	public String updateState(@RequestParam("id") String[] ids){
		messageService.changeState(ids,0);
		return "redirect:/home/toShowHisMsg.action";
	}

}
