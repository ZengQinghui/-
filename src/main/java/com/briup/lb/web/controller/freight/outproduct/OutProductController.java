package com.briup.lb.web.controller.freight.outproduct;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.lb.bean.vo.OutProductVO;
import com.briup.lb.common.utils.DownloadUtil;
import com.briup.lb.service.OutProductService;
import com.briup.lb.web.controller.DateController;

@Controller
public class OutProductController extends DateController {

	@Autowired
	private OutProductService outProductService;

	@RequestMapping("/freight/outproduct/toEdit.action")
	public String toEdit() {
		return "/freight/outproduct/outProduct.jsp";
	}

	// 模板开发
	@RequestMapping("/freight/outproduct/doPrintHSSF.action")
	public void doPrintHSSF(String inputDate, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 模板路径
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xls"));

		Workbook wb = new HSSFWorkbook(is); // 打开一个模板文件，工作簿97-2003版本
		Sheet sheet = wb.getSheetAt(0); // 获取到第一个工作表

		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板上的单元格样式
		nRow = sheet.getRow(2);

		// 客户的样式
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();

		// 订单号的样式
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();

		// 货号的样式
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();

		// 数量的样式
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();

		// 生产厂家的样式
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();

		// 日期的样式
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();

		// 贸易条款的样式
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();

		// 处理大标题
		nRow = sheet.getRow(rowNo++); // 获取一个行对象
		nCell = nRow.getCell(colNo); // 获取一个单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表"); // yyyy-MM

		rowNo++; // 跳过静态表格头

		// 处理内容
		List<OutProductVO> outProductList = outProductService.find(inputDate);
		for (int j = 0; j < outProductList.size(); j++) {
			colNo = 1; // 初始化
			OutProductVO op = outProductList.get(j);

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCustomerName());
			nCell.setCellStyle(customStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getContractNo());
			nCell.setCellStyle(contractNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getProductNo());
			nCell.setCellStyle(productNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getQuantity());
			nCell.setCellStyle(numStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getFactoryName());
			nCell.setCellStyle(factoryStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getShipingDate());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);

		DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
		downloadUtil.download(os, response, "出货表.xls");

		os.flush();
		os.close();
	}

	// 模板开发XSSF
	@RequestMapping("/freight/outproduct/doPrintXSSF.action")
	public void doPrintXSSF(String inputDate, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// 模板路径
		String path = request.getSession().getServletContext().getRealPath("/") + "/make/xlsprint/";
		InputStream is = new FileInputStream(new File(path + "tOUTPRODUCT.xlsx"));

		Workbook wb = new XSSFWorkbook(is); // 打开一个模板文件，工作簿 2007以上版本
		Sheet sheet = wb.getSheetAt(0); // 获取到第一个工作表

		Row nRow = null;
		Cell nCell = null;
		int rowNo = 0; // 行号
		int colNo = 1; // 列号

		// 获取模板上的单元格样式
		nRow = sheet.getRow(2);

		// 客户的样式
		nCell = nRow.getCell(1);
		CellStyle customStyle = nCell.getCellStyle();

		// 订单号的样式
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();

		// 货号的样式
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();

		// 数量的样式
		nCell = nRow.getCell(4);
		CellStyle numStyle = nCell.getCellStyle();

		// 生产厂家的样式
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();

		// 日期的样式
		nCell = nRow.getCell(6);
		CellStyle dateStyle = nCell.getCellStyle();

		// 贸易条款的样式
		nCell = nRow.getCell(8);
		CellStyle tradeStyle = nCell.getCellStyle();

		// 处理大标题
		nRow = sheet.getRow(rowNo++); // 获取一个行对象
		nCell = nRow.getCell(colNo); // 获取一个单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年") + "月份出货表"); // yyyy-MM

		rowNo++; // 跳过静态表格头

		// 处理内容
		List<OutProductVO> outProductList = outProductService.find(inputDate);
		for (int j = 0; j < outProductList.size(); j++) {
			colNo = 1; // 初始化
			OutProductVO op = outProductList.get(j);

			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(24);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCustomerName());
			nCell.setCellStyle(customStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getContractNo());
			nCell.setCellStyle(contractNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getProductNo());
			nCell.setCellStyle(productNoStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getQuantity());
			nCell.setCellStyle(numStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getFactoryName());
			nCell.setCellStyle(factoryStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getShipingDate());
			nCell.setCellStyle(dateStyle);

			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getTradeTerms());
			nCell.setCellStyle(tradeStyle);
		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);

		DownloadUtil downloadUtil = new DownloadUtil(); // 直接弹出下载框，用户可以打开，可以保存
		downloadUtil.download(os, response, "出货表.xlsx");

		os.flush();
		os.close();
	}
}
