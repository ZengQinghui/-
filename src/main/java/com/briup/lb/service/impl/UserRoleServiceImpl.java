package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.UserRole;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.UserRoleDao;
import com.briup.lb.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public List<UserRole> findPage(Page page) {
		return userRoleDao.findPage(page);
	}

	@Override
	public List<UserRole> find(Map paraMap) {
		return userRoleDao.find(paraMap);
	}

	@Override
	public UserRole get(Serializable id) {
		return userRoleDao.get(id);
	}

	@Override
	public void insert(UserRole userRole) {
		// 设置ID属性
		userRole.setId(UUID.randomUUID().toString());
		userRoleDao.insert(userRole);
	}

	@Override
	public void update(UserRole userRole) {
		userRoleDao.update(userRole);
	}

	@Override
	public void deleteById(Serializable id) {
		userRoleDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		userRoleDao.delete(ids);
	}

}
