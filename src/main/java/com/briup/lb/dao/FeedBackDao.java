package com.briup.lb.dao;

import java.util.List;
import java.util.Map;

import com.briup.lb.bean.FeedBack;
import com.briup.lb.bean.User;

public interface FeedBackDao extends BaseDao<FeedBack> {
	// 修改状态码
	public void updateStatus(Map map);
	
	// 通过用户名获取反馈意见列表
	public List<FeedBack> findByUserName(String userName);
	
}
