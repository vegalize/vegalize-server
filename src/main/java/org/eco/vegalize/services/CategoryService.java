package org.eco.vegalize.services;

import org.eco.vegalize.models.Category;
import org.eco.vegalize.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(int id){
        Optional<Category> categoria = categoryRepository.findById(id);
        return categoria;
    }

    public Iterable<Category> findAll(){
        return categoryRepository.findAll();
    }
}
