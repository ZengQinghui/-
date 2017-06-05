package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.Factory;

public interface FactoryDao extends BaseDao<Factory> {
	// 修改状态码
	public void updateStatus(Map map);
}
