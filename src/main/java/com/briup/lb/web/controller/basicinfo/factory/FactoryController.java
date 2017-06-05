package com.briup.lb.web.controller.basicinfo.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Factory;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.service.FactoryService;
import com.briup.lb.web.controller.DateController;

@Controller
public class FactoryController extends DateController {
	@Autowired
	private FactoryService factoryService;

	// 显示所有生产厂家，分页
	@RequestMapping("/basicinfo/factory/list.action")
	public String list( Model model, Page<Factory> page) {
		List<Factory> allFactories = factoryService.findPage(page);
		model.addAttribute("allFactories", allFactories);
		model.addAttribute("pageLinks", page.pageLinks("list.action"));

		return "/basicinfo/factoryList.jsp";
	}

	// 跳转到保存页面
	@RequestMapping("/basicinfo/factory/toSave.action")
	public String toSaveFactory() {
		return "/basicinfo/factorySave.jsp";
	}

	// 执行新增操作
	@RequestMapping("/basicinfo/factory/doInsert.action")
	public String doInsert(Factory factory) {
		factoryService.insert(factory);

		return "redirect:/basicinfo/factory/list.action";
	}

	// 跳转到修改页面
	@RequestMapping("/basicinfo/factory/toUpdate.action")
	public String toUpdate(String id, Model model) {
		Factory factory = factoryService.get(id);
		model.addAttribute("factory", factory);

		return "/basicinfo/factoryUpdate.jsp";
	}

	// 执行修改操作
	@RequestMapping("/basicinfo/factory/doUpdate.action")
	public String doUpdate(Factory factory) {
		factoryService.update(factory);

		return "redirect:/basicinfo/factory/list.action";
	}

	// 批量删除
	@RequestMapping("/basicinfo/factory/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		factoryService.delete(ids);

		return "redirect:/basicinfo/factory/list.action";
	}

	// 查看厂家信息
	@RequestMapping("/basicinfo/factory/toView.action")
	public String toView(String id, Model model) {
		Factory factory = factoryService.get(id);
		model.addAttribute("factory", factory);

		return "/basicinfo/factoryView.jsp";
	}

	// 批量启用
	@RequestMapping("/basicinfo/factory/doStart.action")
	public String doStart(@RequestParam("id") String[] ids) {
		factoryService.start(ids);

		return "redirect:/basicinfo/factory/list.action";
	}

	// 批量停用
	@RequestMapping("/basicinfo/factory/doStop.action")
	public String doStop(@RequestParam("id") String[] ids) {
		factoryService.stop(ids);

		return "redirect:/basicinfo/factory/list.action";
	}

}
