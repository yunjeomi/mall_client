<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.model.*" %>
<%@ page import="mall.client.vo.*" %>
<%@ page import="java.util.*" %>

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
	<!-- 메뉴 네비게이션 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 출력내용 -->
	<h1>ordersList</h1>
	<table border="1">
		<tr>
			<td>ordersNo</td>
			<td>ebookNo</td>
			<td>ordersDate</td>
			<td>ordersState</td>
			<td>ebookTitle</td>
			<td>ebookPrice</td>
		</tr>
<%
	for(Map m : ordersList){
		
%>		
		<tr>
			<td><%=(Integer)(m.get("ordersNo"))%></td>
			<td><%=(Integer)(m.get("ebookNo")) %></td>
			<td><%=(String)(m.get("ordersDate")) %></td>
			<td><%=(String)(m.get("ordersState")) %></td>
			<td><%=(String)(m.get("ebookTitle")) %></td>
			<td><%=(Integer)(m.get("ebookPrice")) %></td>
		</tr>
<%
	}
%>		
	</table>
</body>
</html>