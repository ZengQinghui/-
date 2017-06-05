package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Dept;
import com.briup.lb.bean.vo.DeptVO;
import com.briup.lb.dao.DeptDao;

@Repository("deptDao")
public class DeptDaoImpl extends BaseDaoImpl<Dept> implements DeptDao {
	public DeptDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.DeptMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}

	@Override
	public DeptVO view(String deptId) {
		return (DeptVO) super.getSqlSession().selectOne(super.getNs() + ".view", deptId);
	}
}
