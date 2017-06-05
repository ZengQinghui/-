package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.common.pagination.Page;

public interface ContractHisService {
	public List<Contract> findPage(Page page); // 分页查询

	public List<Contract> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public ContractVO view(String contractId); // 关联对象的查询一个

	public void pigeinhole(String[] contractIds); // 归档

	public void pigeouthole(String[] contractIds); // 取消归档
	
	public void delete(Serializable[] ids);
}
