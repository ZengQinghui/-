<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<body>
	<form method="post">

		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a href="list.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">浏览用户信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">所属部门：</td>
							<td class="tableContent">${user.deptName }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">姓名：</td>
							<td class="tableContent">${user.userName }</td>
							<td class="columnTitle_mustbe">职务：</td>
							<td class="tableContent">${user.duty }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">登录名：</td>
							<td class="tableContent">${user.loginName }</td>
							<td class="columnTitle_mustbe">密码：</td>
							<td class="tableContent">${user.password }</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">年龄：</td>
							<td class="tableContent">${user.age }</td>
							<td class="columnTitle_mustbe">性别：</td>
							<td class="tableContent">${user.gender}</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">月薪：</td>
							<td class="tableContent">${user.salary }</td>
							<td class="columnTitle_mustbe">生日：</td>
							<td class="tableContent"><fmt:formatDate
									value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">电话：</td>
							<td class="tableContent">${user.phone }</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>