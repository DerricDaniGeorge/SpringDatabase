package com.springrecipes.database.config;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mysql.cj.jdbc.Driver;
import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.JpaCourseDao;

@Configuration
@EnableTransactionManagement
public class CourseConfigJPA {
	@Bean(name="JpaCourseDao")
	public CourseDao courseDao() {
		return new JpaCourseDao();
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
	//	emf.setPersistenceUnitName("course");
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		return emf;
	}
	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaAdapter=new HibernateJpaVendorAdapter();
		jpaAdapter.setShowSql(true);
		jpaAdapter.setGenerateDdl(true);
		jpaAdapter.setDatabasePlatform(MySQLDialect.class.getName());
		jpaAdapter.setShowSql(true);
		return jpaAdapter;
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
	@Bean
	public PlatformTransactionManager transactionManger() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

}
