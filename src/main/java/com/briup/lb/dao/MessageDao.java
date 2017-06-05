package com.briup.lb.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Message;

public interface MessageDao extends BaseDao<Message> {
	// 修改状态码
	public void updateStatus(Map map);
	
	public List<Message> findHisMsg(Map map);
	
	public Message getById(Serializable id);
}
