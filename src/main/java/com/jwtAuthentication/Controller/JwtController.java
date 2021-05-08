package com.jwtAuthentication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jwtAuthentication.Services.CustomUserDetailsService;
import com.jwtAuthentication.helper.JwtUtil;
import com.jwtAuthentication.modelData.JwtRequest;
import com.jwtAuthentication.modelData.JwtResponse;

//to generate the token for the first time
@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Accept-Language","Authorization"})
public class JwtController {
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@RequestMapping(value = "/token",method =RequestMethod.POST)
	//Post MApping beacuse we will be getting username and password 
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Invalid Username");
		}catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		//fine area
		UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		 String token=this.jwtUtil.generateToken(userDetails);
		 
		 System.out.print("JWT "+token);
		 
		 // now we need to return the token in JSON format as its the data type of this method
		 // for that we need to create another class JwtResponse in the modelData package 
		 
		 
		 return ResponseEntity.ok(new JwtResponse(token));
	}
}
