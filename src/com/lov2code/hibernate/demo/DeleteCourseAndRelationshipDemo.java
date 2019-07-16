package com.lov2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lov2code.hibernate.demo.entity.Course;
import com.lov2code.hibernate.demo.entity.Instructor;
import com.lov2code.hibernate.demo.entity.InstructorDetail;
import com.lov2code.hibernate.demo.entity.Review;
import com.lov2code.hibernate.demo.entity.Student;


public class DeleteCourseAndRelationshipDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {

			// start the transaction 
			session.beginTransaction();
			
			// get course form db
			int theCourseId = 10;
			Course tempCourse = session.get(Course.class, theCourseId);
			
			// deleting
			System.out.println("Deleting course: " + tempCourse);
			session.delete(tempCourse);
			
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		} catch (Exception e) {			
			
			
			
		}finally {
			session.close();
			factory.close();
		}
		
		
	}	
}
