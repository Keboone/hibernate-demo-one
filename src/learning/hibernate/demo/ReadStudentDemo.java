package learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Student;

public class ReadStudentDemo 
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
			System.out.println("Readin Student object from table");
			Student tempStudent = new Student("Daffu", "Duck", "daffy@gmail.com");


			// start transaction
			session.beginTransaction();


			// save the student object
			System.out.println("Saving the student object");
			System.out.println(tempStudent);
			session.save(tempStudent);


			// commit transaction
			session.getTransaction().commit();

			// MY NEW CODE TO READ FROM MySql
			
			// find out the student id: primary key
			
			System.out.println("Saved student. Generated id: " + tempStudent.getId());
			
			// new session and start the transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id: primary key
			
			System.out.println("\nGetting student with id: " + tempStudent.getId());
			
			Student myStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get	complete: " + myStudent);
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			
			
			
			
			System.out.println("Done");

		}
		finally {
			factory.close();
		}		
	}

}
