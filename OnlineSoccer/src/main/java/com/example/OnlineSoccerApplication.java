package com.example;



import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.dao.BookDaoImpl;
import com.example.dao.CountryDao;
import com.example.dao.LeagueDao;
import com.example.dao.MatchDao;
import com.example.entity.Book;
import com.example.serviceImpl.LeagueServiceImpl;
import com.example.serviceImpl.NewsServiceImpl;


@SpringBootApplication
public class OnlineSoccerApplication {
	
	public static void main(String[] args) {

//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaConfiguration.class);
//
//		MatchDao matchDao = context.getBean("matchDao", MatchDao.class);
//		LeagueDao LeagueDao = context.getBean("leagueDao", LeagueDao.class);
//		CountryDao countryDao = context.getBean("countryDao", CountryDao.class);
//		NewsServiceImpl newsServiceImpl = context.getBean("newsServiceImpl", NewsServiceImpl.class);
//		LeagueServiceImpl leagueServiceImpl = context.getBean("leagueServiceImpl", LeagueServiceImpl.class);
//			
//		BookDaoImpl bookDao = context.getBean(BookDaoImpl.class);
//		List<Book> books = bookDao.getBooks();
//		bookDao.lol();
		SpringApplication.run(OnlineSoccerApplication.class, args);
		
	}
}
