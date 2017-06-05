package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Finance;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.FinanceDao;
import com.briup.lb.service.FinanceService;

@Service("financeService")
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	private FinanceDao financeDao;

	@Override
	public List<Finance> findPage(Page page) {
		return financeDao.findPage(page);
	}

	@Override
	public List<Finance> find(Map paraMap) {
		return financeDao.find(paraMap);
	}

	@Override
	public Finance get(Serializable id) {
		return financeDao.get(id);
	}

	@Override
	public void insert(Finance finance) {
		financeDao.insert(finance);
	}

	@Override
	public void update(Finance finance) {
		financeDao.update(finance);
	}

	@Override
	public void deleteById(Serializable id) {
		financeDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		financeDao.delete(ids);
	}

}
