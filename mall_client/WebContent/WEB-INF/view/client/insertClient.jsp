<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertClient</title>
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
		<div class="container 75%">
			<header class="major special">
				<h2>insert Client</h2>
			</header>
			
			<form action="${pageContext.request.contextPath}/InsertClientController" method="post">
				<div class="table-wrapper">		
					<!-- 정규표현식 나중에 추가 required pattern="" -->
					<table class="alt">
						<tr>
							<td>clientMail</td>
							<td><input type="text" name="clientMail" placeholder="aaa@example.com"></td>
						</tr>
						<tr>
							<td>clientPw</td>
							<td><input type="password" name="clientPw"></td>
						</tr>
					</table>
				</div>
				<button type="submit">회원가입</button>
			</form>
		</div>
	</section>
	
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