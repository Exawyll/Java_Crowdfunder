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
import fr.imie.supcrowdfunder.entity.Category;
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.util.WSUserAuthentication;

/**
 * @author jerome
 *
 */
@Path("/categories")
public class CategoryResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Category addCategory(Category category, @QueryParam("token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				DaoFactory.getBaseDao(Category.class).add(category);
			} catch (Exception e) {
				return null;
			}

			return category;
		} else {
			return null;
		}

	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Category> getAllCategoriesInXml(@QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all categories from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Category.class).findAll();

			List<Category> categoryList = new ArrayList<Category>();

			for (Object object : objectList) {
				categoryList.add((Category) object);
			}
			return categoryList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategoriesInJson(@QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			// get all categories from database
			Collection<Object> objectList = DaoFactory.getBaseDao(Category.class).findAll();

			List<Category> categoryList = new ArrayList<Category>();

			for (Object object : objectList) {
				categoryList.add((Category) object);
			}
			return categoryList;
		} else {
			return null;
		}
	}

	@GET
	@Path("/xml/{idBooster}")
	@Produces(MediaType.APPLICATION_XML)
	public Category getCategoryInXml(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			Category category = (Category) DaoFactory.getBaseDao(Category.class).findById(id);
			return category;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json/{idBooster}")
	@Produces(MediaType.APPLICATION_JSON)
	public Category getCategoryInJson(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			Category category = (Category) DaoFactory.getBaseDao(Category.class).findById(id);
			return category;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Category updateCategory(Category category, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				category = (Category) DaoFactory.getBaseDao(Category.class).update(category);
			} catch (Exception e) {
				return null;
			}

			return category;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{idBooster}")
	public String removeCategory(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			Collection<Object> objectList = DaoFactory.getBaseDao(Project.class).findByAttribute("category_fk",
					id.toString());
			if (objectList.isEmpty()) {
				try {
					DaoFactory.getBaseDao(Category.class).remove(id);
				} catch (Exception e) {
					return "NOK";
				}
				return "OK";
			} else {
				return "FORBIDEN";
			}
		} else {
			return null;
		}
	}
}
