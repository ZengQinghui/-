package com.briup.lb.web.controller.freight.contract;

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
import com.briup.lb.common.pagination.Page;
import com.briup.lb.common.print.ContractPrint;
import com.briup.lb.common.print.ContractPrintTemplate;
import com.briup.lb.service.ContractService;
import com.briup.lb.web.controller.DateController;

@Controller
public class ContractController extends DateController {
	@Autowired
	private ContractService contractService;
	@Autowired
	private ContractPrint contractPrint;
	@Autowired
	private ContractPrintTemplate contractPrintTemplate;

	/*// 显示所有合同
	@RequestMapping("/freight/contract/list.action")
	public String list(Model model) {
		List<Contract> allContracts = contractService.find(null);
		model.addAttribute("allContracts", allContracts);

		return "/freight/contract/contractList.jsp";
	}*/
	
	// 显示所有合同
		@RequestMapping("/freight/contract/list.action")
		public String list(Page<Contract> page,Model model) {
			List<Contract> allContracts = contractService.findPage(page);
			model.addAttribute("allContracts", allContracts);
			model.addAttribute("pageLinks", page.pageLinks("list.action"));

			return "/freight/contract/contractList.jsp";
		}

	@RequestMapping("/freight/contract/toSave.action")
	public String toSave() {
		return "/freight/contract/contractSave.jsp";
	}

	@RequestMapping("/freight/contract/doInsert.action")
	public String doInsert(Contract contract) {
		contractService.insert(contract);

		return "redirect:/freight/contract/list.action";
	}

	@RequestMapping("/freight/contract/toUpdate.action")
	public String toUpdate(String id, Model model) {
		Contract contract = contractService.get(id);
		model.addAttribute("contract", contract);

		return "/freight/contract/contractUpdate.jsp";
	}

	@RequestMapping("/freight/contract/doUpdate.action")
	public String doUpdate(Contract contract) {
		contractService.update(contract);

		return "redirect:/freight/contract/list.action";
	}

	@RequestMapping("/freight/contract/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		contractService.delete(ids);

		return "redirect:/freight/contract/list.action";
	}

	@RequestMapping("/freight/contract/doDeleteById.action")
	public String doDeleteById(String id) {
		contractService.deleteById(id);

		return "redirect:/freight/contract/list.action";
	}

	@RequestMapping("/freight/contract/toView.action")
	public String toView(String id, Model model) {
		ContractVO contractVO = contractService.view(id);
		model.addAttribute("contract", contractVO);

		return "/freight/contract/contractView.jsp";
	}

	@RequestMapping("/freight/contract/doSubmit.action")
	public String doSubmit(String[] id) {
		contractService.submit(id);

		return "redirect:/freight/contract/list.action";
	}

	@RequestMapping("/freight/contract/doCancel.action")
	public String doCancel(String[] id) {
		contractService.cancel(id);

		return "redirect:/freight/contract/list.action";
	}

	// 打印购销合同
	@RequestMapping("/freight/contract/doPrint.action")
	public void doPrint(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			ContractVO contract = contractService.view(id);
			contractPrint.print(contract, request.getServletContext().getRealPath("/"), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 模板打印购销合同
	@RequestMapping("/freight/contract/doPrintByTemplate.action")
	public void doPrintByTemplate(String id, HttpServletRequest request, HttpServletResponse response) {
		try {
			ContractVO contract = contractService.view(id);
			contractPrintTemplate.print(contract, request.getServletContext().getRealPath("/"), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
