package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Role;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.exception.PermissionException;
import com.briup.lb.common.pagination.Page;

public interface RoleService {
	public List<Role> findPage(Page page); 	// 分页查询

	public List<Role> find(Map paraMap); 	// 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Role get(Serializable id); 		// 只查询一个，常用于修改

	public void insert(Role role,String[] ids); 		// 插入，用实体作为参数

	public void update(Role role,String[] ids); 		// 修改，用实体作为参数

	public void deleteById(Serializable id); 	// 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); 	// 批量删除；支持整数型和字符串类型ID
	
	public void checkPermission(String id) throws PermissionException,CommonException;
	
}
