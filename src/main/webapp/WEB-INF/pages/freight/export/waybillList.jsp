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
<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script type="text/javascript">
	function view() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要查看的合同!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/export/toView.action";
		document.forms.icform.submit();
	}
	function toUpdate() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一份要修改的合同!");
			return;
		}
		document.forms.icform.action = "toUpdate.action";
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
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择至少一份要删除的报运单!");
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
					document.forms.icform.action = "${ctx}/freight/export/doDelete.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function doSubmit() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择至少一份要上报的报运单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/export/doSubmit.action";
		document.forms.icform.submit();
	}
	function doCancel() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择至少一份要取消上报的报运单!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/export/doCancel.action";
		document.forms.icform.submit();
	}
	function toSavePackingList() {
		var state;
		$('.checked').each(function(index,item){
			if($(item).attr('checked')){
				 state = $(item).parent().parent().find('.state').text();
			}
		})
	
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择至少一份要装箱的报运单!");
			return;
		} else if(state.indexOf('草稿')!=-1){
			swal("报运单还没有上报,请先上报!!!");
			return;
		}
		document.forms.icform.action = "${ctx}/freight/packinglist/toSavePackingList.action";
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
								onclick="javascript:doSubmit();this.blur();">上报</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doCancel();this.blur();">取消</a></li>
							<li id="new"><a href="#"
								onclick="javascript:toSavePackingList();this.blur();">装箱</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">出口报运列表</div>
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
								<td class="tableHeader">合同或确认书号</td>
								<td class="tableHeader">信用证号</td>
								<td class="tableHeader">货物数/附件数</td>
								<td class="tableHeader">收货人及地址</td>
								<td class="tableHeader">装运港</td>
								<td class="tableHeader">目的港</td>
								<td class="tableHeader">运输方式</td>
								<td class="tableHeader">价格条件</td>
								<td class="tableHeader">制单日期</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${waybillList}" var="waybill"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td ><input type="checkbox" name="id" value="${waybill.id}" class='checked'/></td>
									<td>${status.index+1}</td>
									<td><a
										href="${ctx}/freight/export/toView.action?id=${waybill.id}">${waybill.customerContract}</a></td>
									<td>${waybill.lcno}</td>
									<td align="center">${waybill.epnum}/${waybill.extnum}</td>
									<td>${waybill.consignee}</td>
									<td>${waybill.shipmentPort}</td>
									<td>${waybill.destinationPort}</td>
									<td>${waybill.transportMode}</td>
									<td>${waybill.priceCondition}</td>
									<td><fmt:formatDate value="${waybill.inputDate}"
											pattern="yyyy-MM-dd" /></td>
									<td class='state'>
										<c:if test="${waybill.state==0}">草稿</c:if>
										<c:if test="${waybill.state==1}"><font color="green">已上报,待装箱</font></c:if> 
										<c:if test="${waybill.state==2}"><font color="orange">已装箱,待委托</font></c:if>
										<c:if test="${waybill.state==3}"><font color="blue">已委托,待发票通知</font></c:if>
										<c:if test="${waybill.state==4}"><font color="#FF3030">已发票通知</font></c:if>
										<c:if test="${waybill.state==5}"><font color="#FF0000">已报向财务</font></c:if>
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

