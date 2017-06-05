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
	function update() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要修改的合同!");
			return;
		}
		document.forms.icform.action = "toUpdate.action";
		document.forms.icform.submit();
	}
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一条你要删除的信息!");
			return;
		} else if (cCount == 1) {
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
					document.forms.icform.action = "doDeleteById.action";
					document.forms.icform.submit();
				}
			})
		} else if (cCount > 1) {
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
					document.forms.icform.action = "doDelete.action";
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
		document.forms.icform.action = "toView.action";
		document.forms.icform.submit();
	}
	function doSubmit() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一份要上报的合同!");
			return;
		}
		document.forms.icform.action = "doSubmit.action";
		document.forms.icform.submit();
	}
	function doCancel() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一个要取消上报的合同!");
			return;
		}
		document.forms.icform.action = "doCancel.action";
		document.forms.icform.submit();
	}
	function doPrint() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要打印的合同!");
			return;
		}
		document.forms.icform.action = "doPrint.action";
		document.forms.icform.submit();
	}
	/* function doPrintByTemplate() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			alert("请选择一份要打印的合同!");
			return;
		}
		document.forms.icform.action = "doPrintByTemplate.action";
		document.forms.icform.submit();
	} */
	function doPigeinhole() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一份要归档的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/contracthis/doPigeinhole.action";
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
							<li id="new"><a href="#"
								onclick="formSubmit('toSave.action','_self');this.blur();">新增</a></li>
							<li id="update"><a href="#"
								onclick="javascript:update();this.blur();">修改</a></li>
							<li id="delete"><a href="#"
								onclick="javascript:doDelete();this.blur();">删除</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doSubmit();this.blur();">上报</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doCancel();this.blur();">取消</a></li>
							<li id="print"><a href="#"
								onclick="javascript:doPrint();this.blur();">打印</a></li>
							<!-- <li id="print" style="white-space: nowrap;"><a href="#"
								onclick="javascript:doPrintByTemplate();this.blur();">模板打印</a></li> -->
							<li id="new"><a href="#"
								onclick="javascript:doPigeinhole();this.blur();">归档</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">购销合同列表</div>
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
								<td class="tableHeader">操作</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${allContracts}" var="contract"
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
									<td><c:if test="${contract.state==1}">
											<font color="green">已上报</font>
										</c:if> <c:if test="${contract.state==2}">
											<font color="blue">已报运</font>
										</c:if>
										<c:if test="${contract.state==3}">
											<font color="red">已完成全部流程,可归档</font>
										</c:if>
										<c:if test="${contract.state==0}">草稿</c:if></td>
									<td><a
										href="toProductSave.action?contractId=${contract.id}"
										title="新增货物信息">[货物]</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${pageLinks}
				</div>
			</div>
		</div>
	</form>
</body>
</html>