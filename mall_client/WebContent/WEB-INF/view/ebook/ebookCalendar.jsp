<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ebookCalendar</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	<h1>Ebook Calendar</h1>
<%
	int currentYear = (Integer)request.getAttribute("currentYear");
	int currentMonth = (Integer)request.getAttribute("currentMonth");
	int endDay = (Integer)request.getAttribute("endDay");
	int firstDayOfWeek = (Integer)request.getAttribute("firstDayOfWeek");
	List<Map<String,Object>> ebookListByDay = (List<Map<String,Object>>)(request.getAttribute("ebookListByDay"));
	
	int preYear = currentYear;
	int preMonth = currentMonth-1;
	if(preMonth == 0){	//1월일 때
		preMonth = 12;
		preYear -= 1;
	}
	int nextYear = currentYear;
	int nextMonth = currentMonth+1;
	if(nextMonth == 11){
		nextMonth = 1;
		nextYear += 1;
	}
%>

	<!-- n행 7열 -->
	<div>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=preYear %>&currentMonth=<%=preMonth%>">이전 달</a>
		<span><%=currentYear %>년</span>
		<span><%=currentMonth %>월</span>
		<a href="<%=request.getContextPath()%>/EbookCalendarController?currentYear=<%=nextYear %>&currentMonth=<%=nextMonth%>">다음 달</a>
	</div>
	<table border="1" width="50%">
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
<%
			for(int i=1; i<firstDayOfWeek; i++){	//캘린더 1일 이전 공백
%>
				<td>&nbsp;</td>
<%
			}

			for (int i = 1; i <= endDay; i++) {	//캘린더 1일부터 출력
%>
					<td>
						<%=i%> <!-- 날짜 ~일 -->
						<%
							for(Map m : ebookListByDay) {
								if(i == (Integer)m.get("d")) {
						%>
									<div>
										<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=m.get("ebookNo")%>">
											<%
												String ebookTitle = (String)m.get("ebookTitle");
												if(ebookTitle.length() > 10) {
											%>
													<%=ebookTitle.substring(0, 10)%>...
											<%		
												} else {
											%>
													<%=ebookTitle%>
											<%		
												}
											%>
										</a>
									</div>
						<%	
								}
							}
						%>
					</td>
<%
						if (firstDayOfWeek % 7 == 0) {	//토요일에 줄 바꿔주기
%>
							</tr>
							<tr>
<%
						}
					firstDayOfWeek += 1;
			}
			
			firstDayOfWeek -= 1;	//위에 더해준 마지막 +1을 다시 원래대로 되돌린다.
			
			if((firstDayOfWeek%7) != 0){	//끝요일이 토요일인 경우 밑에 tr이 한줄 더 생기는데 없애기 위해
				for(int i=1; i<=7-(firstDayOfWeek%7); i++){	//캘린더 마지막일 이후 공백
%>				
					<td>&nbsp;</td>
<%					
				}
			}
%>
		</tr>
	</table>
</body>
</html>