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

public class CreateCoursesAndStudentsDemo {

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

			Instructor ins = new Instructor("chad", "darby", "chad@mail.com");

			InstructorDetail insDet = new InstructorDetail("youtube/luv2code.com", "coding");

			ins.setInstructorDetial(insDet);

			Course c1 = new Course("PackMan");

			c1.addReview(new Review("Good"));
			c1.addReview(new Review("Woow"));
			c1.addReview(new Review("Bad bad game"));

			ins.addCOurse(c1);

			Course c2 = new Course("Java");

			c2.addReview(new Review("Fabulous"));
			c2.addReview(new Review("Not so goood"));
			c2.addReview(new Review("Cooooool"));
			
			Student s1 = new Student("nick", "pete", "nick@mail.com");
			Student s2 = new Student("Siv", "chid", "siv@mail.com");
			Student s3 = new Student("Jam", "Mono", "jam@mail.com");
			
			c1.addStudent(s1);
			c1.addStudent(s2);
			c2.addStudent(s2);
			c2.addStudent(s3);

			ins.addCOurse(c2);

			session.save(ins);
			
			session.save(c1);
			session.save(c2);
			
			session.save(s1);
			session.save(s2);
			session.save(s3);
			
			
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
