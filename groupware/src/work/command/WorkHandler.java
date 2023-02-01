package work.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import work.model.Work;
import work.service.DatePg;
import work.service.Page;
import work.service.WorkService;

public class WorkHandler implements CommandHandler {

	private static final String FORM_VIEW = "/view/work/work.jsp";
	
	private WorkService workService = new WorkService();
	
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("WorkHandler process()");
		User authUser = (User)request.getSession().getAttribute("AUTHUSER");
		int emp_no = authUser.getEmp_no();
		Work work = workService.selectIn(emp_no);
		DatePg date = new DatePg();
		
		String strPageYear = request.getParameter("pageYear");
		int pageYear = date.getYear(); //2023
		if(strPageYear != null) {
			pageYear = Integer.parseInt(strPageYear);
		}
		String strPageMon = request.getParameter("pageMon");
		int pageMon = date.getMonth(); //
		if(strPageMon != null) {
			pageMon = Integer.parseInt(strPageMon);
			if(pageMon == 0) {
				pageYear -= 1;
				pageMon = 12;
			}
			if(pageMon == 13) {
				pageYear += 1;
				pageMon = 1;
			}
		}
		List<Work> workList = workService.selectMonth(pageYear, pageMon, emp_no);
		Page pageAtt = new Page(pageYear, pageMon);
		if(work == null) {
			work = new Work(null, null, "퇴근", emp_no, null);
		}
		request.setAttribute("pageAtt", pageAtt);
		request.setAttribute("work", work);
		request.setAttribute("monthList", workList);
		
		//Model
		return FORM_VIEW;
		
		
		
	}

	

}
