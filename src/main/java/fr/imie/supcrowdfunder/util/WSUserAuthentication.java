/**
 * 
 */
package fr.imie.supcrowdfunder.util;

import java.util.ArrayList;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Token;
import fr.imie.supcrowdfunder.entity.User;
import io.jsonwebtoken.Jwts;

/**
 * @author jerome
 *
 */
public class WSUserAuthentication {

	public static Boolean processToken(Token token) {
		String email = null;
		try {
			email = Jwts.parser().setSigningKey(User.getKey()).parseClaimsJws(token.getContent()).getBody()
					.getSubject();
		} catch (Exception e) {
			return false;
		}

		User foundUser = null;

		ArrayList<Object> users = (ArrayList<Object>) DaoFactory.getBaseDao(User.class).findByAttribute("email", email);

		if (!users.isEmpty()) {
			foundUser = (User) users.get(0);
			if (Jwts.parser().setSigningKey(User.getKey()).parseClaimsJws(token.getContent()).getBody().getSubject()
					.equals(foundUser.getEmail())) {
				return true;
			} else {
				return false;
			}
		}
		return false;

	}

	public static User getUser(Token token) {
		String email = null;
		try {
			email = Jwts.parser().setSigningKey(User.getKey()).parseClaimsJws(token.getContent()).getBody()
					.getSubject();
		} catch (Exception e) {
			return null;
		}

		User foundUser = null;

		ArrayList<Object> users = (ArrayList<Object>) DaoFactory.getBaseDao(User.class).findByAttribute("email", email);

		if (!users.isEmpty()) {
			foundUser = (User) users.get(0);
		}
		return foundUser;

	}

}
