package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ContestantDao;
import model.Contestant;
import util.Contest;
import util.ContestantManager;

/**
 * Servlet implementation class WriteDate
 */
@WebServlet("/WriteDate")
public class WriteDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteDate() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Contestant> contestant_list = null;
		if (request.getSession().getAttribute("contestant_list") == null) {
			contestant_list = new ArrayList<>();
		}else {
			contestant_list = (List<Contestant>) request.getSession().
					getAttribute("contestant_list");			
		}
		
		Contest contest = (Contest) this.getServletContext().getAttribute("contest");
		try {
			//将缓存数据添加到数据库
			new ContestantDao(contest).add(contestant_list);
			//清空添加时的缓存
			request.getSession().setAttribute("contestant_list", null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<Contestant> contestants = null;
		try {
			//从数据库中得到数据
			contestants = new ContestantDao(contest).query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ContestantManager.updateList(contestants);
		this.getServletContext().setAttribute("contestants", contestants);
		response.sendRedirect("controlpage.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
