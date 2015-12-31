package vu.springhibernate.common;

//import java.util.Arrays;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import vu.springhibernate.util.HibernateUtil;
//import vu.springhibernate.common.Department;
//import vu.springhibernate.common.Employee;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Criteria;
public class App 
{
    
	/**
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main( String[] args )
    {
        
		Session session = HibernateUtil.createSessionFactory().openSession();
        /*session.beginTransaction();
        Department department = new Department("Java");
        session.save(department);
        session.save(new Employee("vu",department));
        session.save(new Employee("vu1",department));
        session.getTransaction().commit();*/
        Query q = session.createQuery("From Employee");
		List<Employee> resultLIst = q.list();
        System.out.println("num of emplyoess:" + resultLIst.size());
        for (Employee next : resultLIst) {
        	System.out.println("next employee : "+ next);
        }
        Query q1 = session.createQuery("Select id ,name From Department");
		List<Object[]> listQ1 = (List<Object[]>)q1.list();
		for (Object[] next : listQ1) {
			System.out.println(next[0]);
			System.out.println(next[1]);
        }
		List<Object[]> listQ2 = getAllRecords();
		for (int i=0; i<listQ2.size(); i++){
			Object[] row = (Object[]) listQ2.get(i);
			System.out.println(row[0].toString() + row[1].toString());
		}
		List<?> listQ3 = getAllNameRecords();
		for (Object next : listQ3) {
			Map<?, ?> row = (Map<?, ?>)next;
			System.out.print(row.get("name")); 
		}
     }
	 @SuppressWarnings("unchecked")
	public static List<Object[]> getAllRecords() {
	        Session session = HibernateUtil.createSessionFactory().openSession();
	        Query q1 = session.createQuery("Select id, name from Department");
	        return q1.list();     
	        
	 }
	 public static List<?> getAllNameRecords() {
	        Session session = HibernateUtil.createSessionFactory().openSession();
	        SQLQuery q1 = session.createSQLQuery("SELECT id, name from Department");
	        q1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        return q1.list();     
	        
	 }
}
