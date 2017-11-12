/**
 * 
 */
package fr.imie.supcrowdfunder.servlet.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

/**
 * @author WYLLIAM
 *
 */
@WebServlet(urlPatterns = "/auth/showCategory")
public class ShowCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SESSION_USER = "userSession";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			Long id = Long.parseLong(req.getParameter("id"));
			Category c = (Category) DaoFactory.getBaseDao(Category.class).findById(id);

			// retrieve all projects related to the category
			Collection<Object> objectList = DaoFactory.getBaseDao(Project.class).findByAttribute("category_fk",
					id.toString());
			List<Project> projectList = new ArrayList<Project>();
			for (Object object : objectList) {
				projectList.add((Project) object);
			}

			req.setAttribute("projects", projectList);
			req.setAttribute("c", c);

			if (c != null) {
				// TODO: generate view for one category "showCategory.jsp"
				RequestDispatcher rd = req.getRequestDispatcher("/auth/viewCategory.jsp");
				rd.forward(req, resp);
			} else {
				resp.getWriter().println("No category with id " + id);
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
