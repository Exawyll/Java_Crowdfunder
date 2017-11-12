/**
 * 
 */
package fr.imie.supcrowdfunder.entity;

import java.io.Serializable;
import java.security.Key;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 * @author jerome
 *
 */
@Entity
@XmlRootElement(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Key key = MacProvider.generateKey();
	private static final String ADMIN = "admin@admin.admin";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstname;

	private String lastname;

	private String password;

	@OneToOne
	@JoinColumn(name = "token_fk")
	private Token token;

	@Column(unique = true)
	private String email;

	@OneToMany(mappedBy = "donator")
	private Collection<Donation> donations;

	@OneToMany(mappedBy = "creator")
	private Collection<Project> projects;

	/**
	 * Initiates a user.
	 */
	public User() {
	}

	public User(String firstname, String lastname, String password, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
	}

	/**
	 * @return the donations
	 */
	@XmlTransient
	public Collection<Donation> getDonations() {
		return donations;
	}

	/**
	 * @param donations
	 *            the donations to set
	 */
	public void setDonations(Collection<Donation> donations) {
		this.donations = donations;
	}

	/**
	 * @return the projects
	 */
	@XmlTransient
	public Collection<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects
	 *            the projects to set
	 */
	public void setProjects(Collection<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Token generateToken() {

		String compactJws = Jwts.builder().setSubject(this.email).signWith(SignatureAlgorithm.HS512, key).compact();

		return new Token(compactJws);
	}

	public static Key getKey() {
		return key;
	}

	public boolean verifyToken(Token token) {
		if (Jwts.parser().setSigningKey(key).parseClaimsJws(token.getContent()).getBody().getSubject()
				.equals(this.email)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAdmin() {
		return this.email.equals(new String(ADMIN));
	}

}
