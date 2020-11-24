<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="a4.jsp"%>
    <!-- errorPage="a4.jsp":当页面发生异常时,所跳转的页面 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String number = request.getParameter("number");
		Integer num = Integer.parseInt(number);
	%>
	<%=
		num * 2
	%>
</body>
</html>