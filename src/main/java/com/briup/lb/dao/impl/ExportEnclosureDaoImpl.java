package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.ExportEnclosure;
import com.briup.lb.dao.ExportEnclosureDao;

@Repository("exportEnclosureDao")
public class ExportEnclosureDaoImpl extends BaseDaoImpl<ExportEnclosure> implements ExportEnclosureDao {

	public ExportEnclosureDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.ExportEnclosureMapper");
	}

	@Override
	public void deleteByWaybillId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByWaybillId",ids);
	}

}
