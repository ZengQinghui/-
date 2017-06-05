<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" rev="stylesheet" type="text/css"
	href="${ctx}/components/alert/sweetalert.css" media="all" />
<script type="text/javascript"
	src="${ctx}/components/alert/sweetalert.min.js"></script>
<script type="text/javascript"
	src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script type="text/javascript">
	function update() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一个要修改的用户!");
			return;
		}
		document.forms.icform.action = "toUpdate.action";
		document.forms.icform.submit();
	}
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一条你要删除的信息!");
			return;
		} else if (cCount >= 1) {
			swal({
				title : "确定要删除吗?",
				text : "删除后将无法恢复,请谨慎操作!",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "是的, 删除!",
				cancelButtonText : "取消",
				closeOnConfirm : false
			}, function(isConfirm) {
				if (isConfirm) {
					document.forms.icform.action = "doDelete.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function view() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一个要查看的用户!");
			return;
		}
		document.forms.icform.action = "toView.action";
		document.forms.icform.submit();
	}
	function doDisable() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一个你要禁用的用户!");
			return;
		} else if (cCount >= 1) {
			document.forms.icform.action = "doDisable.action";
			document.forms.icform.submit();
		}
	}
	function doCancel() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一个你要取消禁用的用户!");
			return;
		} else if (cCount >= 1) {
			document.forms.icform.action = "doCancel.action";
			document.forms.icform.submit();
		}
	}
</script>
</head>

<body>
	<form name="icform" method="post">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="view"><a href="#"
								onclick="javascript:view();this.blur();">查看</a></li>
							<li id="new"><a href="#"
								onclick="formSubmit('toSave.action','_self');this.blur();">新增</a></li>
							<li id="update"><a href="#"
								onclick="javascript:update();this.blur();">修改</a></li>
							<li id="delete"><a href="#"
								onclick="javascript:doDelete();this.blur();">删除</a></li>
							<li id="delete"><a href="#"
								onclick="javascript:doDisable();this.blur();">禁用</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doCancel();this.blur();">取消</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">用户列表</div>
				</div>
			</div>

			<div>

				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('id',this)" /></td>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">姓名</td>
								<td class="tableHeader">登录名</td>
								<td class="tableHeader">所属部门</td>
								<td class="tableHeader">职务</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${userList}" var="user" varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${user.id}"
										class="checked" /></td>
									<td>${status.index+1}</td>
									<td><a href="toView.action?id=${user.id}">${user.userName}</a></td>
									<td>${user.loginName}</td>
									<td>${user.deptName}</td>
									<td>${user.duty}</td>
									<td class="state"><c:if test="${user.state==1}">
											<img title='在线' src='${ctx}/images/state/1.gif' />
										</c:if> <c:if test="${user.state==0}">
											<img title='离线' src='${ctx}/images/state/0.gif' />
										</c:if> <c:if test="${user.state==2}">
											<font color="red">已禁用</font>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${pageLinks}
				</div>
			</div>
		</div>
	</form>
	<script type="text/javaScript">
		if ('${message}' != '') {
			swal('${message}')
		}
	</script>
</body>
</html>

