<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.vo.*" %>
<%@ page import="mall.client.model.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebookOne</title>
</head>
<body>
<%
	Ebook ebook = (Ebook)request.getAttribute("ebookOne");
%>
	<!-- 메뉴 -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>

	<h1>ebookOne</h1>
	
	<table border="1">
		<tr>
			<td>ebookTitle</td>
			<td><%=ebook.getEbookTitle()%></td>
		</tr>	
		<tr>
			<td>categoryName</td>
			<td><%=ebook.getCategoryName()%></td>
		</tr>
		<tr>
			<td>ebookISBN</td>
			<td><%=ebook.getEbookISBN()%></td>
		</tr>
		<tr>
			<td>ebookAuthor</td>
			<td><%=ebook.getEbookAuthor()%></td>
		</tr>	
		<tr>
			<td>ebookCompany</td>
			<td><%=ebook.getEbookCompany()%></td>
		</tr>	
		<tr>
			<td>ebookSummary</td>
			<td><%=ebook.getEbookSummary()%></td>
		</tr>
		<tr>
			<td>ebookImg</td>
			<td><%=ebook.getEbookImg()%></td>
		</tr>	
		<tr>
			<td>ebookDate</td>
			<td><%=ebook.getEbookDate()%></td>
		</tr>
		<tr>
			<td>ebookState</td>
			<td><%=ebook.getEbookState()%></td>
		</tr>
		<tr>
			<td>ebookPageCount</td>
			<td><%=ebook.getEbookPageCount()%></td>
		</tr>	
		<tr>
			<td>ebookPrice</td>
			<td><%=ebook.getEbookPrice()%></td>
		</tr>	
	</table>
	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect;CartListController -->
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
<%
	if(session.getAttribute("loginClient")!=null && ebook.getEbookState().equals("판매중")){
%>	
		<button type="button">장바구니추가</button>
<%
	} else{
%>	
		<button type="button" disabled="disabled">장바구니추가</button>
<%
	}
%>	
	</a>
</body>
</html>




