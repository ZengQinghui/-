<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" rev="stylesheet" type="text/css"
	href="${ctx}/components/alert/sweetalert.css" media="all" />
<script type="text/javascript"
	src="${ctx}/components/alert/sweetalert.min.js"></script>
<link rel="stylesheet" rev="stylesheet" type="text/css"
	href="${ctx}/skin/default/css/default.css" media="all" />
<script type="text/javascript"
	src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script language="javascript" src="${ctx}/js/common.js"></script>
<style>
.curbody {
	CURSOR: url(${ctx}/images/olmsg/shubiao.ani);
	background: url(${ctx}/images/olmsg/pic738x571.jpg);
}

.msgcontent {
	width: 218px;
	overflow: hidden;
	word-break: break-all;
	padding: 10px;
	font-size: 14px;
	color: #339966;
	font-family: Tahoma;
	line-height: 180%;
}

.msgcontent p {
	text-indent: 0px;
}

.msgcontent ul {
	margin: 0px;
}

.msgbackcontent {
	width: 218px;
	overflow: hidden;
	word-break: break-all;
	padding: 10px;
	font-size: 14px;
	color: #339966;
	font-family: Tahoma;
	line-height: 180%;
}

.msgbackcontent p {
	text-indent: 0px;
}

.msgbackcontent ul {
	margin: 0 px;
}

li {
	text-indent: 0px;
	margin: 0px;
	list-style: default;
}
</style>

<script language="javascript">
	//-- 控制层移动start of script -->
	var Obj = '';
	var index = 10000;//z-index;
	var color = '';
	var str = '';
	document.onmouseup = MUp
	document.onmousemove = MMove

	function MMove() {
		if (Obj != '') {
			document.all(Obj).style.left = event.x - pX;
			document.all(Obj).style.top = event.y - pY;
		}
	}

	function MUp() {
		if (Obj != '') {
			document.all(Obj).releaseCapture();
			Obj = '';
		}
		var srcEle = event.srcElement;

		var children = srcEle.children;
		if (children.length > 0) {
			children[1].value = "1"; //isChange
			children[2].value = event.x - pX;
			children[3].value = event.y - pY;
		}
	}

	function MDown(objtd, id) {
		Obj = id
		document.all(Obj).setCapture()
		pX = event.x - document.all(Obj).style.pixelLeft;
		pY = event.y - document.all(Obj).style.pixelTop;
	}

	//-- 控制层移动end of script -->
	//获得焦点;
	function getFocus(obj) {
		if (obj.style.zIndex != index) {
			index = index + 2;
			var idx = index;
			obj.style.zIndex = idx;
			//obj.nextSibling.style.zIndex=idx-1;
		}
	}

	//针对未已阅的、未回复的、工作任务
	function msgrevoke(id) {
		if (confirm("是否确定要撤销此条信息?")) {
			//_Submit("/home/olmsgRevokeAction.do?flag=revoke&id="+id,null,"撤销");
		}
	}

	//需回复的留言
	function msgback(id) {
		//_Submit("/home/olmsgUpdateAction.do?flag=back&id="+id,null,"回复");
	}

	function msgdel(id) {
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
				window.location="${ctx}/home/doDelete.action?id=" + id;
			}
		})
	}

	function msgstate(id) {
		swal({
			title : "是否确定已阅此条信息?",
			type : "warning",
			showCancelButton : true,
			confirmButtonColor : "#DD6B55",
			confirmButtonText : "确定",
			cancelButtonText : "取消",
			closeOnConfirm : false
		}, function(isConfirm) {
			if (isConfirm) {
				window.location="${ctx}/home/changeState.action?id=" + id;
			}
		})
	}

	function changRowColor(obj) {
		//obj.removeAttribute("className");
		//alert(obj.className);
		//obj.setAttribute("bgcolor","#FFECB0");
		//obj.sytle.backgroundColor = "#FFECB0";
	}

	function removeOverRowColor(obj) {
		//alert(obj.getAttribute("style"));
	}

	function killErrors() {
		return true;
	}
	
	window.onerror = killErrors;
</script>

</head>


