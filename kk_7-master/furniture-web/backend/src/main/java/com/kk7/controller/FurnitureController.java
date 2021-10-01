package com.kk7.controller;

import com.kk7.repository.FurnitureRepository;
import com.kk7.domain.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("/api-f")
@RestController
public class FurnitureController {
    @Autowired
    FurnitureRepository furnitureRepository;

    @GetMapping(path = "/all")
    public List<Furniture> showAllFurniture() {
        return furnitureRepository.findAll();
    }

    @PostMapping(path = "/furniture")
    public void addFurniture(@Valid @NonNull @RequestBody Furniture furniture) {
        furnitureRepository.save(furniture);
    }
}
