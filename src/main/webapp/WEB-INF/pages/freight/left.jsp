<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../baselist.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<script type="text/javascript"
	src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script type="text/javascript" src="${ctx}/skin/default/js/toggle.js"></script>
</head>

<body id="left_frame">
	<div class="PositionFrame_black" id="PositionFrame"></div>


	<!-- begin1  -->
	<div id="sidebar" class="sidebar">
		<div class="sidebar_t">
			<div class="sidebar_t_l"></div>
			<div class="sidebar_t_c"></div>
			<div class="sidebar_t_r"></div>
		</div>
		<div class="panel">
			<div class="panel_icon">
				<img src="${ctx}/skin/default/images/icon/document_into.png" />
			</div>
			<div class="panel-header">
				<div class="panel-title">货运管理</div>
				<div class="panel-content">
					<ul>
						<li><a href="${ctx}/freight/contract/list.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">购销合同</a></li>
						<li><a href="${ctx}/freight/outproduct/toEdit.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">出货表统计</a></li>
						<li><a href="${ctx}/freight/export/contractList.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">已上报购销合同查询</a></li>
						<li><a href="${ctx}/freight/export/list.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">出口报运</a></li>
						<li><a href="${ctx}/freight/packinglist/list.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">装箱</a></li>
						<li><a href="${ctx}/freight/packinglist/listShipingOrder.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">委托</a></li>
						<li><a href="${ctx}/ws/export/toEdit.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">出口报运跟踪</a></li>
						<li><a href="${ctx}/freight/contracthis/list.action"
							onclick="linkHighlighted(this)" target="main" id="aa_1">历史购销合同</a></li>
					</ul>
				</div>
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
