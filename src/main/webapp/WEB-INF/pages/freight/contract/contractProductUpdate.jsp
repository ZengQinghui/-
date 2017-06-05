<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript">
	//设置冗余的生产厂家名称
	function setFactoryName(val) {
		var ele = document.getElementById("factoryName");
		ele.value = val;
	}
	function amount() {
		var quantity = document.getElementById("quantity").value;
		var loadingRate = document.getElementById("loadingRate").value;
		var boxNum = document.getElementById("boxNum");
		if (quantity != '' && loadingRate != '') {
			var rate = (loadingRate.split("/"))[1];
			if ((parseInt(quantity) % parseInt(rate)) == 0) {
				boxNum.value = parseInt(quantity) / parseInt(rate);
			} else {
				boxNum.value = parseInt(parseInt(quantity) / parseInt(rate)) + 1;
			}
		}
	}
</script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${contractProduct.id}" /> 
		<input type="hidden" name="contractId" value="${contractProduct.contractId}" />

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('doProductUpdate.action','_self');">确定</a></li>
							<li id="back"><a href="toProductSave.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">修改货物信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">厂家名称：</td>
							<td class="tableContent"><select name="factoryId"
								onchange="setFactoryName(this.options[this.selectedIndex].text);">
									<option value="">--请选择--</option>
									<c:forEach items="${factoryList}" var="f">
										<option value="${f.id}"
											<c:if test="${contractProduct.factoryId==f.id}">selected</c:if>>${f.factoryName}</option>
									</c:forEach>
							</select> <input type="hidden" id="factoryName" name="factoryName"
								value="${contractProduct.factoryName}" /></td>
							<td class="columnTitle_mustbe">货号：</td>
							<td class="tableContent"><input type="text" name="productNo"
								value="${contractProduct.productNo}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">货物照片：<img src="${ctx}/upload files/product images/${contractProduct.productImage }" width="44" height="44" /></td>
							<td class="tableContent"><input type="file" name="file"/></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">数量：</td>
							<td class="tableContent"><input id="quantity" type="text" name="quantity"
								value="${contractProduct.quantity}" /></td>
							<td class="columnTitle_mustbe">包装单位：</td>
							<td class="tableContent"><input type="text"
								name="packingUnit" value="${contractProduct.packingUnit}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">装率：</td>
							<td class="tableContent"><input id="loadingRate" type="text"
								name="loadingRate" value="${contractProduct.loadingRate}" /></td>
							<td class="columnTitle_mustbe">箱数：</td>
							<td class="tableContent"><input id="boxNum" type="text" name="boxNum"
								value="${contractProduct.boxNum}" onclick="amount()" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">单价：</td>
							<td class="tableContent"><input type="text" name="price"
								value="${contractProduct.price}" /></td>
							<td class="columnTitle_mustbe">排序号：</td>
							<td class="tableContent"><input type="text" name="orderNo"
								value="${contractProduct.orderNo}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">货物描述：</td>
							<td class="tableContent"><textarea name="productDesc"
									style="height: 120px;">${contractProduct.productDesc}</textarea></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>