package learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Student;

public class CreateStudentDemo
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
			// create Student object
			System.out.println("Creating new Student object");
			Student tempStudent = new Student("Paul", "Walker", "paulw@gmail.com");
			
			
			// start transaction
			session.beginTransaction();
			
			
			// save the student object
			System.out.println("Saving the student object");
			session.save(tempStudent);
			
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		}
		finally {
			factory.close();
		}
		
		
	}
	
}
