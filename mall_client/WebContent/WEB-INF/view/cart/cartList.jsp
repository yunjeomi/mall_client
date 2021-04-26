<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
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
				<h2>cartList</h2>
			</header>

			<div class="table-wrapper">
				<form>
				<table class="alt">
					<tr>
						<th>cartNo</th>
						<th>ebookTitle</th>
						<th>ebookPrice</th>
						<th>cartDate</th>
						<th>삭제</th>
						<th>주문</th>
					</tr>
					
					<!-- list 출력 -->
					<c:forEach var="m" items="${cartList }">
					<tr>
						<td>${m.cartNo }</td>
						<td>${m.ebookTitle }</td>
						<td>${m.ebookPrice }</td>
						<td>${m.cartDate.substring(0, 11)}</td>
						<!-- DeleteCartController - CartDao.deleteCart() - redirect:CartListController -->
						<td><a href="${pageContext.request.contextPath}/DeleteCartController?ebookNo=${m.ebookNo}">삭제</a></td>
						<!-- InsertOrdersController - insertOrders(), deleteCart():ISSUE 트랜처리 - redirect:OrdersListController주문리스트 -->
						<td><a href="${pageContext.request.contextPath}/InsertOrdersController?ebookNo=${m.ebookNo}">주문</a></td>
					</tr>
					</c:forEach>
				
				</table>
				</form>
			</div>
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