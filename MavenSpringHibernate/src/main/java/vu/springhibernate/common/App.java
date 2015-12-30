package vu.springhibernate.common;

import java.util.List;

import org.hibernate.Session;

import vu.springhibernate.util.HibernateUtil;
import vu.springhibernate.common.Department;
import vu.springhibernate.common.Employee;
import org.hibernate.Query;

public class App 
{
    
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
        
		Session session = HibernateUtil.createSessionFactory().openSession();
        session.beginTransaction();
        Department department = new Department("Java");
        session.save(department);
        session.save(new Employee("vu",department));
        session.save(new Employee("vu1",department));
        session.getTransaction().commit();
        Query q = session.createQuery("From Employee");
		List<Employee> resultLIst = q.list();
        System.out.println("num of emplyoess:" + resultLIst.size());
        for (Employee next : resultLIst) {
        	System.out.println("next employee : "+ next);
        }
        
    }
}
