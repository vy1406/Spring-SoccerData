package com.example.controller;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dao.LeagueDao;
import com.example.dao.MatchDao;
import com.example.model.Game;
import com.example.model.League;

@Controller
@RequestMapping("/match")
public class MatchController {

	// using JDBC - not spring autowire
	private MatchDao matchDao = new MatchDao();
	private LeagueDao leagueDao = new LeagueDao();
	
	@Autowired
	private ServletContext servletContext;
	
	@RequestMapping(value = "/show")
	public String show(@RequestParam String league_id, @RequestParam String season, Model model) {
		String currentSeason;
		ArrayList<Game> gamesBySeason;
		ArrayList<Game> games = matchDao.getGamesByLeagueId(league_id);

		ArrayList<String> seasons = new ArrayList<String>(matchDao.getSeasons());
				
		if ( season.equals("-1")) { // first time. ( show last season ) 
			currentSeason = matchDao.getLastSeason();
			gamesBySeason = matchDao.getBySeason(games, currentSeason);
		}
		else { // sort by season 
			currentSeason = season;
			gamesBySeason = matchDao.getBySeason(games, season);
		}
		model.addAttribute("games", games);
		model.addAttribute("seasons", seasons);
		model.addAttribute("currentLeague_id", league_id);
		model.addAttribute("gamesBySeason", gamesBySeason);
		model.addAttribute("currentSeason", currentSeason);
		
		
		model.addAttribute("leagues", servletContext.getAttribute("leagues"));
		
		
		
		return "showTable";
	}
	@RequestMapping(value = "/showBySeason")
	public String showBySeason(@RequestParam String league_id, @RequestParam String season, Model model) {
		String currentSeason;
		String currentLeagueID = season;
				
		ArrayList<Game> games = matchDao.getGamesByLeagueId(league_id);

		ArrayList<String> seasons = new ArrayList<String>(matchDao.getSeasons());

		currentSeason = matchDao.getLastSeason();
		ArrayList<Game> gamesBySeason = matchDao.getBySeason(games, currentSeason);
		
		model.addAttribute("games", games);
		model.addAttribute("seasons", seasons);
		model.addAttribute("gamesBySeason", gamesBySeason);
		model.addAttribute("currentSeason", currentSeason);
		return "showTable";
	}
}
