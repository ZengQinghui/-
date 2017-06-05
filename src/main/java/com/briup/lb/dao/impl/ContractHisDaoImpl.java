package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.dao.ContractHisDao;

@Repository("contractHisDao")
public class ContractHisDaoImpl extends BaseDaoImpl<Contract> implements ContractHisDao{
	public ContractHisDaoImpl() {
		//设置命名空间
		super.setNs("com.briup.lb.mapper.ContractHisMapper");
	}

	public ContractVO view(String contractId) {
		return super.getSqlSession().selectOne(super.getNs()+".view", contractId);
	}
}
