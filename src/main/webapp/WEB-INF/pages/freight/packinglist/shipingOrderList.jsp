<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			swal("请选择一份要查看的委托单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/packinglist/toViewShipingOrder.action";
		document.forms.icform.submit();
	}
	function toUpdate() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要修改的委托单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/packinglist/toUpdateShipingOrder.action";
		document.forms.icform.submit();
	}
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择至少一份要删除的委托单!");
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
					document.forms.icform.action = "${ctx}/freight/packinglist/doDeleteShipingOrder.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function toInvoice() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要发票通知的委托单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/packinglist/toInvoice.action";
		document.forms.icform.submit();
	}
	function toFinance() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要生成商品出口单的委托单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/packinglist/toFinance.action";
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
							<li id="update"><a href="#"
								onclick="javascript:toUpdate();this.blur();">修改</a></li>
							<li id="delete"><a href="#"
								onclick="javascript:doDelete();this.blur();">删除</a></li>
							<li id="new"><a href="#"
								onclick="javascript:toInvoice();this.blur();">发票</a></li>
							<li id="new"><a href="#"
								onclick="javascript:toFinance();this.blur();">财务</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">装箱列表</div>
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
								<td class="tableHeader">货主</td>
								<td class="tableHeader">提单抬头</td>
								<td class="tableHeader">正本通知人</td>
								<td class="tableHeader">复核人</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${allShipingOrderLists}" var="shipingOrder"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id"
										value="${shipingOrder.id}" /></td>
									<td>${status.index+1}</td>
									<td>${shipingOrder.shipper}</td>
									<td>${shipingOrder.consignee}</td>
									<td>${shipingOrder.notifyParty}</td>
									<td>${shipingOrder.checkBy}</td>
									<td><c:if test="${shipingOrder.state==1}">
											<font color="#FF3030">已发票通知</font>
										</c:if> <c:if test="${shipingOrder.state==0}"><font color="blue">已委托,待发票通知</font></c:if>
										<c:if test="${shipingOrder.state==2}"><font color="#FF0000">已报向财务</font></c:if>
										</td>
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

