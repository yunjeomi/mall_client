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
	
%>
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
</body>
</html>