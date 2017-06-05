package com.briup.lb.dao;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;

public interface ContractHisDao extends BaseDao<Contract> {
	public ContractVO view(String contractId);	//查询某个合同
}
