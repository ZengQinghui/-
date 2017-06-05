package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.PackingList;
import com.briup.lb.dao.PackingListDao;

@Repository("packingListDao")
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao {
	public PackingListDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.PackingListMapper");
	}

	public void updateState(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateState", map);
	}
}
