<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/default.css" media="all" />

<script language="javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script type="text/javascript" src="${ctx}/skin/default/js/toggle.js"></script>


<script language="javascript">
	$().ready(function() {
		//$(fastMenu).hide();
		//document.getElementById('aa_3').click();	//默认打开我的留言板页面
	});

	function showMenu(who) {
		if (who == "fastMenu") {
			//$(fastMenu).show();
			$(customerMenu).hide();
		} else if (who == "customerMenu") {
			$(customerMenu).show();
			$(fastMenu).hide();
		}
	}
</script>


</head>

<body id="left_frame">
	<div class="PositionFrame_black" id="PositionFrame"></div>

	<div id="sidebar" class="sidebar">
		<div class="sidebar_t">
			<div class="sidebar_t_l"></div>
			<div class="sidebar_t_c"></div>
			<div class="sidebar_t_r"></div>
		</div>
		<div class="panel">
			<div class="panel_icon">
				<img src="${ctx}/skin/default/images/icon/user2.png" />
			</div>
			<div class="panel-header">
				<div class="panel-title">个人工作台</div>
				<div class="panel-content">
					<ul>
						<li><a href="${ctx}/main.action" target="main" id="aa_3"
							onclick="linkHighlighted(this)">我的留言板</a></li>
						<li><a href="${ctx}/toUpdate.action" target="main" id="aa_2" onclick="linkHighlighted(this)">个人信息修改</a></li>
						<li><a href="${ctx}/home/list.action" target="main" onclick="linkHighlighted(this)" id="aa_2">系统使用反馈</a></li>
					</ul>
				</div>
				<div style="margin-top: 5px;"></div>
			</div>
		</div>
		<div class="sidebar_t">
			<div class="sidebar_b_l"></div>
			<div class="sidebar_t_c"></div>
			<div class="sidebar_b_r"></div>
		</div>
	</div>
</body>
</html>