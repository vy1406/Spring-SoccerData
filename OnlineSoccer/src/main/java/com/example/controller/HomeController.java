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
	
	
	private NewsServiceImpl newsService = new NewsServiceImpl();
	
	MatchDao matchDao = new MatchDao();
	LeagueDao leagueDao = new LeagueDao();
	
	
	@RequestMapping("/")
	public String home() {

		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model) {
	
		ArrayList<News> news;
		List<League> list = leagueDao.getAll();
		servletContext.setAttribute("leagues", list);
		model.addAttribute("leagues", servletContext.getAttribute("leagues"));
		
		Game randomGame = matchDao.getRandomGame("8455"); // 8455 - chelsea's api id
		model.addAttribute("randomGame", randomGame);
		
		try {
			news = newsService.getNewsByTopic("Chelsea");
			for ( News c : news) {
				System.out.println(c);
			}
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
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
