package com.briup.lb.web.controller.freight.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.briup.lb.bean.Enclosure;
import com.briup.lb.bean.Factory;
import com.briup.lb.bean.SysCode;
import com.briup.lb.service.EnclosureService;
import com.briup.lb.service.FactoryService;
import com.briup.lb.web.controller.DateController;

@Controller
public class EnclosureController extends DateController {

	@Autowired
	private EnclosureService enclosureService;
	@Autowired
	private FactoryService factoryService;

	@RequestMapping("/freight/contract/toEnclosureSave.action")
	public String toEnclosureSave(String contractProductId, Model model) {
		// 传递主表主键信息
		model.addAttribute("contractProductId", contractProductId);
		
		// 获取某个货物下的附件信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractProductId", contractProductId);
		List<Enclosure> allEnclosures = enclosureService.find(map);
		model.addAttribute("allEnclosures", allEnclosures);

		// 获取选择框中的厂家名称
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);

		// 获取分类信息
		List<SysCode> allTypes = enclosureService.getTypeList();
		model.addAttribute("allTypes", allTypes);

		return "/freight/contract/enclosureSave.jsp";
	}

	@RequestMapping("/freight/contract/doEnclosureSave.action")
	public String doEnclosureSave(Enclosure enclosure, @RequestParam("file") MultipartFile file, Model model) {
		enclosure.setProductImage(file.getOriginalFilename());
		enclosureService.insert(enclosure);
		// 传递主表主键信息
		model.addAttribute("contractProductId", enclosure.getContractProductId());

		return "redirect:/freight/contract/toEnclosureSave.action";
	}

	@RequestMapping("/freight/contract/toEnclosureUpdate.action")
	public String toEnclosureUpdate(String id, Model model) {
		Enclosure enclosure = enclosureService.get(id);
		model.addAttribute("enclosure", enclosure);

		// 获取选择框中的厂家名称
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);

		// 获取分类信息
		List<SysCode> allTypes = enclosureService.getTypeList();
		model.addAttribute("allTypes", allTypes);

		return "/freight/contract/enclosureUpdate.jsp";
	}

	@RequestMapping("/freight/contract/doEnclosureUpdate.action")
	public String doEnclosureUpdate(Enclosure enclosure, @RequestParam("file") MultipartFile file,Model model) {
		enclosure.setProductImage(file.getOriginalFilename());
		enclosureService.update(enclosure);
		
		// 传递主表的主键信息
		model.addAttribute("contractProductId", enclosure.getContractProductId());

		return "redirect:/freight/contract/toEnclosureSave.action";
	}
	
	@RequestMapping("/freight/contract/doEnclosureDelete.action")
	public String doEnclosureDelete(String id,String contractProductId,Model model){
		enclosureService.deleteById(id);
		
		model.addAttribute("contractProductId", contractProductId);  // 传递主表ID
		
		return "redirect:/freight/contract/toEnclosureSave.action";
	}

}
