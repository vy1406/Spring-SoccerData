package com.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.model.Student;

public class HibernateClass {

	public static void readObject() {
		System.out.println("in readObject");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			System.out.println("reading an object from mysql");

			Student tempStudent = new Student("Zlatan", "Ibra", "gmail@Zlatan.com");

			session.beginTransaction();
			System.out.println("saving student : " + tempStudent);
			session.save(tempStudent);

			Student myStudent = session.get(Student.class, tempStudent.getId());
			session.getTransaction().commit();
			System.out.println("myStudent : " + myStudent);
			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}

	public static void queryObject() {
		System.out.println("in queryObject");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			List<Student> students = session.createQuery("from Student").list();

			for (Student s : students)
				System.out.println(s.toString());

			session.getTransaction().commit();

			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}
	public static void updateObject() {
		System.out.println("in updateObject");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			int studentId = 2;
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student s = session.get(Student.class, studentId);
			System.out.println(s);
			
			System.out.println("updating:  ");
			
			s.setEmail("GMAIL@zlatan.com");
			session.getTransaction().commit();

			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}
	public static void deleteObject() {
		System.out.println("in deleteObject");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			int studentId = 1;
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			Student s = session.get(Student.class, studentId);
			System.out.println(s);
			
			System.out.println("updating:  ");
			
			session.delete(s);
			session.getTransaction().commit();

			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}
	public static void doMain() {
		System.out.println("in doMain");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new student object...");
			Student tempS = new Student("Paul", "Wall", "paul@lol.com");

			session.beginTransaction();

			System.out.println("saving the student");
			session.save(tempS);

			session.getTransaction().commit();

			System.out.println("DONE!");
		} finally {
			factory.close();
		}
	}
}
