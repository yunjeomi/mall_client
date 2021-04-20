<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
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
		<div class="container">	
		
	 	<header class="major special">
			<!-- 카테고리별 나누기 -->
			<c:forEach var="c" items="${categoryList}">
				<span><a href="${pageContext.request.contextPath}/IndexController?categoryName=${c.categoryName}">${c.categoryName}</a></span>
				<span>&nbsp;</span>
			</c:forEach>
			
			<!-- 무슨 카테고리인지 볼 수 있도록 표시 -->
			<c:if test="${categoryName != null}">
				<div>카테고리 "${categoryName}" 선택</div>
			</c:if>	
		
			<!-- ~로 검색한 결과입니다 표시 -->
			<c:if test="${!searchWord.equals('')}">
				<div>"${searchWord}"로 검색한 결과입니다.</div>
			</c:if>
		</header>

		<!-- 베스트 상품 5개 출력 -->
		<h3>Best Ebook</h3>
		<div class="table-wrapper">
			<table class="alt">
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
		</div>
		<br><br>

		<!-- 전체 상품 출력  -->
		<h3>EbookList</h3>
		<div class="table-wrapper">
			<table class="alt">
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
		</div>

		<!-- 페이징 -->
		<!-- 카테고리 선택X -->
		<c:if test="${categoryName == null}">
			<!-- 1페이지, 다음, 맨오른쪽 -->
			<c:if test="${currentPage==1 && 1<lastPage}">
				<span>[1]</span>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&searchWord=${searchWord}" class="button alt small paging">&gt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&searchWord=${searchWord}" class="button alt small paging">&gt;&gt;</a>
			</c:if>
			<!-- 1페이지만 -->
			<c:if test="${currentPage==1 && lastPage==1}">
				<span>[1]</span>
			</c:if>	
			<!-- 맨왼쪽, 이전, 현재페이지 -->
			<c:if test="${(currentPage == lastPage) && lastPage != 1}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&searchWord=${searchWord}" class="button alt small paging">&lt;&lt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&searchWord=${searchWord}" class="button alt small paging">&lt;</a>
				<span>[${currentPage}]</span>
			</c:if>	
			<!-- 맨왼쪽, 이전, 현재페이지, 다음, 맨오른쪽 -->
			<c:if test="${1<currentPage && currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=1" class="button alt small paging">&lt;&lt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&searchWord=${searchWord}" class="button alt small paging">&lt;</a>
				<span>[${currentPage}]</span>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&searchWord=${searchWord}" class="button alt small paging">&gt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&searchWord=${searchWord}" class="button alt small paging">&gt;&gt;</a>
			</c:if>
		</c:if>
		
		<!-- 카테고리 선택O -->
		<c:if test="${categoryName != null}">
			<!-- 1페이지, 다음, 맨오른쪽 -->
			<c:if test="${currentPage==1 && 1<lastPage}">
				<span>[1]</span>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&gt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&gt;&gt;</a>
			</c:if>
			<!-- 1페이지만 -->
			<c:if test="${currentPage==1 && lastPage==1}">
				<span>[1]</span>
			</c:if>	
			<!-- 맨왼쪽, 이전, 현재페이지 -->
			<c:if test="${(currentPage == lastPage) && lastPage != 1}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&lt;&lt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&lt;</a>
				<span>[${currentPage}]</span>
			</c:if>	
			<!-- 맨왼쪽, 이전, 현재페이지, 다음, 맨오른쪽 -->
			<c:if test="${1<currentPage && currentPage<lastPage}">
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=1&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&lt;&lt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage-1}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&lt;</a>
				<span>[${currentPage}]</span>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${currentPage+1}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&gt;</a>
				<a href="${pageContext.request.contextPath}/IndexController?currentPage=${lastPage}&categoryName=${categoryName}&searchWord=${searchWord}" class="button alt small paging">&gt;&gt;</a>		
			</c:if>	
		</c:if>

		<br><span></span><br>
		<!-- 이북 검색 -->
		<form action="${pageContext.request.contextPath}/IndexController" method="post" class="formSearch">
			<input type="hidden" name="categoryName" value="${categoryName}"> 
			<input type="text" name="searchWord" class="search" placeholder="검색어를 입력해주세요.">
			<button type="submit">검색</button>
		</form>
		
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