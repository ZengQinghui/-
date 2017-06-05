package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.vo.OutProductVO;
import com.briup.lb.dao.OutProductDao;

@Repository("outProductDao")
public class OutProductDaoImpl extends BaseDaoImpl<OutProductVO> implements OutProductDao {
	public OutProductDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.OutProductMapper");
	}
}
