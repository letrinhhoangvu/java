package vu.springhibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
  
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;

	public static SessionFactory createSessionFactory() {
		try
		{
		     if (sessionFactory == null)
		     {
			    Configuration configuration = new Configuration();
			    java.io.File f = new java.io.File("/home/vulth/workspace/MavenSpringHibernate/src/main/resources/hibernate.cfg.xml");
			    configuration.configure(f);
			    //configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		        ///configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/spring_hibernate");
		        //configuration.setProperty("hibernate.connection.username", "root");
		        //configuration.setProperty("hibernate.connection.password", "root");
		        //configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		        //configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		        //configuration.setE ("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
			    serviceRegistry = new ServiceRegistryBuilder().applySettings(
			            configuration.getProperties()). buildServiceRegistry();
			    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		     }
		     return sessionFactory;
		 } catch (Throwable ex)
         {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
         }
	}
	public static SessionFactory getSessionFactory()
    {
		return sessionFactory;
    }
	 
	public static void shutdown()
	{
		getSessionFactory().close();
	}
  
}
