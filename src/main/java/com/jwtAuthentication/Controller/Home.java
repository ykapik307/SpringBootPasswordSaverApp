package com.jwtAuthentication.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Accept-Language","Authorization"})
public class Home {

	@RequestMapping("/welcome")
	public String Welcome() {
		String text="Hello , this is a private page ok ok";
		return text;
	}
}
