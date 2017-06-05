package poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestPOIDemo {

	@Test
	public void HSSF() throws IOException {
		// 1.创建一个工作簿Excel文件
		Workbook wb = new HSSFWorkbook(); // HSSF操作Excel 2003以下版本

		// 2.创建一个工作表
		Sheet sheet = wb.createSheet();

		// 3.创建一个行对象Row
		Row row = sheet.createRow(4); // 第五行，下标从零开始

		// 4.创建一个单元格对象,指定列
		Cell cell = row.createCell(3);

		// 5.往单元格里面写内容
		cell.setCellValue("Zeng Qinghui");

		// 6.保存
		OutputStream os;

		os = new FileOutputStream(new File("D:\\testPOI.xls"));
		wb.write(os);

		// 7.关闭
		os.close();

	}

	@Test
	public void HSSFStyle() throws IOException {
		Workbook wb = new HSSFWorkbook(); // HSSF操作Excel 2003以下版本
		Sheet sheet = wb.createSheet();
		Row row = sheet.createRow(4); // 第五行，下标从零开始
		Cell cell = row.createCell(3);
		cell.setCellValue("Zeng Qinghui");
		
		// 创建一个单元格样式
		CellStyle titleStyle = wb.createCellStyle();
		
		// 创建一个字体对象
		Font titleFont = wb.createFont();
		titleFont.setFontName("华文行楷");  //设置字体
		titleFont.setFontHeightInPoints((short)26);  //设置字体大小
		
		titleStyle.setFont(titleFont);
		
		cell.setCellStyle(titleStyle);
		OutputStream os;
		os = new FileOutputStream(new File("D:\\testPOI.xls"));
		wb.write(os);
		os.close();

	}

}
