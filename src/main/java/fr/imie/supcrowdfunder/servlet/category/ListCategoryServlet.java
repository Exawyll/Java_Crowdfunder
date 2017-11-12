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
import fr.imie.supcrowdfunder.entity.User;

/**
 * @author WYLLIAM
 *
 */
@WebServlet(urlPatterns = "/auth/listCategories")
public class ListCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SESSION_USER = "userSession";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			// get all categories from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Category.class).findAll();

			List<Category> categoryList = new ArrayList<Category>();

			for (Object object : objectList) {
				categoryList.add((Category) object);
			}

			req.setAttribute("categories", categoryList);

			RequestDispatcher rd = req.getRequestDispatcher("/auth/listCategories.jsp");
			rd.forward(req, resp);
		}
	}
}
