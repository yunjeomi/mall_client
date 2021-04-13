package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.*;
import mall.client.vo.*;


@WebServlet("/CartListController")
public class CartListController extends HttpServlet {
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 아니면 redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/WEB-INF/view/index.jsp");
			return;
		}
		
		//clientMail값 받아오기 위해
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		System.out.println("clientMail-> "+clientMail);
		
		//dao 호출
		this.cartDao = new CartDao();
		List<Map<String, Object>> cartList = this.cartDao.cartList(clientMail);
		System.out.println("cartList-> "+cartList);
		
		
		//forward
		request.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/cart/cartList.jsp");
		rd.forward(request, response);
		
	}

}
