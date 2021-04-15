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


@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect("/IndexController");
			return;
		}
		
		//수집&전처리
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		System.out.println("cart에 추가할 ebookNo-> "+ebookNo);
		
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		
		
		
		
		//Dao실행
		this.cartDao = new CartDao();
		
		//카트안에 동일한 이북이 존재하는지 확인
		//true->카트추가 가능  false->중복
		Boolean checkEbook = true;
		checkEbook = this.cartDao.checkEbookInCart(cart);
		if(checkEbook == false) {
			System.out.println("*카트에 동일한 ebook 존재*");
			response.sendRedirect(request.getContextPath()+"/CartListController");
			return;
		}
		
		this.cartDao.insertCart(cart);
		System.out.println("*ebook 장바구니 추가 완료*");
		
		//장바구니에 추가했으니 장바구니로 이동해라. CartListController
		response.sendRedirect(request.getContextPath()+"/CartListController");
		
	}

}
