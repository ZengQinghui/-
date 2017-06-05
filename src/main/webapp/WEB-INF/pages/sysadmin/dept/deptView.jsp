<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
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
					<div class="textbox-title">浏览部门信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle">部门编号：</td>
							<td class="tableContent">${dept.deptNo}</td>
							<td class="columnTitle">部门名称：</td>
							<td class="tableContent">${dept.deptName}</td>
						</tr>
						<tr>
							<td class="columnTitle">上级部门：</td>
							<td class="tableContent">${dept.parentDept}</td>
							<td class="columnTitle">部门描述：</td>
							<td class="tableContent">${dept.deptDesc}</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">员工列表</div>
				</div>
			</div>

			<div>

				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">姓名</td>
								<td class="tableHeader">登录名</td>
								<td class="tableHeader">年龄</td>
								<td class="tableHeader">性别</td>
								<td class="tableHeader">职务</td>
								<td class="tableHeader">联系电话</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach items="${dept.users}" var="u" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td>${status.index+1}</td>
									<td><a href="${ctx}/sysadmin/user/toView.action?id=${u.id}">${u.userName}</a></td>
									<td>${u.loginName}</td>
									<td>${u.age}</td>
									<td>${u.gender}</td>
									<td>${u.duty}</td>
									<td>${u.phone}</td>
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