<%
	if (request.getSession().getAttribute("login") == null || 
			(Boolean)request.getSession().getAttribute("login") == false
			) {
		response.sendRedirect("error.jsp");
	}
%>