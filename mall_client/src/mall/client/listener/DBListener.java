package mall.client.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DBListener implements ServletContextListener {
	//톰캣이 꺼질 때
    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("contextDestroyed!");	//눈으로 확인 할 수 없음!!!
    }

	//톰캣이 켜질 때
    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("mariadb 로딩 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("mariadb 로딩 실패");
			e.printStackTrace();
		}	//finally로 할 것은 없음
    }
	
}
