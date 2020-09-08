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

public class DeleteACoursesDemo {

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

			Course c = session.get(Course.class, 10);
			
			System.out.println(c);
			
			session.delete(c);
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
