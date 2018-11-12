package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ShopController {

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

}
