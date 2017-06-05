package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Finance;
import com.briup.lb.common.pagination.Page;

public interface FinanceService {
	public List<Finance> findPage(Page page);	//分页查询
	public List<Finance> find(Map paraMap);		//带条件查询，条件可以为null，既没有条件；返回list对象集合
	public Finance get(Serializable id);		//只查询一个，常用于修改
	
	public void insert(Finance finance);	//插入，用实体作为参数
	public void update(Finance finance);	//修改，用实体作为参数
	public void deleteById(Serializable id);		//按id删除，删除一条；支持整数型和字符串类型ID
	public void delete(Serializable[] ids);			//批量删除；支持整数型和字符串类型ID
	
}
