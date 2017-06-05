package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.Enclosure;

public interface EnclosureDao extends BaseDao<Enclosure> {
	public void deleteByContratctProductId(Serializable[] ids);
	public void deleteByContractId(Serializable[] ids);
}
