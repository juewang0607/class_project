package com.example.demo.dao;

import com.example.demo.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

}
