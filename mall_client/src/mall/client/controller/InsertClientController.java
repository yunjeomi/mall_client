package mall.client.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;


@WebServlet("/InsertClientController")
public class InsertClientController extends HttpServlet {
	//하나의 컨트롤러로 2개의 방식을 처리한다.
	private ClientDao clientDao;
	
	// get; 주소창/a태그/폼 : c -> v
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {	//로그인 되어 있을 경우에
			response.sendRedirect(request.getContextPath()+"/IndexController");		//인덱스로 넘어가라
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/client/insertClient.jsp");
		rd.forward(request, response);
	}

	// post; action : c -> m (중간에 v가 없음. m끝난 후 또 다른 c를 부른다. redirect로)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request 분석
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		System.out.println("입력한 clientMail-> "+clientMail);
		System.out.println("입력한 clientPw-> "+clientPw);
		
		//model 호출
		clientDao = new ClientDao();
		
		//mail중복체크 메소드 실행
		String returnClientMail = this.clientDao.checkClientMail(clientMail);
		if(returnClientMail != null) {	//중복된 mail 있으면 회원가입 창으로 돌아가라.
			System.out.println("*mail중복*");
			response.sendRedirect(request.getContextPath()+"/InsertClientController"); //돌아가야 할 위치
			return;
		}
		
		//mail중복이 아니면 회원가입 진행. 
		//mail, pw를 client객체에 넣어주자.
		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		
		//회원가입 메소드 실행
		this.clientDao.insertClient(client);
		System.out.println("*회원가입 완료*");
		System.out.println();
		
		//회원가입 후 로그인 페이지로 이동
		response.sendRedirect(request.getContextPath()+"/IndexController");
		
		
	}

}
