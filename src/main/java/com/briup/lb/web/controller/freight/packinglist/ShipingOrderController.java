package com.briup.lb.web.controller.freight.packinglist;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Finance;
import com.briup.lb.bean.Invoice;
import com.briup.lb.bean.PackingList;
import com.briup.lb.bean.ShipingOrder;
import com.briup.lb.bean.Waybill;
import com.briup.lb.service.ContractService;
import com.briup.lb.service.FinanceService;
import com.briup.lb.service.InvoiceService;
import com.briup.lb.service.PackingListService;
import com.briup.lb.service.ShipingOrderService;
import com.briup.lb.service.WaybillService;
import com.briup.lb.web.controller.DateController;

@Controller
public class ShipingOrderController extends DateController {
	@Autowired
	private ShipingOrderService shipingOrderService;
	@Autowired
	private PackingListService packingListService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private FinanceService financeService;
	@Autowired
	private ContractService contractService;

	@RequestMapping("/freight/packinglist/listShipingOrder.action")
	public String listShipingOrder(Model model) {
		List<ShipingOrder> allShipingOrderLists = shipingOrderService.find(null);
		model.addAttribute("allShipingOrderLists", allShipingOrderLists);

		return "/freight/packinglist/shipingOrderList.jsp";
	}

	@RequestMapping("/freight/packinglist/toSaveShipingOrder.action")
	public String toSaveShipingOrder(String id, Model model) {

		PackingList packingList = packingListService.get(id);
		Waybill waybill = waybillService.get(packingList.getWaybillIds().split(",")[0]);

		model.addAttribute("packingList", packingList);
		model.addAttribute("waybill", waybill);
		model.addAttribute("packingListId", id);

		return "/freight/packinglist/shipingOrderSave.jsp";
	}

	@RequestMapping("/freight/packinglist/doEntrust.action")
	public String doEntrust(String packingListId, ShipingOrder shipingOrder) {
		shipingOrder.setId(packingListId);
		shipingOrderService.insert(shipingOrder);

		Serializable[] id = { packingListId };
		packingListService.enstrust(id);

		String[] strings = packingListService.get(packingListId).getWaybillIds().split(",");
		for (String s : strings) {
			String waybillId = s.split("\\|")[0];
			Serializable[] ids = { waybillId };
			waybillService.entrust(ids);
		}

		return "redirect:/freight/packinglist/listShipingOrder.action";
	}

	@RequestMapping("/freight/packinglist/toViewShipingOrder.action")
	public String toViewShipingOrder(String id, Model model) {
		ShipingOrder shipingOrder = shipingOrderService.get(id);
		model.addAttribute("shipingOrder", shipingOrder);

		return "/freight/packinglist/shipingOrderView.jsp";
	}

	@RequestMapping("/freight/packinglist/toUpdateShipingOrder.action")
	public String toUpdateShipingOrder(String id, Model model) {
		ShipingOrder shipingOrder = shipingOrderService.get(id);
		model.addAttribute("shipingOrder", shipingOrder);

		return "/freight/packinglist/shipingOrderUpdate.jsp";
	}

	@RequestMapping("/freight/packinglist/doUpdateShipingOrder.action")
	public String doUpdateShipingOrder(ShipingOrder shipingOrder) {
		shipingOrderService.update(shipingOrder);

		return "redirect:/freight/packinglist/listShipingOrder.action";
	}

	@RequestMapping("/freight/packinglist/doDeleteShipingOrder.action")
	public String doDeleteShipingOrder(@RequestParam("id") String[] shipingOrderIds) {

		packingListService.submit(shipingOrderIds);

		for (String id : shipingOrderIds) {
			String[] strings = packingListService.get(id).getWaybillIds().split(",");
			for (String s : strings) {
				String waybillId = s.split("\\|")[0];
				Serializable[] ids = { waybillId };
				waybillService.packingList(ids);
			}
		}

		shipingOrderService.delete(shipingOrderIds);

		return "redirect:/freight/packinglist/listShipingOrder.action";
	}

	@RequestMapping("/freight/packinglist/toInvoice.action")
	public String toInvoice(String id, Model model) {
		PackingList packingList = packingListService.get(id);
		model.addAttribute("packingList", packingList);

		String[] strings = packingListService.get(id).getWaybillIds().split(",");
		Waybill waybill = null;
		for (String s : strings) {
			String waybillId = s.split("\\|")[0];
			waybill = waybillService.get(waybillId);
		}
		model.addAttribute("waybill", waybill);

		model.addAttribute("id", id);

		return "/freight/packinglist/invoiceEdit.jsp";
	}

	@RequestMapping("/freight/packinglist/doIncoice.action")
	public String doIncoice(Invoice invoice) {

		Serializable[] ids = { invoice.getId() };

		invoiceService.insert(invoice);

		shipingOrderService.invoice(ids);

		packingListService.invoice(ids);

		String[] strings = packingListService.get(invoice.getId()).getWaybillIds().split(",");
		for (String s : strings) {
			String waybillId = s.split("\\|")[0];
			Serializable[] waybillIds = { waybillId };
			waybillService.invoice(waybillIds);
		}

		return "redirect:/freight/packinglist/listShipingOrder.action";
	}
	
	@RequestMapping("/freight/packinglist/toFinance.action")
	public String toFinance(String id, Model model){
		model.addAttribute("id", id);
		
		return "/freight/packinglist/financeEdit.jsp";
	}
	
	@RequestMapping("/freight/packinglist/doFinance.action")
	public String doFinance(Finance finance){
		
		Serializable[] ids = { finance.getId() };
		
		financeService.insert(finance);
		
		shipingOrderService.finance(ids);

		packingListService.finance(ids);

		String[] strings = packingListService.get(finance.getId()).getWaybillIds().split(",");
		for (String s : strings) {
			String waybillId = s.split("\\|")[0];
			Serializable[] waybillIds = { waybillId };
			waybillService.finance(waybillIds);
		}
		
		for(String s : strings){
			String[] split = s.split("\\|");
			for(String s1 : split){
				String[] contractIds = waybillService.get(s1).getContractIds().split(",");
				contractService.finance(contractIds);
			}
		}
		
		return "redirect:/freight/packinglist/listShipingOrder.action";
	}

}
