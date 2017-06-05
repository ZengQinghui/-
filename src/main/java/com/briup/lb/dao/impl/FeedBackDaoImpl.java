package com.briup.lb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.FeedBack;
import com.briup.lb.dao.FeedBackDao;

@Repository("feedBackDao")
public class FeedBackDaoImpl extends BaseDaoImpl<FeedBack> implements FeedBackDao {
	
	public FeedBackDaoImpl() {
		super.setNs("com.briup.lb.mapper.FeedBackMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}

	@Override
	public List<FeedBack> findByUserName(String userName) {
		return super.getSqlSession().selectList(super.getNs()+".findByUserName", userName);
	}

}
