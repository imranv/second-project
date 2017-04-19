   package com.niit.collaboration.controller;



import java.io.File;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FileUploadDAO;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.FileUpload;
import com.niit.collaboration.model.User;



@RestController
public class UserController {
	
	private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	User user;
	
     @Autowired
     UserDAO userDAO;
     
     @Autowired
     FileUploadDAO fileUploadDAO;
     
     @Autowired
     HttpSession httpSession;
     
     
     
     @RequestMapping(value="/users",method = RequestMethod.GET)
 	public ResponseEntity<List<User>> listAllUsers()
 	{
 		//log.debug("->->->calling method listAllUsers");
 		List<User> users = userDAO.list();
 		
 		if(users.isEmpty()) 
 		{
 			user=new User();
 			user.setErrorCode("404");
 			 user.setErrorMessage("no users are avaliable");
 			
 		}
 		
 		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
 	}
 	
   
     
    /* @RequestMapping("/tusers")
     public ResponseEntity<List<User>> getAlluserDetails()
     {
     
     List<User> users = userDAO.list();
     //
     if(users.isEmpty())
     {
    	 user.setErrorCode("404");
    	 user.setErrorMessage("No users are available");
    	 users.add(user);
     }
     return  new ResponseEntity<List<User>>(users,HttpStatus.OK);
     }*/
     
   
     
     @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
     public ResponseEntity<User> deleteuser(@PathVariable("id") String id,@RequestBody User user)
     {
    	 
    	 user = userDAO.get(id);
    	 if(user==null)
    	 {
    		 user = new User();
    		 user.setErrorCode("404");
    	  	 user.setErrorMessage("User does not found with id"+id);
    	 }
    	 else 
    	 {
    		 userDAO.delete(id);
    	 }
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}

     @RequestMapping(value = "/user/{id}" , method = RequestMethod.PUT)
     public ResponseEntity<User> updateuser (@PathVariable("id") String id, @RequestBody User user)
     {
  	   Logger.debug("->->-> calling method UpdateUser");
  	   user = userDAO.get(user.getId());
  	   if(userDAO.get(id) == null)
  	   { 
  		   Logger.debug("->->->->User does not exist with id "+ user.getId());
  		   user = new User();
  		   user.setErrorMessage("User does not exist with id "+ user.getId());
  		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
  	   }
  	   userDAO.update(user);
  	   
  	   return new ResponseEntity<User>(user, HttpStatus.OK);
     }
     
     
     @RequestMapping(value="/user/{id}",method = RequestMethod.GET)
 	public ResponseEntity<User> getuser(@PathVariable("id") String id)
 	{
 	   Logger.debug("->->-> calling method getUser");
 	   Logger.debug("->->->->"+id);
 	   User user = userDAO.get(id);
 	   if(userDAO.get(id) == null)
 	   { 
 		   Logger.debug("->->->->User does not exist with id " +id);
 		   user = new User();
 		   user.setErrorMessage("User does not exist with id ");
 		   return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
 	   }
 	   Logger.debug("->->->->User exist with id " +id);
 	   return new ResponseEntity<User>(user, HttpStatus.OK); 
 	}

     @RequestMapping(value = "/Authenticate/",method =RequestMethod.POST)
     public ResponseEntity<User> authenticate(@RequestBody User user)
     {
    	 user = userDAO.validate(user.getId(),user.getPassword());
    	 if(user==null)
    	 {
         user = new User();
    	 user = userDAO.get("404");
    	 user.setErrorMessage("Invalid credentials...Please try again ");
    	 }
    	 else
    	 {
    		 httpSession.setAttribute("loggedInUser",user);
    		 httpSession.setAttribute("loggedInUserId",user.getId());
    		 user.setErrorCode("200");
    		 user.setErrorMessage("Logged in successfully");
    		
    	 }
     return  new ResponseEntity<User>(user,HttpStatus.OK);

     }
   
     
   

     @RequestMapping(value = "/user/logout/",method =RequestMethod.GET)
     public ResponseEntity<User> LOGOUT(HttpSession session)
     {
    	 
    	 String loggedInUserID = (String) session.getAttribute("loggedInUserId");
    	 user=userDAO.get(loggedInUserID);
    	 user.setIsOnline('N');
    	 session.invalidate();
    	 
    	 if(userDAO.update(user)){
    		 user.setErrorCode("200");
    		 user.setErrorMessage("logged out");
    	 }
    	 else 
    		 {user.setErrorCode("404");
    	 user.setErrorMessage("not logged out");}
    	 
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}
   

     @RequestMapping(value = "/Register/",method =RequestMethod.POST)
     public ResponseEntity<User> register(@RequestBody User user)
     {
  
    	 if (userDAO.get(user.getId()) !=null){
    		 user.setErrorCode("404");
    		 user.setErrorMessage("User already exist with this id "+user.getId());
    	 }
    	 else{
    		 userDAO.save(user);
    		 user.setErrorCode("200");
    		 user.setErrorMessage("Thanks for Registration");
    	 }
     
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}
     
     

     @RequestMapping(value = "/Update/",method =RequestMethod.PUT)
     public ResponseEntity<User> uupdate(@RequestBody User user)
     {
    	
    	 if(userDAO.update(user)==false)
    	 {
    		 
    	 user.setErrorCode("404");
    	 user.setErrorMessage("The update is not success.Please try after some time");
    	 }
    	 else{
    		 user.setErrorCode("200");
        	 user.setErrorMessage("Successfullyupdated the information");
    	 }
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}
     
     @RequestMapping(value = "/useraccept/",method =RequestMethod.PUT)
     public ResponseEntity<User> useraccept(@PathVariable("id") String id,@RequestBody User user)
     {
    	user=userDAO.get(user.getId());
    	 if(user==null)
    	 {
    		Logger.debug("->->User does not exist with id"+user.getId());
    		user=new User();
    	 user.setErrorMessage("User does not exist with id"+user.getId());
    	 return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    	 }
    	 
    		 user.setStatus('A');
        	 userDAO.update(user);
    	 
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}
     
     
     @RequestMapping(value = "/userreject/",method =RequestMethod.PUT)
     public ResponseEntity<User> userreject(@PathVariable("id") String id,@RequestBody User user)
     {
    	user=userDAO.get(user.getId());
    	 if(user==null)
    	 {
    		Logger.debug("->->User does not exist with id"+user.getId());
    		user=new User();
    	 user.setErrorMessage("User does not exist with id"+user.getId());
    	 return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
    	 }
    	 
    		 user.setStatus('R');
        	 userDAO.update(user);
    	 
     return  new ResponseEntity<User>(user,HttpStatus.OK);
}
     
    
}




