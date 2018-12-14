package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.dao.CountryDao;
import com.example.dao.LeagueDao;
import com.example.dao.MatchDao;
import com.example.service.Coach;
import com.example.serviceImpl.LeagueServiceImpl;
import com.example.serviceImpl.NewsServiceImpl;
import com.example.util.AppConfig;

@SpringBootApplication
public class OnlineSoccerApplication {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);

		MatchDao matchDao = context.getBean("matchDao", MatchDao.class);
		LeagueDao LeagueDao = context.getBean("leagueDao", LeagueDao.class);
		CountryDao countryDao = context.getBean("countryDao", CountryDao.class);
		NewsServiceImpl newsServiceImpl = context.getBean("newsServiceImpl", NewsServiceImpl.class);
		LeagueServiceImpl leagueServiceImpl = context.getBean("leagueServiceImpl", LeagueServiceImpl.class);
		
		//System.out.println(theCoach.getDailyWorkout());
		context.close();
		// SpringApplication.run(OnlineSoccerApplication.class, args);
		

		// SpringApplication.run(OnlineSoccerApplication.class, args);
	}
}
