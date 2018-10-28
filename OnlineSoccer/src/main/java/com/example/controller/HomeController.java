package com.example.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.LeagueDao;
import com.example.model.League;

@Controller
public class HomeController   {
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping("/")
	public String home() {

		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
		LeagueDao leagueDao = new LeagueDao();
		List<League> list = leagueDao.getAll();
		servletContext.setAttribute("leagues", list);
		model.addAttribute("leagues", servletContext.getAttribute("leagues"));
		return "index";
	}
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String getIndex(Model model) {
		System.out.println("post?");
		
		return "index";
	}
	@RequestMapping("/index2")
	public String index2(Model model) {
		return "index2";
	}
	
	@RequestMapping("/index3")
	public String index5(Model model) {
		return "index3";
	}
	@RequestMapping("/index54")
	public String index54(Model model) {
		return "index5";
	}
}
