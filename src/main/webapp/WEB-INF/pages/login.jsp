<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>物流商务综合管理平台</title>
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/skin/default/css/login.css" media="all" />
<link rel="stylesheet" rev="stylesheet" type="text/css" href="${ctx}/components/alert/sweetalert.css" media="all" />
<script type="text/javascript" src="${ctx}/components/jquery-ui/jquery-1.2.6.js"></script>
<script type="text/javascript" src="${ctx}/components/alert/sweetalert.min.js"></script>
<script type="text/javascript">
        $(document).ready(function(e){
            /*点击删除 清空输入框的内容*/
            $('.resetImgOut').click(function(){
                $('.zc input').val('');
            });
        });
        
        function login(){
    		var userName=$("#loginName").val();
    		var password=$("#password").val();
    		if(userName==null||userName==""){
    			swal("用户名不能为空！");
    			return;
    		}
    		if(password==null||password==""){
    			swal("密码不能为空！");
    			return;
    		}
    		document.forms.login_main.action = "${ctx}/doLogin.action";
			document.forms.login_main.submit();			
    	}
        
        function bookmarkit(sURL, sTitle)
        {
        	//捕获加入收藏过程中的异常
        	try {
        		//判断浏览器是否支持document.all
        		if(document.all){
        		//如果支持则用external方式加入收藏夹
        		window.external.bookmarkit(sURL,sTitle);
        		}else if(window.sidebar){
        			//如果支持window.sidebar，则用下列方式加入收藏夹
        			window.sidebar.addPanel(sTitle, sURL,'');
        		}
        	}catch (e) {
        		alert("加入收藏夹失败，请使用Ctrl+D快捷键进行添加操作!");
        	}
        }
</script>
</head>
<body>
<form id="login_main" method="post">
<div id="warpbox">
	<div class="main">
		 <div class="zck">
		  <div class="zc">
				<div class="zc_line">用户名：
				<input type="text" value="" name="userName" id="loginName"
				 onFocus="this.select();"
				 autocomplete="off" title="请您输入用户名"/><div id="ts" style="z-index:1;"></div></div>
			    <div class="zc_line">密　码：
				<input type="password" value="" name="password" id="password"
				 onfocus="$('#ts').css('display','none');this.select();"
				 onKeyDown="javascript:if(event.keyCode==13){ submitFind(); }"
				 title="请您输入密码"/></div>
		  </div>
			<div class="dl">
				<input  class="loginImgOut" value="" type="button" onclick="javascript:login();this.blur();"
				  onmouseover="this.className='loginImgOver'" 
				  onmouseout="this.className='loginImgOut'"
				/>
				<input class="resetImgOut" value="" type="button"   
				  onmouseover="this.className='resetImgOver'" 
				  onmouseout="this.className='resetImgOut'"
				/>
			</div>
		</div>
		<div class="bqxx" style="text-align:right;margin-top:0px;">
		<a href="#">系统帮助</a> | <a href="#" onclick="bookmarkit(window.location,document.title);">加入收藏</a>
	    </div>
		</div>
</div>
</form>
<script type="text/JavaScript">
	document.getElementById('login_main').userName.focus();
	if('${message}' != ''){
		swal('${message}')
	}
</script>

</body>
</html>


