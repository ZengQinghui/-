<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//设置冗余的部门名称
	function setDeptName(val) {
		var ele = document.getElementById("deptName");
		ele.value = val;
	}
</script>
</head>
<body>
	<form method="post">
		<input type="hidden" name="id" value="${user.id}" />
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="save"><a href="#"
								onclick="formSubmit('${ctx}/doUpdate.action','_self');">确定</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">修改用户信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">姓名：</td>
							<td class="tableContent"><input type="text" name="userName"
								value="${user.userName }" /></td>
							<td class="columnTitle_mustbe">电话：</td>
							<td class="tableContent"><input type="text" name="phone"
								value="${user.phone }" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">登录名：</td>
							<td class="tableContent"><input type="text" name="loginName"
								value="${user.loginName }" /></td>
							<td class="columnTitle_mustbe">密码：</td>
							<td class="tableContent"><input type="text" name="password"
								value="${user.password }" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">年龄：</td>
							<td class="tableContent"><input type="text" name="age"
								value="${user.age }" /></td>
							<td class="columnTitle_mustbe">性别：</td>
							<td class="tableContentAuto"><input type="radio"
								name="gender" value="男" class="input"
								<c:if test="${user.gender=='男'}">checked</c:if> />男 <input
								type="radio" name="gender" value="女" class="input"
								<c:if test="${user.gender=='女'}">checked</c:if> />女</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">生日：</td>
							<td class="tableContent"><input type="text"
								style="width: 90px;" name="birthday"
								onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});"
								value="<fmt:formatDate
									value="${user.birthday}" pattern="yyyy-MM-dd" />" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>