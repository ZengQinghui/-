package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.PackingList;
import com.briup.lb.bean.Waybill;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.common.utils.UtilFuns;
import com.briup.lb.dao.PackingListDao;
import com.briup.lb.dao.WaybillDao;
import com.briup.lb.service.PackingListService;

@Service("packingListService")
public class PackingListServiceImpl implements PackingListService {
	@Autowired
	private PackingListDao packingListDao;
	@Autowired
	private WaybillDao waybillDao;

	public List<PackingList> findPage(Page page) {
		return packingListDao.findPage(page);
	}

	public List<PackingList> find(Map paraMap) {
		return packingListDao.find(paraMap);
	}

	public PackingList get(Serializable id) {
		return packingListDao.get(id);
	}

	public void insert(PackingList packingList) {
		this.spellString(packingList);

		packingList.setId(UUID.randomUUID().toString());
		packingList.setState(0); // 0草稿1已上报
		packingListDao.insert(packingList);
	}

	public void update(PackingList packingList) {
		this.spellString(packingList);

		packingListDao.update(packingList);
	}

	public void deleteById(Serializable id) {
		Serializable[] ids = { id };
		packingListDao.deleteById(id);
	}

	public void delete(Serializable[] ids) {
		packingListDao.delete(ids);
	}

	public void submit(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 1); // 1已上报
		map.put("ids", ids);

		packingListDao.updateState(map);
	}

	public void cancel(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 0); // 0草稿
		map.put("ids", ids);

		packingListDao.updateState(map);
	}

	// 拼接HTML片段
	public String getDivDataCreate(String[] waybillIds) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < waybillIds.length; i++) {
			Waybill waybill = waybillDao.get(waybillIds[i]);
			sBuf.append("<input type=\"checkbox\" name=\"waybillIds\" checked value=\"").append(waybillIds[i])
					.append("|").append(waybill.getCustomerContract()).append("\" class=\"input\"/>");
			sBuf.append(waybill.getCustomerContract()).append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拼接HTML片段
	public String getDivDataUpdate(String[] waybillIds, String[] waybillNos) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < waybillIds.length; i++) {
			sBuf.append("<input type=\"checkbox\" name=\"waybillIds\" checked value=\"").append(waybillIds[i])
					.append("|").append(waybillNos[i]).append("\" class=\"input\"/>");
			sBuf.append(waybillNos[i]).append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拼接HMTL片段
	public String getDivDataView(String packingListId, String[] waybillIds, String[] exportNos) {
		StringBuffer sBuf = new StringBuffer();
		for (int i = 0; i < exportNos.length; i++) {
			sBuf.append("<a href=\"/LogisticsBusiness/freight/packinglist/toViewWaybill.action?id=")
					.append(waybillIds[i]).append("&&packingListId=").append(packingListId).append("\">")
					.append(exportNos[i]).append("</a>").append("&nbsp;&nbsp;");
		}

		return sBuf.toString();
	}

	// 拆串，拼串
	private PackingList spellString(PackingList packingList) {
		String _waybillIds = "";
		String _waybillNos = "";

		String[] _s = packingList.getWaybillIds().split(","); // id|no
		for (int i = 0; i < _s.length; i++) {
			String[] _tmp = _s[i].split("\\|"); // 正则表达式，转义
			_waybillIds += _tmp[0] + "|";
			_waybillNos += _tmp[1] + "|";
		}
		_waybillIds = UtilFuns.delLastChar(_waybillIds);
		_waybillNos = UtilFuns.delLastChar(_waybillNos);

		packingList.setWaybillIds(_waybillIds);
		packingList.setWaybillNos(_waybillNos);

		return packingList;
	}

	@Override
	public void enstrust(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 2); // 2 已委托，待发票通知
		map.put("ids", ids);

		packingListDao.updateState(map);
	}

	@Override
	public void invoice(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 3); // 3 已发票通知
		map.put("ids", ids);

		packingListDao.updateState(map);
	}

	@Override
	public void finance(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", 4); // 3 已报向财务
		map.put("ids", ids);

		packingListDao.updateState(map);
		
	}

}
