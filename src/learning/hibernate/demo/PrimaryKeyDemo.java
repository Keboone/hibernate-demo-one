package learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Student;

public class PrimaryKeyDemo 
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
			// create 3 Student objects
			System.out.println("Creating new 3 Student objects");
			Student tempStudent1 = new Student("John", "Doe", "JohnD@gmail.com");
			Student tempStudent2 = new Student("Mary", "Public	", "MaryPub@gmail.com");
			Student tempStudent3 = new Student("Bonita", "Applebum", "BonApple@gmail.com");

			// start transaction
			session.beginTransaction();


			// save students object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);

			// commit transaction (update the info in the database)
			session.getTransaction().commit();

			System.out.println("Done");

		}
		finally {
			factory.close();
		}
		
		
		
		
	}

}
