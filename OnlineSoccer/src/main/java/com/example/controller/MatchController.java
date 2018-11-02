package com.example.controller;

import java.util.ArrayList;
import java.util.Map;

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
	public String show(@RequestParam String league_id, @RequestParam String season, Model model) {
		String currentSeason;
		String currentLeagueID = null;
		
//		Map<String,Object> modelMap = model.asMap();
//		league_id = (String) modelMap.get("currentLeagueID");
//		
//		if ( league_id != null ) 
//			currentLeagueID = league_id;
		
		ArrayList<Game> games = matchDao.getGamesByLeagueId(league_id);

		ArrayList<String> seasons = new ArrayList<String>(matchDao.getSeasons());

		currentSeason = matchDao.getLastSeason();
		ArrayList<Game> gamesBySeason = matchDao.getBySeason(games, currentSeason);

		model.addAttribute("games", games);
		model.addAttribute("seasons", seasons);
		model.addAttribute("gamesBySeason", gamesBySeason);
		model.addAttribute("currentSeason", currentSeason);
	//	model.addAttribute("currentLeague", currentLeagueID);
		return "showTable";
	}
}
