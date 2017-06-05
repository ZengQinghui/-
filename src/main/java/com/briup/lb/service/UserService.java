package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.User;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.pagination.Page;

public interface UserService {
	public List<User> findPage(Page page); // 分页查询

	public List<User> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public User get(Serializable id); // 只查询一个，常用于修改

	public void insert(User user, String[] ids); // 插入，用实体作为参数

	public void update(User user, String[] ids); // 修改，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

	public void changeState(Serializable id, Integer state);

	public void disable(Serializable[] ids) throws CommonException;

	public void cancel(Serializable[] ids);

	public List<User> getUserList(String id);

	public User login(String loginName, String password) throws CommonException;
	
	public void updateUser(User user);
	
	public void saveLoginLog(String loginName);
	
	public List<User> listUser();

}
