package com.briup.lb.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.User;
import com.briup.lb.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	public UserDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.UserMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}

	@Override
	public List<User> findByRoleId(String id) {
		List<User> userList = super.getSqlSession().selectList(super.getNs() + ".findByRoleId", id);
		return userList;
	}

	@Override
	public void updateByDept(Serializable[] ids) {
		super.getSqlSession().update(super.getNs() + ".updateByDept", ids);
	}

	@Override
	public User checkUser(Map map) {
		return super.getSqlSession().selectOne(super.getNs()+".checkUser", map);
	}

	@Override
	public List<User> listUser(Map map) {
		List<User> userList = super.getSqlSession().selectList(super.getNs() + ".listUser", map);
		return userList;
	}
}
