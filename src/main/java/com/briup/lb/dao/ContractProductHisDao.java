package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.ContractProduct;

public interface ContractProductHisDao extends BaseDao<ContractProduct> {
	public void deleteByContratctId(Serializable[] ids);
}
