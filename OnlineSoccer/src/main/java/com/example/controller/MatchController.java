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

//	// using JDBC - not spring autowire
//	private MatchDao matchDao = new MatchDao();
//	private LeagueDao leagueDao = new LeagueDao();
//	
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private MatchDao matchDao;
	
	@Autowired
	private LeagueDao leagueDao;
	
	// ------------------------------
	// showing games = chosen league + all seasons. and showing the last season if its first time.
	// next step ( else ) it will show by selected season the relevant games.
	// ------------------------------
	@RequestMapping(value = "/show")
	public String show(@RequestParam String league_id, @RequestParam String season, Model model) {
		String currentSeason;
		ArrayList<Game> gamesBySeason;
		ArrayList<Game> games = matchDao.getGamesByLeagueId(league_id);

		ArrayList<String> seasons = new ArrayList<String>(matchDao.getSeasons());
				
		// if a user enters the first time to this page
		// it will show last season before he can choose them
		if ( season.equals("-1")) { 
			currentSeason = matchDao.getLastSeason();
			gamesBySeason = matchDao.getBySeason(games, currentSeason);
		}
		// sort by season 
		else {
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
	
	// ------------------------------
	// whenever a person chooses a season - it gets all games according to season. 
	// ------------------------------
	@RequestMapping(value = "/showBySeason")
	public String showBySeason(@RequestParam String league_id, @RequestParam String season, Model model) {
		
		String currentSeason;				
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
