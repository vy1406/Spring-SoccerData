package com.example.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.MatchDao;
import com.example.model.Game;


@Controller
@RequestMapping("/match")
public class MatchController {
	
	private MatchDao matchDao = new MatchDao();
	
	@RequestMapping(value = "/show")
	public String show(@RequestParam String league_id, Model model) {
		System.out.println(league_id);	
		ArrayList<Game> games = matchDao.getGamesByLeagueId(league_id);
		model.addAttribute("games", games);
		
		//TODO 
		// put season in a set
		ArrayList<String> seasons = matchDao.getSeasonList();	
		
		model.addAttribute("seasons", seasons);
		return "showTable";
	}
}
