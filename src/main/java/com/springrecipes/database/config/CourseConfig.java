package com.springrecipes.database.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;

import com.mysql.cj.jdbc.Driver;
import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.HibernateCourseDao;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
@Configuration
@EnableTransactionManagement
public class CourseConfig {
	
	@Bean(name="HibernateCourseDao")
		public CourseDao courseDao() {
			return new HibernateCourseDao(sessionFactory().getObject());
	}
	
	@Bean
		public PlatformTransactionManager platformTransactionManager() {
			return new HibernateTransactionManager(sessionFactory().getObject());
	}  
	@Bean
		public LocalSessionFactoryBean sessionFactory() {
			LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
			sessionFactoryBean.setConfigLocation(new ClassPathResource("/hibernate.cfg.xml"));
			sessionFactoryBean.setDataSource(dataSource());
			//sessionFactoryBean.setPackagesToScan("com.springrecipes.database.beans");
			sessionFactoryBean.setHibernateProperties(hibernateProperties());
			return sessionFactoryBean;
	}
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName(Driver.class.getName());
		dataSource.setUrl("jdbc:mysql://localhost:3306/vehicle");
		dataSource.setUsername("root");
		dataSource.setPassword("admin");
		dataSource.setInitialSize(2);
		dataSource.setMaxTotal(5);
		return dataSource;
	}
	private Properties hibernateProperties() {
		Properties properties=new Properties();
		properties.put("hibernate.dilect", org.hibernate.dialect.MySQLDialect.class.getName());
		properties.put("show_sql", true);
		properties.put("hibernate.hbm2dll.auto","update");
		return properties;
	}
		
}
