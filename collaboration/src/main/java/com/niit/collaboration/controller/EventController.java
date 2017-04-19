package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.Event;
import com.niit.collaboration.model.User;
@RestController
public class EventController {
	
	public String Ename="";
	@Autowired
	Event event;
	
     @Autowired
     EventDAO eventDAO;
     
     @Autowired
     HttpSession httpSession;
     
     
     
     @RequestMapping(value="/events",method = RequestMethod.GET)
 	public ResponseEntity<List<Event>> listAllUsers()
 	{
 		//log.debug("->->->calling method listAllUsers");
 		List<Event> events  = eventDAO.list();
 		
 		if(events==null||events.isEmpty()) 
 		{
 			event=new Event();
 			event.setErrorCode("404");
 			 event.setErrorMessage("no events are avaliable");
 			
 		}

 		return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
 	}
     @RequestMapping("/Eevents")
     public ResponseEntity<List<Event>> getAlluserDetails()
     {
     
     List<Event> events = eventDAO.list();
     //
     if(events.isEmpty())
     {
    	 event.setErrorCode("404");
    	 event.setErrorMessage("No events are available");
    	 events.add(event);
     }
     return  new ResponseEntity<List<Event>>(events,HttpStatus.OK);
     }
     
   
     
     @RequestMapping("/events/{id}")
     public ResponseEntity<Event> getEventByID(@PathVariable("id") String userID)
     {
    	 
    	 event = eventDAO.get(userID);
    	 if(event==null)
    	 {
    		 event = new Event();
    		 event.setErrorCode("404");
    	  	 event.setErrorMessage("User does not found with id"+userID);
    	 }
     return  new ResponseEntity<Event>(event,HttpStatus.OK);
}
    

    /* @RequestMapping(value = "/tAuthenticate/",method =RequestMethod.POST)
     public ResponseEntity<Event> authenticate(@RequestBody Event event)
     {
    	 event = eventDAO.validate(event.getEvent_Id(),event.getPassword());
    	 if(event==null)
    	 {
        event = new Event();
    	 event = eventDAO.get("404");
    	 event.setErrorMessage("Invalid credentials...Please try again ");
    	 }
    	 else
    	 {
    		 httpSession.setAttribute("loggedInUserId",event.getEvent_Id());
    	 }
     
     return  new ResponseEntity<Event>(event,HttpStatus.OK);
}
   */
     
   
     

     @RequestMapping(value = "/ERegister/",method =RequestMethod.POST)
     public ResponseEntity<Event> register(@RequestBody Event event)
     {
    	httpSession.setAttribute("Eventname",event.getEvent_Name());  
    	System.out.println("successfully registered"+Ename);
    	  eventDAO.save(event);
    	 
    		 event.setErrorCode("200");
    		 event.setErrorMessage("Thanks for event Registration");
    	 
     
     return  new ResponseEntity<Event>(event,HttpStatus.OK);
}
     
     

     @RequestMapping(value = "/EUpdate/",method =RequestMethod.PUT)
     public ResponseEntity<Event> Update(@RequestBody Event event)
     {
    	
    	 if(eventDAO.update(event)==false)
    	 {
    		 
    	 event.setErrorCode("404");
    	 event.setErrorMessage("The update is not success.Please try after some time");
    	 }
    	 else{
    		 event.setErrorCode("200");
        	 event.setErrorMessage("Successfullyupdated the information");
    	 }
     return  new ResponseEntity<Event>(event,HttpStatus.OK);
}
    
}


 	


 