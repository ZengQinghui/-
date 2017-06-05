<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="back"><a href="${ctx}/home/toShowHisMsg.action">返回</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="textbox" id="centerTextbox">

			<div class="textbox-header">
				<div class="textbox-inner-header">
					<div class="textbox-title">查看已读留言信息</div>
				</div>
			</div>
			<div>
				<div>
					<table class="commonTable" cellspacing="1">
						<tr>
							<td class="columnTitle_mustbe">留言人：</td>
							<td class="tableContent">${message.messageAuthor }</td>
							<td class="columnTitle_mustbe">留言时间：</td>
							<td class="tableContent"><fmt:formatDate value="${message.messageDate }" pattern="yyyy-MM-dd HH:mm" /></td>
						</tr>
						<tr>
							<td class="columnTitle_mustbe">留言内容：</td>
							<td class="tableContent">${message.messageContents }</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>