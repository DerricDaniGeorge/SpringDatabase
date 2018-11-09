package com.springrecipes.database.dao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import java.util.List;
import javax.persistence.Query;
import com.springrecipes.database.beans.Course;

public class JpaCourseDao implements CourseDao{
	private EntityManagerFactory entityManagerFactory;
	
	public JpaCourseDao(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory=entityManagerFactory;
	}
	public void store(Course course) {
		EntityManager manager=entityManagerFactory.createEntityManager();
		EntityTransaction tx=manager.getTransaction();
		try {
			tx.begin();
			manager.merge(course);
			tx.commit();
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		}finally {
			manager.close();
			//entityManagerFactory.close();
		}
	}
	public void delete(Long courseId) {
		EntityManager manager=entityManagerFactory.createEntityManager();
		EntityTransaction tx=manager.getTransaction();
		try {
			tx.begin();
			Course course=manager.find(Course.class, courseId);
			manager.remove(course);
			tx.commit();
		}catch(RuntimeException e){
			tx.rollback();
			throw e;
		}finally {
			manager.close();
			//entityManagerFactory.close();
		}
	}
	public Course findById(Long courseId) {
		EntityManager manager=entityManagerFactory.createEntityManager();
		try {
			return manager.find(Course.class,courseId);
		}finally {
			manager.close();
			//entityManagerFactory.close();
		}
	}
	public List<Course> findAll(){
		EntityManager manager=entityManagerFactory.createEntityManager();
		try {
			Query query=manager.createQuery("select course FROM Course course");
			return query.getResultList();
		}finally {
			manager.close();
			//entityManagerFactory.close();
		}
	}
}
