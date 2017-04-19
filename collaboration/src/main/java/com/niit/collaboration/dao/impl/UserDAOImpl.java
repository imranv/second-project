package com.niit.collaboration.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;


	@SuppressWarnings("deprecation")
	@Repository("userDAO")
	public class UserDAOImpl  implements UserDAO {
		
		//private final Logger log = (Logger) LoggerFactory.logger(UserDAOImpl.class);
		
		@Autowired (required=true)
		 private SessionFactory sessionFactory;
		
		
		public void UserDaoimpl(SessionFactory sessionFactory)
		{
			try {
				this.sessionFactory=sessionFactory;
			} catch (Exception e) {
				//log.error("Unable to connect to db");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	  
		
		@SuppressWarnings("unchecked")
		@Transactional
		public List<User> list() {
			//log.debug("->->Starting of the method list");
			   @SuppressWarnings("unchecked")
			String s="from User";
			  List<User> li=(List<User>) sessionFactory.getCurrentSession().createQuery(s). list();
				return li;
		   	
		
			// TODO Auto-generated method stub
	
			
		}
		  @Transactional
			public boolean update(User user) {
			 // log.debug("->->Starting of the method update");
			   try {
				sessionFactory.getCurrentSession().update(user);
				return true;
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
				// TODO Auto-generated method stub
				return false;
			}}

		
	 
		
		
		@Transactional
		public boolean save(User user) {
			//log.debug("->->Starting of the method save");
			
		   try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			// TODO Auto-generated method stub
			return false;
		}}
	   
	 	   
	    @Transactional
		public void delete(String id) {
	    	User user=new User();
			user.setId(id);
	    	
				sessionFactory.getCurrentSession().delete(user);
				
		}
	    
	  
	    
	    @SuppressWarnings("unchecked")
		@Transactional
		public User get(String id) {
	    	{
	    		String hql="from User where id="+"'"+id+"'";;
	    		 @SuppressWarnings({ "rawtypes", "deprecation" })
				Query query=sessionFactory.getCurrentSession().createQuery(hql);
	    		 @SuppressWarnings("deprecation")
				List<User> li=(List<User>)query.list();
	    		 if(li==null||li.isEmpty())
	    			 return null;
	    		 else
	    			 return li.get(0);
	    	
			// TODO Auto-generated method stub
	    	}
		
			// TODO Auto-generated method stub
		
		}



	    @Transactional
		public User validate(String id, String password) {
		String hql="from User where id= '"+id+"'and password='"+password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		User user = (User) query.uniqueResult();

			return user;
			// TODO Auto-generated method stub
			
		}

		@Transactional
		public void setOnline(String loggedInUserID) {
			//log.debug("Starting of the method setOffline");
			String hql = "UPDATE User SET isOnline = 'Y' where userID = '" + loggedInUserID + "'";
			//log.debug("hql: " + hql);
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.executeUpdate();
			//log.debug("Ending of the method setOffline");
	
		}
	    


	}