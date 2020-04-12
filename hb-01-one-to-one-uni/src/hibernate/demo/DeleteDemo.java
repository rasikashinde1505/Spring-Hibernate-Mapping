package hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

	public static void main(String[] args) {
// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)

				.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();

		try {
			// start transaction
			session.beginTransaction();
// get instructor by primary key / id
			int theId=1;
			Instructor tempInstructor = session.get(Instructor.class,  theId);
			
			System.out.println("found instructor"+tempInstructor);
			//delete the instructor
			if(tempInstructor != null) {
				System.out.println("deleting: " + tempInstructor);
				
				//also delete associated details object bcz we have used cascade type here
				session.delete(tempInstructor);
			}
			

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");

		} finally {
			factory.close();
		}
	}

}
