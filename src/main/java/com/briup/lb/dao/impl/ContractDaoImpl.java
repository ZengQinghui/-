package com.briup.lb.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.dao.ContractDao;

@Repository("contractDao")
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao {
	public ContractDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.ContractMapper");
	}

	@Override
	public void updateStatus(Map map) {
		super.getSqlSession().update(super.getNs() + ".updateStatus", map);
	}

	@Override
	public ContractVO view(String contractId) {
		
		return super.getSqlSession().selectOne(super.getNs() + ".view", contractId);
	}

}
