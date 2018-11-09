package com.springrecipes.database.config;

import org.springframework.context.annotation.Configuration;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import com.springrecipes.database.dao.VehicleDao;
import com.mysql.cj.jdbc.Driver;
import com.springrecipes.database.dao.JdbcVehicleDao;
import javax.sql.DataSource;

@Configuration
public class VehicleConfig {
	@Bean
	public VehicleDao vehicleDao() {
		JdbcVehicleDao jdbcDao=new JdbcVehicleDao();
		jdbcDao.setDataSource(dataSource());
		return jdbcDao;
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
}
