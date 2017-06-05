package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Enclosure;
import com.briup.lb.dao.EnclosureHisDao;

@Repository("enclosureHisDao")
public class EnclosureHisDaoImpl extends BaseDaoImpl<Enclosure> implements EnclosureHisDao {
	public EnclosureHisDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.EnclosureHisMapper");
	}

	@Override
	public void deleteByContractId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", ids);
		
	}

}
