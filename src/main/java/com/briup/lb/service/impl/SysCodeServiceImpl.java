package com.briup.lb.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.SysCode;
import com.briup.lb.dao.SysCodeDao;
import com.briup.lb.service.SysCodeService;

@Service("sysCodeService")
public class SysCodeServiceImpl implements SysCodeService {
	@Autowired
	private SysCodeDao sysCodeDao;

	@Override
	public List<SysCode> find(Map paraMap) {
		return sysCodeDao.find(paraMap);
	}

}
