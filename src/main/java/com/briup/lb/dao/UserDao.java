package com.briup.lb.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.User;

public interface UserDao extends BaseDao<User> {
	// 修改状态码
	public void updateStatus(Map map);
	
	// 通过角色获取该角色已分配的用户
	public List<User> findByRoleId(String id);
	
	public void updateByDept(Serializable[] ids);
	
	public User checkUser(Map map);
	
	public List<User> listUser(Map map);
}
