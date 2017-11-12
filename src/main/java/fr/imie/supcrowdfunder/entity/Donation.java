/**
 * 
 */
package fr.imie.supcrowdfunder.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

/**
 * @author jerome
 *
 */
@Entity
@XmlRootElement(name="donation")
public class Donation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private float amount;
	
//	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="user_fk")
	private User donator;
	
	@ManyToOne
	@JoinColumn(name="project_fk")
	private Project project;

	/**
	 * Initiates a donation.
	 */
	public Donation() {
	}

	/**
	 * @return the donator
	 */
	public User getDonator() {
		return donator;
	}

	/**
	 * @param donator the donator to set
	 */
	public void setDonator(User donator) {
		this.donator = donator;
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the createdAt
	 */
	public DateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
