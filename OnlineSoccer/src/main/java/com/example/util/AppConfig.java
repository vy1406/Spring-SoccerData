package com.example.util;

import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan("com.example.model")
public class AppConfig {

//	@Bean
//	public SessionFactory getSessionFactory() throws Exception {
//		
//		Properties properties = new Properties();
//		properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//		properties.put("hibernate.show_sql", "true");
//		
//		AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();
//		factory.setPackagesToScan(new String [] {"my.app.domain"});
//		factory.setDataSource(getDataSource());
//		factory.setHibernateProperties(properties);
//		
//		factory.afterPropertiesSet();
//		return factory.getObject();
//	}
}