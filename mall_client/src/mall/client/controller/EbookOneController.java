package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.vo.Ebook;


@WebServlet("/EbookOneController")
public class EbookOneController extends HttpServlet {
	private EbookDao ebookDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수집
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		System.out.println("선택한 ebookNo-> "+ebookNo);
		
		//모델
		this.ebookDao = new EbookDao();
		Ebook ebook = this.ebookDao.ebookOne(ebookNo);
		System.out.println("*ebookOne출력 완료*");
		System.out.println();
		
		//view forward
		request.setAttribute("ebookOne", ebook);
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookOne.jsp").forward(request, response);
		
	}

}
