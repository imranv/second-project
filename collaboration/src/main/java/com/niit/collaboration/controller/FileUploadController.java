package com.niit.collaboration.controller;





import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.niit.collaboration.dao.FileUploadDAO;
import com.niit.collaboration.model.FileUpload;
import com.niit.collaboration.model.User;



/** Handles requests for the file upload page. */
@RestController
public class FileUploadController 
{
	@Autowired
	private FileUploadDAO fileUploadDAO;

	 @RequestMapping(value = "/eventimage", method = RequestMethod.POST)
	    public String eimage(HttpServletRequest request,HttpSession session,@RequestParam("uploadFile") CommonsMultipartFile uploadFile) throws Exception 
	    {
		 String n=(String) session.getAttribute("Eventname");
		 handleFileUpload(request,session,uploadFile,n);
		 return "event image";
	    }
	 @RequestMapping(value = "/userimage", method = RequestMethod.POST)
	    public String uimage(HttpServletRequest request,HttpSession session,@RequestParam("uploadFile") CommonsMultipartFile uploadFile) throws Exception 
	    {
		 User user=(User)session.getAttribute("loggedInUser");
		 handleFileUpload(request,session,uploadFile,user.getName());
		 return "user image Is Uploaded";
	    }
	
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String handleFileUpload(HttpServletRequest request,HttpSession session,@RequestParam("uploadFile") CommonsMultipartFile uploadFile,String n) throws Exception 
    {
    	
         User user=(User)session.getAttribute("loggedInUser");
         if(user==null)
        	 throw new RuntimeException("Not logged in");
         System.out.println("USER is " + user.getName());
         
         if (uploadFile != null ) {
             CommonsMultipartFile aFile = uploadFile;
            
                System.out.println("Saving file: " + aFile.getOriginalFilename());
                
                FileUpload fileUpload = new FileUpload();
                fileUpload.setFileName(aFile.getOriginalFilename());
                fileUpload.setData(aFile.getBytes());//image 
                System.out.println("successfully registered");
                fileUpload.setUsername(n);//login details
                fileUploadDAO.save(fileUpload);
                //select * from proj2_profie_pics where username='smith'
                FileUpload getFileUpload=fileUploadDAO.getFile(n);
            	String name=getFileUpload.getFileName();
            	System.out.println(getFileUpload.getData());
            	byte[] imagefiles=getFileUpload.getData();  //image
            	try{
            		//change the path according to your workspace and the name of your project
            		String path="C:/Users/lenovo/Downloads/CollaborationFrontEnd/CollaborationFrontEnd/src/main/webapp/ImageStored/"+n+".jpg";
            		File file=new File(path);
            		//file.mkdirs();
            		FileOutputStream fos = new FileOutputStream(file);
            		fos.write(imagefiles);// write the array of bytes in username file.
            		fos.close();
            		}catch(Exception e){
            		e.printStackTrace();
            		}
             }
                
 
        return "<h1>Successfully uploaded the Profile Picture</h1>";
    }
}


