package com.briup.lb.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.Waybill;
import com.briup.lb.common.pagination.Page;

public interface WaybillService {
	public List<Waybill> findPage(Page page); 	// 分页查询

	public List<Waybill> find(Map paraMap); 	// 带条件查询，条件可以为null，既没有条件；返回list对象集合

	public Waybill get(String id); 		// 只查询一个，常用于修改

	public void insert(String[] contractIds);   // 插入

	public void update(Waybill waybill,			//修改，用实体作为参数
			String[] mr_id,
			Integer[] mr_orderNo,
			Integer[] mr_cnumber,
			Double[] mr_grossWeight,
			Double[] mr_netWeight,
			Double[] mr_sizeLength,
			Double[] mr_sizeWidth,
			Double[] mr_sizeHeight,
			Double[] mr_exPrice,
			Double[] mr_tax
		); 

	public void deleteById(Serializable id); 	// 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); 	// 批量删除；支持整数型和字符串类型ID
	
	public void submit(Serializable[] ids);		// 上报
	
	public void cancel(Serializable[] ids);		// 取消
	
	public List<Contract> getContracts();       // 获取已上报的购销合同列表
	
	public String getMrecordData(String waybillId);		//拼接js串
	
	public void packingList(Serializable[] ids);
	
	public void entrust(Serializable[] ids);
	
	public void invoice(Serializable[] ids);
	
	public void finance(Serializable[] ids);
	
}
