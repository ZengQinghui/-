package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.Dept;
import com.briup.lb.bean.vo.DeptVO;

public interface DeptDao extends BaseDao<Dept> {
	// 修改状态码
	public void updateStatus(Map map);
	
	// 查看某个部门
	public DeptVO view(String deptId);
}
