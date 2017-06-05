package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.common.springdao.SqlDao;
import com.briup.lb.common.utils.UtilFuns;
import com.briup.lb.dao.ContractHisDao;
import com.briup.lb.dao.ContractProductHisDao;
import com.briup.lb.dao.EnclosureHisDao;
import com.briup.lb.service.ContractHisService;

@Service("contractHisService")
public class ContractHisServiceImpl implements ContractHisService {
	@Resource
	private ContractHisDao contractHisDao;
	@Resource
	private SqlDao sqlDao;
	@Autowired
	private ContractProductHisDao contractProductHisDao;
	@Autowired
	private EnclosureHisDao enclosureHisDao;

	public void pigeinhole(String[] contractIds) {
		sqlDao.batchSQL(this.doData(contractIds, "", "_his"));		//批量执行
	}

	public void pigeouthole(String[] contractIds) {
		sqlDao.batchSQL(this.doData(contractIds, "_his", ""));
	}
	
	//处理数据：由源表向目标表复制数据，将源表数据删除
	public String[] doData(String[] contractIds, String source, String target){
		StringBuffer sBuf = new StringBuffer();
		String inStr = UtilFuns.joinStr(contractIds, "'", "'", ",");			//合同的id串 x,y ，构造成in子查询串 'x','y'
		
		sBuf.append("insert into tbl_contract").append(target).append(" (select * from tbl_contract").append(source).append(" where contract_id in (").append(inStr).append("));");
		sBuf.append("insert into tbl_contract_product").append(target).append(" (select * from tbl_contract_product").append(source).append(" where contract_id in (").append(inStr).append("));");
		sBuf.append("insert into tbl_enclosure").append(target).append(" (select * from tbl_enclosure").append(source).append(" where contract_product_id in (select contract_product_id from tbl_contract_product").append(source).append(" where contract_id in (").append(inStr).append(")));");
				
		sBuf.append("delete from tbl_enclosure").append(source).append(" where contract_product_id in (select contract_product_id from tbl_contract_product").append(source).append(" where contract_id in (").append(inStr).append("));");
		sBuf.append("delete from tbl_contract_product").append(source).append(" where contract_id in (").append(inStr).append(");");
		sBuf.append("delete from tbl_contract").append(source).append(" where contract_id in (").append(inStr).append(");");
		
		return sBuf.toString().split(";");
	}

	public List<Contract> findPage(Page page) {
		return contractHisDao.findPage(page);
	}

	public List<Contract> find(Map paraMap) {
		return contractHisDao.find(paraMap);
	}

	public ContractVO view(String contractId) {
		return contractHisDao.view(contractId);
	}

	@Override
	public void delete(Serializable[] ids) {
		enclosureHisDao.deleteByContractId(ids);	     // 删除当前合同下面的所有附件信息
		contractProductHisDao.deleteByContratctId(ids); // 删除当前合同下面的所有货物
		contractHisDao.delete(ids);
	}

}
