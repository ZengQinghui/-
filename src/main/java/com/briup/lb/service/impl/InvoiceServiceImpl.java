package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Invoice;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.dao.InvoiceDao;
import com.briup.lb.service.InvoiceService;

@Service("invoiceService")
public class InvoiceServiceImpl implements InvoiceService {
	@Autowired
	private InvoiceDao invoiceDao;

	@Override
	public List<Invoice> findPage(Page page) {
		return invoiceDao.findPage(page);
	}

	@Override
	public List<Invoice> find(Map paraMap) {
		return invoiceDao.find(paraMap);
	}

	@Override
	public Invoice get(Serializable id) {
		return invoiceDao.get(id);
	}

	@Override
	public void insert(Invoice invoice) {
		invoiceDao.insert(invoice);
	}

	@Override
	public void update(Invoice invoice) {
		invoiceDao.update(invoice);
	}

	@Override
	public void deleteById(Serializable id) {
		invoiceDao.deleteById(id);
	}

	@Override
	public void delete(Serializable[] ids) {
		invoiceDao.delete(ids);
	}

	@Override
	public void submit(Serializable[] ids) {
		
	}

	@Override
	public void cancel(Serializable[] ids) {
		
	}

}
