package com.example.dao;

import java.util.List;

import com.example.entity.Book;

public interface BookDao {
	public List<Book> getBooks();

	public void saveBook(Book argBook);

	public Book getBook(int bookID);

	public void deleteBook(int bookID);
}
