package com.jwtAuthentication.Configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.jwtAuthentication.Services.CustomUserDetailsService;
import com.jwtAuthentication.helper.JwtUtil;

//before sending the token ,this class will authenticate the token whether its valid or not				 

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get jwt token
		//Check if it starts with Bearer
		//validation
		String requestTokenHeader=request.getHeader("Authorization"); // to check the authorization is of type Bearer
	
		String username=null;
		String jwtToken=null;
		
		//checking for null and format
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken=requestTokenHeader.substring(7);
			
			
			//because token header starts with "bearer " and then has the token so it starts from index 7 
			try {
				
				username=this.jwtUtil.extractUsername(jwtToken);  //extract username from token
			}catch (Exception e) {
				e.printStackTrace();
			}
			//security
			
			UserDetails userDetails =this.customUserDetailsService.loadUserByUsername(username);
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken); 
			}
			else {
				System.out.print("Token is not valid");
			}
		
		}
		filterChain.doFilter(request, response);
	}

}
