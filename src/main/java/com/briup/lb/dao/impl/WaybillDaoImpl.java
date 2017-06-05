package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Waybill;
import com.briup.lb.dao.WaybillDao;

@Repository("waybillDao")
public class WaybillDaoImpl extends BaseDaoImpl<Waybill> implements WaybillDao {

	public WaybillDaoImpl() {
		//设置命名空间
		super.setNs("com.briup.lb.mapper.WaybillMapper");
	}
	
	@Override
	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs()+".updateState", map);
	}
	
}
