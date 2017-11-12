package fr.imie.supcrowdfunder.servlet.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.User;

@WebServlet(urlPatterns = "/auth/removeProject")
public class RemoveProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_SESSION_USER = "userSession";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			Long id = Long.parseLong(req.getParameter("id"));

			DaoFactory.getBaseDao(Project.class).remove(id);

			resp.sendRedirect("/SupCrowdfunder/listProjects");
		}
	}
}
