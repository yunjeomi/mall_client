<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateClientPw</title>
</head>
<body>

	<!-- 메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 패스워드 수정 테이블 -->
	<form action="${pageContext.request.contextPath}/UpdateClientPwController" method="post">
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
		<button type="submit">변경</button>
	</form>
</body>
</html>