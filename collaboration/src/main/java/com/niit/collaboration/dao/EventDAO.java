package com.niit.collaboration.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.collaboration.model.Event;



	@Repository("eventDAO")
	public interface EventDAO {
	
		public boolean save(Event event);
		public boolean update(Event event);
		public void delete(String id);
		public Event get(String id);
		public List<Event> list();
	    public Event validate(String id,String password);
	    public void setOnline(String userID);
		void delete(int id);	
	   
	}

