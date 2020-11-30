<%@page import="entity.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>用户修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
			
				<!-- 1.include指令:代码片段
​					File属性:指定要包含的文件名,比如<%-- <%@ include file = "路径"> --%>
					注意:容器在将JSP转换成servlet时,会将file属性指定的文件的内容插入到该指令所在的路径位置 -->
				<%@ include file = "header.jsp" %>
					
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						用户修改
					</h1>
					<form action="update.do" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户ID:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" value="${user.id}" name="id" readonly="readonly"  />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" value ="${user.userName}" name="username" />
								</td>
							</tr>
							<!-- <tr>
								<td valign="middle" align="right">
									用户密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" />
								</td>
							</tr> -->
							<tr>
								<td valign="middle" align="right">
									电话:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" value="${user.userPhone}" name="phone" />
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									邮箱:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" value="${user.userEmail}" name="email" />
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="修改" />
						</p>
					</form>
				</div>
			</div>
			<%@ include file = "footer.jsp" %>
		</div>
	</body>
</html>
