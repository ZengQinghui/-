package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.FeedBack;
import com.briup.lb.dao.FeedBackDao;
import com.briup.lb.dao.RoleDao;
import com.briup.lb.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService {
	@Autowired
	private FeedBackDao feedBackDao;
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<FeedBack> find(Map paraMap) {
		return feedBackDao.find(paraMap);
	}

	@Override
	public FeedBack get(Serializable id) {
		return feedBackDao.get(id);
	}

	@Override
	public void insert(FeedBack feedBack) {
		feedBack.setId(UUID.randomUUID().toString());
		feedBack.setInputTime(new Date());
		feedBack.setState("0");
		feedBackDao.insert(feedBack);
	}

	@Override
	public void update(FeedBack feedBack) {
		feedBackDao.update(feedBack);
	}

	@Override
	public void deleteById(Serializable id) {
		feedBackDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		feedBackDao.delete(ids);
	}

	@Override
	public void changeState(Serializable id, String state) {
		Map<String,Object> map = new HashMap<String,Object>();
		Serializable[] ids = {id};
		map.put("state", state);  
		map.put("ids", ids);
		feedBackDao.updateStatus(map);
	}

	@Override
	public List<FeedBack> findByUserName(String userName) {
		return feedBackDao.findByUserName(userName);
	}

	@Override
	public boolean isAdmin(String id) {
		List<String> roleList = roleDao.findByUserId(id);
		if(roleList.contains("管理员")){
			return true;
		}
		return false;
	}

}
