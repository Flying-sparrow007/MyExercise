<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
    <!-- 
    	session="true"(默认)时才能够在jsp中使用session
    	session="true"时不能够在jsp中使用session
     -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("username", "kun");
	%>
	<%=
		request.getAttribute("username")
	%>
</body>
</html>