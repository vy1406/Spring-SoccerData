package com.example.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Book;
@Controller
public class ShopController {

	// if i leave autowired, it will cause problems cuz it cannot find ?? 

	@RequestMapping("/helloShop")
	public String showShop() {
		return "helloShop";
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
	// using request.getParameter
	// --------------------------------
	@RequestMapping("/processFormVersionTwo")
	public String letsShoutDude(HttpServletRequest request, Model model) {
		String theName = request.getParameter("studentName");
		theName = theName.toUpperCase();
		String result = "Yo" + theName;
		model.addAttribute("message", result);

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
		//List<Book> books = bookService.getBooks();
//		System.out.println(books);
//		model.addAttribute("books", books);
		System.out.println("------------------------------------------------");
		return "helloShop";
	}
}
