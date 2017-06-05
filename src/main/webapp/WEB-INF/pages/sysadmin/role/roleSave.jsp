<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('doInsert.action','_self');">确定</a></li>
							<li id="back"><a href="list.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">新增角色信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">角色名称：</td>
							<td class="tableContent"><input type="text" name="roleName" /></td>
							<td class="columnTitle_mustbe">角色描述：</td>
							<td class="tableContent"><input type="text" name="roleDesc" /></td>

						</tr>
						<tr>
							<td class="columnTitle_mustbe">分配用户：</td>
							<td class="tableContentAuto"><c:forEach items="${userList}"
									var="user">
									<input type="checkbox" name="userId" value="${user.id }"
										class="input" style="width: 20px;" />${user.duty }/${user.userName }
							</c:forEach></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>