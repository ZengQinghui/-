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
			swal("请选择一个要修改的角色!");
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
			swal("请选择一个要查看的角色!");
			return;
		}
		document.forms.icform.action = "toView.action";
		document.forms.icform.submit();
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
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">角色列表</div>
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
								<td class="tableHeader">角色名称</td>
								<td class="tableHeader">角色描述</td>
							</tr>
						</thead>
						<tbody class="tableBody">
							<c:forEach items="${roleList}" var="role"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${role.id}" /></td>
									<td>${status.index+1}</td>
									<td><a href="toView.action?id=${role.id}">${role.roleName}</a></td>
									<td>${role.roleDesc}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					${pageLinks}
				</div>
			</div>
		</div>
	</form>
</body>
</html>

