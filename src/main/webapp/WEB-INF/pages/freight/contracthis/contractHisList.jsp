<%@ page language="java" pageEncoding="UTF-8"%>
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
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一条你要删除的信息!");
			return;
		} else if (cCount >= 1) {
			swal({
				title : "确定要删除吗?",
				text : "删除后将无法恢复,请谨慎操作!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "是的, 删除!",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm) {
					document.forms.icform.action = "${ctx}/freight/contracthis/doDelete.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function view() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要查看的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contracthis/toView.action";
		document.forms.icform.submit();
	}
	function doPrint() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要打印的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contracthis/doPrint.action";
		document.forms.icform.submit();
	}
	function doPigeouthole() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一份要取消归档的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contracthis/doPigeouthole.action";
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
							<li id="delete"><a href="#"
								onclick="javascript:doDelete();this.blur();">删除</a></li>
							<li id="print"><a href="#"
								onclick="javascript:doPrint();this.blur();">打印</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doPigeouthole();this.blur();" title="取消归档">取消</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">历史购销合同列表</div>
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
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${contractHisList}" var="contract"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${contract.id}" /></td>
									<td>${status.index+1}</td>
									<td>${contract.customerName}</td>
									<td><a href="toView.action?id=${contract.id}">${contract.contractNo}</a></td>
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

