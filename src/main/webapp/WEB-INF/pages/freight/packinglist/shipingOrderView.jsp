<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<form method="post">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a
								href="${ctx}/freight/packinglist/listShipingOrder.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">查看委托单信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">货主：</td>
							<td class="tableContent">${shipingOrder.shipper }</td>
							<td class="columnTitle_mustbe">收货人及地址：</td>
							<td class="tableContent">${shipingOrder.consignee }</td>
							<td class="columnTitle_mustbe">正本通知人：</td>
							<td class="tableContent">${shipingOrder.notifyParty }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">信用证：</td>
							<td class="tableContent">${shipingOrder.lcNo }</td>
							<td class="columnTitle_mustbe">转船港：</td>
							<td class="tableContent">${shipingOrder.portOfTrans }</td>
							<td class="columnTitle_mustbe">卸货港：</td>
							<td class="tableContent">${shipingOrder.portOfDischarge }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">装船港：</td>
							<td class="tableContent">${shipingOrder.portOfLoading }</td>
							<td class="columnTitle_mustbe">装期：</td>
							<td class="tableContent"><fmt:formatDate value="${shipingOrder.loadingDate }" pattern="yyyy-MM-dd"/></td>
							<td class="columnTitle_mustbe">效期：</td>
							<td class="tableContent"><fmt:formatDate value="${shipingOrder.limitDate }" pattern="yyyy-MM-dd"/></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">运费：</td>
							<td class="tableContent">${shipingOrder.carriage }</td>
							<td class="columnTitle_mustbe">运输方式：</td>
							<td class="tableContentAuto">${shipingOrder.orderType }</td>
							<td class="columnTitle_mustbe">是否分批：</td>
							<td class="tableContentAuto">${shipingOrder.isBatch }</td>
							<td class="columnTitle_mustbe">是否转船：</td>
							<td class="tableContentAuto">${shipingOrder.isTrans }</td>
							<td class="columnTitle_mustbe">份数：</td>
							<td class="tableContent">${shipingOrder.copyNum }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">运输要求：</td>
							<td class="tableContent">${shipingOrder.specialCondition }</td>
							<td class="columnTitle_mustbe">复核人：</td>
							<td class="tableContent">${shipingOrder.checkBy }</td>
							<td class="columnTitle_mustbe">扼要说明：</td>
							<td class="tableContent">${shipingOrder.cnote }</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

