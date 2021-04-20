<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainMenu</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</head>
<body>
<!-- 상단 메인 메뉴 -->

<!-- 로그인 전 -->
<c:if test="${loginClient==null}">
			<form action="${pageContext.request.contextPath}/LoginController" method="post" class="formSearch">
				ID <input type="text" name="clientMail" class="loginId">
				PW <input type="password" name="clientPw" class="loginPw">
				<button type="submit">로그인</button>
			</form>
			<ul>
				<li><a href="${pageContext.request.contextPath}/IndexController">홈으로</a></li>
				<li><a href="${pageContext.request.contextPath}/InsertClientController">회원가입</a></li>
				<li><a href="${pageContext.request.contextPath}/EbookCalendarController">이북캘린더</a></li>
			</ul>
		
</c:if>	

<!-- 로그인 후-->
<c:if test="${loginClient!=null}">
	<div>
		<ul>
			<li>${loginClient.clientMail}님 환영합니다</li>
			<li><a href="${pageContext.request.contextPath}/IndexController">홈으로</a></li>
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