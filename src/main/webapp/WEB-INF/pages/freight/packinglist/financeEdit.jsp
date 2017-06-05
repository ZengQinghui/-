<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
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
								onclick="formSubmit('${ctx}/freight/packinglist/doFinance.action','_self');">确定</a></li>
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
					<div class="textbox-title">新增财务信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">制单日期：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="inputDate"
								onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
							</td>
							<td class="columnTitle_mustbe">制单人：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="inputBy" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

