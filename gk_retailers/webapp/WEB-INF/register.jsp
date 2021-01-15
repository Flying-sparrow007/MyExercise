<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="basePath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>学子商城注册页面</title>
  <link href="${basePath}/css/header.css" rel="stylesheet"/>
  <link href="${basePath}/css/footer.css" rel="stylesheet"/>
  <link href="${basePath}/css/animate.css" rel="stylesheet"/>
  <link href="${basePath}/css/login.css" rel="stylesheet" />
  <link href="${basePath}/css/footTxt.css" rel="Stylesheet"/>
</head>
<body>
<!-- 页面顶部-->
<header id="top">
  <div class="top">
    <img src="../images/header/logo.png" alt=""/>
    <span>欢迎注册</span>
  </div>
</header>
<div class="parent">
  <!--<video src="audio/snow.mp4" width="100%" autoplay loop muted></video>-->
  <div class="container">
    <div class="panel rt">
      <form id="form-register" method="post" action="">
        <div class="txt">
          <p>新用户注册
            <span>
              <a href="${basePath}/user/toLoginHtml.do">直接登录</a>
            </span>
          </p>
        </div>
        <div class="form-group">
          <label for="uname">用户名：</label>
          <input autocomplete required maxlength="9" type="text" placeholder="请输入用户名" autofocus name="userName" id="uname" onblur="checkName()"/>
          <span id="nameSpan"></span>
        </div>
        <div class="form-group">
          <label for="upwd">登录密码：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="请输入密码" name="password" autofocus id="upwd" onblur="checkPwd()"/>
          <span id="pwdSpan"></span>
        </div>
		 <div class="form-group">
          <label for="upwdconfirm">确认密码：</label>
          <input required type="password" minlength="6" maxlength="12" placeholder="请确认密码" name="upwdconfirm" autofocus id="upwdconfirm" onblur="checkConfirmPwd()"/>
          <span id="confirmPwdSpan"></span>
        </div>
        <div class="form-group">
          <label for="email">邮箱：</label>
          <input autocomplete required type="email" placeholder="请输入邮箱地址" name="email" id="email" onblur="checkEmail()"/>
          <span id="emailSpan"></span>
        </div>
        <div class="form-group">
          <label for="phone">手机号：</label>
          <input id="phone" name="phone" placeholder="请输入您的手机号" onblur="checkPhone()" pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$" required type="text" />
          <span id="phoneSpan"></span>
        </div>
        <div class="form-group">
          <label></label>
          <input type="button" value="提交注册信息" id="bt-register"/>
        </div>
      </form>
    </div>
  </div>
</div>

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
<!--弹出的小广告-->
<script src="../js/jquery-3.1.1.min.js"></script>
<script>

	//光标离开用户名文本框事件,校验用户名
	function checkName(){
		//清空span标签体
		$("#nameSpan").html("");
		
		//获取用户名文本框数据
		var name = $("#uname").val();
		
		var reg = /^\w{5,9}$/;
		//判断用户名是否匹配正则
		if(!reg.test(name)){
			$("#nameSpan").html("用户名长度在5到9位之间").attr("style", "color: red");
			return false;
		}
		
		//向服务端发送的请求地址
		var url = "${basePath}/user/checkName.do";
		//向服务端发送的参数
		var params = {"userName": name};
		//以post方式进行异步请求
		$.post(url, params, function(result){
			if(result.state == 1){
				$("#nameSpan").html("通过").attr("style", "color: orange");
				flag = true;
			}else{
				$("#nameSpan").html(result.message).attr("style", "color: red");
				flag = false;
			}
		});
		return true;
	}
	
	//校验用户密码
	function checkPwd(){
		//清空span标签体
		$("#pwdSpan").html("");
		var password = $("#upwd").val();
		//正则
		var reg = /^\w{6,12}$/;
		//判断是否匹配正则
		if(!reg.test(password)){
			$("#pwdSpan").html("密码长度在6到12位之间").attr("style", "color: red");
			return false;
		}else{
			$("#pwdSpan").html("通过").attr("style", "color: orange");
			return true;
		}
		return true;
		
	}
	
	//校验用户确认密码
	function checkConfirmPwd(){
		//清空span标签体
		$("#confirmPwdSpan").html("");
		//获取用户输入的密码
		var password = $("#upwd").val();
		//获取用户输入的确认密码
		var confirmPwd = $("#upwdconfirm").val();
		if(confirmPwd != password){//密码不一致
			$("#confirmPwdSpan").html("原密码与确认密码不一致").attr("style", "color: red");
			return false;
		}else{
			$("#confirmPwdSpan").html("通过").attr("style", "color: orange");
			return true;
		}
	}
	
	//校验邮箱
	function checkEmail(){
		//清空span标签体
		$("#emailSpan").html("");
		//获取用户输入的邮箱
		var email = $("#email").val();
		//邮箱正则
		var reg = /^\w{5,}@[0-9a-z]+\.[a-z]{2,4}$/;
		if(!reg.test(email)){
			$("#emailSpan").html("请输入合法的邮箱地址").attr("style", "color: red");
			return false;
		}else{
			$("#emailSpan").html("通过").attr("style", "color: orange");
			return true;
		}
	}
	
	//校验电话
	function checkPhone(){
		//清空span标签体
		$("#phoneSpan").html("");
		//获取用户输入的电话
		var phone = $("#phone").val();
		//电话正则
		var reg = /^1\d{10}$/;
		if(!reg.test(phone)){
			$("#phoneSpan").html("请输入合法的手机号").attr("style", "color: red");
			return false;
		}else{
			$("#phoneSpan").html("通过").attr("style", "color: orange");
			return true;
		}
	}
	
	/* 点击注册按钮事件 */
	$('#bt-register').click(function(){
		var n = checkName() + checkPwd() + checkConfirmPwd() + checkEmail() + checkPhone();
		if(n == 5){//提交
			/* var name = $("#uname").val();
			var pwd = $("#upwd").val();
			var email = $("#email").val();
			var phone = $("#phone").val();
			var params = {"userName": name, "password": pwd, "email": email, "phone": phone}*/
			//form表单序列化(获取form表单中的所有name属性值)
			var url = "${basePath}/user/toRegister.do";
			var params = $("#form-register").serialize();
			$.post(url, params, function(result){
				if(result.state == 1){
					//跳转到登录页面
					location.href = "${basePath}/user/toLoginHtml.do";
				}else{
					$("#nameSpan").html(result.message).attr("style", "color: red");
				}
			});
		}
		
	})
</script>
<script src="../js/footTxt.js"></script>
</body>
</html>