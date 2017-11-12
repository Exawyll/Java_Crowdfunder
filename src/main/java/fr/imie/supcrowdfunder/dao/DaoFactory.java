/**
 * 
 */
package fr.imie.supcrowdfunder.dao;

import fr.imie.supcrowdfunder.dao.jpa.JpaDao;
import fr.imie.supcrowdfunder.util.PersistenceManager;

/**
 * @author jerome
 *
 */
public class DaoFactory {
	
	//Private constructor prevent instantiation
	private DaoFactory(){}
	
	/**
	 * returns a new instance of JpaDao
	 * @param objectClass
	 * @return JpaDao instance
	 */
	public static BaseDao getBaseDao(Object objectClass) {
	   return new JpaDao(PersistenceManager.getEntityManagerFactory(), objectClass);
	}
}
