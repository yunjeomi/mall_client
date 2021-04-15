package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.vo.Client;


@WebServlet("/ClientOneController")
public class ClientOneController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 안 되어있을 경우 강제 이동
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/WEB-INF/view/index.jsp");
			return;
		}
		
		//세션값 가져오기
		Client client = (Client)session.getAttribute("loginClient");
		System.out.println("client정보-> "+client);
		
		//값 넣어주기
		request.setAttribute("clientOne", client);
		request.getRequestDispatcher("/WEB-INF/view/client/clientOne.jsp").forward(request, response);
	}

}
