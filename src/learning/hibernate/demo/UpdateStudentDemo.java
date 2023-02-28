package learning.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import learning.hibernate.demo.entity.Student;

public class UpdateStudentDemo 
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
			int studentId = 1;

			// get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on id: primary key
			
			System.out.println("\nGetting student with id: " + studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			// updating Student with id=1 firstName
			System.out.println("Updating student... " + myStudent);
			myStudent.setFirstName("Scooby");
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");
			
			
			// OTHER CODE WITH CHANGING EMAILS
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			
			System.out.println("Update email for all students");
			
			session.createQuery("UPDATE Student SET email='foo@gmail.com'")
				.executeUpdate();
			
			
			// commit transaction (update the info in the database)
			session.getTransaction().commit();
			
			System.out.println("Done");

		}
		finally {
			factory.close();
		}		
	}

}
