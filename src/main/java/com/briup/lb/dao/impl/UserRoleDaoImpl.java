package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.UserRole;
import com.briup.lb.dao.UserRoleDao;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDaoImpl<UserRole> implements UserRoleDao {
	public UserRoleDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.UserRoleMapper");
	}

	@Override
	public void deleteByRoleId(String id) {
		super.getSqlSession().delete(super.getNs()+".deleteByRoleId", id);
	}

	@Override
	public void deleteByUserId(String id) {
		super.getSqlSession().delete(super.getNs()+".deleteByUserId", id);
	}
}
