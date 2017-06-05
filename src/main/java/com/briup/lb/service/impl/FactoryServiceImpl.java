package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Factory;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.FactoryDao;
import com.briup.lb.service.FactoryService;

@Service("factoryService")
public class FactoryServiceImpl implements FactoryService {
	@Autowired
	private FactoryDao factoryDao;

	@Override
	public List<Factory> findPage(Page page) {
		return factoryDao.findPage(page);
	}

	@Override
	public List<Factory> find(Map paraMap) {
		return factoryDao.find(paraMap);
	}

	@Override
	public Factory get(Serializable id) {
		return factoryDao.get(id);
	}

	@Override
	public void insert(Factory factory) {
		// 设置ID属性
		factory.setId(UUID.randomUUID().toString());
		// 默认设置为启用
		factory.setStatus("1");
		factoryDao.insert(factory);
	}

	@Override
	public void update(Factory factory) {
		factoryDao.update(factory);
	}

	@Override
	public void deleteById(Serializable id) {
		factoryDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		factoryDao.delete(ids);
	}

	@Override
	public void start(Serializable[] ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "1");  // 启用
		map.put("ids", ids);
		factoryDao.updateStatus(map);
	}

	@Override
	public void stop(Serializable[] ids) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", "0");  // 停用
		map.put("ids", ids);
		factoryDao.updateStatus(map);
	}

	@Override
	public List<Factory> getFactoryList() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status", 1);  // 只查询启用的厂家
		return factoryDao.find(map);
	}

}
