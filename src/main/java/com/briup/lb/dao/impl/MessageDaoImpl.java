package com.briup.lb.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Message;
import com.briup.lb.dao.MessageDao;

@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {
	public MessageDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.MessageMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}

	@Override
	public List<Message> findHisMsg(Map map) {
		return super.getSqlSession().selectList(super.getNs()+".findHisMsg", map);
	}

	@Override
	public Message getById(Serializable id) {
		return super.getSqlSession().selectOne(super.getNs()+".getById", id);
	}
}
