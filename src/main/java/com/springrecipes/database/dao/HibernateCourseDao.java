package com.springrecipes.database.dao;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;
import com.springrecipes.database.beans.Course;
public class HibernateCourseDao implements CourseDao{
	private SessionFactory sessionFactory;
	

	public HibernateCourseDao(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	@Transactional
	public void store(Course course) {
		sessionFactory.getCurrentSession().saveOrUpdate(course);
	/*	Session session=sessionFactory.openSession();
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
		}  */
	}
	@Transactional
	public void delete(Long courseId) {
		Course course=(Course)sessionFactory.getCurrentSession().get(Course.class, courseId);
		sessionFactory.getCurrentSession().delete(course);
	/*	Session session=sessionFactory.openSession();
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
		}  */
	}
	@Transactional(readOnly=true)
	public Course findById(Long courseId) {
		return sessionFactory.getCurrentSession().get(Course.class, courseId);
	/*	Session session=sessionFactory.openSession();
		try {
			return (Course)session.get(Course.class, courseId);
		}finally {
			session.close();
			//sessionFactory.close();
		}  */
	}
	@Transactional(readOnly=true)
	public List<Course> findAll(){
		Query<Course> query=sessionFactory.getCurrentSession().createQuery("from Course");
		return query.list();
	/*	Session session=sessionFactory.openSession();
		try {
			Query<Course> query=session.createQuery("from Course");
			return query.list();
		}finally {
			session.close();
			//sessionFactory.close();
		}  */
	}
}
