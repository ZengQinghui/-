package com.briup.lb.web.controller.freight.export;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.Goods;
import com.briup.lb.bean.Waybill;
import com.briup.lb.service.ContractService;
import com.briup.lb.service.GoodsService;
import com.briup.lb.service.WaybillService;
import com.briup.lb.web.controller.DateController;

@Controller
public class WaybillController extends DateController {
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ContractService contractService;

	@RequestMapping("/freight/export/list.action")
	public String list(Model model) {
		List<Waybill> waybillList = waybillService.find(null);
		model.addAttribute("waybillList", waybillList);

		return "/freight/export/waybillList.jsp";
	}

	// 购销合同查询列表
	@RequestMapping("/freight/export/contractList.action")
	public String contractList(Model model) {
		List<Contract> contractList = waybillService.getContracts();
		model.addAttribute("contractList", contractList);

		return "/freight/export/contractList.jsp"; // 报运目录下调用购销合同列表
	}

	// 报运新增，直接进行后台保存
	@RequestMapping("/freight/export/doInsert.action")
	public String doInsert(@RequestParam("id") String[] contractIds,Model model) { // 合同的id集合
		waybillService.insert(contractIds);
		contractService.changeState(contractIds);

		return "redirect:/freight/export/list.action";
	}

	@RequestMapping("/freight/export/toUpdate.action")
	public String toUpdate(String id, Model model) {
		Waybill waybill = waybillService.get(id);
		model.addAttribute("waybill", waybill);

		// 准备批量修改控件的数据mrecord
		model.addAttribute("mRecordData", waybillService.getMrecordData(id));

		return "/freight/export/waybillUpdate.jsp";
	}

	@RequestMapping("/freight/export/doUpdate.action")
	public String doUpdate(Waybill waybill, String[] mr_id, Integer[] mr_orderNo, Integer[] mr_cnumber,
			Double[] mr_grossWeight, Double[] mr_netWeight, Double[] mr_sizeLength, Double[] mr_sizeWidth,
			Double[] mr_sizeHeight, Double[] mr_exPrice, Double[] mr_tax) {
		waybillService.update(waybill, mr_id, mr_orderNo, mr_cnumber, mr_grossWeight, mr_netWeight, mr_sizeLength,
				mr_sizeWidth, mr_sizeHeight, mr_exPrice, mr_tax);

		return "redirect:/freight/export/list.action";
	}

	@RequestMapping("/freight/export/toView.action")
	public String toView(String id, Model model) {
		Waybill waybill = waybillService.get(id);
		model.addAttribute("waybill", waybill);

		// 获取报运下面的货物信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("waybillId", id);
		List<Goods> goods = goodsService.find(map);
		model.addAttribute("goods", goods);

		return "/freight/export/waybillView.jsp";
	}

	@RequestMapping("/freight/export/doDelete.action")
	public String doDelete(@RequestParam("id") String[] ids) {
		for(String id : ids){
			String[] contractIds = waybillService.get(id).getContractIds().split(",");
			contractService.submit(contractIds);
		}
		waybillService.delete(ids);
		return "redirect:/freight/export/list.action";
	}

	@RequestMapping("/freight/export/doSubmit.action")
	public String doSubmit(@RequestParam("id") String[] ids) {
		waybillService.submit(ids);

		return "redirect:/freight/export/list.action";
	}

	@RequestMapping("/freight/export/doCancel.action")
	public String doCancel(@RequestParam("id") String[] ids) {
		waybillService.cancel(ids);

		return "redirect:/freight/export/list.action";
	}

}
