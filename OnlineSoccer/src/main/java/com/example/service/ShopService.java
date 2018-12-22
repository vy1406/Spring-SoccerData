package com.example.service;

import java.util.List;

import com.example.entity.Book;

public interface ShopService {

	public List<Book> getBooks();

	public void saveBook(Book argBook);
}
