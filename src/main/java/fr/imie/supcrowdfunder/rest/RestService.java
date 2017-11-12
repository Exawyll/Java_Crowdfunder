/**
 * 
 */
package fr.imie.supcrowdfunder.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author jerome
 *
 */
@ApplicationPath("/rest")
public class RestService extends Application {
	public RestService() {
	}

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> returnValue = new HashSet<Class<?>>();
		returnValue.add(ProjectResource.class);
		returnValue.add(CategoryResource.class);
		returnValue.add(UserResource.class);
		returnValue.add(DonationResource.class);
		returnValue.add(TokenResource.class);
		return returnValue;
	}
}
