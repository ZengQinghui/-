package com.briup.lb.web.controller.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.briup.lb.bean.FeedBack;
import com.briup.lb.bean.User;
import com.briup.lb.service.FeedBackService;
import com.briup.lb.web.controller.DateController;

@Controller
@SessionAttributes(value = { "user" })
public class FeedBackController extends DateController {
	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping("/home/list.action")
	public String list(User user,Model model){
		if(feedBackService.isAdmin(user.getId())){
			List<FeedBack> feedBackList = feedBackService.find(null);
			model.addAttribute("feedBackList", feedBackList);
			return "/home/feedBackList.jsp";
		}
		List<FeedBack> userFeedBackList = feedBackService.findByUserName(user.getUserName());
		model.addAttribute("userFeedBackList", userFeedBackList);
		return "/home/userFeedBackList.jsp";
	}
	
	@RequestMapping("/home/toSaveFB.action")
	public String toSaveFB(){
		return "/home/feedBackSave.jsp";
	}
	
	@RequestMapping("/home/doInsertFB.action")
	public String doInsertFB(User user,FeedBack feedBack){
		
		feedBack.setInputBy(user.getUserName());
		feedBack.setTelephone(user.getPhone());
		
		feedBackService.insert(feedBack);
		
		return "redirect:/home/list.action";
	}
	
	@RequestMapping("/home/toViewFB.action")
	public String toViewFB(User user,String feedBackId,Model model){
		if(feedBackService.isAdmin(user.getId())){
			FeedBack feedBack = feedBackService.get(feedBackId);
			model.addAttribute("feedBack", feedBack);
			
			return "/home/feedBackView.jsp";
		}
		FeedBack userFeedBack = feedBackService.get(feedBackId);
		model.addAttribute("userFeedBack", userFeedBack);
		
		return "/home/userFeedBackView.jsp";
	}
	
	@RequestMapping("/home/toReply.action")
	public String toReply(String feedBackId,Model model){
		model.addAttribute("feedBackId", feedBackId);
		
		return "/home/feedBackReply.jsp";
	}
	
	@RequestMapping("/home/doReply.action")
	public String doReply(FeedBack feedBack){
		feedBack.setState("1");
		feedBackService.update(feedBack);
		
		return "redirect:/home/list.action";
	}
	
	@RequestMapping("/home/doDeleteFB.action")
	public String doDeleteFB(@RequestParam("feedBackId") String[] ids){
		feedBackService.delete(ids);
		return "redirect:/home/list.action";
	}
	
}
