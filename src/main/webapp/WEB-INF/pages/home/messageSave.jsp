<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//设置冗余的生产厂家名称
	function setUserName(val) {
		var ele = document.getElementById("userName");
		ele.value = val;
	}
</script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('${ctx}/home/doInsert.action','_self');">确定</a></li>
							<li id="back"><a href="${ctx}/main.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">新增留言信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">留言对象：</td>
							<td class="tableContent"><select name="userId"
								onchange="setUserName(this.options[this.selectedIndex].text);">
									<option value="">--请选择--</option>
									<c:forEach items="${userList}" var="u">
										<option value="${u.id}">${u.userName}</option>
									</c:forEach>
							</select> <input type="hidden" id="uName" name="uName" value="" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">留言内容：</td>
							<td class="tableContent"><textarea name="messageContents"
									style="height: 120px;"></textarea></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>