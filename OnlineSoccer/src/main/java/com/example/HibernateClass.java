package com.example;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Book;
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

			Student myStudent = (Student) session.get(Student.class, tempStudent.getId());
			session.getTransaction().commit();
			System.out.println("myStudent : " + myStudent);
			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}

	public static void queryObject() {
		System.out.println("in queryObject");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {

			session.beginTransaction();
			List<Book> students = session.createQuery("from Book").list();

//			for (Student s : students)
//				System.out.println(s.toString());
//
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

			
			Student s = (Student) session.get(Student.class, studentId);
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

			Student s = (Student) session.get(Student.class, studentId);
			System.out.println(s);

			System.out.println("updating:  ");

			session.delete(s);
			session.getTransaction().commit();

			System.out.println("FOKING A!");
		} finally {
			factory.close();
		}
	}

	public static void checkConnection() {
		System.out.println("in checkConnection");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			System.out.println("checking connection");
			session.getTransaction().commit();
			System.out.println("connection established! ");
		} finally {
			factory.close();
		}
	}
	// ---------------------------
	// manually filling the table
	// ---------------------------
	public static void fillTable() {
		System.out.println("in fillTable");
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Book.class)
				.buildSessionFactory();

		Session session = factory.getCurrentSession();

		try {
			System.out.println("Creating new book object...");
			Book b1= new Book("I Am Zlatan Ibrahimovic", "9780241966839", 50, 8.87);
			Book b2= new Book("I Think Therefore I Play", "1909430161", 80, 14.99);
			Book b3= new Book("Commitment: My Autobiography", "1473647525", 40, 10.19);
			session.beginTransaction();

			System.out.println("saving the books");
			session.save(b1);
			session.save(b2);
			session.save(b3);

			session.getTransaction().commit();
			
			System.out.println("DONE!");
		} finally {
			factory.close();
		}
	}
	// do i need it ? 
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
