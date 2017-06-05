package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Dept;
import com.briup.lb.bean.vo.DeptVO;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.DeptDao;
import com.briup.lb.dao.UserDao;
import com.briup.lb.service.DeptService;

@Service("deptService")
public class DeptServiceImpl implements DeptService {
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private UserDao userDao;

	@Override
	public List<Dept> findPage(Page page) {
		return deptDao.findPage(page);
	}

	@Override
	public List<Dept> find(Map paraMap) {
		return deptDao.find(paraMap);
	}

	@Override
	public Dept get(Serializable id) {
		return deptDao.get(id);
	}

	@Override
	public void insert(Dept dept) {
		// 设置ID属性
		dept.setId(UUID.randomUUID().toString());
		// 默认设置为启用
		dept.setState(1);
		deptDao.insert(dept);
	}

	@Override
	public void update(Dept dept) {
		deptDao.update(dept);
	}

	@Override
	public void deleteById(Serializable id) {
		deptDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		userDao.updateByDept(ids);
		deptDao.delete(ids);
	}

	@Override
	public void start(Serializable[] ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", "1");  // 启用
		map.put("ids", ids);
		deptDao.updateStatus(map);
	}

	@Override
	public void stop(Serializable[] ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", "0");  // 停用
		map.put("ids", ids);
		deptDao.updateStatus(map);
	}

	@Override
	public List<Dept> getDeptList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", 1);  // 只查询启用的部门
		return deptDao.find(map);
	}

	@Override
	public DeptVO view(String deptId) {
		return deptDao.view(deptId);
	}

}
