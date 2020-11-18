<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<!-- 引入header.jsp代码片段 -->
				<%@ include file = "header.jsp" %>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						欢迎使用!
					</h1>
					<table class="table">
						<tr class="table_header">
							<td>
								用户ID
							</td>
							<td>
								用户名
							</td>
							<td>
								电话
							</td>
							<td>
								邮箱
							</td>
							<td>
								操作
							</td>
						</tr>
						
						<%
							/* UserDao dao = new UserDaoImpl();
							List<User> list = dao.findAllUser();*/
							List<User> list = (List<User>)request.getAttribute("list");
							for(User user: list){
						%>
						<tr class="row1">
							<td><%= user.getId() %></td>
							<td><%= user.getUserName() %></td>
							<td><%= user.getUserPhone() %></td>
							<td><%= user.getUserEmail() %></td>
							<td><a onclick = "return confirm('确定要删除 <%= user.getUserName() %> 吗')" href="delete?id=<%= user.getId() %>">删除</a>&nbsp;</td>
						</tr>
						<%		
							}
						%>						
						<!-- <tr class="row1">
							<td>
								1
							</td>
							<td>
								zhangshan
							</td>
							<td>
								111
							</td>
							<td>
								eric@126.com
							</td>
							<td>
								<a href="listUsers.html">删除</a>&nbsp;
							</td>
						</tr> -->
					</table>
					<p>
						<input type="button" class="button" value="添加用户" onclick="location='addUser.jsp'"/>
					</p>
				</div>
			</div>
			<%@ include file = "footer.jsp" %>
		</div>
	</body>
</html>
