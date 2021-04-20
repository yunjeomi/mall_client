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
		<div class="container">
			<header class="major special">
				<h1>ordersList</h1>
			</header>
		
			<div class="table-wrapper">
				<table class="alt">
					<tr>
						<td>ordersNo</td>
						<td>ebookNo</td>
						<td>ordersDate</td>
						<td>ordersState</td>
						<td>ebookTitle</td>
						<td>ebookPrice</td>
					</tr>
			
					<c:forEach var="m" items="${ordersList}">
					<tr>
						<td>${m.ordersNo}</td>
						<td>${m.ebookNo}</td>
						<td>${m.ordersDate}</td>
						<td>${m.ordersState}</td>
						<td>${m.ebookTitle}</td>
						<td>${m.ebookPrice}</td>
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