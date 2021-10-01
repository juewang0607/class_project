package com.springboot.app.Databases;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.Entities.Message;

public interface MessageDatabase extends JpaRepository<Message, Long>{

}
