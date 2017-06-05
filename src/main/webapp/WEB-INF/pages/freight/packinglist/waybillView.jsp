<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<body>
	<form method="post">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a href="${ctx}/freight/packinglist/toView.action?id=${packingListId}">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">查看出口报运信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">合同或确认书号：</td>
							<td class="tableContent">${waybill.customerContract}</td>
							<td class="columnTitle_mustbe">制单日期：</td>
							<td class="tableContent">${waybill.inputDate}</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">信用证号：</td>
							<td class="tableContent">${waybill.lcno}</td>
							<td class="columnTitle_mustbe">收货人及地址：</td>
							<td class="tableContent">${waybill.consignee}</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">装运港：</td>
							<td class="tableContent">${waybill.shipmentPort}</td>
							<td class="columnTitle_mustbe">目的港：</td>
							<td class="tableContent">${waybill.destinationPort}</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">运输方式：</td>
							<td class="tableContent">${waybill.transportMode}</td>
							<td class="columnTitle_mustbe">价格条件：</td>
							<td class="tableContent">${waybill.priceCondition}</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">唛头：</td>
							<td class="tableContent">${waybill.marks}</td>
							<td class="columnTitle_mustbe">备注：</td>
							<td class="tableContent">${waybill.remark}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>


		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">报运下货物的列表信息</div>
				</div>
			</div>
			<div>
				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('id',this)" /></td>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">货号</td>
								<td class="tableHeader">数量</td>
								<td class="tableHeader">毛重</td>
								<td class="tableHeader">净重</td>
								<td class="tableHeader">长</td>
								<td class="tableHeader">宽</td>
								<td class="tableHeader">高</td>
								<td class="tableHeader">出口单价</td>
								<td class="tableHeader">含税</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach items="${goods}" var="good" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${good.id}" /></td>
									<td>${status.index+1}</td>
									<td>${good.productNo}</td>
									<td>${good.quantity}</td>
									<td>${good.grossWeight}</td>
									<td>${good.netWeight}</td>
									<td>${good.sizeLength}</td>
									<td>${good.sizeWidth}</td>
									<td>${good.sizeHeight}</td>
									<td>${good.exPrice}</td>
									<td>${good.tax}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="textbox-bottom">
				<div class="textbox-inner-bottom">
					<div class="textbox-go-top"></div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

