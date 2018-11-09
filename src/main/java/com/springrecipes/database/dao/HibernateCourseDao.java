package com.springrecipes.database.dao;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.Transaction;

import com.springrecipes.database.beans.Course;
public class HibernateCourseDao implements CourseDao{
	private SessionFactory sessionFactory;
	

	public HibernateCourseDao(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	public void store(Course course) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(course);
			tx.commit();
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		}finally {
			session.close();
			//sessionFactory.close();
		}
	}
	public void delete(Long courseId) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		try {
			tx.begin();
			Course course=(Course)session.get(Course.class,courseId);
			session.delete(course);
			tx.commit();
		}catch(RuntimeException e) {
			tx.rollback();
			throw e;
		}finally {
			session.close();
			//sessionFactory.close();
		}
	}
	public Course findById(Long courseId) {
		Session session=sessionFactory.openSession();
		try {
			return (Course)session.get(Course.class, courseId);
		}finally {
			session.close();
			//sessionFactory.close();
		}
	}
	public List<Course> findAll(){
		Session session=sessionFactory.openSession();
		try {
			Query<Course> query=session.createQuery("from Course");
			return query.list();
		}finally {
			session.close();
			//sessionFactory.close();
		}
	}
}
