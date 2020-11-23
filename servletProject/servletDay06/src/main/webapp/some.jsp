<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%= session.getAttribute("username") %><br>
	<%-- 
		采用request绑定数据进行重定向时,会出现数据丢失,因为request生命周期到了
		<%= request.getAttribute("username") %>
	 --%>
	 <%= session.getAttribute("age") %>
</body>
</html>