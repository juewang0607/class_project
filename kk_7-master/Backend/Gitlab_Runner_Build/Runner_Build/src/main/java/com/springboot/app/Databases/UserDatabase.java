package com.springboot.app.Databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.Entities.User;


@Repository
public interface UserDatabase extends JpaRepository<User, Integer>{

	

}
