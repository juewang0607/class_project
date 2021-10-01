package com.kk7.repository;


import com.kk7.domain.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, Integer> {

}
