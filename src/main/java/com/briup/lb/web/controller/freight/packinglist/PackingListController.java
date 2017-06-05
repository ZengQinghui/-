package com.briup.lb.web.controller.freight.packinglist;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.lb.bean.Goods;
import com.briup.lb.bean.PackingList;
import com.briup.lb.bean.Waybill;
import com.briup.lb.service.GoodsService;
import com.briup.lb.service.PackingListService;
import com.briup.lb.service.WaybillService;
import com.briup.lb.web.controller.DateController;

@Controller
public class PackingListController extends DateController {
	@Autowired
	private PackingListService packingListService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private GoodsService goodsService;

	@RequestMapping("/freight/packinglist/list.action")
	public String list(Model model) {
		List<PackingList> allPackingLists = packingListService.find(null);
		model.addAttribute("allPackingLists", allPackingLists);

		return "/freight/packinglist/packingListList.jsp";
	}

	@RequestMapping("/freight/packinglist/toSavePackingList.action")
	public String toSavePackingList(String[] id, Model model) { // 出口报运的id集合
		// 携带出口报运的id集合 //显示装箱和报运的关系
		model.addAttribute("divData", packingListService.getDivDataCreate(id));

		return "/freight/packinglist/packingListSave.jsp";
	}

	@RequestMapping("/freight/packinglist/doInsert.action")
	public String doInsert(PackingList packingList, String waybillIds) {
		packingListService.insert(packingList);

		String[] strings = waybillIds.split(",");
		for (String s : strings) {
			String waybillId = s.split("\\|")[0];
			Serializable[] ids = { waybillId };
			waybillService.packingList(ids);
		}

		return "redirect:/freight/packinglist/list.action";
	}

	@RequestMapping("/freight/packinglist/toUpdate.action")
	public String toUpdate(String id, Model model) {
		PackingList packingList = packingListService.get(id);
		model.addAttribute("packingList", packingList);

		String _s = packingListService.getDivDataUpdate(packingList.getWaybillIds().split("\\|"),
				packingList.getWaybillNos().split("\\|"));
		model.addAttribute("divData", _s);

		return "/freight/packinglist/packingListUpdate.jsp";
	}

	@RequestMapping("/freight/packinglist/doUpdate.action")
	public String doUpdate(PackingList packingList) {
		packingListService.update(packingList);

		return "redirect:/freight/packinglist/list.action";
	}

	@RequestMapping("/freight/packinglist/doSubmit.action")
	public String doSubmit(@RequestParam("id") String[] ids) {
		packingListService.submit(ids);

		return "redirect:/freight/packinglist/list.action";
	}

	@RequestMapping("/freight/packinglist/doCancel.action")
	public String doCancel(@RequestParam("id") String[] ids) {
		packingListService.cancel(ids);

		return "redirect:/freight/packinglist/list.action";
	}

	@RequestMapping("/freight/packinglist/doDelete.action")
	public String doDelete(@RequestParam("id") String[] packingListIds) {
		for(String packingListId : packingListIds){
			String[] ids = packingListService.get(packingListId).getWaybillIds().split("\\|");
			waybillService.submit(ids);
		}
		
		packingListService.delete(packingListIds);
		
		return "redirect:/freight/packinglist/list.action";
	}

	@RequestMapping("/freight/packinglist/toView.action")
	public String toView(String id, Model model) {
		PackingList packingList = packingListService.get(id);
		model.addAttribute("packingList", packingList);

		model.addAttribute("divData", packingListService.getDivDataView(packingList.getId(),
				packingList.getWaybillIds().split("\\|"), packingList.getWaybillNos().split("\\|")));

		return "/freight/packinglist/packingListView.jsp";
	}

	@RequestMapping("/freight/packinglist/toViewWaybill.action")
	public String toViewWaybill(String packingListId, String id, Model model) {
		Waybill waybill = waybillService.get(id);
		model.addAttribute("waybill", waybill);

		// 获取报运下面的货物信息
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("waybillId", id);
		List<Goods> goods = goodsService.find(map);
		model.addAttribute("goods", goods);

		model.addAttribute("packingListId", packingListId);

		return "/freight/packinglist/waybillView.jsp";
	}

}
