package com.briup.lb.dao;

import java.io.Serializable;

import com.briup.lb.bean.Goods;

public interface GoodsDao extends BaseDao<Goods> {
	public void deleteByWaybillId(Serializable[] ids);
}
