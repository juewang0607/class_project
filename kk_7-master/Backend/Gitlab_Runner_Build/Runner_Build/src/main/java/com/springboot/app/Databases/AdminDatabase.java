package com.springboot.app.Databases;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.app.Entities.Admin;
import com.springboot.app.Entities.Item;


@Repository
public interface AdminDatabase extends JpaRepository<Admin, Integer>{

	

}
