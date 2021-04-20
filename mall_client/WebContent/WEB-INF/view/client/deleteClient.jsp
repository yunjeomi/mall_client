<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deleteClient</title>
</head>
<body>

	<!-- 메뉴소환 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 내용입력 -->
	<h1>deleteClient</h1>
	<form action="${pageContext.request.contextPath}/DeleteClientController" method="post">
		<table border="1">
			<tr>
				<td>client_mail</td>
				<td><input type="text" name="clientMail" value="${clientMail}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>client_pw</td>
				<td><input type="password" name="clientPw"></td>
			</tr>
		</table>
		<button type="submit">삭제</button>
	</form>
</body>
</html>