package com.springrecipes.database.dao;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityTransaction;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.springrecipes.database.beans.Course;
import javax.persistence.PersistenceContext;
public class JpaCourseDao implements CourseDao{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void store(Course course) {
		entityManager.merge(course);
	/*	EntityManager manager=entityManagerFactory.createEntityManager();
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
		}  */
	}
	@Transactional
	public void delete(Long courseId) {
		Course course=entityManager.find(Course.class,courseId);
		entityManager.remove(course);
	/*	EntityManager manager=entityManagerFactory.createEntityManager();
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
		} */
	}
	@Transactional(readOnly=true)
	public Course findById(Long courseId) {
		return entityManager.find(Course.class, courseId);
	/*	EntityManager manager=entityManagerFactory.createEntityManager();
		try {
			return manager.find(Course.class,courseId);
		}finally {
			manager.close();
			//entityManagerFactory.close();
		}  */
	}
	@Transactional
	public List<Course> findAll(){
		TypedQuery<Course> query=entityManager.createQuery("from Course",Course.class);
		return query.getResultList();
		/*EntityManager manager=entityManagerFactory.createEntityManager();
		try {
			Query query=manager.createQuery("select course FROM Course course");
			return query.getResultList();
		}finally {
			manager.close();
			//entityManagerFactory.close();
		} */
	}
}
