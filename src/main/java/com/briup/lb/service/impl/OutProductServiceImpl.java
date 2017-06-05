package com.briup.lb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.vo.OutProductVO;
import com.briup.lb.dao.OutProductDao;
import com.briup.lb.service.OutProductService;

@Service("outProductService")
public class OutProductServiceImpl implements OutProductService {

	@Autowired
	private OutProductDao outProductDao;
	
	@Override
	public List<OutProductVO> find(String inputDate) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("inputDate", inputDate);
		return outProductDao.find(map);
	}

}
