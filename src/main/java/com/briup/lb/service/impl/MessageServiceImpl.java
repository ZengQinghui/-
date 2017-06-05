package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Message;
import com.briup.lb.dao.MessageDao;
import com.briup.lb.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;

	@Override
	public List<Message> find(Map paraMap) {
		return messageDao.find(paraMap);
	}

	@Override
	public Message get(Serializable id) {
		return messageDao.get(id);
	}

	@Override
	public void insert(Message message) {
		message.setId(UUID.randomUUID().toString());
		message.setState(0);
		message.setMessageDate(new Date());
		messageDao.insert(message);
	}

	@Override
	public void deleteById(Serializable id) {
		messageDao.deleteById(id);
	}

	@Override
	public void changeState(Serializable[] ids,Integer state) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", state);  // 已读
		map.put("ids", ids);
		messageDao.updateStatus(map);
	}

	@Override
	public List<Message> getHisMsg(Serializable id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 1);
		map.put("userId", id);
		return messageDao.findHisMsg(map);
	}

	@Override
	public Message getById(Serializable id) {
		return messageDao.getById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		messageDao.delete(ids);
	}

}
