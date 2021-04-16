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
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//수집&전처리
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		System.out.println("cart에 추가할 ebookNo-> "+ebookNo);
		
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		int clientNo = ((Client)(session.getAttribute("loginClient"))).getClientNo();
		
		//Dao실행
		this.cartDao = new CartDao();
		
		//카트안에 동일한 이북이 존재하는지 확인
		//주문리스트에 동일한 이북이 존재하는지 확인
		//true->카트추가 가능  false->중복
		Boolean checkEbook = true;
		Boolean checkOrdersList = true;
		checkEbook = this.cartDao.checkEbookInCart(cart);
		checkOrdersList = this.cartDao.checkEbookInOrdersList(clientNo, ebookNo);
		
		if(checkOrdersList == false) {
			System.out.println("*이미 구매한 ebook입니다.*\n");
			response.sendRedirect(request.getContextPath()+"/EbookOneController?ebookNo="+ebookNo);
			return;
		}
		
		if(checkEbook == false) {
			System.out.println("*카트에 동일한 ebook 존재*\n");
			response.sendRedirect(request.getContextPath()+"/CartListController");
			return;
		}
		
		
		this.cartDao.insertCart(cart);
		System.out.println("*ebook 장바구니 추가 완료*\n");
		
		//장바구니에 추가했으니 장바구니로 이동해라. CartListController
		response.sendRedirect(request.getContextPath()+"/CartListController");
		
	}

}
