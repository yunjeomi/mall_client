<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.model.*" %>
<%@ page import="mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>
<%
	//ClientOneController에서 보낸 clientOne값을 Client에 넣어준다
	Client client = (Client)(request.getAttribute("clientOne"));

%>
	<!-- 메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 회원정보 -->
	<table border="1">
		<tr>
			<td>clientMail</td>
			<td><%=client.getClientMail() %></td>
		</tr>
		<tr>
			<td>clientDate</td>
			<td><%=client.getClientDate() %></td>
		</tr>
		<tr>
			<td>clientPw</td>
			<td><a href="">변경</a></td>
		</tr>
	</table>
</body>
</html>