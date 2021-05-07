package com.jwtAuthentication.DaoLayer;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwtAuthentication.Entities.Application;



public interface AppRepository extends JpaRepository<Application, String> {

}
