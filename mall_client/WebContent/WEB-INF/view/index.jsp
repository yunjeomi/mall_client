<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<!-- 메뉴 1 로그인/회원가입/회원정보-->
	<!-- 장바구니(세션) -->
	<!-- 캘린더(이번달에 나온 책들) -->
	<!-- 베스트셀러(주문량) -->
	<!-- 메뉴 2 카테고리-->
	<h1>index</h1>
<%
	List<Ebook> ebookList = (List<Ebook>)(request.getAttribute("ebookList")); //object type -> List<Ebook> type으로
	List<Category> categoryList = (List<Category>)(request.getAttribute("categoryList"));
%>
	<!-- 카테고리별 나누기 -->
	<ul>
		<li><a href="<%=request.getContextPath()%>/IndexController">전체보기</a></li>
<%
	for(Category c : categoryList) {
%>	
		<li><a href="<%=request.getContextPath()%>/IndexController?categoryName=<%=c.getCategoryName()%>"><%=c.getCategoryName()%></a></li>
<%	
	}
%>
	</ul>

	<table border="1">
		<tr>
<%
		//한 줄에 5개의 책을 보여주기 위해 i값 설정
		int i = 0;
		for(Ebook ebook : ebookList) {
			i += 1;
%>	
			<td>
				<div><img src="<%=request.getContextPath()%>/img/default.jpg"></div>
				<!-- EbookOneController - EbookDao.ebookOne() - ebookOne.jsp -->
				<div><a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebook.getEbookNo()%>"><%=ebook.getEbookTitle() %></a></div>
				<div><%=ebook.getEbookPrice() %></div>
			</td>
<%
			if(i%5==0){	//5번째 출력 후 다음 줄로 넘어가기
%>				
		</tr>
		<tr>
<%				
			}
		}
%>
		</tr>
	</table>
	
	<!-- 페이징하기
	1. 1페이지 + 다음 + 맨오른쪽		;
	2. -1페이지-					;
	3. 맨왼쪽 + 이전 + 끝페이지		;
	4. 맨왼쪽 + 이전 + 다음 + 맨오른쪽	;
	 -->
	<a href=""></a>
	
	<!-- 이북 검색 -->
	<form action="<%=request.getContextPath()%>/IndexController" method="post">
		EbookTitle : 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
</body>
</html>