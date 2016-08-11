package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Contest;
import util.ContestantManager;

/**
 * Servlet implementation class SetContestParam
 */
@WebServlet("/SetContestParam")
public class SetContestParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetContestParam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String startTime = request.getParameter("startTime");
		String duration = request.getParameter("duration");
		String contestName = request.getParameter("contestName");
		int questionNums = Integer.parseInt(request.getParameter("questionNums"));
		
		Contest contest = new Contest(contestName, startTime, duration, questionNums);
		
		//将contest对象放入application中保存
		this.getServletContext().setAttribute("contest", contest);
		ContestantManager.setContest(contest);
		response.sendRedirect("add.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
