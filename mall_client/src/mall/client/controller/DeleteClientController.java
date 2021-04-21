package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.*;
import mall.client.vo.Client;

@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {
	private ClientDao clientDao;
	private CartDao cartDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 정보 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")==null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//수집
		String clientMail = request.getParameter("clientMail");
		
		//forward
		request.setAttribute("clientMail", clientMail);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/client/deleteClient.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//수집
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		System.out.println("삭제할 clientMail-> "+clientMail);
		System.out.println("삭제할 clientPw-> "+clientPw);
		
		//dao호출
		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		
		int delCnt = 0;
		this.clientDao = new ClientDao();
		delCnt = this.clientDao.deleteClient(client);
		//delCnt == 1; 삭제 완료, delCnt == 0; 삭제 실패 
		if(delCnt == 0) {
			//비밀번호 일치하지 않으면 삭제 하지 않고 페이지 유지
			response.sendRedirect(request.getContextPath()+"/DeleteClientController?clientMail="+clientMail);
			System.out.println("*비밀번호가 일치하지 않습니다.*");
			System.out.println();
			return;
		}
		
		//장바구니에 찜해놓은 책 있으면 삭제하기
		this.cartDao = new CartDao();
		this.cartDao.deleteCartByRemovedClient(client);
		System.out.println("*장바구니 비우기 완료*");
		
		//세션 초기화하고 index페이지로 이동하기
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("*회원탈퇴 완료*");
		System.out.println();
		
		response.sendRedirect(request.getContextPath()+"/IndexController");
		
	}

}
