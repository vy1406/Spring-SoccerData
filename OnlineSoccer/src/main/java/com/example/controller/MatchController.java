package com.example.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.MatchDao;
import com.example.model.ShowMatch;


@Controller
@RequestMapping("/match")
public class MatchController {
	
	private MatchDao matchDao = new MatchDao();
	
	@RequestMapping(value = "/show")
	public String show(@RequestParam String id, Model model) {
		System.out.println(id);	
		ArrayList<ShowMatch> matchesByLeague = matchDao.getMatchesByLeagueId(id);
		model.addAttribute("matches", matchesByLeague);
		//System.out.println(matchesByLeague);
		
		ArrayList<String> seasons = (ArrayList<String>) matchDao.seasonList;	
		
		model.addAttribute("seasons", seasons);
		return "showTable";
	}
}
