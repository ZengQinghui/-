package com.briup.lb.service;

import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Goods;

public interface GoodsService {

	public List<Goods> find(Map paraMap); // 带条件查询，条件可以为null，既没有条件；返回list对象集合

}
