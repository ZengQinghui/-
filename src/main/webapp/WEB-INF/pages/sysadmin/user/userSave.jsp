<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//设置冗余的生产厂家名称
	function setDeptName(val) {
		var ele = document.getElementById("deptName");
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
					<div class="textbox-title">新增用户信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">所属部门：</td>
							<td class="tableContent"><select name="deptId"
								onchange="setDeptName(this.options[this.selectedIndex].text);">
									<option value="">--请选择--</option>
									<c:forEach items="${deptList}" var="d">
										<option value="${d.id}">${d.deptName}</option>
									</c:forEach>
							</select> <input type="hidden" id="deptName" name="deptName" value="" /></td>
							
							<td class="columnTitle_mustbe">分配角色：</td>
							<td class="tableContentAuto"><c:forEach items="${roleList}"
									var="role">
									<input type="checkbox" name="roleId" value="${role.id }"
										class="input" style="width: 20px;" />${role.roleName }
							</c:forEach></td>
							
						</tr>
						<tr>
							<td class="columnTitle_mustbe">姓名：</td>
							<td class="tableContent"><input type="text" name="userName" /></td>
							<td class="columnTitle_mustbe">职务：</td>
							<td class="tableContent"><input type="text" name="duty" /></td>
							
						</tr>
						<tr>
							<td class="columnTitle_mustbe">登录名：</td>
							<td class="tableContent"><input type="text" name="loginName" /></td>
							<td class="columnTitle_mustbe">密码：</td>
							<td class="tableContent"><input type="text" name="password" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">年龄：</td>
							<td class="tableContent"><input type="text" name="age" /></td>
							<td class="columnTitle_mustbe">性别：</td>
							<td class="tableContentAuto">
								<input type="radio"
								name="gender" value="男" class="input"/>男 <input
								type="radio" name="gender" value="女" class="input"/>女
							</td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">月薪：</td>
							<td class="tableContent"><input type="text" name="salary" /></td>
							<td class="columnTitle_mustbe">生日：</td>
							<td class="tableContent"><input type="text" style="width: 90px;" name="birthday"
								onclick="WdatePicker({el:this,isShowOthers:true,dateFmt:'yyyy-MM-dd'});" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">电话：</td>
							<td class="tableContent"><input type="text" name="phone" /></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>