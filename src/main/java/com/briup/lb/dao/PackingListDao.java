package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.PackingList;

public interface PackingListDao extends BaseDao<PackingList> {
	public void updateState(Map map); // 修改状态
}
