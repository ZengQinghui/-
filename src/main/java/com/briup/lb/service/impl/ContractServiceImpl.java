package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.ContractDao;
import com.briup.lb.dao.ContractProductDao;
import com.briup.lb.dao.EnclosureDao;
import com.briup.lb.service.ContractService;

@Service("contractService")
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private ContractProductDao contractProductDao;
	@Autowired
	private EnclosureDao enclosureDao;

	@Override
	public List<Contract> findPage(Page page) {
		return contractDao.findPage(page);
	}

	@Override
	public List<Contract> find(Map paraMap) {
		return contractDao.find(paraMap);
	}

	@Override
	public Contract get(Serializable id) {
		return contractDao.get(id);
	}

	@Override
	public void insert(Contract contract) {
		contract.setId(UUID.randomUUID().toString()); // 设置ID属性
		contract.setState(0); // 默认设置为草稿
		contractDao.insert(contract);
	}

	@Override
	public void update(Contract contract) {
		contractDao.update(contract);
	}

	@Override
	public void deleteById(Serializable id) {
		Serializable[] ids = { id };
		enclosureDao.deleteByContractId(ids);      // 删除当前合同下面的所有附件信息
		contractProductDao.deleteByContratctId(ids); // 删除当前合同下面的所有货物
		contractDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		enclosureDao.deleteByContractId(ids);	     // 删除当前合同下面的所有附件信息
		contractProductDao.deleteByContratctId(ids); // 删除当前合同下面的所有货物
		contractDao.delete(ids);
	}

	@Override
	public void submit(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "1"); // 1已上报
		map.put("ids", ids);
		contractDao.updateStatus(map);
	}

	@Override
	public void cancel(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "0"); // 0草稿
		map.put("ids", ids);
		contractDao.updateStatus(map);
	}

	@Override
	public ContractVO view(String contractId) {
		return contractDao.view(contractId);
	}

	@Override
	public void changeState(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "2"); // 已报运
		map.put("ids", ids);
		contractDao.updateStatus(map);
	}

	@Override
	public void finance(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "3"); // 已完成全部流程，可归档
		map.put("ids", ids);
		contractDao.updateStatus(map);
	}

}
