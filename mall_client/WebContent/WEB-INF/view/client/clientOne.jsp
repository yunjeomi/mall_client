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
			<!-- UpdateClientPwController.doGet - forward:updateClientPw.jsp -->
			<!-- UpdateClientPwController.doPost - ClientDao.updateClientPw() - 로그아웃 되도록 session.invalidate() 세션초기화 - redirect:IndexController -->
			<td><a href="<%=request.getContextPath()%>/UpdateClientPwController?clientMail=<%=client.getClientMail()%>">변경</a></td>
		</tr>
	</table>
	<!-- DeleteClientController - CartDao.deleteCartAll(mail), ClientDao.deleteClient() - 로그아웃 되도록 session.invalidate() 세션초기화 - redirect:IndexController -->
	<a href="<%=request.getContextPath()%>/DeleteClientController?clientMail=<%=client.getClientMail()%>">회원탈퇴</a>
</body>
</html>