package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.Enclosure;

public interface EnclosureHisDao extends BaseDao<Enclosure> {
	public void deleteByContractId(Serializable[] ids);
}
