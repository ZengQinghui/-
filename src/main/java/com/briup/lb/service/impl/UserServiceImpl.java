package com.briup.lb.service.impl;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.LoginLog;
import com.briup.lb.bean.User;
import com.briup.lb.bean.UserRole;
import com.briup.lb.common.exception.CommonException;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.LoginLogDao;
import com.briup.lb.dao.UserDao;
import com.briup.lb.dao.UserRoleDao;
import com.briup.lb.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public List<User> findPage(Page page) {
		return userDao.findPage(page);
	}

	@Override
	public List<User> find(Map paraMap) {
		return userDao.find(paraMap);
	}

	@Override
	public User get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public void insert(User user,String[] ids) {
		user.setId(UUID.randomUUID().toString());
		user.setState(0);
		
		for(String roleId : ids){
			UserRole userRole = new UserRole();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setRoleId(roleId); 
			userRole.setUserId(user.getId());
			userRoleDao.insert(userRole);
		}
		
		userDao.insert(user);
	}

	@Override
	public void update(User user,String[] ids) {
		userDao.update(user);
		userRoleDao.deleteByUserId(user.getId());
		for(String roleId : ids){
			UserRole userRole = new UserRole();
			userRole.setId(UUID.randomUUID().toString());
			userRole.setRoleId(roleId); 
			userRole.setUserId(user.getId());
			userRoleDao.insert(userRole);
		}
	}

	@Override
	public void deleteById(Serializable id) {
		userDao.deleteById(id);
		
	}

	@Override
	public void delete(Serializable[] ids) {
		userDao.delete(ids);
	}

	@Override
	public void changeState(Serializable id,Integer state) {
		Map<String,Object> map = new HashMap<String,Object>();
		Serializable[] ids = {id};
		map.put("state", state);  
		map.put("ids", ids);
		userDao.updateStatus(map);
		
	}

	@Override
	public void disable(Serializable[] ids) throws CommonException {
		
		for(Serializable id : ids){
			User user = userDao.get(id);
			System.out.println(user.getState());
			if(user.getState()==1){
				throw new CommonException("该用户处于在线状态,请等待下线之后再执行此操作!");
			}
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", "2");  // 禁用
		map.put("ids", ids);
		userDao.updateStatus(map);
	}

	@Override
	public void cancel(Serializable[] ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 0);  // 取消禁用
		map.put("ids", ids);
		userDao.updateStatus(map);
	}

	@Override
	public List<User> getUserList(String id) {
		return userDao.findByRoleId(id);
	}

	@Override
	public User login(String loginName, String password) throws CommonException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", loginName);
		map.put("password", password);
		User user = userDao.checkUser(map);
		
		if(user==null){
			throw new CommonException("用户名或密码输入错误!!!");
		}
		if(user!=null && user.getState()==2){
			throw new CommonException("该用户已被禁用,请联系管理员!!!");
		}
		
		return user;
	}

	@Override
	public void updateUser(User user) {
		user.setState(1);
		userDao.update(user);
	}

	@Override
	public void saveLoginLog(String loginName) {
		InetAddress ia=null;
		try {
			LoginLog loginLog = new LoginLog(UUID.randomUUID().toString(), loginName, ia.getLocalHost().getHostAddress(), new Date());
			loginLogDao.insert(loginLog);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> listUser() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 2);  // 只查询启用的部门
		return userDao.listUser(map);
	}
	
}
