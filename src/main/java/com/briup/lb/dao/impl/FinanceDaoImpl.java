package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Finance;
import com.briup.lb.dao.FinanceDao;

@Repository("financeDao")
public class FinanceDaoImpl extends BaseDaoImpl<Finance> implements FinanceDao {
	public FinanceDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.FinanceMapper");
	}

}
