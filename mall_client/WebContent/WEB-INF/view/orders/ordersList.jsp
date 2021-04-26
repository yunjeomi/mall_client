<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.model.*" %>
<%@ page import="mall.client.vo.*" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ordersList</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />

<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
</head>
<body>
<%
	List<Map<String, Object>> ordersList = (List<Map<String, Object>>)request.getAttribute("ordersList");
%>
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
				<h2>ordersList</h2>
			</header>
		
			<div class="table-wrapper">
				<table class="alt">
					<tr>
						<th>ordersNo</th>
						<th>ordersState</th>
						<th>ebookNo</th>
						<th>ebookTitle</th>
						<th>ebookPrice</th>
						<th>ordersDate</th>
					</tr>
			
					<c:forEach var="m" items="${ordersList}">
					<tr>
						<td>${m.ordersNo}</td>
						<td>${m.ordersState}</td>
						<td>${m.ebookNo}</td>
						<td>${m.ebookTitle}</td>
						<td>${m.ebookPrice}</td>
						<td>${m.ordersDate}</td>
					</tr>
					</c:forEach>
				</table>
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