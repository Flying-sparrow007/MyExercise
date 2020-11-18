<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	jsp如何添加java代码
	第一种形式 java代码片段 <%-- <% java代码片段 %> --%>
	第二种形式 java表达式   <%-- <%= java表达式 %> --%>
	
 -->
	<%-- <% 
		for(int i = 0; i < 100; ++i){
			out.println("hello");
		}
	%> --%>
	<%
		int a = 5;
	%>
	<%= a %>
</body>
</html>