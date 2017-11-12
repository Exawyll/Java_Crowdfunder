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
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.util.WSUserAuthentication;

/**
 * @author jerome
 *
 */
@Path("/projects")
public class ProjectResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Project addProject(Project project, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token)) {

			try {
				DaoFactory.getBaseDao(Project.class).add(project);
			} catch (Exception e) {
				return null;
			}

			return project;
		} else {
			return null;
		}
	}

	@GET
	@Path("/xml")
	@Produces(MediaType.APPLICATION_XML)
	public List<Project> getAllProjectsInXml() {
		// get all projects from database
		Collection<Object> objectList = DaoFactory.getBaseDao(Project.class).findAll();

		List<Project> projectList = new ArrayList<Project>();

		for (Object object : objectList) {
			projectList.add((Project) object);
		}
		return projectList;
	}

	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjectsInJson() {
		// get all projects from database
		Collection<Object> objectList = DaoFactory.getBaseDao(Project.class).findAll();

		List<Project> projectList = new ArrayList<Project>();

		for (Object object : objectList) {
			projectList.add((Project) object);
		}
		return projectList;
	}

	@GET
	@Path("/xml/{idBooster}")
	@Produces(MediaType.APPLICATION_XML)
	public Project getProjectInXml(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token)) {
			Project project = (Project) DaoFactory.getBaseDao(Project.class).findById(id);
			return project;
		} else {
			return null;
		}
	}

	@GET
	@Path("/json/{idBooster}")
	@Produces(MediaType.APPLICATION_JSON)
	public Project getProjectInJson(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token)) {
			Project project = (Project) DaoFactory.getBaseDao(Project.class).findById(id);
			return project;
		} else {
			return null;
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Project updateProject(Project project, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token)
				&& WSUserAuthentication.getUser(token).getEmail() == project.getCreator().getEmail()) {
			try {
				project = (Project) DaoFactory.getBaseDao(Project.class).update(project);
			} catch (Exception e) {
				return null;
			}

			return project;
		} else {
			return null;
		}
	}

	@DELETE
	@Path("/{idBooster}")
	public String removeProject(@PathParam("idBooster") Long id, @QueryParam("Token") Token token) {
		if (WSUserAuthentication.processToken(token) && WSUserAuthentication.getUser(token).isAdmin()) {
			try {
				DaoFactory.getBaseDao(Project.class).remove(id);
			} catch (Exception e) {
				return "NOK";
			}
			return "OK";
		} else {
			return null;
		}

	}
}
