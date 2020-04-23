package org.eco.vegalize.services;

import org.eco.vegalize.models.Category;
import org.eco.vegalize.models.Product;
import org.eco.vegalize.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product save(Product product) throws ChangeSetPersister.NotFoundException {
        Optional<Category> category = categoryService.findById(product.getCategory().getId());
        if (!category.isPresent()){
            throw new ChangeSetPersister.NotFoundException();
        }
        product.setCategory(category.get());
        Product obj = productRepository.save(product);
        return product;
    }

    public Iterable<Product> findAllProducts(){
        return productRepository.findAll();
    }
}