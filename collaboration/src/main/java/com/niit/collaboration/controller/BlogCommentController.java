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
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.BlogCommentDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;

@RestController
public class BlogCommentController {

	@Autowired
	private BlogCommentDAO blogCommentDAO;
	
	@Autowired
	private BlogComment blogComment;
	
	@RequestMapping(value = "/blogcomments" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getblogcomments()
	{
		List<BlogComment> blogcomment = blogCommentDAO.list();
		if(blogcomment == null)
		{
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("No blogs are available");
       	  return new ResponseEntity<List<BlogComment>>(HttpStatus.NO_CONTENT);
		}
		 else
		 {
       	  return new ResponseEntity<List<BlogComment>>(blogcomment,HttpStatus.OK);
         }
	}
	
	@RequestMapping(value = "/blogcomment/{blogID}" , method = RequestMethod.GET)
	public ResponseEntity<List<BlogComment>> getBlog(@PathVariable("blogID")int blogID){
		
		List<BlogComment> blogcomments = blogCommentDAO.get(blogID);
		if(blogcomments == null)
		{
			blogComment = new BlogComment();
			blogComment.setErrorCode("404");
			blogComment.setErrorMessage("Blog not found with the id"+ blogID);
		}
		
		return new ResponseEntity<List<BlogComment>>(blogcomments,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/blogcomment/{id}/{bcomment}/{rating}" , method = RequestMethod.POST)
	public ResponseEntity<BlogComment> createBlog(@RequestBody BlogComment blogcomment, @PathVariable("id") int id,
			                @PathVariable("bcomment") String bcomment, @PathVariable("rating") String rating, HttpSession httpsession) {
		
		System.out.println("Comment from the front end "+blogComment.getBcomment());
   		String loggedInuserID = (String) httpsession.getAttribute("loggedInUserID");
	
   		blogComment.setUserID(loggedInuserID);  	
   		blogComment.setBlogID(id);
   		blogComment.setBcomment(bcomment);
   		blogComment.setRating(rating);
   		blogComment.setDateTime(null);
		blogCommentDAO.save(blogComment);
		
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}
}
	
	


	/*
	@RequestMapping(value = "/blogcomment/{id}" , method = RequestMethod.GET)
	public ResponseEntity<BlogComment> getBlog(@PathVariable("id")int id){
		
		BlogComment blogcomment = blogCommentDAO.get(id);
		if(blogcomment == null)
		{
			blogcomment = new BlogComment();
			blogcomment.setErrorCode("404");
			blogcomment.setErrorMessage("Blog not found with the id"+ id);
		}
		
		return new ResponseEntity<BlogComment>(blogcomment, HttpStatus.OK);
	}
	
}*/
