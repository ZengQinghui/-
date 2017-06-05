package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.ContractProduct;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.common.utils.UtilFuns;
import com.briup.lb.dao.ContractProductDao;
import com.briup.lb.dao.EnclosureDao;
import com.briup.lb.service.ContractProductService;

@Service("contractProductService")
public class ContractProductServiceImpl implements ContractProductService {
	@Autowired
	private ContractProductDao contractProductDao;
	@Autowired
	private EnclosureDao enclosureDao;

	@Override
	public List<ContractProduct> findPage(Page page) {
		return contractProductDao.findPage(page);
	}

	@Override
	public List<ContractProduct> find(Map paraMap) {
		return contractProductDao.find(paraMap);
	}

	@Override
	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	@Override
	public void insert(ContractProduct contractProduct) {
		contractProduct.setId(UUID.randomUUID().toString()); // 设置ID属性

		if (UtilFuns.isNotEmpty(contractProduct.getQuantity()) && UtilFuns.isNotEmpty(contractProduct.getPrice())) {
			contractProduct.setAmount(contractProduct.getQuantity() * contractProduct.getPrice());
		}

		contractProductDao.insert(contractProduct);
	}

	@Override
	public void update(ContractProduct contractProduct) {

		if (UtilFuns.isNotEmpty(contractProduct.getQuantity()) && UtilFuns.isNotEmpty(contractProduct.getPrice())) {
			contractProduct.setAmount(contractProduct.getQuantity() * contractProduct.getPrice());
		}

		contractProductDao.update(contractProduct);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = {id};
		enclosureDao.deleteByContratctProductId(ids);; //删除某货物下的所有附件
		contractProductDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		contractProductDao.deleteByContratctId(ids);; //删除某货物下的所有附件
		contractProductDao.delete(ids);
	}

}
