<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mainMenu</title>
</head>
<body>
<!-- 상단 메인 메뉴 -->
<%
if(session.getAttribute("loginClient")==null){
%>
	<!-- 로그인 전 -->
	<div>
		<form action="<%=request.getContextPath()%>/LoginController" method="post">
			ID <input type="text" name="clientMail"><br>
			PW <input type="password" name="clientPw"><br>
			<button type="submit">로그인</button>
		</form>
		<ul>
			<!-- InsertClientController - /view/client/insertClient.jsp -->
			<li><a href="<%=request.getContextPath()%>/InsertClientController">회원가입</a></li>
			<li><a href="<%=request.getContextPath()%>/EbookCalendarController">이북캘린더</a></li>
		</ul>
	</div>
<%
	} else{
%>
	<!-- 로그인 후-->
	<div>
		<div>
			<%=((Client)(session.getAttribute("loginClient"))).getClientMail()%>님 반갑습니다.
			
		</div>
		<ul>
			<li><a href="<%=request.getContextPath()%>/IndexController">홈으로</a></li>
			<li><a href="<%=request.getContextPath()%>/EbookCalendarController">이북캘린더</a></li>
			<li><a href="<%=request.getContextPath()%>/ClientOneController">회원정보</a></li>
			<li><a href="<%=request.getContextPath()%>/CartListController">카트</a></li>
			<!-- 주문리스트 OrdersListcontroller - OrdersDao.selectOrdersListByClient() - OrdersList.jsp -->
			<li><a href="<%=request.getContextPath()%>/OrdersListController">주문리스트</a></li>
			<li><a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a></li>
		</ul>
	</div>
<%
}
%>
</body>
</html>