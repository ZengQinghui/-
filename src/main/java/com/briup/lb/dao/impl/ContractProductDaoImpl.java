package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.ContractProduct;
import com.briup.lb.dao.ContractProductDao;

@Repository("contractProductDao")
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao {
	public ContractProductDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.ContractProductMapper");
	}

	@Override
	public void deleteByContratctId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContratctId", ids);
	}

}
