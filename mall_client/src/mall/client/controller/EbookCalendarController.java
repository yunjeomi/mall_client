package mall.client.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.vo.Ebook;


@WebServlet("/EbookCalendarController")
public class EbookCalendarController extends HttpServlet {
	private EbookDao ebookDao;	//이달의 신간
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 년/월 매개값
		Calendar dday = Calendar.getInstance();
		if(request.getParameter("currentYear") != null) {
			dday.set(Calendar.YEAR, Integer.parseInt(request.getParameter("currentYear")));
		}
		if(request.getParameter("currentMonth") != null) {
			dday.set(Calendar.MONTH, Integer.parseInt(request.getParameter("currentMonth"))-1);
		}
		int currentYear = dday.get(Calendar.YEAR);
		int currentMonth = dday.get(Calendar.MONTH)+1;	//0~11로 되어있음
		
		dday.set(currentYear, currentMonth-1, 1);
		
		int endDay = dday.getActualMaximum(Calendar.DATE); //이번 달 마지막 날짜 
		int firstDayOfWeek = dday.get(Calendar.DAY_OF_WEEK); //이번 달 1일의 해당 요일(1:일요일 … 7:토요일)
		
		//디버깅
		System.out.println("currentYear-> "+currentYear);
		System.out.println("currentMonth-> "+currentMonth);
		System.out.println("endDay-> "+endDay);
		System.out.println("firstDayOfWeek-> "+firstDayOfWeek);
		
		this.ebookDao = new EbookDao();
		List<Map<String,Object>> ebookListByDay = this.ebookDao.ebookListByDay(currentYear, currentMonth);
		
		//preYear, preMonth, nextYear, nextMonth
		int preYear = currentYear;
		int preMonth = currentMonth-1;
		if(preMonth == 0){	//1월에서 이전 클릭하면 12월로
			preMonth = 12;
			preYear -= 1;
		}
		int nextYear = currentYear;
		int nextMonth = currentMonth+1;
		if(nextMonth == 11){	//12월에서 이후 클릭하면 1월로
			nextMonth = 1;
			nextYear += 1;
		}
		
		//디버깅추가
		System.out.println("preYear-> "+preYear);
		System.out.println("preMonth-> "+preMonth);
		System.out.println("nextYear-> "+nextYear);
		System.out.println("nextMonth-> "+nextMonth);
		
		request.setAttribute("currentYear", currentYear);
		request.setAttribute("currentMonth", currentMonth);
		request.setAttribute("endDay", endDay);
		request.setAttribute("firstDayOfWeek", firstDayOfWeek);
		request.setAttribute("ebookListByDay", ebookListByDay);
		
		request.setAttribute("preYear", preYear);
		request.setAttribute("preMonth", preMonth);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookCalendar.jsp").forward(request, response);
	}

}
