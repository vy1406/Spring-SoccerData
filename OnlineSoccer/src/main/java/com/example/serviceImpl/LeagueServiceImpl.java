package com.example.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.LeagueDao;
import com.example.model.League;
import com.example.service.LeagueService;

@Service
public class LeagueServiceImpl implements LeagueService{

	@Autowired
	private LeagueDao leagueDao;
		
	@Override
	public ArrayList<String> getLeagueNames() {
		ArrayList<String> arrList = new ArrayList<>();
		
		ArrayList<League> arrLeagues = leagueDao.getAll();
		for ( League L : arrLeagues ) {
			arrList.add(L.getName());
		}
		
		return arrList;
	}
	
}
