package mall.client.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


@WebFilter("/*")
// /IndexController -> public/IndexController 등 앞의 폴더같은 이름을 하나 붙여서
//필터가 해당 컨트롤러만 지나갈 수 있도록 한다.
//필터는 Filter 인터페이스를 구현한다. 
public class EncodingFilter implements Filter {

	//servlet의 doGet,goPost는 HttpServletRequest이다. HttpServletRequest는 ServletRequest를 상속받음.
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");
		System.out.println("EncodingFilter실행");
		
		chain.doFilter(request, response);	//위로 적으면 request, 아래로 적으면 response
	}

}
