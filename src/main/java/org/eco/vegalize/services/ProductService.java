package org.eco.vegalize.services;

import org.eco.vegalize.models.Product;
import org.eco.vegalize.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
}