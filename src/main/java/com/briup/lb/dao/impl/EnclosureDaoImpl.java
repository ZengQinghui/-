package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Enclosure;
import com.briup.lb.dao.EnclosureDao;

@Repository("enclosureDao")
public class EnclosureDaoImpl extends BaseDaoImpl<Enclosure> implements EnclosureDao {
	public EnclosureDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.EnclosureMapper");
	}

	@Override
	public void deleteByContratctProductId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContratctProductId", ids);
	}

	@Override
	public void deleteByContractId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByContractId", ids);
		
	}

}
