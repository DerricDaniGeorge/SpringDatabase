package com.springrecipes.database.config;
import org.springframework.context.annotation.Configuration;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.JpaCourseDao;

@Configuration
public class CourseConfigJPA {
	@Bean(name="JpaCourseDao")
	public CourseDao courseDao() {
		return new JpaCourseDao(entityManagerFactory().getObject());
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("course");
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		return emf;
	}
	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaAdapter=new HibernateJpaVendorAdapter();
		jpaAdapter.setShowSql(true);
		jpaAdapter.setGenerateDdl(true);
		jpaAdapter.setDatabasePlatform(MySQLDialect.class.getName());
		return jpaAdapter;
	}
}