<body class="curbody">

	<form name="form2">
		<!-- 工具栏部分 ToolBar -->
		<div id="menubar">
			<div id="middleMenubar">
				<div id="innerMenubar">
					<div id="navMenubar">
						<ul>
							<li id="new"><a href="${ctx}/home/toSave.action">新建</a></li>
							<li id="stat"><a href="${ctx}/home/toShowHisMsg.action">历史</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div id='${messageList[0].id }'
			style='position: absolute; left: 122px; top: 97px; z-index: 1001; height: 164px; background: none;'
			onmousedown='getFocus(this)'>
			<table border=0 cellspacing="0" cellpadding="0" width="220">
				<tr>
					<td style='cursor: move;'
						onmousedown="MDown(this,'${messageList[0].id }')"
						background="${ctx}/images/olmsg/C0FFE51.gif" height="45"><input
						type="hidden" name="id" class="input"
						value="${messageList[0].id }" /> <input type="hidden"
						name="isChange" class="input" value="0" /> <input type="hidden"
						name="posX" class="input" value="122" /> <input type="hidden"
						name="posY" class="input" value="97" /> &nbsp;</td>
				</tr>
				<tr>
					<td style='cursor: move; white-space: nowrap;' width='100%'
						onmousedown="MDown('${messageList[0].id }')"
						background="${ctx}/images/olmsg/C0FFE52.gif">
						<div
							style="float: left; width: 130px; padding-left: 7px; font-family: Tahoma; color: gray; font-style: oblique;" id="date1">
							<fmt:formatDate value="${messageList[0].messageDate }"
								pattern="yyyy-MM-dd HH:mm" />
						</div>
						<div
							style="float: right; width: 80px; text-align: right; padding-right: 7px;">
							<a style='cursor: pointer;' title="已读"
								onclick="msgstate('${messageList[0].id }')"><img
								src="${ctx}/images/olmsg/doc_ok.gif" /></a> <a
								style='cursor: pointer;' title="删除"
								onclick="msgdel('${messageList[0].id }')"><img
								src="${ctx}/images/olmsg/doc_del.gif" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td background="${ctx}/images/olmsg/C0FFE52.gif">
						<div class="msgcontent">${messageList[0].messageContents }</div>
					</td>
				</tr>
				<tr>
					<td id="tagBPic" background="${ctx}/images/olmsg/C0FFE53.gif"
						height="63">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50" align="center"><img border="0"
									src="${ctx}/images/olmsg/2.gif" /></td>
								<td style="text-align: right; padding-right: 8px;" nowrap>
									留言人：${messageList[0].messageAuthor }</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>

		<div id='${messageList[1].id }'
			style='position: absolute; left: 442px; top: 91px; z-index: 1002; height: 164px; background: none;'
			onmousedown='getFocus(this)'>
			<table border=0 cellspacing="0" cellpadding="0" width="220">
				<tr>
					<td style='cursor: move;'
						onmousedown="MDown(this,'${messageList[1].id }')"
						background="${ctx}/images/olmsg/FFE7E81.gif" height="45"><input
						type="hidden" name="id" class="input"
						value="${messageList[1].id }" /> <input type="hidden"
						name="isChange" class="input" value="0" /> <input type="hidden"
						name="posX" class="input" value="442" /> <input type="hidden"
						name="posY" class="input" value="91" /> &nbsp;</td>
				</tr>
				<tr>
					<td style='cursor: move; white-space: nowrap;' width='100%'
						onmousedown="MDown('${messageList[1].id }')"
						background="${ctx}/images/olmsg/FFE7E82.gif">
						<div
							style="float: left; width: 130px; padding-left: 7px; font-family: Tahoma; color: gray; font-style: oblique;" id="date2">
							<fmt:formatDate value="${messageList[1].messageDate }"
								pattern="yyyy-MM-dd HH:mm" />
						</div>
						<div
							style="float: right; width: 80px; text-align: right; padding-right: 7px;">
							<a style='cursor: pointer;' title="已读"
								onclick="msgstate('${messageList[1].id }')"><img
								src="${ctx}/images/olmsg/doc_ok.gif" /></a> <a
								style='cursor: pointer;' title="删除"
								onclick="msgdel('${messageList[1].id }')"><img
								src="${ctx}/images/olmsg/doc_del.gif" /></a>
						</div>
					</td>
				</tr>
				<tr>
					<td background="${ctx}/images/olmsg/FFE7E82.gif">
						<div class="msgcontent">${messageList[1].messageContents }</div>
					</td>
				</tr>
				<tr>
					<td id="tagBPic" background="${ctx}/images/olmsg/FFE7E83.gif"
						height="63">
						<table border="0" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td width="50" align="center"><img border="0"
									src="${ctx}/images/olmsg/0.gif" /></td>
								<td style="text-align: right; padding-right: 8px;" nowrap>
									留言人：${messageList[1].messageAuthor }</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/JavaScript">
		if(document.getElementById("date1").innerText==''){
			document.getElementById("date1").parentNode.parentNode.parentNode.parentNode.parentNode.style.display="none";
			
		}
		
		if(document.getElementById("date2").innerText==''){
			document.getElementById("date2").parentNode.parentNode.parentNode.parentNode.parentNode.style.display="none";
		}
		
		if ('${message}' != '') {
			swal('${message}')
		}
	</script>
</body>
</html>

