package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.*;


@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//수집
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		Client client = (Client)(session.getAttribute("loginClient"));
		
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(client.getClientMail());
		System.out.println("삭제할 ebook정보"+cart.toString());
		
		//dao
		this.cartDao = new CartDao();
		this.cartDao.deleteCart(cart);
		System.out.println("*선택한 ebook 카트에서 삭제 완료*\n");
		
		//redirect
		response.sendRedirect(request.getContextPath()+"/CartListController");
	}
	

}
