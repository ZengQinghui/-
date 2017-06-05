package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Enclosure;
import com.briup.lb.bean.SysCode;

public interface EnclosureService {

	public List<Enclosure> find(Map paraMap); 	// 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Enclosure get(Serializable id); 		// 只查询一个，常用于修改

	public void insert(Enclosure enclosure); 		// 插入，用实体作为参数

	public void update(Enclosure enclosure); 		// 修改，用实体作为参数

	public void deleteById(Serializable id); 	// 按id删除，删除一条；支持整数型和字符串类型ID	
	
	public List<SysCode> getTypeList();         // 获取分类信息列表
	
	public void delete (Serializable[] id);
}
