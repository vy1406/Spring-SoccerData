package com.example.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookDao;
import com.example.entity.Book;
import com.example.service.BookService;

//@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getBooks() {
		System.out.println("??");
		return bookDao.getBooks();
	}

}
