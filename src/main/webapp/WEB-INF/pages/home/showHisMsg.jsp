<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="./../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	function doDelete() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一条你要删除的留言!");
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
					document.forms.icform.action = "${ctx}/home/doDeleteHisMsg.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function view() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请选择一条你要查看的留言!");
			return;
		}
		document.forms.icform.action = "${ctx}/home/toView.action";
		document.forms.icform.submit();
	}
	function doStart() {
		cCount = getCheckedCount("id");
		if (cCount == 0) {
			swal("请至少选择一条要标为未读的留言!");
			return;
		}
		document.forms.icform.action = "${ctx}/home/updateState.action";
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
							<li id="delete"><a href="#"
								onclick="javascript:doDelete();this.blur();">删除</a></li>
							<li id="new"><a href="#"
								onclick="javascript:doStart();this.blur();">标为未读</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">
			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">已读留言列表</div>
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
								<td class="tableHeader">留言人</td>
								<td class="tableHeader">留言时间</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${hisMsgList}" var="msg"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="id" value="${msg.id}" /></td>
									<td>${status.index+1}</td>
									<td><a href="${ctx}/home/toView.action?id=${msg.id}">${msg.messageAuthor}</a></td>
									<td><fmt:formatDate value="${msg.messageDate }" pattern="yyyy-MM-dd HH:mm" /></td>
									<td><c:if test="${msg.state==1}">
											<a href="${ctx}/home/updateState.action?id=${msg.id}"><font color="red">已读</font></a>
										</c:if> 
									</td>
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

