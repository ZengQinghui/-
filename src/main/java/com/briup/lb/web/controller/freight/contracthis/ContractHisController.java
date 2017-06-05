package com.briup.lb.web.controller.freight.contracthis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.common.print.ContractPrint;
import com.briup.lb.service.ContractHisService;
import com.briup.lb.web.controller.DateController;

@Controller
public class ContractHisController extends DateController {
	@Autowired
	private ContractHisService contractHisService;
	@Autowired
	private ContractPrint contractPrint;

	// 历史列表
	@RequestMapping("/freight/contracthis/list.action")
	public String list(Model model) {
		List<Contract> contractHisList = contractHisService.find(null);
		model.addAttribute("contractHisList", contractHisList);

		return "/freight/contracthis/contractHisList.jsp";
	}

	// 归档
	@RequestMapping("/freight/contracthis/doPigeinhole.action")
	public String doPigeinhole(String[] id) {
		contractHisService.pigeinhole(id);

		return "redirect:/freight/contracthis/list.action";
	}

	// 取消归档
	@RequestMapping("/freight/contracthis/doPigeouthole.action")
	public String doPigeouthole(String[] id) {
		contractHisService.pigeouthole(id);

		return "redirect:/freight/contracthis/list.action";
	}

	@RequestMapping("/freight/contracthis/toView.action")
	public String toView(String id, Model model) {
		ContractVO contractVO = contractHisService.view(id);
		model.addAttribute("contract", contractVO);

		return "/freight/contracthis/contractHisView.jsp";
	}

	// 打印历史购销合同
	@RequestMapping("/freight/contracthis/doPrint.action")
	public void doPrint(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			ContractVO contract = contractHisService.view(id);
			contractPrint.print(contract, request.getServletContext().getRealPath("/"), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/freight/contracthis/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		contractHisService.delete(ids);

		return "redirect:/freight/contracthis/list.action";
	}

}
