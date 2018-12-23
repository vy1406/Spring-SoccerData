package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// -------------------------------
// Book.class represents a book in a shop.
// -------------------------------

@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
		
	@Column(name ="bookTitle")
	private String bookTitle;
	
	@Column(name ="author")
	private String author;
	
	@Column(name ="ISBN")
	private String ISBN;
	
	@Column(name ="quantity")
	private int quantity;
	
	@Column(name ="price")
	private double price;

	public Book() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookTitle=" + bookTitle + ", author=" + author + ", ISBN=" + ISBN + ", quantity="
				+ quantity + ", price=" + price + "]";
	}

	public Book(String bookTitle, String author, String iSBN, int quantity, double d) {
		this.bookTitle = bookTitle;
		this.author = author;
		ISBN = iSBN;
		this.quantity = quantity;
		this.price = d;
	}
	
	
}
