/**
 * 
 */
package fr.imie.supcrowdfunder.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author jerome
 *
 */
@Entity
@XmlRootElement( name = "category" )
public class Category implements Serializable {

    /**
     * 
     */
    private static final long   serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long                id;

    private String              name;

    @OneToMany( mappedBy = "category", cascade = CascadeType.ALL )
    private Collection<Project> projects;

    /**
     * Initiates a category.
     */
    public Category() {
    }

    /**
     * @return the projects
     */
    @XmlTransient
    public Collection<Project> getProjects() {
        return projects;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProjects( Collection<Project> projects ) {
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
    public void setId( Long id ) {
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
    public void setName( String name ) {
        this.name = name;
    }

}
