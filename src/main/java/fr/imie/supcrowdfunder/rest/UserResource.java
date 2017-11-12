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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.util.WSUserAuthentication;

/**
 * @author jerome
 *
 */
@Path("/users")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User addUser(User user, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				DaoFactory.getBaseDao(User.class).add(user);
			} catch (Exception e) {
				return null;
			}

			return user;
		} else {
			return null;
		}
	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<User> getAllUsersInXml(@QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all users from database
			Collection<Object> objectList = DaoFactory.getBaseDao(User.class).findAll();

			List<User> userList = new ArrayList<User>();

			for (Object object : objectList) {
				userList.add((User) object);
			}
			return userList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsersInJson(@QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all users from database
			Collection<Object> objectList = DaoFactory.getBaseDao(User.class).findAll();

			List<User> userList = new ArrayList<User>();

			for (Object object : objectList) {
				userList.add((User) object);
			}
			return userList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/xml/{idBooster}")
	@Produces(MediaType.APPLICATION_XML)
	public User getUserInXml(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			User user = (User) DaoFactory.getBaseDao(User.class).findById(id);
			return user;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json/{idBooster}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserInJson(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			User user = (User) DaoFactory.getBaseDao(User.class).findById(id);
			return user;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(User user, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token)
				&& WSUserAuthentication.getUser(token).getEmail() == user.getEmail()) {
			try {
				user = (User) DaoFactory.getBaseDao(User.class).update(user);
			} catch (Exception e) {
				return null;
			}

			return user;
		}
		return null;
	}

	@DELETE
	@Path("/{idBooster}")
	public String removeUser(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				DaoFactory.getBaseDao(User.class).remove(id);
			} catch (Exception e) {
				return "NOK";
			}
			return "OK";
		} else {
			return null;
		}

	}

}
