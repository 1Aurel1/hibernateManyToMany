package com.lov2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.lov2code.hibernate.demo.entity.Course;
import com.lov2code.hibernate.demo.entity.Instructor;
import com.lov2code.hibernate.demo.entity.InstructorDetail;
import com.lov2code.hibernate.demo.entity.Review;
import com.lov2code.hibernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

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
			
			// Create course
			Course tempCourse = new Course("Pacman - How to score");
			// add some reviews 
			tempCourse.addReviews(new Review("Great course"));
			tempCourse.addReviews(new Review("Super course"));
			tempCourse.addReviews(new Review("Laking creativity for naming reviews"));
			
			
			// save stuff
			System.out.println("Saving the course");
			
			session.save(tempCourse);
			
			System.out.println(tempCourse);
			
			//creating a students
			Student tempStudent1 = new Student("Aurel", "Marishta", "a@gmail.com");
			Student tempStuent2 = new Student("John", "Doe", "john@high");
			
			// save students
			System.out.println("Seving the students");
			session.save(tempStudent1);
			session.save(tempStuent2);
			System.out.println("Students saved");
			
			// add students to course
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStuent2);

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
