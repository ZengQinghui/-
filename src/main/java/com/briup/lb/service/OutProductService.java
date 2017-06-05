package com.briup.lb.service;

import java.util.List;

import com.briup.lb.bean.vo.OutProductVO;

public interface OutProductService {

	public List<OutProductVO> find(String inputDate);
}
