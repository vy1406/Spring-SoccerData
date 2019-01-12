package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="authors")
public class AuthorDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="author")
	private String author;
	
	@Column(name="dateOfBirth")
	private String dateOfBirth;
	

	@OneToMany(mappedBy="authorDetail", 
			cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
					  CascadeType.DETACH, CascadeType.REMOVE})
	private List<Book> books;
	
	public AuthorDetail() {
		
	}

	public AuthorDetail(String author, String dateOfBirth) {
		this.author = author;
		this.dateOfBirth = dateOfBirth;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	// ------------------------
	// linking two entities.
	// ------------------------
	public void add(Book book) {
		if (books == null)
			books = new ArrayList<>();
		
		books.add(book);
		book.setAuthorDetail(this);
	}
	
	@Override
	public String toString() {
		return "AuthorDetail [id=" + id + ", author=" + author + ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
}
