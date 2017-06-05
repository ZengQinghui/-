package com.briup.lb.dao;

import java.util.Map;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;

public interface ContractDao extends BaseDao<Contract> {
	// 修改状态码
	public void updateStatus(Map map);
	
	// 查看某个合同
	public ContractVO view(String contractId);
	
}
