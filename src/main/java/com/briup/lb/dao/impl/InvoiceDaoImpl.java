package com.briup.lb.dao.impl;

import org.springframework.stereotype.Repository;

import com.briup.lb.bean.Invoice;
import com.briup.lb.dao.InvoiceDao;

@Repository("invoiceDao")
public class InvoiceDaoImpl extends BaseDaoImpl<Invoice> implements InvoiceDao {
	public InvoiceDaoImpl() {
		// 设置命名空间
		super.setNs("com.briup.lb.mapper.InvoiceMapper");
	}

}
