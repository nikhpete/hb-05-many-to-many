package com.nick.hibernate.demo;

import java.text.ParseException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nick.hibernate.demo.entity.Course;
import com.nick.hibernate.demo.entity.Instructor;
import com.nick.hibernate.demo.entity.InstructorDetail;
import com.nick.hibernate.demo.entity.Review;
import com.nick.hibernate.demo.entity.Student;

public class AddCoursesForAStudentDemo {

	public static void main(String[] args) throws ParseException {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();

			Student std = session.get(Student.class, 2);
			
			System.out.println(std);
			System.out.println(std.getCourses());
			
			Course c1 = new Course("Rubiks Cube");
			Course c2 = new Course("Atari 2600");
			
			c1.addStudent(std);
			c2.addStudent(std);
			
			session.save(c1);
			session.save(c2);
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
