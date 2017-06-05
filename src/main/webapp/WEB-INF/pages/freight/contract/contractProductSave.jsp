<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
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
		<input type="hidden" name="contractId" value="${contractId}" />

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('doProductInsert.action','_self');">确定</a></li>
							<li id="back"><a href="list.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">新增货物信息</div>
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
										<option value="${f.id}">${f.factoryName}</option>
									</c:forEach>
							</select> <input type="hidden" id="factoryName" name="factoryName"
								value="" /></td>
							<td class="columnTitle_mustbe">货号：</td>
							<td class="tableContent"><input type="text" name="productNo" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">货物照片：</td>
							<td class="tableContent"><input type="file" name="file" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">数量：</td>
							<td class="tableContent"><input id="quantity" type="text"
								name="quantity" /></td>
							<td class="columnTitle_mustbe">包装单位：</td>
							<td class="tableContent"><input type="text"
								name="packingUnit" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">装率：</td>
							<td class="tableContent"><input id="loadingRate" type="text"
								name="loadingRate" /></td>
							<td class="columnTitle_mustbe">箱数：</td>
							<td class="tableContent"><input id="boxNum" type="text"
								name="boxNum" onclick="amount()" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">单价：</td>
							<td class="tableContent"><input type="text" name="price" /></td>
							<td class="columnTitle_mustbe">排序号：</td>
							<td class="tableContent"><input type="text" name="orderNo" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">货物描述：</td>
							<td class="tableContent"><textarea name="productDesc"
									style="height: 120px;"></textarea></td>
						</tr>
					</table>
				</div>
			</div>

			<div class="textbox" id="centerTextbox">
				<div class="textbox-header">
					<div class="textbox-inner-header">
						<div class="textbox-title">货物列表</div>
					</div>
				</div>

				<div>

					<div class="eXtremeTable">
						<table id="ec_table" class="tableRegion" width="98%">
							<thead>
								<tr>
									<td class="tableHeader"><input type="checkbox"
										name="selid" onclick="checkAll('id',this)" /></td>
									<td class="tableHeader">序号</td>
									<td class="tableHeader">厂家名称</td>
									<td class="tableHeader">货号</td>
									<td class="tableHeader">数量</td>
									<td class="tableHeader">包装单位</td>
									<td class="tableHeader">装率</td>
									<td class="tableHeader">箱数</td>
									<td class="tableHeader">单价</td>
									<td class="tableHeader">总金额</td>
									<td class="tableHeader">操作</td>
								</tr>
							</thead>
							<tbody class="tableBody">
								<c:forEach items="${allContractProducts}" var="contractProduct"
									varStatus="status">
									<tr class="odd" onmouseover="this.className='highlight'"
										onmouseout="this.className='odd'">
										<td><input type="checkbox" name="id"
											value="${contractProduct.id}" /></td>
										<td>${status.index+1}</td>
										<td>${contractProduct.factoryName}</td>
										<td>${contractProduct.productNo}</td>
										<td>${contractProduct.quantity}</td>
										<td>${contractProduct.packingUnit}</td>
										<td>${contractProduct.loadingRate}</td>
										<td>${contractProduct.boxNum}</td>
										<td>${contractProduct.price}</td>
										<td>${contractProduct.amount}</td>
										<td>
										<a href="toProductUpdate.action?id=${contractProduct.id}">[修改]</a> 
										<a href="doProductDelete.action?id=${contractProduct.id}&contractId=${contractProduct.contractId}">[删除]</a>
										<a href="toEnclosureSave.action?contractProductId=${contractProduct.id}" title="新增附件信息">[附件]</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>