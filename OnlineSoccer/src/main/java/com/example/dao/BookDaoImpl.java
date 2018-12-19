package com.example.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public class BookDaoImpl implements BookDao {
	
	SessionFactory sessionFactory = new Configuration()
			.configure("shop.cfg.xml")
			.addAnnotatedClass(Book.class)
			.buildSessionFactory();

	@Override
	@Transactional
	public List<Book> getBooks() {

	//	Session currentSession = sessionFactory.getCurrentSession();
		return null;
	}
	public void lol() {
		System.out.println("--------------------------------------------------------------------");
		Session session;
		try { 
			session = sessionFactory.openSession();
			session.beginTransaction();
			
			List<Book> books = session.createQuery("from Book").list();
			
			for ( Book b : books ) 
				System.out.println(b);
			
			session.getTransaction().commit();
		}
		catch (Exception e ) { 
			e.printStackTrace();
		}
		
		System.out.println("--------------------------------------------------------------------");
	}
}
