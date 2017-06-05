package com.briup.lb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Goods;
import com.briup.lb.dao.GoodsDao;
import com.briup.lb.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public List<Goods> find(Map paraMap) {
		return goodsDao.find(paraMap);
	}

}
