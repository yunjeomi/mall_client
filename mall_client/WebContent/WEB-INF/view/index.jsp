<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "mall.client.vo.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<!-- 카테고리별 나누기 -->
	<ul> 
		<li><a href="${pageContext.request.contextPath}/IndexController">전체보기</a></li>
	
	<c:forEach var="c" items="${categoryList}">
		<li><a href="${pageContext.request.contextPath}/IndexController?categoryName=${c.categoryName}">${c.categoryName}</a></li>
	</c:forEach>

	</ul>

	<!-- 무슨 카테고리인지 볼 수 있도록 표시 -->
	<c:if test="${categoryName != null}">
		<div>카테고리 "${categoryName}" 선택</div>
	</c:if>	

	<!-- ~로 검색한 결과입니다 표시 -->
	<c:if test="${!searchWord.equals('')}">
		<div>"${searchWord}"로 검색한 결과입니다.</div>
	</c:if>	

	<!-- 베스트 상품 5개 출력 -->
	<h3>Best Ebook</h3>
	<table border="1">
		<tr>
			<c:forEach var="m" items="${bestOrdersList}">
				<td>
					<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
					<div><a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${m.ebookNo}">${m.ebookTitle}</a></div>
					<div>${m.ebookAuthor}</div>
					<div>￦ ${m.ebookPrice}</div>	
				</td>
			</c:forEach>
		</tr>
	</table>


	<!-- 전체 상품 출력  -->
	<h3>EbookList</h3>
	<table border="1">
		<tr>
			<c:set var="row" value="0"/>
			
			<c:forEach var="e" items="${ebookList}">
				<c:set var="row" value="${row+1}"/>
				<td>
					<div><img src="${pageContext.request.contextPath}/img/default.jpg"></div>
					<div><a href="${pageContext.request.contextPath}/EbookOneController?ebookNo=${e.ebookNo}">${e.ebookTitle}</a></div>
					<div>${e.ebookAuthor}</div>
					<div>￦ ${e.ebookPrice}</div>	
				</td>
				
				<c:if test="${(row%5) == 0}">
					</tr>
					<tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>

	<!-- 페이징 -->
	<!-- 카테고리 선택X -->
	<c:if test="${categoryName == null}">
		<!-- 1페이지, 다음, 맨오른쪽 -->
		<c:if test="${currentPage==1 && 1<lastPage}">
			<span>[1]</span>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&searchWord=${searchWord}">&gt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&searchWord=${searchWord}">&gt;&gt;</a>
		</c:if>
		<!-- 1페이지만 -->
		<c:if test="${currentPage==1 && lastPage==1}">
			<span>[1]</span>
		</c:if>	
		<!-- 맨왼쪽, 이전, 현재페이지 -->
		<c:if test="${(currentPage == lastPage) && lastPage != 1}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&searchWord=${searchWord}">&lt;&lt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&searchWord=${searchWord}">&lt;</a>
			<span>[${currentPage}]</span>
		</c:if>	
		<!-- 맨왼쪽, 이전, 현재페이지, 다음, 맨오른쪽 -->
		<c:if test="${1<currentPage && currentPage<lastPage}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=1">&lt;&lt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&searchWord=${searchWord}">&lt;</a>
			<span>[${currentPage}]</span>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&searchWord=${searchWord}">&gt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&searchWord=${searchWord}">&gt;&gt;</a>
		</c:if>
	</c:if>
	
	
	<!-- 카테고리 선택O -->
	<c:if test="${categoryName != null}">
		<!-- 1페이지, 다음, 맨오른쪽 -->
		<c:if test="${currentPage==1 && 1<lastPage}">
			<span>[1]</span>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&categoryName=${categoryName}&searchWord=${searchWord}">&gt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&categoryName=${categoryName}&searchWord=${searchWord}">&gt;&gt;</a>
		</c:if>
		<!-- 1페이지만 -->
		<c:if test="${currentPage==1 && lastPage==1}">
			<span>[1]</span>
		</c:if>	
		<!-- 맨왼쪽, 이전, 현재페이지 -->
		<c:if test="${(currentPage == lastPage) && lastPage != 1}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&categoryName=${categoryName}&searchWord=${searchWord}">&lt;&lt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&categoryName=${categoryName}&searchWord=${searchWord}">&lt;</a>
			<span>[${currentPage}]</span>
		</c:if>	
		<!-- 맨왼쪽, 이전, 현재페이지, 다음, 맨오른쪽 -->
		<c:if test="${1<currentPage && currentPage<lastPage}">
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&categoryName=${categoryName}&searchWord=${searchWord}">&lt;&lt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&categoryName=${categoryName}&searchWord=${searchWord}">&lt;</a>
			<span>[${currentPage}]</span>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&categoryName=${categoryName}&searchWord=${searchWord}">&gt;</a>
			<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&categoryName=${categoryName}&searchWord=${searchWord}">&gt;&gt;</a>		
		</c:if>	
	</c:if>

	<!-- 이북 검색 -->
	<form action="${pageContext.request.contextPath}/IndexController" method="post">
		<input type="hidden" name="categoryName" value="${categoryName}"> 
		<input type="text" name="searchWord">
		<button type="submit">검색</button>
	</form>
</body>
</html>