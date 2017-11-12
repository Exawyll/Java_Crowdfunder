/**
 * 
 */
package fr.imie.supcrowdfunder.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.imie.supcrowdfunder.util.PersistenceManager;

/**
 * @author jerome
 *
 */
@WebListener
public class PersistenceAppListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent evt) {
		PersistenceManager.closeEntityManagerFactory();
	}

	public void contextInitialized(ServletContextEvent evt) {

	}
}
