package com.example;

import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.example.dao.LeagueDao;
import com.example.model.League;

public class MasterDataLoader implements ServletContextAware  {

	private LeagueDao leagueDao;
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		ArrayList<League> list = leagueDao.getAll();
        this.servletContext.setAttribute("leagues", list);		
	}
	
}
