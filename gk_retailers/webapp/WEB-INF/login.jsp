<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>学子商城登陆页面</title>
    <link href="../css/header.css" rel="stylesheet"/>
    <link href="../css/footer.css" rel="stylesheet"/>
    <link href="../css/animate.css" rel="stylesheet"/>
    <link href="../css/login.css" rel="stylesheet"/>
    <link href="../css/footTxt.css" rel="Stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
    <div class="top">
        <img src="../images/header/logo.png" alt=""/>
        <span>欢迎登录</span>
    </div>
</header>
<div id="container">
    <div id="cover" class="rt">
        <form id="login-form" method="post" name="form1">
            <div class="txt">
                <p>
					登录学子商城<span><a href="${basePath}/user/registerHtml.do">新用户注册</a></span>
                </p>
                <div class="text">
                    <input type="text" placeholder="请输入您的用户名" name="lname" id="username" required>
                    <span><img src="${basePath}/images/login/yhm.png"></span>
                </div>
                <div class="text">
                    <input type="password" id="password" placeholder="请输入您的密码" name="lwd" required minlength="6" maxlength="15">
                    <span><img src="${basePath}/images/login/mm.png"></span>
                </div>
                <div class="text" style="position: relative">
                    <input type="text" id="code" name="code" placeholder="请输入验证码" required>
                    <span><img id="codeImg" style="position: absolute; right: -5px; top: 20px; height: 30px; width: 65px;" src="${basePath}/user/code.do" onclick="this.src='${basePath}/user/code.do?id=' + new Date().getTime()"></span>
                </div>
                <div class="chose">
                    <input type="checkbox" class="checkbox" id="ck_rmbUser" value="0">自动登录
                    <span>忘记密码？</span>
                </div>
                <input class="button_login" type="button" value="登录" id="bt-login"/>
            </div>
        </form>
    </div>
</div>
<!--错误提示-->
<div id="showResult"></div>
<!-- 品质保障，私人定制等-->
<div id="foot_box">
    <div class="icon1 lf">
        <img src="../images/footer/icon1.png" alt=""/>

        <h3>品质保障</h3>
    </div>
    <div class="icon2 lf">
        <img src="../images/footer/icon2.png" alt=""/>

        <h3>私人定制</h3>
    </div>
    <div class="icon3 lf">
        <img src="../images/footer/icon3.png" alt=""/>

        <h3>学员特供</h3>
    </div>
    <div class="icon4 lf">
        <img src="../images/footer/icon4.png" alt=""/>

        <h3>专属特权</h3>
    </div>
</div>
<!-- 页面底部-->
<%@ include file="footer.jsp" %>
<script src="../js/jquery-3.1.1.min.js"></script>
<script src="../jquery/jquery.cookie.js"></script>
<script>
    $("#username").blur(function(){
        var data = $("#username").val();
        if (data == null || data == "") {
            $("#showResult").text("用户名不能为空！");
            $("#showResult").css("color","red");
            return false;
        }
        
    });
    
    $('#bt-login').click(function(){
    	//读取用户的输入——表单序列化
        var inputData = $('#login-form').serialize();
        var url = "${basePath}/user/toLogin.do";
        $.post(url, inputData, function(result){
        	if(result.state == 1){
        		Save();//保存用户名和密码
        		//进入首页
        		location.href = "${basePath}/user/toIndexHtml.do";
        	}else{
        		$("#showResult").html(result.message).attr("style", "color: red");
        	}
        	
        	$("#codeImg").attr("src", "${basePath}/user/code.do?id=" + new Date().getTime());
        });
	});
</script>
<script type="text/javascript">

	$(document).ready(function () {
	    if ($.cookie("rmbUser") == "true") {
	        $("#ck_rmbUser").attr("checked", true);
	        $("#username").val($.cookie("username"));
	        $("#password").val($.cookie("password"));
	    }
	});
	
	//记住用户名密码
	function Save() {
	    if ($("#ck_rmbUser").prop("checked")) {
	        var str_username = $("#username").val();
	        console.log(str_username);
	        var str_password = $("#password").val();
	        $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
	        $.cookie("username", str_username, { expires: 7 });
	        $.cookie("password", str_password, { expires: 7 });
	    }else {
	        $.cookie("rmbUser", "false", { expire: -1 });
	        $.cookie("username", "", { expires: -1 });
	        $.cookie("password", "", { expires: -1 });
	    }
	};
</script>
<script src="../js/footTxt.js"></script>
</body>
</html>