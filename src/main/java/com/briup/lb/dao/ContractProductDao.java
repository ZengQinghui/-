package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.ContractProduct;

public interface ContractProductDao extends BaseDao<ContractProduct> {
	public void deleteByContratctId(Serializable[] ids);
}
