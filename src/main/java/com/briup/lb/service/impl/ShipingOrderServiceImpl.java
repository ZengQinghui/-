package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.ShipingOrder;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.FinanceDao;
import com.briup.lb.dao.InvoiceDao;
import com.briup.lb.dao.ShipingOrderDao;
import com.briup.lb.service.ShipingOrderService;

@Service("shipingOrderService")
public class ShipingOrderServiceImpl implements ShipingOrderService {
	@Autowired
	private ShipingOrderDao shipingOrderDao;
	@Autowired
	private InvoiceDao invoiceDao;
	@Autowired
	private FinanceDao financeDao;

	@Override
	public List<ShipingOrder> findPage(Page page) {
		return shipingOrderDao.findPage(page);
	}

	@Override
	public List<ShipingOrder> find(Map paraMap) {
		return shipingOrderDao.find(paraMap);
	}

	@Override
	public ShipingOrder get(Serializable id) {
		return shipingOrderDao.get(id);
	}

	@Override
	public void insert(ShipingOrder shipingOrder) {
		shipingOrder.setState(0);
		shipingOrderDao.insert(shipingOrder);
	}

	@Override
	public void update(ShipingOrder shipingOrder) {
		shipingOrderDao.update(shipingOrder);
	}

	@Override
	public void deleteById(Serializable id) {
		shipingOrderDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		shipingOrderDao.delete(ids);
		for (Serializable id : ids) {
			if (invoiceDao.get(id) != null && financeDao != null) {
				invoiceDao.deleteById(id);
				financeDao.deleteById(id);
			}
		}
	}

	@Override
	public void cancel(Serializable[] ids) {

	}

	@Override
	public void invoice(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "1"); // 1已发票通知
		map.put("ids", ids);
		shipingOrderDao.updateState(map);
	}

	@Override
	public void finance(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "2"); // 1 已报向财务
		map.put("ids", ids);
		shipingOrderDao.updateState(map);

	}

}
