package com.niit.collaboration.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;
import com.niit.collaboration.model.User;
@RestController
public class FriendController {

	@Autowired
	Friend friend;
	
	
	@Autowired
	FriendDAO friendDAO;
	 
	
	  @Autowired
	  HttpSession httpSession;
	  
	  
	     @RequestMapping(value="/friends",method = RequestMethod.GET)
	  	public ResponseEntity<List<Friend>> listAllUsers()
	  	{
	  		//log.debug("->->->calling method listAllUsers");
	  		List<Friend> friends = friendDAO.list();
	  		
	  		if(friends.isEmpty()) 
	  		{
	  			friend=new Friend();
	  			friend.setErrorCode("404");
	  			 friend.setErrorMessage("no friends are avaliable");
	  			
	  		}
	  		
	  		return new ResponseEntity<List<Friend>>(friends, HttpStatus.OK);
	  	}
	  	
	     
	     
	     @RequestMapping("/friends/{id}")
	     public ResponseEntity<Friend> getFriendByID(@PathVariable("id") String friendID)
	     {
	    	 
	    	// friend = friendDAO.get(friendID);
	    	 if(friend==null)
	    	 {
	    		 friend = new Friend();
	    		 friend.setErrorCode("404");
	    	  	 friend.setErrorMessage("friend does not found with id"+friendID);
	    	 }
	     return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	     
	     
	     @RequestMapping(value = "/fupdate/",method =RequestMethod.PUT)
	     public ResponseEntity<Friend> Update(@RequestBody Friend friend)
	     {
	    	
	    	 if(friendDAO.update(friend)==false)
	    	 {
	    		 
	    	 friend.setErrorCode("404");
	    	 friend.setErrorMessage("The update is not success.Please try after some time");
	    	 }
	    	 else{
	    		 friend.setErrorCode("200");
	        	 friend.setErrorMessage("Successfullyupdated the information");
	    	 }
	     return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	    
	     
	     @RequestMapping("/friends/{name}")
	     public ResponseEntity<Friend> getFriendByname(@PathVariable("name")  String friendname)
	     {
	    	 
	    	 //friend = friendDAO.get(friendname);
	    	 if(friend==null)
	    	 {
	    		 friend = new Friend();
	    		 friend.setErrorCode("404");
	    	  	 friend.setErrorMessage("friend does not found with name,  "+friendname);
	    	 }
	     return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	     
	     
	     @RequestMapping(value = "/addfriend/{friendID}",method =RequestMethod.POST)
	     public ResponseEntity<Friend> sendFriendRequest(@PathVariable("friendID")  String friendID,HttpSession session)
	     {
	    	 String uid=(String) session.getAttribute("loggedInUserId");
	    	 friend=new Friend();
	    	 friend.setUserID(uid);
	    	 friend.setFriendID(friendID);
	    	 friend.setStatus("N");
	    	 friend.setIsOnline('y');
	    	 friendDAO.save(friend);
	     
	     return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	     }
	     @RequestMapping(value = "/acceptFriend/{friendID}",method=RequestMethod.PUT)
	     public ResponseEntity<Friend> acceptFriendRequest(@PathVariable("friendID") String friendID,HttpSession session)
	     {
	    	 
	    	 User loggedInUser=(User) session.getAttribute("loggedInUser");
	    	 friend=friendDAO.get(friendID, loggedInUser.getId());
	    	 friend.setStatus("A");
	    	 friendDAO.update(friend);
	
	    	 
	    	 return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	    	 
	     }
	     
	     @RequestMapping(value = "/rejectFriend/{friendID}",method=RequestMethod.PUT)
	     public ResponseEntity<Friend>rejectFriendRequest(@PathVariable("friendID") String friendID,HttpSession session)
	     {
	    	 
	    	 User loggedInUser=(User) session.getAttribute("loggedInUser");
	    	 friend=friendDAO.get(friendID, loggedInUser.getId());
	    	 friend.setStatus("R");
	    	 friendDAO.update(friend);
	
	    	 
	    	 return  new ResponseEntity<Friend>(friend,HttpStatus.OK);
	    	 
	     }
	    
	    @RequestMapping(value = "/getMyFriends/",method=RequestMethod.GET)
	     public ResponseEntity<List<Friend>> getMyFriends(HttpSession session)
	     {
	    	String userid= (String) session.getAttribute("loggedInUserId");
	    	 List<Friend> myFriendRequests = friendDAO.ListMyFriends(userid);
	    	 return  new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
	    	 
	     }
	     
	     @RequestMapping(value = "/getMyFriendRequests/",method=RequestMethod.GET)
	     public ResponseEntity<List<Friend>> getMyFriendRequests(HttpSession session)
	     {
	    	 
	    	 User user=(User) session.getAttribute("loggedInUser");
	    	 List<Friend> myFriendRequests = friendDAO.ListNewFriendRequest(user.getId());
	    	 
	    	 return  new ResponseEntity<List<Friend>>(myFriendRequests,HttpStatus.OK);
	    	 
	     }
	     
}
