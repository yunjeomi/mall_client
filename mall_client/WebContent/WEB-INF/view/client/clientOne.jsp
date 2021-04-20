<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>clientOne</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</head>
<body>
<!-- Header -->
	<header id="header">
		<h1><strong><a href="${pageContext.request.contextPath}/IndexController">EbookMall</a></strong> for you</h1>
		<nav id="nav">
			<ul>
				<li><jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include></li>
			</ul>
		</nav>
	</header>
	
	<section id="main" class="wrapper">
		<div class="container">
			<header class="major special">
				<h1>clientOne</h1>
			</header>
		
			<div class="table-wrapper">
				<table class="alt">
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
						<td><a href="${pageContext.request.contextPath}/UpdateClientPwController?clientMail=${clientOne.clientMail }">변경</a></td>
					</tr>
				</table>
			</div>
		</div>
	</section>
	<!-- DeleteClientController - CartDao.deleteCartAll(mail), ClientDao.deleteClient() - 로그아웃 되도록 session.invalidate() 세션초기화 - redirect:IndexController -->
	<a href="${pageContext.request.contextPath}/DeleteClientController?clientMail=${clientOne.clientMail }">회원탈퇴</a>
	
<!-- Footer -->
	<footer id="footer">
		<div class="container">
			<ul class="icons">
				<li><a href="#" class="icon fa-facebook"></a></li>
				<li><a href="#" class="icon fa-twitter"></a></li>
				<li><a href="#" class="icon fa-instagram"></a></li>
			</ul>
			<ul class="copyright">
				<li>&copy; yunjeong</li>
				<li>Design: <a href="http://templated.co">TEMPLATED</a></li>
				<li>Images: <a href="http://unsplash.com">Unsplash</a></li>
			</ul>
		</div>
	</footer>
</body>
</html>