package fr.imie.supcrowdfunder.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.forms.PersistUserForm;

@WebServlet(urlPatterns = "/auth/editProfil")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String ACTION = "update";
	public static final String VIEW = "/auth/editUser.jsp";
	public static final String ATT_SESSION_USER = "userSession";
	public static final String ATT_USER = "user";
	public static final String ATT_FORM = "form";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User u = (User) req.getSession().getAttribute(ATT_SESSION_USER);

		req.setAttribute("u", u);

		if (u != null) {
			RequestDispatcher rd = req.getRequestDispatcher("/auth/editUser.jsp");
			rd.forward(req, resp);
		} else {
			resp.getWriter().println("No user with id ");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PersistUserForm form = new PersistUserForm();

		User user;

		user = form.persistUser(req, ACTION);

		if (form.getErrors().isEmpty()) {
			HttpSession session = req.getSession();

			session.setAttribute(ATT_FORM, form);
			session.setAttribute(ATT_USER, user);
			doGet(req, resp);
		} else {
			this.getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
		}
	}

}
