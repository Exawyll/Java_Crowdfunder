/**
 * 
 */
package fr.imie.supcrowdfunder.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.forms.LoginForm;
import fr.imie.supcrowdfunder.util.Credentials;

/**
 * @author jerome
 *
 */
@Path("/tokens")
public class TokenResource {
	@POST
	@Path("/get")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Token authenticateUser(Credentials credentials) {

		String email = credentials.getEmail();
		String password = credentials.getPassword();

		LoginForm form = new LoginForm();
		User user;
		try {
			user = form.checkIfEmailAndPasswordMatch(email, password);
		} catch (Exception e) {
			return null;
		}

		return user.generateToken();

	}

}
