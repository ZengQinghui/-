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
		<input type="hidden" name="packingListId" value="${packingListId}" />
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('${ctx}/freight/packinglist/doEntrust.action','_self');">确定</a></li>
							<li id="back"><a
								href="${ctx}/freight/packinglist/list.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">新增委托单信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">货主：</td>
							<td class="tableContent"><textarea name="shipper"
									style="height: 120px;">${packingList.seller }</textarea></td>
							<td class="columnTitle_mustbe">收货人及地址：</td>
							<td class="tableContent"><textarea name="consignee"
									style="height: 120px;">${waybill.consignee }</textarea></td>
							<td class="columnTitle_mustbe">正本通知人：</td>
							<td class="tableContent"><textarea name="notifyParty"
									style="height: 120px;">${packingList.buyer }</textarea></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">信用证：</td>
							<td class="tableContent"><input type="text" name="lcNo"
								value="${waybill.lcno }" /></td>
							<td class="columnTitle_mustbe">转船港：</td>
							<td class="tableContent"><input type="text"
								name="portOfTrans" /></td>
							<td class="columnTitle_mustbe">卸货港：</td>
							<td class="tableContent"><input type="text"
								name="portOfDischarge" value="${waybill.destinationPort }" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">装船港：</td>
							<td class="tableContent"><input type="text"
								name="portOfLoading" value="${waybill.shipmentPort }" /></td>
							<td class="columnTitle_mustbe">装期：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="loadingDate"
								onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
							</td>
							<td class="columnTitle_mustbe">效期：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="limitDate"
								onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" />
							</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">运费：</td>
							<td class="tableContent"><input type="text" name="carriage" /></td>
							<td class="columnTitle_mustbe">运输方式：</td>
							<td class="tableContentAuto"><input type="radio"
								name="orderType" value="SEA" class="input"
								<c:if test="${waybill.transportMode=='SEA'}">checked</c:if>>SEA
									<input type="radio" name="orderType" value="AIR" class="input"
									<c:if test="${waybill.transportMode=='AIR'}">checked</c:if>>AIR</td>
							<td class="tableContentAuto"><input type="checkbox"
								name="isBatch" value="1" class="input" style="width: 20px;" />分批</td>
							<td class="tableContentAuto"><input type="checkbox"
								name="isTrans" value="1" class="input" style="width: 20px;" />转船</td>
							<td class="columnTitle_mustbe">份数：</td>
							<td class="tableContent"><input type="text" name="copyNum" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">运输要求：</td>
							<td class="tableContent"><textarea name="specialCondition"
									style="height: 120px;"></textarea></td>
							<td class="columnTitle_mustbe">复核人：</td>
							<td class="tableContent"><input type="text" name="checkBy" /></td>
							<td class="columnTitle_mustbe">扼要说明：</td>
							<td class="tableContent"><textarea name="cnote"
									style="height: 120px;">${packingList.descriptions }</textarea></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

