package org.eco.vegalize.services;

import org.eco.vegalize.models.Category;
import org.eco.vegalize.models.Product;
import org.eco.vegalize.models.User;
import org.eco.vegalize.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private UserService userService;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Optional<User> findProvider(int id){
        Optional<User> provider = userService.findUserById(id);
        return provider;
    }
    public Product save(Product product) throws ChangeSetPersister.NotFoundException {
        Optional<Category> category = categoryService.findById(product.getCategory().getId());
        if (!category.isPresent()){
            throw new ChangeSetPersister.NotFoundException();
        }

        product.setCategory(category.get());
        Product obj = productRepository.save(product);
        return product;
    }

    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }

    public Iterable<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public URI savePictureProduct(MultipartFile multipartFile, Product product) throws IOException, URISyntaxException {
        URI uri = s3Service.uploadFile(multipartFile);
        product.setPicture(uri);
        productRepository.save(product);
        return uri;
    }
}