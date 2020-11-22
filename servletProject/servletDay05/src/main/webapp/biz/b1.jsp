<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>我是b1</p>
	<!-- <a href = "../b2.jsp">进入b2</a> -->
	<a href = "<%= request.getContextPath() %>/b2.jsp">进入b2(使用绝对路径)</a>
	<%= request.getContextPath() %>
	
	<% response.getWriter().print("益康特"); %>
</body>
</html>