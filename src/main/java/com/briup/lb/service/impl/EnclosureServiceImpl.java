package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Enclosure;
import com.briup.lb.bean.SysCode;
import com.briup.lb.common.utils.UtilFuns;
import com.briup.lb.dao.EnclosureDao;
import com.briup.lb.dao.SysCodeDao;
import com.briup.lb.service.EnclosureService;

@Service("enclosureService")
public class EnclosureServiceImpl implements EnclosureService {
	@Autowired
	private EnclosureDao enclosureDao;
	@Autowired
	private SysCodeDao sysCodeDao;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Enclosure> find(Map paraMap) {
		return enclosureDao.find(paraMap);
	}

	@Override
	public Enclosure get(Serializable id) {
		return enclosureDao.get(id);
	}

	@Override
	public void insert(Enclosure enclosure) {
		enclosure.setId(UUID.randomUUID().toString()); // 设置ID属性

		if (UtilFuns.isNotEmpty(enclosure.getQuantity()) && UtilFuns.isNotEmpty(enclosure.getPrice())) {
			enclosure.setAmount(enclosure.getQuantity() * enclosure.getPrice());
		}

		enclosureDao.insert(enclosure);
	}

	@Override
	public void update(Enclosure enclosure) {
		if (UtilFuns.isNotEmpty(enclosure.getQuantity()) && UtilFuns.isNotEmpty(enclosure.getPrice())) {
			enclosure.setAmount(enclosure.getQuantity() * enclosure.getPrice());
		}
		enclosureDao.update(enclosure);
	}

	@Override
	public void deleteById(Serializable id) {
		enclosureDao.deleteById(id);
	}

	@Override
	public List<SysCode> getTypeList() {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		paraMap.put("parentId", "0104");
		return sysCodeDao.find(paraMap);
	}

	@Override
	public void delete(Serializable[] id) {
		
	}

}
