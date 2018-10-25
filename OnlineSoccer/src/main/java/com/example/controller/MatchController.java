package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/match")
public class MatchController {
	
	@RequestMapping(value = "/show")
	public String show(@RequestParam String id) {
		System.out.println(id);
		

		return "index4";
	}
}
