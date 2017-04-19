package com.niit.collaboration.dao.impl;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;
@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {
	@Autowired (required=true)
	 private SessionFactory sessionFactory;

	
	public void EventDaoimpl(SessionFactory sessionFactory)
	{
		try {
			this.sessionFactory=sessionFactory;
		} catch (Exception e) {
			//log.error("Unable to connect to db");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	} 
	@Override
	@Transactional
	public boolean save(Event event) {
		try {
			sessionFactory.getCurrentSession().save(event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(int id) {
		Event event=new Event();
		event.setEvent_Id(id);
	try {
		sessionFactory.getCurrentSession().delete(event);
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	@Override
	public Event get(String id) {
		{
    		String hql="from Event where Event_id="+"'"+id+"'";;
    		 @SuppressWarnings({ "rawtypes", "deprecation" })
			Query query=sessionFactory.getCurrentSession().createQuery(hql);
    		 @SuppressWarnings("deprecation")
			List<Event> li=(List<Event>)query.list();
    		 if(li==null||li.isEmpty())
    			 return null;
    		 else
    			 return li.get(0);
		}
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional
	public List<Event> list() {
		
	
			//log.debug("->->Starting of the method list");
			   @SuppressWarnings("unchecked")
			String s="from Event";
			  List<Event> li=sessionFactory.getCurrentSession().createQuery(s).list();
				return li;
		   	
		
			// TODO Auto-generated method stub
	
			
		}
		// TODO Auto-generated method stub
		
	

	@Override
	public Event validate(String id, String password) {
		String hql="from Event where Event_id= '"+id+"'and password='"+password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Event event = (Event) query.uniqueResult();

			return null;
			// TODO Auto-generated method stub
			
		}
		// TODO Auto-generated method stub
		
	@Override
	public void setOnline(String userID) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
