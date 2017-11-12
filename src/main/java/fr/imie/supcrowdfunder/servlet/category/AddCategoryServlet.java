package fr.imie.supcrowdfunder.servlet.category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Category;
import fr.imie.supcrowdfunder.entity.User;

@WebServlet(urlPatterns = "/auth/addCategory")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SESSION_USER = "userSession";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			RequestDispatcher rd = req.getRequestDispatcher("/auth/addCategory.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			// Create a new Category object
			Category cat = new Category();
			// Retrieve the form parameters
			// Set the parameters in the object
			cat.setName(req.getParameter("name"));

			DaoFactory.getBaseDao(Category.class).add(cat);

			resp.sendRedirect("/SupCrowdfunder/auth/listCategories");
		}
	}
}
