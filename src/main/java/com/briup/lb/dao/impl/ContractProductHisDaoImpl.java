package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.ContractProduct;
import com.briup.lb.dao.ContractProductHisDao;

@Repository("contractProductHisDao")
public class ContractProductHisDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductHisDao {
	public ContractProductHisDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.ContractProductHisMapper");
	}

	@Override
	public void deleteByContratctId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContratctId", ids);
	}

}
