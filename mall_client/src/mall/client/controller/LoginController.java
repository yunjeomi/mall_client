package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private ClientDao clientDao;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient")!=null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//한글 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//로그인 정보 넣어주기
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		System.out.println("clientMail-> "+clientMail);
		System.out.println("clientPw-> "+clientPw);
		
		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		
		//로그인 메소드 실행
		this.clientDao = new ClientDao();
		Client returnClient = this.clientDao.login(client);
		
		//로그인 정보 입력 시 세션에 정보를 넣어준다.
		if(returnClient != null) {
			session.setAttribute("loginClient", returnClient);
		}
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}
