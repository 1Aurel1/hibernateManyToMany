package com.lov2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lov2code.hibernate.demo.entity.Course;
import com.lov2code.hibernate.demo.entity.Instructor;
import com.lov2code.hibernate.demo.entity.InstructorDetail;
import com.lov2code.hibernate.demo.entity.Review;
import com.lov2code.hibernate.demo.entity.Student;


public class GetStudentAndCoursesDemo {

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
			
			// find student
			int theStudentId = 1;
			Student tempStudent = session.get(Student.class, theStudentId);
			System.out.println("Getting stuent form database: " + tempStudent);
			
			// printing
			
			System.out.println("Printing student: ");
			System.out.println(tempStudent);
			
			System.out.println("Printing courses for the student:");
			System.out.println(tempStudent.getCourses());
			
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
