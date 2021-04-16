package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.*;
import mall.client.vo.*;


@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {
	private OrdersDao ordersDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//수집
		Client client = (Client)session.getAttribute("loginClient");
		int clientNo = client.getClientNo();
		
		//dao
		this.ordersDao = new OrdersDao();
		List<Map<String, Object>> ordersList = this.ordersDao.ordersListByClient(clientNo);
		request.setAttribute("ordersList", ordersList);
		
		//forward
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
	}


}
