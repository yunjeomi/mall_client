package mall.client.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.*;
import mall.client.vo.*;

//C->M->V로 이동
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	//controller는 원래 dao를 가져온다.
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//의존객체 생성
		this.ebookDao = new EbookDao();
		
		//request 분석
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 15;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		int beginRow = (currentPage-1)*rowPerPage;
		
		String searchWord = "";
		if(request.getParameter("searchWord") != null) {
			searchWord = request.getParameter("searchWord");
		}
		
		int totalRow = 0;
		String categoryName = null;
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}

		if(categoryName != null) {	//카테고리 보기
			totalRow = this.ebookDao.totalRowOfCategory(categoryName, searchWord);
		} else {	//전체보기
			totalRow = this.ebookDao.totalRow(searchWord);
		}
		
		int lastPage = totalRow/rowPerPage;
		if(totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		
		//디버깅
		System.out.println("currentPage-> "+currentPage);
		System.out.println("rowPerPage-> "+rowPerPage);
		System.out.println("beginRow-> "+beginRow);
		System.out.println("totalRow-> "+totalRow);
		System.out.println("lastPage-> "+lastPage);
		System.out.println("categoryName-> "+categoryName);
		System.out.println("searchWord-> "+searchWord);
		
		//페이징용 vo에 위 값들 넣어주기
		Paging paging = new Paging();
		paging.setBeginRow(beginRow);
		paging.setCategoryName(categoryName);
		paging.setCurrentPage(currentPage);
		paging.setLastPage(lastPage);
		paging.setRowPerPage(rowPerPage);
		paging.setSearchWord(searchWord);
		paging.setTotalRow(totalRow);
		
		//model 호출
		//전체이북 리스트 출력 메소드 실행
		List<Ebook> ebookList = new ArrayList<>();
		if(categoryName != null) {
			ebookList = this.ebookDao.ebookListByCategory(beginRow, rowPerPage, searchWord, categoryName);
			System.out.println("*카테고리("+categoryName+") List 출력*\n");
		} else {
			ebookList = this.ebookDao.ebookList(beginRow, rowPerPage, searchWord);
			System.out.println("*전체보기 List 출력*\n");
		}
		//카테고리 리스트 출력 메소드 실행
		this.categoryDao = new CategoryDao();
		List<Category> categoryList = this.categoryDao.categoryList();
		//베스트이북 리스트 출력 메소드 실행
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> bestOrdersList = this.ordersDao.bestOrdersList();
		
		//View forwarding
		request.setAttribute("ebookList", ebookList);	//request에 넣어서 뽑아낼 수 있도록
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("paging", paging);
		request.setAttribute("bestOrdersList", bestOrdersList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
	
	//검색 시 post로 넘겨받는다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		//의존객체 생성
		this.ebookDao = new EbookDao();
		
		//request 분석
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 15;
		if(request.getParameter("rowPerPage") != null) {
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}
		
		int beginRow = (currentPage-1)*rowPerPage;
		
		
		String searchWord = "";
		if(request.getParameter("searchWord") != null) {
			searchWord = request.getParameter("searchWord");
		}
		
		int totalRow = 0;
		String categoryName = null;
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		} 
		
		//전체보기에서 검색어 입력할 경우 카테고리 값은 문자열 null. 문자열을 객체가없는 null로 다시 넣어준다.
		if(categoryName.equals("null")) {
			categoryName = null;
		}
		
		if(categoryName != null) {	//카테고리보기
			totalRow = this.ebookDao.totalRowOfCategory(categoryName, searchWord);
		} else {	//전체보기
			totalRow = this.ebookDao.totalRow(searchWord);
		}
		
		int lastPage = totalRow/rowPerPage;
		if(totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		
		//디버깅
		System.out.println("currentPage-> "+currentPage);
		System.out.println("rowPerPage-> "+rowPerPage);
		System.out.println("beginRow-> "+beginRow);
		System.out.println("totalRow-> "+totalRow);
		System.out.println("lastPage-> "+lastPage);
		System.out.println("categoryName-> "+categoryName);
		System.out.println("searchWord-> "+searchWord);
		
		//페이징용 vo에 위 값들 넣어주기
		Paging paging = new Paging();
		paging.setBeginRow(beginRow);
		paging.setCategoryName(categoryName);
		paging.setCurrentPage(currentPage);
		paging.setLastPage(lastPage);
		paging.setRowPerPage(rowPerPage);
		paging.setSearchWord(searchWord);
		paging.setTotalRow(totalRow);
		
		//model 호출
		//전체이북 리스트 출력 메소드 실행
		List<Ebook> ebookList = new ArrayList<>();
		if(categoryName != null) {
			ebookList = this.ebookDao.ebookListByCategory(beginRow, rowPerPage, searchWord, categoryName);
			System.out.println("*카테고리("+categoryName+") List 출력*\n");
		} else {
			ebookList = this.ebookDao.ebookList(beginRow, rowPerPage, searchWord);
			System.out.println("*전체보기 List 출력*\n");
		}
		//카테고리 리스트 출력 메소드 실행
		this.categoryDao = new CategoryDao();
		List<Category> categoryList = this.categoryDao.categoryList();
		//베스트이북 리스트 출력 메소드 실행
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> bestOrdersList = this.ordersDao.bestOrdersList();
		
		//View forwarding
		request.setAttribute("ebookList", ebookList);	//request에 넣어서 뽑아낼 수 있도록
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("paging", paging);
		request.setAttribute("bestOrdersList", bestOrdersList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
		
	}
}
