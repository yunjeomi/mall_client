<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<body>

	<!-- 메인메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 카트리스트 -->
	<h1>cartList</h1>
	<form>
	<table border="1">
		<tr>
			<td>cartNo</td>
			<td>ebookTitle</td>
			<td>ebookPrice</td>
			<td>cartDate</td>
			<td>삭제</td>
			<td>주문</td>
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
</body>
</html>