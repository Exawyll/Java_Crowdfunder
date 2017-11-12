/**
 * 
 */
package fr.imie.supcrowdfunder.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author jerome
 *
 */
public class PersistenceManager {
	private static EntityManagerFactory emf;
	
	// init
	public static EntityManagerFactory
	getEntityManagerFactory(){
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("PersistenceUnit");
		}
		return emf;
	}
	
	public static void closeEntityManagerFactory(){
		if(emf != null && emf.isOpen()) emf.close();
	}
}


