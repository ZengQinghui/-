<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="../../base.jsp"%>
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
					<div class="textbox-title">新增部门信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">部门编号：</td>
							<td class="tableContent"><input type="text"
								name="deptNo" /></td>
							<td class="columnTitle_mustbe">部门名称：</td>
							<td class="tableContent"><input type="text"
								name="deptName" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">上级部门：</td>
							<td class="tableContent"><input type="text" name="parentDept" /></td>
							<td class="columnTitle_mustbe">部门描述：</td>
							<td class="tableContent"><input type="text" name="deptDesc" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>