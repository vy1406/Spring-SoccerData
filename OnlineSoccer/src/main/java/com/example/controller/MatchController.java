package com.example.controller;


import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.MatchDao;
import com.example.model.Match;


@Controller
@RequestMapping("/match")
public class MatchController {
	
	private MatchDao matchDao = new MatchDao();
	
	@RequestMapping(value = "/show")
	public String show(@RequestParam String id, Model model) {
		System.out.println(id);	
		ArrayList<Match> matchesByLeague = matchDao.getMatchesByLeagueId(id);
		model.addAttribute("matches", matchesByLeague);
		//System.out.println(matchesByLeague);
		
		ArrayList<String> seasons = (ArrayList<String>) matchDao.seasonList;	
//				new ArrayList<String>(
//			    Arrays.asList("2009", "2010", "2011", "2012"));

		
		model.addAttribute("seasons", seasons);
		return "showTable";
	}
}
