package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.LoginLog;
import com.briup.lb.dao.LoginLogDao;

@Repository("loginLogDao")
public class LoginLogDaoImpl extends BaseDaoImpl<LoginLog> implements LoginLogDao {
	public LoginLogDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.LoginLogMapper");
	}
}
