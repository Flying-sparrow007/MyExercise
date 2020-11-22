<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		Cookie c = new Cookie("username", "admin");
		//c.setPath(String path);//改变cookie路径,必须在response添加cookie之前
		response.addCookie(c);
		//只能是a1同级或者是a1的子目录才能够访问到a1设置的cookie
	%>
</body>
</html>