package com.lov2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lov2code.hibernate.demo.entity.Course;
import com.lov2code.hibernate.demo.entity.Instructor;
import com.lov2code.hibernate.demo.entity.InstructorDetail;
import com.lov2code.hibernate.demo.entity.Review;
import com.lov2code.hibernate.demo.entity.Student;


public class CreateStudentAndCoursesDemo {

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
			int theStudentId = 2;
			Student tempStudent = session.get(Student.class, theStudentId);
			System.out.println("Getting stuent form database: " + tempStudent);
			
			// create more courses
			Course tempCourse1 = new Course("Rubik's cube - How to");
			Course tempCourse2 = new Course("KungFu - How to kick");
			
			System.out.println("Saving the courses");
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			System.out.println("saved the courses");
			// add student to course
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			
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
