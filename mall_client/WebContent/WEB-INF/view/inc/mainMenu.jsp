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
			<!-- ClientOneController -> ClientDao.clientOne() - /view/client/clientOne.jsp -->
			<li><a href="<%=request.getContextPath()%>/CartListController">회원정보</a></li>
			<li><a href="<%=request.getContextPath()%>/ClientOneController">카트</a></li>
			<li><a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a></li>
		</ul>
	</div>
<%
}
%>
</body>
</html>