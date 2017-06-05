package com.briup.lb.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Goods;
import com.briup.lb.dao.GoodsDao;

@Repository("goodsDao")
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {
	public GoodsDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.GoodsMapper");
	}

	@Override
	public void deleteByWaybillId(Serializable[] ids) {
		super.getSqlSession().delete(super.getNs()+".deleteByWaybillId", ids);
	}
}
