package mall.client.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.vo.Ebook;

//C->M->V로 이동
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	//controller는 원래 dao를 가져온다.
	private EbookDao ebookDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request 분석
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		int rowPerPage = 15;
		int beginRow = (currentPage-1)*rowPerPage;
		
		//model 호출
		this.ebookDao = new EbookDao();
		List<Ebook> list = this.ebookDao.ebookList(beginRow, rowPerPage);
		
		//View forwarding
		request.setAttribute("ebookList", list);	//request에 넣어서 뽑아낼 수 있도록
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
}
