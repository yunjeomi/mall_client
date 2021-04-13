<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cartList</title>
</head>
<body>
<%
	List<Map<String, Object>> cartList = (List<Map<String, Object>>)request.getAttribute("cartList");
%>
	<!-- 메인메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- 카트리스트 -->
	<h1>cartList</h1>
	<table border="1">
		<tr>
			<td>cartNo</td>
			<td>ebookTitle</td>
			<td>ebookPrice</td>
			<td>cartDate</td>
		</tr>
<%
		//list 출력
		for(Map<String, Object> list : cartList){
%>		
		<tr>
			<td><%=list.get("cartNo")%></td>
			<td><%=list.get("ebookTitle") %></td>
			<td><%=list.get("ebookPrice") %></td>
			<td><%=list.get("cartDate") %></td>
		</tr>
<%
		}
%>		
	</table>
</body>
</html>