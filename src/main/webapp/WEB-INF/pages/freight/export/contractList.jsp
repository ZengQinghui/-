<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" rev="stylesheet" type="text/css"
	href="${ctx}/components/alert/sweetalert.css" media="all" />
<script type="text/javascript"
	src="${ctx}/components/alert/sweetalert.min.js"></script>
<script type="text/javascript">
	function view() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要查看的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contract/toView.action";
		document.forms.icform.submit();
	}
	function doPrint() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要打印的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contract/doPrint.action";
		document.forms.icform.submit();
	}
	function doInsert() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要报运的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/export/doInsert.action";
		document.forms.icform.submit();
	}
</script>
</head>

<body>
	<form name="icform" method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="view"><a href="#"
								onclick="javascript:view();this.blur();">查看</a></li>
							<li id="print"><a href="#"
								onclick="javascript:doPrint();this.blur();">打印</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doInsert();this.blur();">报运</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">已上报购销合同列表</div>
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
								<td class="tableHeader">客户名称</td>
								<td class="tableHeader">合同号</td>
								<td class="tableHeader">货物数/附件数</td>
								<td class="tableHeader">制单人</td>
								<td class="tableHeader">审单人</td>
								<td class="tableHeader">验货员</td>
								<td class="tableHeader">签单日期</td>
								<td class="tableHeader">交货期限</td>
								<td class="tableHeader">船期</td>
								<td class="tableHeader">总金额</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${contractList}" var="contract"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${contract.id}" /></td>
									<td>${status.index+1}</td>
									<td>${contract.customerName}</td>
									<td><a
										href="${ctx}/freight/contract/toView.action?id=${contract.id}">${contract.contractNo}</a></td>
									<td align="center">${contract.cpnum}/${contract.extnum}</td>
									<td>${contract.inputBy}</td>
									<td>${contract.checkBy}</td>
									<td>${contract.inspector}</td>
									<td><fmt:formatDate value="${contract.signingDate}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${contract.deliveryPeriod}"
											pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${contract.shipingDate}"
											pattern="yyyy-MM-dd" /></td>
									<td>${contract.totalAmount}</td>
									<td><c:if test="${contract.state==1}">
											<font color="green">已上报,待报运</font>
										</c:if></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>

