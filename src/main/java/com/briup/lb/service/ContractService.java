package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.common.pagination.Page;

public interface ContractService {
	public List<Contract> findPage(Page page); 	// 分页查询

	public List<Contract> find(Map paraMap); 	// 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Contract get(Serializable id); 		// 只查询一个，常用于修改
	
	public ContractVO view(String contractId);  // 查看某一合同

	public void insert(Contract contract); 		// 插入，用实体作为参数

	public void update(Contract contract); 		// 修改，用实体作为参数

	public void deleteById(Serializable id); 	// 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); 	// 批量删除；支持整数型和字符串类型ID
	
	public void submit(Serializable[] ids);		// 上报
	
	public void cancel(Serializable[] ids);		// 取消
	
	public void changeState(Serializable[] ids);
	
	public void finance(Serializable[] ids);
}
