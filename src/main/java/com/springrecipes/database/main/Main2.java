package com.springrecipes.database.main;
import java.util.GregorianCalendar;
import java.util.List;
import com.springrecipes.database.beans.Course;
import com.springrecipes.database.config.CourseConfigJPA;
import com.springrecipes.database.dao.CourseDao;
import com.springrecipes.database.dao.JpaCourseDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main2 {
	public static void main(String[] args) {
		ApplicationContext ctx=new AnnotationConfigApplicationContext(CourseConfigJPA.class);
		CourseDao courseDao=ctx.getBean("JpaCourseDao",CourseDao.class);
		Course course=new Course();
		course.setTitle("Java Core");
		course.setBeginDate(new GregorianCalendar(2018,7,23).getTime());
		course.setEndDate(new GregorianCalendar(2018,8,23).getTime());
		course.setFee(2000);
		courseDao.store(course);
		
		
		List<Course> courses=courseDao.findAll();
		for(Course c:courses) {
			System.out.println(c.getId()+":"+c.getTitle()+":"+c.getBeginDate()+":"+c.getEndDate()+":"+c.getFee());
		}
		//CourseDao courseDao3=new JpaCourseDao();
		Course course2=courseDao.findById(courses.get(1).getId());
		//CourseDao courseDao4=new JpaCourseDao();
		courseDao.delete(course2.getId());
	}
}
