package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Book;
import com.example.service.ShopService;

@Controller
@RequestMapping("/shop")
public class ShopController {

	// if i leave autowired, it will cause problems cuz it cannot find ??
	@Autowired
	private ShopService shopService;

	// ------------------------------
	// show list of all books
	// ------------------------------
	@RequestMapping("/books")
	public String showShop(Model model) {
		List<Book> books = shopService.getBooks();
		System.out.println(books);
		model.addAttribute("books", books);

		return "showBooks";
	}

	// ------------------------------
	// move to create form-book page to add to DB
	// ------------------------------
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		Book book = new Book();
		model.addAttribute("book",book);
		
		return "showFormForAdd";
	}
	
	@PostMapping("/saveBook")
	public String saveBook(@ModelAttribute("argBook") Book argBook) {
		
		shopService.saveBook(argBook);
		
		
		return "redirect:/shop/books";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookID") int bookID, 
									Model model)
	{
		Book book = shopService.getBook(bookID);
		model.addAttribute("book", book);
		System.out.println(book);
		return "showFormForAdd";
	}
	
	
	// ========================== OLD CODE ====================================================
	
	// --------------------------------
	// just playing
	// using request.getParameter
	// --------------------------------
	
	// ========================== OLD CODE ====================================================
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		String theName = request.getParameter("studentName");
		theName = theName.toUpperCase();
		String result = "Yo" + theName;
		model.addAttribute("message", result);

		return "showShop";
	}

	// ------------------------------
	// The way to pass get params is shown in the helloShop.html
	// ------------------------------
	@RequestMapping("/processForm")
	public String processForm(@RequestParam String studentName) {
		System.out.println(studentName);
		return "showShop";
	}
	// --------------------------------
	// just playing
	// using requestParam
	// --------------------------------
	@RequestMapping("/processFormVersionThree")
	public String processFormVersionThree(@RequestParam("studentName") String studentName, Model model) {
		String theName = studentName;
		theName = theName.toUpperCase();
		String result = "hey my friend" + theName;
		model.addAttribute("message", result);

		return "showShop";
	}

	@RequestMapping("/list")
	public String bookList(Model model) {
		System.out.println("------------------------------------------------");
		// List<Book> books = bookService.getBooks();
		// System.out.println(books);
		// model.addAttribute("books", books);
		System.out.println("------------------------------------------------");
		return "helloShop";
	}
}
