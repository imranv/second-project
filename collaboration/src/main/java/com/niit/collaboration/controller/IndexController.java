 package com.niit.collaboration.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class IndexController {
	@RequestMapping(value="/")
	public String Table()
	{
		System.out.println("index page is loading");
		return "index";
	}
	

}
