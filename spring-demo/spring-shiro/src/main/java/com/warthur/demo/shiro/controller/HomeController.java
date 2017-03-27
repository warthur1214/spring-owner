package com.warthur.demo.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by admin on 2017/3/27.
 */
@Controller
public class HomeController {

	@RequestMapping({"/","/index"})
	public String index(){
		return "/index";
	}

	@RequestMapping(value="/login",method= RequestMethod.GET)
	public String login(){
		return "login";
	}
}
