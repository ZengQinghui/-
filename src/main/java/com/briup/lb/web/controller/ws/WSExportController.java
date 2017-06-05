package com.briup.lb.web.controller.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.lb.bean.Waybill;
import com.briup.lb.service.WaybillService;

/**
 * 在系统中调用cxf提供WebService
 */
@Controller
public class WSExportController {
	@Autowired
	private WaybillService waybillService;
	
	@RequestMapping("/ws/export/toEdit.action")
	public String toEdit(Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", "0");
 		List<Waybill> waybillList = waybillService.find(map);
		model.addAttribute("waybillList", waybillList);
		
		return "/ws/export/ajaxExport.jsp";
	}
}
