<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
						
						<c:forEach items="${map.list}" var="u" varStatus="s">
							<tr class="row${s.index % 2 + 1}">
								<td>${u.id}</td>
								<td>${u.userName}</td>
								<td>${u.userPhone}</td>
								<td>${u.userEmail}</td>
								<td>
									<a onclick = "return confirm('确定要删除 ${u.userName} 吗')" href="delete.do?id=${u.id}">删除</a>&nbsp;
									<a href="userById.do?id=${u.id}">修改</a>&nbsp;
								</td>
							</tr>
						</c:forEach>
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
					<div style="text-align: center;">
						<%-- <a href="upPage.do?page=${map.page}">上一页</a>&nbsp;&nbsp;${map.page}&nbsp;&nbsp;<a href="nextPage.do?page=${map.page}">下一页</a><span style="float: right; margin-right: 15px;">总共:${map.pageSize}页</span> --%>
						<a href="upPage.do?page=${map.page}">上一页</a>
						<c:forEach begin="1" end="${map.pageSize}" var="m" step="1">
							<c:if test="${m == map.page}">
								<a style="color: red; text-decoration: none;" href="userPage.do?page=${m}">${m}</a>
							</c:if>
							<c:if test="${m != map.page}">
								<a style="color: #ccc; text-decoration: none;" href="userPage.do?page=${m}">${m}</a>
							</c:if>
						</c:forEach>
						<a href="nextPage.do?page=${map.page}">下一页</a><span style="float: right; margin-right: 15px;">总共:&nbsp;${map.pageSize}&nbsp;页</span>
					</div>
				</div>
			</div>
			<%@ include file = "footer.jsp" %>
		</div>
	</body>
</html>
