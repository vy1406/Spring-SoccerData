package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.dao.LeagueDao;
import com.example.dao.MatchDao;
import com.example.model.Game;
import com.example.model.League;
import com.example.model.News;
import com.example.serviceImpl.NewsServiceImpl;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
public class HomeController   {
	
	@Autowired
	private ServletContext servletContext;
		
	@Autowired
	private NewsServiceImpl newsServiceImpl;
	
	@Autowired
	MatchDao matchDao;
	
	@Autowired
	LeagueDao leagueDao;
	
	// -----------------------------
	// redirect index
	// -----------------------------
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}
	
	// -----------------------------
	// getting list of leagues
	// getting random game to show. 
	// getting news
	// -----------------------------
	@RequestMapping("/index")
	public String index(Model model) {
	
		ArrayList<News> news;
		List<League> list = leagueDao.getAll();
		servletContext.setAttribute("leagues", list); // TODO - what am i doing here ? fix it... 
		model.addAttribute("leagues", servletContext.getAttribute("leagues"));
		
		Game randomGame = matchDao.getRandomGame("8455"); // 8455 - chelsea's api id - custom made by me for now
		model.addAttribute("randomGame", randomGame);
		
		try {
			news = newsServiceImpl.getNewsByTopic("Chelsea");
			model.addAttribute("news", news);
		} catch (UnirestException e) {
			
			e.printStackTrace();
		}
		
		
		return "index";
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String getIndex(Model model) {
		System.out.println("post?");
		
		return "index";
	}
	
}
