package fr.imie.supcrowdfunder.servlet.donation;

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
import fr.imie.supcrowdfunder.entity.Donation;
import fr.imie.supcrowdfunder.entity.User;

/**
 * Servlet implementation class ListDonation
 */
@WebServlet(urlPatterns = "/listDonation")
public class ListDonationServlet extends HttpServlet {
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
			// get all categories from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Donation.class).findAll();

			List<Donation> donationList = new ArrayList<Donation>();

			for (Object object : objectList) {
				donationList.add((Donation) object);
			}

			request.setAttribute("donations", donationList);

			// TODO: Generate the view for list of donations "listDonations.jsp"
			RequestDispatcher rd = request.getRequestDispatcher("/views/donation/listDonation.jsp");
			rd.forward(request, response);
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
