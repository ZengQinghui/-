package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.FeedBack;

public interface FeedBackService {
	public List<FeedBack> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public FeedBack get(Serializable id); // 只查询一个，常用于修改

	public void insert(FeedBack feedBack); // 插入，用实体作为参数

	public void update(FeedBack feedBack); // 修改，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

	public void changeState(Serializable id, String state);

	public List<FeedBack> findByUserName(String userName);
	
	public boolean isAdmin(String id);
}
