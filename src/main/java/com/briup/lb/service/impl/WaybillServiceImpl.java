package com.briup.lb.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.lb.bean.Contract;
import com.briup.lb.bean.ExportEnclosure;
import com.briup.lb.bean.Goods;
import com.briup.lb.bean.Waybill;
import com.briup.lb.bean.vo.ContractProductVO;
import com.briup.lb.bean.vo.ContractVO;
import com.briup.lb.bean.vo.EnclosureVO;
import com.briup.lb.common.pagination.Page;
import com.briup.lb.common.utils.UtilFuns;
import com.briup.lb.dao.ContractDao;
import com.briup.lb.dao.ExportEnclosureDao;
import com.briup.lb.dao.GoodsDao;
import com.briup.lb.dao.WaybillDao;
import com.briup.lb.service.WaybillService;

@Service("waybillService")
@WebService
public class WaybillServiceImpl implements WaybillService {
	@Autowired
	private WaybillDao waybillDao;
	@Autowired
	private ContractDao contractDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ExportEnclosureDao exportEnclosureDao;

	// 利用set方法，在cxf中注入dao，这样cxf的webService才能查询我们系统的数据
	@WebMethod(exclude = true)
	public void setWaybillDao(WaybillDao waybillDao) {
		this.waybillDao = waybillDao;
	}

	@Override
	@WebMethod(exclude = true)
	public List<Waybill> findPage(Page page) {
		return waybillDao.findPage(page);
	}

	@Override
	@WebMethod(exclude = true)
	public List<Waybill> find(Map paraMap) {
		return waybillDao.find(paraMap);
	}

	@Override
	public Waybill get(String id) {
		return waybillDao.get(id);
	}

	@Override
	@WebMethod(exclude = true)
	public void deleteById(Serializable id) {
		waybillDao.deleteById(id);
	}

	@Override
	@WebMethod(exclude = true)
	public void delete(Serializable[] ids) {
		// 删除报运单前，先删除报运单下面的附件信息
		exportEnclosureDao.deleteByWaybillId(ids);
		goodsDao.deleteByWaybillId(ids);
		waybillDao.delete(ids);
	}

	@Override
	@WebMethod(exclude = true)
	public void submit(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "1"); // 1已上报,待装箱
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

	@Override
	@WebMethod(exclude = true)
	public void cancel(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "0"); // 1草稿
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

