package fr.imie.supcrowdfunder.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.imie.supcrowdfunder.adapter.DateTimeAdapter;

/**
 * @author jerome
 *
 */
@Entity
@XmlRootElement(name = "project")
public class Project implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime createdAt;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime endsAt;

	@Column(columnDefinition = "MEDIUMTEXT")
	private String description;

	private Float objective;

	private String picture;

	@ManyToOne
	@JoinColumn(name = "category_fk")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User creator;

	@OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<Donation> donations;

	/**
	 * Initiates a project.
	 */
	public Project() {
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @return the donations
	 */
	public Collection<Donation> getDonations() {
		return donations;
	}

	/**
	 * @return totalDonations total donations amount
	 */
	public Float getDonationsTotal() {
		Float totalDonations = new Float(0);
		for (Donation donation : donations) {
			totalDonations += donation.getAmount();
		}
		return totalDonations;
	}

	public Float getGoal() {
		Float goal = new Float(0);
		Float actual = this.getObjective();
		if (actual != 0) {
			goal = getDonationsTotal() * 100 / actual;
		} else {
			goal = (float) 100;
		}
		return goal;
	}

	/**
	 * @param donations
	 *            the donations to set
	 */
	public void setDonations(Collection<Donation> donations) {
		this.donations = donations;
	}

	/**
	 * @param donation
	 *            the donation to add
	 */
	public void addDonation(Donation donation) {
		this.donations.add(donation);
	}

	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createdAt
	 */
	@XmlElement
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	public DateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @return the CreatedAt
	 */
	public String affCreatedAt() {

		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("EEE dd MMMM yyyy");
		dtfOut.getLocale();
		return dtfOut.withLocale(Locale.ENGLISH).print(createdAt);
	}

	/**
	 * @param dateTime
	 *            the createdAt to set
	 */
	public void setCreatedAt(DateTime dateTime) {
		this.createdAt = dateTime;
	}

	/**
	 * @return the endsAt
	 */
	@XmlElement
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	public DateTime getEndsAt() {
		return endsAt;
	}

	/**
	 * @return the endsAt
	 */
	public String affEndsAt() {

		DateTimeFormatter dtfOut = DateTimeFormat.forPattern("EEE dd MMMM yyyy");
		dtfOut.getLocale();
		return dtfOut.withLocale(Locale.ENGLISH).print(endsAt);
	}

	/**
	 * @param endsAt
	 *            the endsAt to set
	 */
	public void setEndsAt(DateTime completedAt) {
		this.endsAt = completedAt;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the objective
	 */
	public Float getObjective() {
		return objective;
	}

	/**
	 * @param objective
	 *            the objective to set
	 */
	public void setObjective(Float objective) {
		this.objective = objective;
	}

	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * @param picture
	 *            the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

}
