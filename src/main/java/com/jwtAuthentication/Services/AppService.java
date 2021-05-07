package com.jwtAuthentication.Services;

import java.util.ArrayList;

import com.jwtAuthentication.Entities.Application;



public interface AppService {
	public Application getSingleApp(String appName);
	public ArrayList<Application> getThemAll();
	public Application addSingleApplication(Application app);
	public Application updateSingleApplication(Application app);
	public void deleteApp(String appName);

}
