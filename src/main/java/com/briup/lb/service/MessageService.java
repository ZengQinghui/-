package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Message;
import com.briup.lb.bean.User;

public interface MessageService {

	public List<Message> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Message get(Serializable id); // 只查询一个，常用于修改

	public void insert(Message message); // 插入，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID
	
	public void delete(Serializable[] ids);

	public void changeState(Serializable[] ids,Integer state);
	
	public List<Message> getHisMsg(Serializable id);
	
	public Message getById(Serializable id);

}
