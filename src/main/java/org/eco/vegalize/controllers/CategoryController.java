package org.eco.vegalize.controllers;

import org.eco.vegalize.models.Category;
import org.eco.vegalize.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> allCategories(){
        List<Category> categories = (List) categoryService.findAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping()
    public ResponseEntity<Category> registerCategory(@RequestBody Category category){
        Category obj = categoryService.save(category);
        return ResponseEntity.status(201).body(obj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findCategory(@PathVariable Integer id){
        Optional<Category> optional = categoryService.findById(id);
        if (!optional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
        }
        return ResponseEntity.ok().body(optional.get());
    }

}
