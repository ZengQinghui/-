package com.briup.lb.dao;


import com.briup.lb.bean.UserRole;

public interface UserRoleDao extends BaseDao<UserRole> {
	public void deleteByRoleId(String id);
	
	public void deleteByUserId(String id);
	
}
