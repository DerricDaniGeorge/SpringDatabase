package com.springrecipes.database.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.HibernateCourseDao;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
@Configuration
public class CourseConfig {
	
	@Bean
		public CourseDao courseDao() {
			return new HibernateCourseDao(sessionFactory().getObject());
	}
	@Bean
		public LocalSessionFactoryBean sessionFactory() {
			LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
			sessionFactoryBean.setConfigLocation(new ClassPathResource("hibernate.cfg.xml"));
			return sessionFactoryBean;
	}
}
