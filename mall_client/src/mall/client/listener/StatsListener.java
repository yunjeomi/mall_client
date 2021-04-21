package mall.client.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import mall.client.model.StatsDao;

@WebListener
public class StatsListener implements HttpSessionListener {
	private StatsDao statsDao;
	
	//새로운 세션이 생성되면 실행
    public void sessionCreated(HttpSessionEvent se)  { 
        //1. 오늘 날짜의 카운트가 없으면 1을 insert
    	//2. 오늘 날짜의 카운트가 있으면 +1을 업데이트
    	
    	if(se.getSession().isNew()) {
	    	System.out.println("새로운 세션이 생성 되었습니다.");
	    	this.statsDao = new StatsDao();
	    	if(this.statsDao.statsByToday() == null) {
	    		this.statsDao.insertStats();
	    	} else {
	    		this.statsDao.updateStats();
	    	}
    	}
    }

	//세션이 닫혔을 때
    public void sessionDestroyed(HttpSessionEvent se)  { 
         
    }
	
}
