package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.Waybill;

public interface WaybillDao extends BaseDao<Waybill> {
	public void updateState(Map map);			//修改状态
}
