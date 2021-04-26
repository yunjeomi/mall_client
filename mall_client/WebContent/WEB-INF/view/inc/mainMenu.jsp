<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainMenu</title>
</head>
<body>
<!-- 로그인 전 -->
<c:if test="${loginClient==null}">
	<div>
		<form action="${pageContext.request.contextPath}/LoginController" method="post">
			ID <input type="text" name="clientMail" class="loginId">
			&nbsp;
			PW <input type="password" name="clientPw" class="loginPw">
			<button type="submit">로그인</button>
			<span>&nbsp&nbsp</span>
			<span><a href="${pageContext.request.contextPath}/InsertClientController">회원가입</a></span>
			<span>&nbsp&nbsp</span>
			<span><a href="${pageContext.request.contextPath}/EbookCalendarController">이북캘린더</a></span>
			<span>&nbsp&nbsp</span>
			<span><a href="${pageContext.request.contextPath}/IndexController">홈</a></span>
		</form>
	</div>	
</c:if>	

<!-- 로그인 후-->
<c:if test="${loginClient!=null}">
	<div>
		<ul>
			<li>${loginClient.clientMail}님 환영합니다</li>
			<li><a href="${pageContext.request.contextPath}/IndexController">홈</a></li>
			<li><a href="${pageContext.request.contextPath}/EbookCalendarController">이북캘린더</a></li>
			<li><a href="${pageContext.request.contextPath}/CartListController">장바구니</a></li>
			<li><a href="${pageContext.request.contextPath}/OrdersListController">주문내역</a></li>
			<li><a href="${pageContext.request.contextPath}/ClientOneController">회원정보</a></li>
			<li><a href="${pageContext.request.contextPath}/LogoutController" class="button alt small logout">로그아웃</a></li>
		</ul>
	</div>
</c:if>
</body>
</html>