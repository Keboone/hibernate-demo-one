package learning.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Student;

public class QueryStudentDemo
{
	public static void main(String[] args)
	{
		// create session factory
		
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//create session
		
		Session session = factory.getCurrentSession();
		
		try 
		{
			// start transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("FROM Student").getResultList();
			
			
			// display students
			displayStudents(theStudents);
			
			// query students: lastName='Doe'
			theStudents = session.createQuery("FROM Student s WHERE s.lastName='Doe'").getResultList();
			
			// display students who have last name of 'Doe' 
			System.out.println("\n\nStudents who have last name of 'Doe':");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("FROM Student s WHERE s.lastName='Doe' OR s.firstName='Daffu'").getResultList();
			System.out.println("\n\nStudents who have last name of 'Doe' or first name of 'Daffu':");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("FROM Student s WHERE s.email LIKE '%@gmail.com'").getResultList();
			System.out.println("\n\nStudents who have email that ends with '@gmail.com'");
			displayStudents(theStudents);
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		finally {
			factory.close();
		}
		
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents)
		{
			System.out.println(tempStudent);
		}
	}
	
}
