package fr.imie.supcrowdfunder.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.User;

public class LoginForm {

	private static final String EMAIL_FIELD = "email";
	private static final String PASS_FIELD = "password";
	private static final String ERROR_FIELD = "error";

	private static String result;

	private Map<String, String> errors = new HashMap<String, String>();

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	/**
	 * Log the user.
	 * 
	 * @param request
	 * @return
	 */
	public User logUser(HttpServletRequest request) {
		String email = getFieldValue(request, EMAIL_FIELD);
		String password = getFieldValue(request, PASS_FIELD);

		User user = null;

		try {
			validateEmail(email);
		} catch (Exception e) {
			errors.put(EMAIL_FIELD, e.getMessage());
		}

		try {
			validatePassword(password);
		} catch (Exception e) {
			errors.put(PASS_FIELD, e.getMessage());
		}

		try {
			user = checkIfEmailAndPasswordMatch(email, password);
		} catch (Exception e) {
			errors.put(ERROR_FIELD, e.getMessage());
		}

		if (errors.isEmpty()) {
			result = "Connection success.";
		} else {
			result = "Connection failed.";
		}

		return user;
	}

	public User checkIfEmailAndPasswordMatch(String email, String password) throws Exception {
		ArrayList<Object> users = (ArrayList<Object>) DaoFactory.getBaseDao(User.class).findByAttribute("email", email);

		if (!users.isEmpty()) {
			User foundUser = (User) users.get(0);

			if (!foundUser.getPassword().equals(password)) {
				throw new Exception("Wrong password.");
			}

			return foundUser;

		} else {
			throw new Exception("Email doesn't exists.");
		}
	}

	/**
	 * Validate the email.
	 */
	private void validateEmail(String email) throws Exception {
		if (email != null && !email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Check your email spelling and try again with a valid email.");
		}
	}

	/**
	 * Validate the password.
	 */
	private void validatePassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Please, at least 3 character for the password.");
			}
		} else {
			throw new Exception("Please, enter your password.");
		}
	}

	/**
	 * Get the fiel value.
	 * 
	 * @param request
	 *            HttpServletRequest.
	 * @param fieldName
	 *            String.
	 * @return
	 */
	private static String getFieldValue(HttpServletRequest request, String fieldName) {
		String value = request.getParameter(fieldName);
		if (value == null || value.trim().length() == 0) {
			return null;
		} else {
			return value;
		}
	}

}
