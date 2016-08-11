package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contestant;
import util.Contest;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Contestant> contestant_list = null;
		if (request.getSession().getAttribute("contestant_list") == null) {
			contestant_list = new ArrayList<>();
		}else {
			contestant_list = (List<Contestant>) request.getSession().
					getAttribute("contestant_list");			
		}
		
		String name = request.getParameter("name");
		String className = request.getParameter("classname");
		String gender = request.getParameter("selection");
		Contest contest = (Contest) this.getServletContext().getAttribute("contest");
		Contestant contestant = new Contestant(name, className, gender, contest);
		contestant_list.add(contestant);
		
		request.getSession().setAttribute("contestant_list", contestant_list);
		response.sendRedirect("add.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
