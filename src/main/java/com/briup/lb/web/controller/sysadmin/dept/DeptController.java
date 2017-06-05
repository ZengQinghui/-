package com.briup.lb.web.controller.sysadmin.dept;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Dept;
import com.briup.lb.bean.vo.DeptVO;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.service.DeptService;

@Controller
public class DeptController {
	@Autowired
	private DeptService deptService;

	@RequestMapping("/sysadmin/dept/list.action")
	public String list(Model model, Page<Dept> page) {
		List<Dept> deptList = deptService.findPage(page);
		model.addAttribute("deptList", deptList);
		model.addAttribute("pageLinks", page.pageLinks("list.action"));

		return "/sysadmin/dept/deptList.jsp";
	}

	@RequestMapping("/sysadmin/dept/toSave.action")
	public String toSave() {
		return "/sysadmin/dept/deptSave.jsp";
	}

	@RequestMapping("/sysadmin/dept/doInsert.action")
	public String doInsert(Dept dept) {
		deptService.insert(dept);
		return "redirect:/sysadmin/dept/list.action";
	}

	@RequestMapping("/sysadmin/dept/toUpdate.action")
	public String toUpdate(String id, Model model) {
		Dept dept = deptService.get(id);
		model.addAttribute("dept", dept);
		model.addAttribute("id", id);

		return "/sysadmin/dept/deptUpdate.jsp";
	}

	@RequestMapping("/sysadmin/dept/doUpdate.action")
	public String doUpdate(Dept dept) {
		deptService.update(dept);

		return "redirect:/sysadmin/dept/list.action";
	}

	@RequestMapping("/sysadmin/dept/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		deptService.delete(ids);
		
		return "redirect:/sysadmin/dept/list.action";
	}
	
	@RequestMapping("/sysadmin/dept/doStart.action")
	public String doStart(@RequestParam("id") String[] ids){
		deptService.start(ids);
		return "redirect:/sysadmin/dept/list.action";
	}
	
	@RequestMapping("/sysadmin/dept/doStop.action")
	public String doStop(@RequestParam("id") String[] ids){
		deptService.stop(ids);
		return "redirect:/sysadmin/dept/list.action";
	}
	
	@RequestMapping("/sysadmin/dept/toView.action")
	public String toView(String id,Model model){
		DeptVO dept = deptService.view(id);
		model.addAttribute("dept", dept);
		
		return "/sysadmin/dept/deptView.jsp";
	}

}
