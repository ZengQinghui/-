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
</script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${enclosure.id}" /> <input
			type="hidden" name="contractProductId"
			value="${enclosure.contractProductId}" />

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('doEnclosureUpdate.action','_self');">确定</a></li>
							<li id="back"><a href="${ctx}/freight/contract/list.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">修改附件信息</div>
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
											<c:if test="${enclosure.factoryId==f.id}">selected</c:if>>${f.factoryName}</option>
									</c:forEach>
							</select> <input type="hidden" id="factoryName" name="factoryName"
								value="${enclosure.factoryName}" /></td>
							<td class="columnTitle_mustbe">货号：</td>
							<td class="tableContent"><input type="text" name="productNo"
								value="${enclosure.productNo}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">分类：</td>
							<td class="tableContent"><select name="ctype">
									<option value="">--请选择--</option>
									<c:forEach items="${allTypes}" var="type">
										<option value="${type.orderNo}"
											<c:if test="${enclosure.ctype==type.orderNo}">selected</c:if>>${type.name}</option>
									</c:forEach>
							</select></td>
							<td class="columnTitle_mustbe">货物照片：<img src="${ctx}/upload files/product images/${enclosure.productImage }" width="44" height="44" /></td>
							<td class="tableContent"><input type="file" name="file"/></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">数量：</td>
							<td class="tableContent"><input type="text" name="quantity"
								value="${enclosure.quantity}" /></td>
							<td class="columnTitle_mustbe">包装单位：</td>
							<td class="tableContent"><input type="text"
								name="packingUnit" value="${enclosure.packingUnit}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">单价：</td>
							<td class="tableContent"><input type="text" name="price"
								value="${enclosure.price}" /></td>
							<td class="columnTitle_mustbe">排序号：</td>
							<td class="tableContent"><input type="text" name="orderNo"
								value="${enclosure.orderNo}" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">货物描述：</td>
							<td class="tableContent"><textarea name="productDesc"
									style="height: 120px;">${enclosure.productDesc}</textarea></td>
							<td class="columnTitle_mustbe">要求：</td>
							<td class="tableContent"><textarea name="productRequest"
									style="height: 120px;">${enclosure.productRequest}</textarea></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>