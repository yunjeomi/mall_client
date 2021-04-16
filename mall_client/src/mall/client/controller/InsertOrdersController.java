package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.OrdersDao;
import mall.client.vo.*;


@WebServlet("/InsertOrdersController")
public class InsertOrdersController extends HttpServlet {
	private CartDao cartDao;
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//의존객체 생성 후 주입
		this.ordersDao = new OrdersDao();
		this.cartDao = new CartDao();
		
		//수집
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		Client client = (Client)session.getAttribute("loginClient");
		
		Orders orders = new Orders();
		orders.setEbookNo(ebookNo);
		orders.setClientNo(client.getClientNo());
		
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(client.getClientMail());
		
		//dao
		//insert 후 delete 되기 전 db에 문제가 생기면? -> insert롤백 -> 트랜잭션처리
		ordersDao.insertOrders(orders);
		cartDao.deleteCart(cart);
		
		response.sendRedirect(request.getContextPath()+"/OrdersListController");
	}


}
