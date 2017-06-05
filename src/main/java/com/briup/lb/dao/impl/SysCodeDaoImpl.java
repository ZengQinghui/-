package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.SysCode;
import com.briup.lb.dao.SysCodeDao;

@Repository("sysCodeDao")
public class SysCodeDaoImpl extends BaseDaoImpl<SysCode> implements SysCodeDao {
	public SysCodeDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.SysCodeMapper");
	}
}
