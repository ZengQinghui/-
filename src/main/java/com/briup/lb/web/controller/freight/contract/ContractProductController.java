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

import com.briup.lb.bean.ContractProduct;
import com.briup.lb.bean.Factory;
import com.briup.lb.service.ContractProductService;
import com.briup.lb.service.FactoryService;
import com.briup.lb.web.controller.DateController;

@Controller
public class ContractProductController extends DateController {
	@Autowired
	private ContractProductService contractProductService;

	@Autowired
	private FactoryService factoryService;

	// 转向新增页面
	@RequestMapping("/freight/contract/toProductSave.action")
	public String toProductSave(String contractId, Model model) {
		// 传递主表的主键信息
		model.addAttribute("contractId", contractId);
		
		// 获取某个合同下的货物信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contractId", contractId);
		List<ContractProduct> allContractProducts = contractProductService.find(map);
		model.addAttribute("allContractProducts", allContractProducts);

		// 获取下拉列表框中的厂家名称
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);

		return "/freight/contract/contractProductSave.jsp";
	}

	// 新增
	@RequestMapping("/freight/contract/doProductInsert.action")
	public String doProductInsert(ContractProduct contractProduct, @RequestParam("file") MultipartFile file,
			Model model) {
		contractProduct.setProductImage(file.getOriginalFilename());
		contractProductService.insert(contractProduct);
		model.addAttribute("contractId", contractProduct.getContractId());

		return "redirect:/freight/contract/toProductSave.action"; // 新增完转向新增页面
	}

	// 转向修改页面
	@RequestMapping("/freight/contract/toProductUpdate.action")
	public String toProductUpdate(String id, Model model) {
		ContractProduct contractProduct = contractProductService.get(id);
		List<Factory> factoryList = factoryService.getFactoryList();
		model.addAttribute("factoryList", factoryList);
		model.addAttribute("contractProduct", contractProduct);

		return "/freight/contract/contractProductUpdate.jsp";
	}

	// 修改
	@RequestMapping("/freight/contract/doProductUpdate.action")
	public String doProductUpdate(ContractProduct contractProduct, @RequestParam("file") MultipartFile file,Model model) {
		contractProduct.setProductImage(file.getOriginalFilename());
		contractProductService.update(contractProduct);
		
		model.addAttribute("contractId", contractProduct.getContractId());

		return "redirect:/freight/contract/toProductSave.action";
	}

	// 删除
	@RequestMapping("/freight/contract/doProductDelete.action")
	public String doProductDelete(String id,String contractId,Model model) {
		contractProductService.deleteById(id);
		
		model.addAttribute("contractId", contractId);

		return "redirect:/freight/contract/toProductSave.action";
	}

}
