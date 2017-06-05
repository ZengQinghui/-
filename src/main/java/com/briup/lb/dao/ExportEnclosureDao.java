package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.ExportEnclosure;

public interface ExportEnclosureDao extends BaseDao<ExportEnclosure> {
	public void deleteByWaybillId(Serializable[] ids);
}
