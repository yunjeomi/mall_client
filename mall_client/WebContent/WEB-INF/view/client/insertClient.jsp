<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertClient</title>
</head>
<body>
	<h1>insertClient</h1>
	<form action="${pageContext.request.contextPath}/InsertClientController" method="post">
		<!-- 정규표현식 나중에 추가 required pattern="" -->
		<table border="1">
			<tr>
				<td>clientMail</td>
				<td><input type="text" name="clientMail" placeholder="aaa@example.com"></td>
			</tr>
			<tr>
				<td>clientPw</td>
				<td><input type="password" name="clientPw"></td>
			</tr>
		</table>
		<button type="submit">회원가입</button>
	</form>
</body>
</html>