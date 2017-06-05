package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Role;
import com.briup.lb.bean.UserRole;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.exception.PermissionException;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.RoleDao;
import com.briup.lb.dao.UserRoleDao;
import com.briup.lb.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public List<Role> findPage(Page page) {
		return roleDao.findPage(page);
	}

	@Override
	public List<Role> find(Map paraMap) {
		return roleDao.find(paraMap);
	}

	@Override
	public Role get(Serializable id) {
		return roleDao.get(id);
	}

	@Override
	public void insert(Role role,String[] ids) {
		// 设置ID属性
		role.setId(UUID.randomUUID().toString());
		
		for(String userId : ids){
			UserRole userRole = new UserRole();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setRoleId(role.getId()); 
			userRole.setUserId(userId);
			userRoleDao.insert(userRole);
		}
		
		roleDao.insert(role);
	}

	@Override
	public void update(Role role,String[] ids) {
		roleDao.update(role);
		userRoleDao.deleteByRoleId(role.getId());
		for(String userId : ids){
			UserRole userRole = new UserRole();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setRoleId(role.getId()); 
			userRole.setUserId(userId);
			userRoleDao.insert(userRole);
		}
	}

	@Override
	public void deleteById(Serializable id) {
		roleDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		userRoleDao.delete(ids);
		roleDao.delete(ids);
	}

	@Override
	public void checkPermission(String id) throws PermissionException, CommonException {
		List<String> roleNameList = roleDao.findByUserId(id);
		if(roleNameList.contains("管理员")){
			//throw new PermissionException("只有管理员才能访问这个模块!");
		}else if(roleNameList.contains("部门经理")  && !roleNameList.contains("管理员")){
			throw new PermissionException("只有管理员才能访问这个模块!");
		}else{
			throw new CommonException("你没有权限访问这个模块!");
		}
		
	}

}
