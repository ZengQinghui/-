package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.ShipingOrder;

public interface ShipingOrderDao extends BaseDao<ShipingOrder> {
	public void updateState(Map map); // 修改状态
}
