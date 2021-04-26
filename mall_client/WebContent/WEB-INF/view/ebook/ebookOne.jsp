<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebookOne</title>
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
				<h2>ebookOne</h2>
			</header>
		
			<div class="table-wrapper">
				<table class="alt">
					<tr>
						<td>ebookTitle</td>
						<td>${ebookOne.ebookTitle }</td>
					</tr>	
					<tr>
						<td>categoryName</td>
						<td>${ebookOne.categoryName }</td>
					</tr>
					<tr>
						<td>ebookISBN</td>
						<td>${ebookOne.ebookISBN }</td>
					</tr>
					<tr>
						<td>ebookAuthor</td>
						<td>${ebookOne.ebookAuthor }</td>
					</tr>	
					<tr>
						<td>ebookCompany</td>
						<td>${ebookOne.ebookCompany }</td>
					</tr>	
					<tr>
						<td>ebookSummary</td>
						<td>${ebookOne.ebookSummary }</td>
					</tr>
					<tr>
						<td>ebookImg</td>
						<td>${ebookOne.ebookImg }</td>
					</tr>	
					<tr>
						<td>ebookDate</td>
						<td>${ebookOne.ebookDate }</td>
					</tr>
					<tr>
						<td>ebookState</td>
						<td>${ebookOne.ebookState }</td>
					</tr>
					<tr>
						<td>ebookPageCount</td>
						<td>${ebookOne.ebookPageCount }</td>
					</tr>	
					<tr>
						<td>ebookPrice</td>
						<td>${ebookOne.ebookPrice }</td>
					</tr>	
				</table>
				
			</div>
			
			<!-- 장바구니 추가 버튼 -->
			<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebookOne.ebookNo}">
				<!-- 버튼 활성화 -->
				<c:if test="${loginClient!=null && ebookOne.ebookState.equals('판매중')}" >
					<button type="button">장바구니추가</button>
				</c:if>
				<!-- 버튼 비활성화 -->
				<c:if test="${loginClient==null || !ebookOne.ebookState.equals('판매중')}">
					<button type="button" disabled="disabled">장바구니추가</button>
				</c:if>
			</a>
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




