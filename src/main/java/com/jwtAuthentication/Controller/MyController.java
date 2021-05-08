package com.jwtAuthentication.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jwtAuthentication.Entities.Application;
import com.jwtAuthentication.Services.AppService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"Accept-Language","Authorization"})
public class MyController {
	
	
	@Autowired
	private AppService appSer;
	


	@GetMapping("/apps/{appName}")
	public Application getOneApplication(@PathVariable String appName) {
		 return this.appSer.getSingleApp(appName);
	}
	
	@GetMapping("/apps")
	public ArrayList<Application> getAllApplication()
	{
		return this.appSer.getThemAll();
	}
	
	@PostMapping("/apps")
	public Application addApplication(@RequestBody Application app)
	{
		return this.appSer.addSingleApplication(app);
	}
	
	@PutMapping("/apps")
	public Application updateApplication(@RequestBody Application app)
	{
		return this.appSer.updateSingleApplication(app);
	}
	
	@DeleteMapping("/apps/{appName}")
	public ResponseEntity<HttpStatus> deleteApplication(@PathVariable String appName)
	{
		try {
			this.appSer.deleteApp(appName);
	return new ResponseEntity<>(HttpStatus.OK);
	}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
}

}
