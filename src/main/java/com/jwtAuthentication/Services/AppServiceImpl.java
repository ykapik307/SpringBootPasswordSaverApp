package com.jwtAuthentication.Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwtAuthentication.DaoLayer.AppRepository;
import com.jwtAuthentication.Entities.Application;

@Service
public class AppServiceImpl implements AppService {
	
	@Autowired
	public AppRepository appRepo;

	@Override
	public Application getSingleApp(String appName) {
		return appRepo.findById(appName).get();
	}

	@Override
	public ArrayList<Application> getThemAll() {
		return (ArrayList<Application>) appRepo.findAll();
	}

	@Override
	public Application addSingleApplication(Application app) {
		appRepo.save(app);
		return app;
	}

	@Override
	public Application updateSingleApplication(Application app) {
		appRepo.save(app);
		return app;
	}

	@Override
	public void deleteApp(String appName) {
		Application entity=appRepo.getOne(appName);
		appRepo.delete(entity);

	}

}
