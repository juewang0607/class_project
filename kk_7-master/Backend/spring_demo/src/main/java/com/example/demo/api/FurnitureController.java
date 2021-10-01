package com.example.demo.api;

import com.example.demo.dao.FurnitureRepository;
import com.example.demo.model.Furniture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/furniture")
@RestController
public class FurnitureController {
    @Autowired
    FurnitureRepository furnitureRepository;

    @GetMapping
    public List<Furniture> showAllFurniture() {
        return furnitureRepository.findAll();
    }

    @PostMapping
    public void addFurniture(@Valid @NonNull @RequestBody Furniture furniture) {
        furnitureRepository.save(furniture);
    }
}
