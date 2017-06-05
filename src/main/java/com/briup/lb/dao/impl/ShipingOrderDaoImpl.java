package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.ShipingOrder;
import com.briup.lb.dao.ShipingOrderDao;

@Repository("shipingOrderDao")
public class ShipingOrderDaoImpl extends BaseDaoImpl<ShipingOrder> implements ShipingOrderDao {
	public ShipingOrderDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.ShipingOrderMapper");
	}

	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateState", map);
	}
}
