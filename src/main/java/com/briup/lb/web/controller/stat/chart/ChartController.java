package com.briup.lb.web.controller.stat.chart;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.lb.common.springdao.SqlDao;
import com.briup.lb.common.utils.file.FileUtil;

@Controller
public class ChartController {
	@Autowired
	private SqlDao sqlDao;
	
	//生产厂家销售饼形图
	/*
	 * 开发步骤：
	 * 1、组织数据源
	 * 2、拼接成xml
	 * 3、创建一个文件txt格式，xml 工具类
	 * 4、转向对应目录下的index.html
	 */
	
	@RequestMapping("/stat/chart/factorySale.action")
	public String factorySale(HttpServletRequest request) throws FileNotFoundException{
		String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
		String dir = "factorysale";
		
		String sql = "SELECT f.factory_name,cp.countnum FROM (SELECT factory_id,factory_name FROM tbl_factory) f RIGHT JOIN (SELECT factory_id,COUNT(*) AS countnum FROM tbl_contract_product GROUP BY factory_id ) cp ON f.factory_id=cp.factory_id";
		this.writeXML(path, dir, this.getPieXml(this.getData(sql)));
		
		return "/stat/chart/stat.jsp?forward="+dir;
	}
	
	//产品销售排行，畅销产品排行
	@RequestMapping("/stat/chart/productSale.action")
	public String productSale(HttpServletRequest request) throws FileNotFoundException{
		String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
		String dir = "productsale";
		
		String sql = "select t.*,rownum from ( select product_no,sum(quantity) as sumnum from tbl_contract_product group by product_no order by sum(quantity)  desc ) t where rownum<=15";
		this.writeXML(path, dir, this.getColumnAndLineXml(this.getData(sql)));
		
		return "/stat/chart/stat.jsp?forward=" + dir;
	}
	
	//系统访问压力的曲线图
	@RequestMapping("/stat/chart/onlineInfo.action")
	public String onlineInfo(HttpServletRequest request) throws FileNotFoundException{
		String path = request.getSession().getServletContext().getRealPath("/");	//真实路径
		String dir = "onlineinfo";
		
		String sql = "SELECT t.a1,p.countnum FROM (SELECT a1 FROM tbl_onlineinfo) t LEFT JOIN (SELECT substr(login_time,11,2) AS a1,COUNT(*) AS countnum FROM tbl_login_log GROUP BY substr(login_time,11,2)) p ON t.a1=p.a1";
		this.writeXML(path, dir, this.getColumnAndLineXml(this.getData(sql)));
		
		return "/stat/chart/stat.jsp?forward=" + dir;
	}
	
	//获取数据
	public List<String> getData(String sql){
		return sqlDao.executeSQL(sql);			//List<String>
	}
	
	//拼接饼形图xml
	public String getPieXml(List<String> dataList){
		//拼接数据位一个xml字符串
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<pie>");
		for(int i=0;i<dataList.size();){
			//在for循环内部控制当前记录标识
			sBuf.append("  <slice title=\"").append(dataList.get(i++)).append("\">").append(dataList.get(i++)).append("</slice>");
		}
		sBuf.append("</pie>");
		
		return sBuf.toString();
	}
	
	//获得柱状图xml
	public String getColumnAndLineXml(List<String> dataList){
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<chart>");
		sBuf.append("<series>");
		
		int xid = 0;			//对应标识
		for(int i=0;i<dataList.size();){
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
			i++;		//skip
		}
		sBuf.append("</series>");
		sBuf.append("<graphs>");
		sBuf.append("<graph gid=\"30\" color=\"#FFCC00\" gradient_fill_colors=\"#111111, #1A897C\">");
		
		xid = 0;
		for(int i=0;i<dataList.size();i++){
			//i++;		//skip
			sBuf.append("<value xid=\"").append(xid++).append("\">").append(dataList.get(i++)).append("</value>");
		}
		
		sBuf.append("</graph>");
		sBuf.append("</graphs>");
		sBuf.append("</chart>");
		
		return sBuf.toString();
	}
	
	//写xml文本文件，格式utf-8
	public void writeXML(String path, String dir, String content) throws FileNotFoundException{
		//输出文件，如果目录下没有文件，直接创建；如果目录下文件存在，覆盖。
		FileUtil fu = new FileUtil();
		fu.createTxt(path + "/stat/chart/" + dir, "data.xml", content, "utf-8");
	}
	
}
