package com.briup.lb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Role;
import com.briup.lb.dao.RoleDao;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
	public RoleDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.RoleMapper");
	}

	@Override
	public List<String> findByUserId(String id) {
		return super.getSqlSession().selectList(super.getNs()+".findByUserId", id);
	}
}
