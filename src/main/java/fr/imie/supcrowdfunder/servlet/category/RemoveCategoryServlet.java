package fr.imie.supcrowdfunder.servlet.category;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Category;
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.User;

@WebServlet(urlPatterns = "/auth/removeCategory")
public class RemoveCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SESSION_USER = "userSession";

	private String result = new String();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {

			Long id = Long.parseLong(req.getParameter("id"));
			Collection<Object> objectList = DaoFactory.getBaseDao(Project.class).findByAttribute("category_fk",
					id.toString());
			if (objectList.isEmpty()) {
				DaoFactory.getBaseDao(Category.class).remove(id);
				result = "success";
			} else {
				result = "forbiden";
			}
			req.setAttribute("result", result);

			RequestDispatcher rd = req.getRequestDispatcher("/auth/listCategories");
			rd.forward(req, resp);
		}
	}
}
