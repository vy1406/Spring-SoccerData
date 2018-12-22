package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.dao.BookDao;
import com.example.dao.BookDaoImpl;
import com.example.dao.CountryDao;
import com.example.dao.LeagueDao;
import com.example.dao.MatchDao;
import com.example.dao.TeamDao;
import com.example.service.ShopService;
import com.example.serviceImpl.LeagueServiceImpl;
import com.example.serviceImpl.NewsServiceImpl;
import com.example.serviceImpl.ShopServiceImpl;


@Configuration
@ComponentScan({ "com.example.dao", "com.example.serviceImpl", "com.example.contoller" })
public class JavaConfiguration {

	@Bean
	public CountryDao countryDao() {
		return new CountryDao();
	}

	@Bean
	public LeagueDao leagueDao() {
		return new LeagueDao();
	}

	@Bean
	public MatchDao matchDao() {
		return new MatchDao();
	}

	@Bean
	public TeamDao teamDao() {
		return new TeamDao();
	}

	@Bean
	public BookDao bookDaoImpl() {
		return new BookDaoImpl();
	}

	@Bean
	public LeagueServiceImpl leagueServiceImpl() {
		return new LeagueServiceImpl();
	}

	@Bean
	public ShopService shopService() {
		return new ShopServiceImpl();
	}
	@Bean
	public NewsServiceImpl newsServiceImpl() {
		return new NewsServiceImpl();
	}

}