	@Override
	@WebMethod(exclude = true)
	public List<Contract> getContracts() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "1");
		return contractDao.find(map);
	}

	@Override
	@WebMethod(exclude = true)
	public void insert(String[] contractIds) {
		/*
		 * 步骤： 1、根据合同id获得合同对象，获取合同号 2、将合同下的货物信息搬家到报运下的货物表中
		 * 3、将合同下的附件信息搬家到报运下的附件表中
		 */

		// 拼接合同号，报运号
		String contractNos = "";
		for (int i = 0; i < contractIds.length; i++) {
			ContractVO contract = contractDao.view(contractIds[i]);
			contractNos += contract.getContractNo() + " "; // 以空格作为分隔符
		}
		contractNos = UtilFuns.delLastChar(contractNos); // 工具类，删除最后一个字符

		Waybill waybill = new Waybill();
		waybill.setId(UUID.randomUUID().toString());

		// x,y
		waybill.setContractIds(UtilFuns.joinStr(contractIds, ",")); // 工具类，拼串
		waybill.setCustomerContract(contractNos);

		waybill.setState(0); // 0草稿

		waybillDao.insert(waybill);

		// 处理货物信息
		for (int i = 0; i < contractIds.length; i++) {
			ContractVO contract = contractDao.view(contractIds[i]);

			for (ContractProductVO cp : contract.getContractProducts()) {

				Goods good = new Goods();
				good.setId(UUID.randomUUID().toString());
				good.setWaybillId(waybill.getId());
				; // 绑定外键

				// 数据搬家，将合同下的对应的货物信息写入到报运下的货物信息中
				good.setFactoryId(cp.getFactory().getId());
				good.setFactoryName(cp.getFactory().getFactoryName());
				good.setProductNo(cp.getProductNo());
				good.setPackingUnit(cp.getPackingUnit());
				good.setQuantity(cp.getQuantity());
				good.setBoxNum(cp.getBoxNum());
				good.setPrice(cp.getPrice());

				goodsDao.insert(good);

				// 处理附件信息
				for (EnclosureVO evo : cp.getEnclosures()) {
					ExportEnclosure exen = new ExportEnclosure();

					// copyProperties spring
					BeanUtils.copyProperties(evo, exen); // spring工具类，数据的拷贝

					exen.setId(UUID.randomUUID().toString());
					exen.setGoodsId(good.getId());
					; // 绑定外键

					exen.setFactoryId(evo.getFactory().getId());
					exen.setFactoryName(evo.getFactory().getFactoryName());

					exportEnclosureDao.insert(exen);
				}
			}
		}

	}

	@Override
	public String getMrecordData(String waybillId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("waybillId", waybillId);

		List<Goods> oList = goodsDao.find(paraMap);

		StringBuffer sBuf = new StringBuffer();
		for (Goods good : oList) {
			sBuf.append("addTRRecord(\"mRecordTable\", \"").append(good.getId()).append("\", \"")
					.append(good.getProductNo()).append("\", \"").append(good.getQuantity()).append("\", \"")
					.append(UtilFuns.convertNull(good.getGrossWeight())).append("\", \"")
					.append(UtilFuns.convertNull(good.getNetWeight())).append("\", \"")
					.append(UtilFuns.convertNull(good.getSizeLength())).append("\", \"")
					.append(UtilFuns.convertNull(good.getSizeWidth())).append("\", \"")
					.append(UtilFuns.convertNull(good.getSizeHeight())).append("\", \"")
					.append(UtilFuns.convertNull(good.getExPrice())).append("\", \"")
					.append(UtilFuns.convertNull(good.getTax())).append("\");");
		}

		return sBuf.toString();
	}

	@Override
	@WebMethod(exclude = true)
	public void update(Waybill waybill, String[] mr_id, Integer[] mr_orderNo, Integer[] mr_cnumber,
			Double[] mr_grossWeight, Double[] mr_netWeight, Double[] mr_sizeLength, Double[] mr_sizeWidth,
			Double[] mr_sizeHeight, Double[] mr_exPrice, Double[] mr_tax) {
		waybillDao.update(waybill);

		// 批量修改货物信息
		for (int i = 0; i < mr_id.length; i++) {

			// 修改标识，只有用户修改的行才进行更新
			Goods good = goodsDao.get(mr_id[i]);

			good.setOrderNo(mr_orderNo[i]);
			good.setQuantity(mr_cnumber[i]);
			good.setGrossWeight(mr_grossWeight[i]);
			good.setNetWeight(mr_netWeight[i]);
			good.setSizeLength(mr_sizeLength[i]);
			good.setSizeWidth(mr_sizeWidth[i]);
			good.setSizeHeight(mr_sizeHeight[i]);
			good.setExPrice(mr_exPrice[i]);
			good.setTax(mr_tax[i]);

			goodsDao.update(good);
		}
	}

	@Override
	@WebMethod(exclude = true)
	public void packingList(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "2"); // 1 已装箱,待委托
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

	@Override
	@WebMethod(exclude = true)
	public void entrust(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "3"); // 3 已委托，待发票通知
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

	@Override
	@WebMethod(exclude = true)
	public void invoice(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "4"); // 4 已发票通知
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

	@Override
	@WebMethod(exclude = true)
	public void finance(Serializable[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "5"); //5 已报向财务
		map.put("ids", ids);
		waybillDao.updateState(map);
	}

}
