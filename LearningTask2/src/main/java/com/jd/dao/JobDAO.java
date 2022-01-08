package com.jd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.jd.model.Job;

public class JobDAO{
	@Autowired
	private Session session;
    private Transaction transaction;
    String hql;
    
    public void openConnection(){
    	try {
    		session=NewHibernateUtil.getSessionFactory().openSession();
    		transaction=session.beginTransaction();
    	}
    	catch(Exception ex) {
    		System.out.println(ex);
    	}
    }
    public void closeConnection()    {
    	transaction.commit();
    }
	
	public void create(Job e) {
		openConnection();
		
		session.save(e);
		
		closeConnection();
	}

	public List<Job> getItems() {
		openConnection();
		
		String hql = "FROM Job";
		Query query = session.createQuery(hql);
		List<Job> job = (List<Job>)query.getResultList();
        System.out.println("Output: " + (List<Job>)job);
		
		closeConnection();
		return (List<Job>) job;
	}

	public Job getItem(Long id) {
		openConnection();
		
		String hql = "FROM Job where id='"+id+"'";
		Query query = session.createQuery(hql);
		List<Job> job = (List<Job>)query.getResultList();
        System.out.println("Output: " + (List<Job>)job);
		
		closeConnection();
		return job.get(0);
	}

	public void update(Job e) {
		Job job = getItem(e.getId());
		if(e.getTitle() != null) {
			job.setTitle(e.getTitle());
		}
		if(e.getDescription() != null) {
			job.setDescription(e.getDescription());
		}
		if(Boolean.compare(e.isStatus(), job.isStatus()) > 0) {
			job.setStatus(e.isStatus());
		}
		
		openConnection();
		session.update(job);
		closeConnection();
	}

	public void delete(Long id) {
		Job job = getItem(id);

		openConnection();
		
		session.delete(job);
		
		closeConnection();
	}
	
}