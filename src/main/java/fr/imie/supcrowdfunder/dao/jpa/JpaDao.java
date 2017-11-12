/**
 * 
 */
package fr.imie.supcrowdfunder.dao.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.imie.supcrowdfunder.dao.BaseDao;

/**
 * @author jerome
 *
 */
public class JpaDao implements BaseDao {

    private EntityManagerFactory emf;
    private Class<Object>        entityClass;

    @SuppressWarnings( "unchecked" )
    public JpaDao( EntityManagerFactory emf, Object objectClass ) {
        super();
        this.emf = emf;

        try {
            this.entityClass = (Class<Object>) objectClass;
        } catch ( ClassCastException e ) {
            throw e;

        }
    }

    @SuppressWarnings( "unchecked" )
    public Collection<Object> findAll() {

        Collection<Object> items = new ArrayList<Object>();

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();

            em.createQuery( "FROM " + entityClass.getSimpleName() ).getResultList().forEach( item -> {
                items.add( item );
            } );
            t.commit();
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return items;
    }

    public Object findById( Long id ) {

        Object entity = null;

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            entity = (Object) em.find( entityClass, id );
            t.commit();
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return entity;
    }

    @SuppressWarnings( "unchecked" )
    public Collection<Object> findByAttribute( String attribute, String value ) {

        Collection<Object> items = new ArrayList<Object>();

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Query query = em.createQuery( "FROM " + entityClass.getSimpleName() + " WHERE " + attribute + " = :value" );
            query.setParameter( "value", value );

            query.getResultList().forEach( item -> {
                items.add( item );
            } );

            t.commit();
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return items;
    }

    public Object add( Object entity ) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist( entity );
            t.commit();
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return entity;
    }

    public Object update( Object entity ) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.merge( entity );
            t.commit();
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return entity;
    }

    public boolean remove( Long id ) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        Object entity = null;

        try {
            t.begin();
            entity = (Object) em.find( entityClass, id );
            em.remove( entity );
            t.commit();
        } catch ( Exception e ) {
            return false;
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return true;
    }

    @SuppressWarnings( "unchecked" )
    public Collection<Object> customQuery( String queryString, HashMap<String, String> params ) {

        Collection<Object> items = new ArrayList<Object>();

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Query query = em.createQuery( queryString );
            Iterator<Entry<String, String>> it = params.entrySet().iterator();
            while ( it.hasNext() ) {
                @SuppressWarnings( "rawtypes" )
                Map.Entry pair = (Map.Entry) it.next();
                query.setParameter( (String) pair.getKey(), (String) pair.getValue() );
                it.remove();
            }
            items = query.getResultList();
            t.commit();
        } catch ( Exception e ) {
            return null;
        } finally {
            if ( t.isActive() )
                t.rollback();
            em.close();
        }
        return items;
    }

    public boolean checkIfExists( String attribute, String value ) {
        EntityManager em = emf.createEntityManager();

        Query checkExists = em.createQuery( "SELECT COUNT(" + attribute + ") FROM " + entityClass.getSimpleName()
                + " WHERE " + attribute + " = :param" );
        checkExists.setParameter( "param", value );
        long matchCounter = 0;
        matchCounter = (Long) checkExists.getSingleResult();
        if ( matchCounter > 0 ) {
            return true;
        }
        return false;

    }

}
