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


@WebServlet("/UpdateClientPwController")
public class UpdateClientPwController extends HttpServlet {
	private ClientDao clientDao;
	
	//clientOne.jsp에서 변경 클릭했을 때
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 됐는 지 안 됐는 지 확인
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//수집
		String clientMail = request.getParameter("clientMail");
		
		//forward
		request.setAttribute("clientMail", clientMail);
		request.getRequestDispatcher("/WEB-INF/view/client/updateClientPw.jsp").forward(request, response);
	}

	//updateClientPw.jsp에서 정보 넘길 때
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//수집
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		System.out.println("pw 변경하는 사용자의 mail-> "+clientMail);
		System.out.println("변경할 pw-> "+clientPw);
		
		//Dao호출
		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		
		this.clientDao = new ClientDao();
		this.clientDao.updateClientPw(client);
		System.out.println("*비밀번호 변경 완료*");
		
		//세션 초기화 후 index페이지로 이동해라
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("*로그아웃 완료*");
		System.out.println("");
		
		response.sendRedirect(request.getContextPath()+"/IndexController");
		
	}

}
