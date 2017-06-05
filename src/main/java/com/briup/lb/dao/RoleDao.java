package com.briup.lb.dao;


import java.util.List;

import com.briup.lb.bean.Role;

public interface RoleDao extends BaseDao<Role> {
	public List<String> findByUserId(String id);
}
