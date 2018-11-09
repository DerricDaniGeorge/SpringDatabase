package com.springrecipes.database.main;

import com.springrecipes.database.config.CourseConfig;
import com.springrecipes.database.config.VehicleConfig;
import java.util.List;
import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.HibernateCourseDao;
import java.util.Arrays;
import java.util.GregorianCalendar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.springrecipes.database.dao.VehicleDao;
import com.springrecipes.database.beans.Vehicle;
import com.springrecipes.database.beans.Course;
public class Main {
	public static void main(String[] args) {		
		ApplicationContext context=new AnnotationConfigApplicationContext(CourseConfig.class);
	/*	VehicleDao vehicleDao=context.getBean("vehicleDao",VehicleDao.class);
		Vehicle vehicle=vehicleDao.findByVehicleNo("TEM0001");
		System.out.println(vehicle.getVehicleNo());
		System.out.println(vehicle.getColor());
		System.out.println(vehicle.getWheel());
		System.out.println(vehicle.getSeat());
		
		Vehicle v2=new Vehicle("TEM0003","Yellow",6,7);
		Vehicle v3=new Vehicle("RMX0004","White",4,4);
	//	vehicleDao.insertBatch(Arrays.asList(v2,v3));  */
		
		CourseDao courseDao=context.getBean(CourseDao.class);
		Course course=new Course();
		course.setTitle("Hibernate ORM");
		course.setBeginDate(new GregorianCalendar(2018,8,1).getTime());
		course.setEndDate(new GregorianCalendar(2018,9,1).getTime());
		course.setFee(1000);
		courseDao.store(course);
		
		//CourseDao courseDao1=new HibernateCourseDao();
		List<Course> courses=courseDao.findAll();
		for(Course c:courses) {
			System.out.println(c.getId()+":"+c.getTitle()+":"+c.getBeginDate()+":"+c.getEndDate()+":"+c.getFee());
		}
		//CourseDao courseDao2=new HibernateCourseDao();
		Long id=courses.get(1).getId();
		Course course2=courseDao.findById(id);
		System.out.println(course2.getId()+":"+course2.getTitle()+":"+course2.getBeginDate()+":"+course2.getEndDate()+":"+course2.getFee());
		
		//CourseDao courseDao3=new HibernateCourseDao();
		courseDao.delete(id);
		
	}
}
