<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<body>
	<form method="post">
		<input type="hidden" name="id" value="${id}" />
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('${ctx}/freight/packinglist/doIncoice.action','_self');">确定</a></li>
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
					<div class="textbox-title">新增发票信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">SC_NO：</td>
							<td class="tableContent"><input type="text" name="scNo"
								value="${waybill.customerContract }" /></td>
							<td class="columnTitle_mustbe">发票号：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="invoiceNo"
								value="${packingList.invoiceNo }" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">贸易条款：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="tradeTerms" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

