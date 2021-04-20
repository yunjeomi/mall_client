<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
</head>
<body>

	<!-- 메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 회원정보 -->
	<table border="1">
		<tr>
			<td>clientMail</td>
			<td>${clientOne.clientMail }</td>
		</tr>
		<tr>
			<td>clientDate</td>
			<td>${clientOne.clientDate }</td>
		</tr>
		<tr>
			<td>clientPw</td>
			<!-- UpdateClientPwController.doGet - forward:updateClientPw.jsp -->
			<!-- UpdateClientPwController.doPost - ClientDao.updateClientPw() - 로그아웃 되도록 session.invalidate() 세션초기화 - redirect:IndexController -->
			<td><a href="${pageContext.request.contextPath}/UpdateClientPwController?clientMail=${clientOne.clientMail }">변경</a></td>
		</tr>
	</table>
	<!-- DeleteClientController - CartDao.deleteCartAll(mail), ClientDao.deleteClient() - 로그아웃 되도록 session.invalidate() 세션초기화 - redirect:IndexController -->
	<a href="${pageContext.request.contextPath}/DeleteClientController?clientMail=${clientOne.clientMail }">회원탈퇴</a>
</body>
</html>