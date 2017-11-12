/**
 * 
 */
package fr.imie.supcrowdfunder.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Donation;
import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.util.WSUserAuthentication;

/**
 * @author jerome
 *
 */
@Path("/donations")
public class DonationResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Donation addDonation(Donation donation, @QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token)) {
			try {
				DaoFactory.getBaseDao(Donation.class).add(donation);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}

		return donation;
	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Donation> getAllDonationsInXml(@QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all donations from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Donation.class).findAll();

			List<Donation> donationList = new ArrayList<Donation>();

			for (Object object : objectList) {
				donationList.add((Donation) object);
			}
			return donationList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Donation> getAllDonationsInJson(@QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all donations from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Donation.class).findAll();

			List<Donation> donationList = new ArrayList<Donation>();

			for (Object object : objectList) {
				donationList.add((Donation) object);
			}
			return donationList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/xml/{idBooster}")
	@Produces(MediaType.APPLICATION_XML)
	public Donation getDonationInXml(@PathParam("idBooster") Long id, @QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			Donation donation = (Donation) DaoFactory.getBaseDao(Donation.class).findById(id);
			return donation;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json/{idBooster}")
	@Produces(MediaType.APPLICATION_JSON)
	public Donation getDonationInJson(@PathParam("idBooster") Long id, @QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			Donation donation = (Donation) DaoFactory.getBaseDao(Donation.class).findById(id);
			return donation;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{idBooster}")
	public String removeDonation(@PathParam("idBooster") Long id, @QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				DaoFactory.getBaseDao(Donation.class).remove(id);
			} catch (Exception e) {
				return "NOK";
			}
			return "OK";
		} else {
			return null;
		}

	}
}
