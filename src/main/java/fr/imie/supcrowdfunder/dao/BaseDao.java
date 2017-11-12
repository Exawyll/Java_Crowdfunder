/**
 * 
 */
package fr.imie.supcrowdfunder.dao;

import java.util.Collection;
import java.util.HashMap;

/**
 * @author jerome
 *
 */
public interface BaseDao {

    /**
     * Return all elements of a type
     * 
     * @return list of elements
     */
    public Collection<Object> findAll();

    /**
     * Find an element by id
     * 
     * @param id
     *            : id of the element to find
     * @return element found or null
     */
    public Object findById( Long id );

    /**
     * Find elements by an attribute
     * 
     * @param attribute
     *            : name of the attribute
     * @param value
     *            : value of the attribute
     * @return
     */
    public Collection<Object> findByAttribute( String attribute, String value );

    /**
     * add the element in db
     * 
     * @param entity
     *            : element to add
     * @return element added
     */
    public Object add( Object entity );

    /**
     * Update an element
     * 
     * @param id
     *            : id of the element to update
     * @return element updated
     */
    public Object update( Object entity );

    /**
     * Remove an element
     * 
     * @param id
     *            : id of the element to remove
     * @return true if removed, otherwise false
     */
    public boolean remove( Long id );

    /**
     * Execute a custom query; query format must be like jpql queries
     * 
     * @param query
     *            : query to execute
     * @param params
     *            : query parameters
     * @return query result
     */
    public Collection<Object> customQuery( String queryString, HashMap<String, String> params );

    /**
     * Check if attribute already exists in DB.
     * 
     * @param attribute
     *            : name of the attribute
     * @param value
     *            : value of the attribute
     * @return
     */
    public boolean checkIfExists( String attribute, String value );
}
