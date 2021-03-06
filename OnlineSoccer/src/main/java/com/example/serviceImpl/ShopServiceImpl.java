package com.example.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookDao;
import com.example.entity.Book;
import com.example.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private BookDao bookDao;
	
	@Override
	@Transactional
	public List<Book> getBooks() {
		return bookDao.getBooks();
	}

	@Override
	@Transactional
	public void saveBook(Book argBook) {
		bookDao.saveBook(argBook);
		
	}

	@Override
	@Transactional
	public Book getBook(int bookID) {
		return bookDao.getBook(bookID);
		
	}

	@Override
	public void deleteBook(int bookID) {
		 bookDao.deleteBook(bookID);
	}

}
