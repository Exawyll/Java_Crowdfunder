package fr.imie.supcrowdfunder.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.User;

public class PersistUserForm {

	private static final String EMAIL_FIELD = "email";
	private static final String PASS_FIELD = "password";
	private static final String ERROR_FIELD = "error";
	private static final String CONFIRM_FIELD = "confirm";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LASTNAME_FIELD = "lastname";
	private static final String UPDATE_ACTION = "update";
	private static final String CREATE_ACTION = "create";

	private static String result;

	private Map<String, String> errors = new HashMap<String, String>();

	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public User persistUser(HttpServletRequest request, String action) {
		String email = getFieldValue(request, EMAIL_FIELD);
		String password = getFieldValue(request, PASS_FIELD);
		String confirmation = getFieldValue(request, CONFIRM_FIELD);
		String firstname = getFieldValue(request, FIRSTNAME_FIELD);
		String lastname = getFieldValue(request, LASTNAME_FIELD);

		User user = new User();

		try {
			validateEmail(email);
		} catch (Exception e) {
			setError(EMAIL_FIELD, e.getMessage());
		}
		user.setEmail(email);

		try {
			validatePassword(password, confirmation);
		} catch (Exception e) {
			setError(PASS_FIELD, e.getMessage());
			setError(CONFIRM_FIELD, null);
		}
		user.setPassword(password);

		try {
			validateName(firstname);
		} catch (Exception e) {
			setError(FIRSTNAME_FIELD, e.getMessage());
		}
		user.setFirstname(firstname);

		try {
			validateName(lastname);
		} catch (Exception e) {
			setError(FIRSTNAME_FIELD, e.getMessage());
		}
		user.setLastname(lastname);

		if (errors.isEmpty()) {

			switch (action) {
			case UPDATE_ACTION:
				try {
					updateUser(user);
				} catch (Exception e) {
					setError(ERROR_FIELD, e.getMessage());
				}

				result = "The user have been successfully updated.";
				break;
			case CREATE_ACTION:
				try {
					createUser(user);
				} catch (Exception e) {
					setError(ERROR_FIELD, e.getMessage());
				}

				result = "The user have been successfully created.";
				break;
			}
		}

		if (errors.isEmpty()) {
			result = "Success.";
		} else {
			result = "Failure.";
		}

		return user;
	}

	private void createUser(User user) throws Exception {
		User obj = (User) DaoFactory.getBaseDao(User.class).add(user);

		if (obj == null || !(obj instanceof User)) {
			throw new Exception("Database error creating the user.");
		}
	}

	private void updateUser(User user) throws Exception {
		Object obj = DaoFactory.getBaseDao(User.class).update(user);

		if (obj == null || !(obj instanceof User)) {
			throw new Exception("Database error updating the user.");
		}
	}

	private void validateEmail(String email) throws Exception {

		boolean alreadyExists = false;

		if (email != null) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Please, enter a valid email address.");
			}

			alreadyExists = DaoFactory.getBaseDao(User.class).checkIfExists("email", email);

			if (alreadyExists) {
				throw new Exception("This email already exists.");
			}
		} else {
			throw new Exception("Please, fill the email field.");
		}
	}

	private void validatePassword(String password, String confirmation) throws Exception {
		if (password != null && confirmation != null) {
			if (!password.equals(confirmation)) {
				throw new Exception("The passwords do not match.");
			} else if (password.length() < 3) {
				throw new Exception("Please, at least 3 character for the password.");
			}
		} else {
			throw new Exception("Please, you need to enter a password and confirm.");
		}
	}

	private void validateName(String nom) throws Exception {

		if (nom != null && nom.length() < 3) {
			throw new Exception("The firstname or lastname should be at least 2 character.");
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

	private void setError(String field, String message) {
		errors.put(field, message);
	}
}
