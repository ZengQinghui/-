package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Factory;
import com.briup.lb.dao.FactoryDao;

@Repository("factoryDao")
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao {
	public FactoryDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.FactoryMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}
}
