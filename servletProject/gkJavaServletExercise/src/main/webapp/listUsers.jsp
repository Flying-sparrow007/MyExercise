<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户列表</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
	</head>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		function toPage() {
			var page = $(".pageNum").val();
			location = "toPage.do?page=" + page;
		}
	</script>
	<body>
		<div id="wrap">
			<div id="top_content"> 
				<%@ include file="header.jsp" %>
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
						<c:if test="${empty map.list}">
							<tr class="row1">
								<td colspan="5" style="color:white;font-size:15px;line-height:100%;text-align:center;background-color:#35A4B5;">
									没有用户信息
								</td>
							</tr>
						</c:if>
						<c:forEach items="${map.list}" var="v" varStatus="s">
							<tr class="row${s.index % 2 + 1}">
							<td>
								${v.id}
							</td>
							<td>
								${v.userName}
							</td>
							<td>
								${v.userPhone}
							</td>
							<td>
								${v.userEmail}
							</td>
							<td>
								<a onclick="return confirm('确定要删除 ${v.userName} 用户吗?')" href="delete.do?id=${v.id}">删除</a>&nbsp;
								<a href="select.do?id=${v.id}">修改</a>&nbsp;
								<span style="color:red">${failure}</span>
							</td>
						</tr>
						</c:forEach>
						<!-- <tr class="row2">
							<td>
								2
							</td>
							<td>
								sdd
							</td>
							<td>
								111
							</td>
							<td>
								eric@126.com
							</td>
							<td>
								<a href="listUsers.html">delete</a>&nbsp;
							</td>
						</tr> -->
					</table>
					<p class="down">
						<input type="button" class="button" value="注册" onclick="location='addUser.jsp'"/>
						<input type="button" class="page pageLeft" value="上一页" onclick="location='upPage.do'">
						<span class="page down_m">第&nbsp;<input type="text" class="pageNum" onblur="toPage()" value="${map.page}">&nbsp;页&nbsp;共&nbsp;${map.pageCount}&nbsp;页</span>
						<input type="button" class="page pageRight" value="下一页" onclick="location='downPage.do'">
					</p>
				</div>
			</div>
			<%@ include file="footer.jsp" %>
		</div>
	</body>
</html>
