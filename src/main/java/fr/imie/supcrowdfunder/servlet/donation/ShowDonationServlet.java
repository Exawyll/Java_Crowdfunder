package fr.imie.supcrowdfunder.servlet.donation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Donation;
import fr.imie.supcrowdfunder.entity.User;

/**
 * Servlet implementation class ShowDonationServlet
 */
@WebServlet(urlPatterns = "/showDonation")
public class ShowDonationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATT_SESSION_USER = "userSession";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User u = (User) request.getSession().getAttribute(ATT_SESSION_USER);
		if (u.isAdmin()) {
			Long id = Long.parseLong(request.getParameter("id"));
			Donation donation = (Donation) DaoFactory.getBaseDao(Donation.class).findById(id);

			request.setAttribute("donation", donation);

			if (donation != null) {
				// TODO: generate view for one donation "showDonation.jsp"
				RequestDispatcher rd = request.getRequestDispatcher("/views/donation/showDonation.jsp");
				rd.forward(request, response);
			} else {
				// TODO: redirect to noDOnation.jsp
				response.getWriter().println("No donation with id " + id);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
