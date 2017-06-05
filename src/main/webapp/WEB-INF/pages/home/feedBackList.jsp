<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" rev="stylesheet" type="text/css"
	href="${ctx}/components/alert/sweetalert.css" media="all" />
<script type="text/javascript"
	src="${ctx}/components/alert/sweetalert.min.js"></script>
<script type="text/javascript">
	function doDelete() {
		cCount = getCheckedCount("feedBackId");
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
					document.forms.icform.action = "${ctx}/home/doDeleteFB.action";
					document.forms.icform.submit();
				}
			})
		}
	}
	function view() {
		cCount = getCheckedCount("feedBackId");
		if (cCount == 0) {
			swal("请选择一条要查看的反馈意见!");
			return;
		}
		document.forms.icform.action = "${ctx}/home/toViewFB.action";
		document.forms.icform.submit();
	}
	function reply() {
		cCount = getCheckedCount("feedBackId");
		if (cCount == 0) {
			swal("请选择一条要回复的反馈意见!");
			return;
		}
		document.forms.icform.action = "${ctx}/home/toReply.action";
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
								onclick="javascript:reply();this.blur();">回复</a></li>
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
					<div class="textbox-title">反馈意见列表</div>
				</div>
			</div>

			<div>

				<div class="eXtremeTable">
					<table id="ec_table" class="tableRegion" width="98%">
						<thead>
							<tr>
								<td class="tableHeader"><input type="checkbox" name="selid"
									onclick="checkAll('feedBackId',this)" /></td>
								<td class="tableHeader">序号</td>
								<td class="tableHeader">提出人</td>
								<td class="tableHeader">提出时间</td>
								<td class="tableHeader">联系电话</td>
								<td class="tableHeader">状态</td>
							</tr>
						</thead>
						<tbody class="tableBody">

							<c:forEach items="${feedBackList}" var="feedBack"
								varStatus="status">
								<tr class="odd" onmouseover="this.className='highlight'"
									onmouseout="this.className='odd'">
									<td><input type="checkbox" name="feedBackId"
										value="${feedBack.id}" /></td>
									<td>${status.index+1}</td>
									<td><a href="${ctx}/home/toViewFB.action?feedBackId=${feedBack.id}">${feedBack.inputBy}</a></td>
									<td><fmt:formatDate value="${feedBack.inputTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td>${feedBack.telephone}</td>
									<td><c:if test="${feedBack.state==1}">
											<font color="red">已回复</font>
										</c:if> <c:if test="${feedBack.state==0}">
											<font color="blue">未回复</font>
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