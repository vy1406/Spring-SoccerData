package com.example.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

//@Repository
public class BookDaoImpl implements BookDao {

	// -------------------------------
	// put here Autowire, for SessionFactory
	// -------------------------------
	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(Book.class).buildSessionFactory();

	@Override
	@Transactional
	public List<Book> getBooks() {
		Session session = sessionFactory.getCurrentSession();
		List<Book> books;
		try {
			session.beginTransaction();
			Query<Book> query = session.createQuery("From Book", Book.class);
			session.getTransaction().commit();
			books = query.getResultList();
			session.close();
		} finally {
			sessionFactory.close();
		}
		return books;
	}
}
