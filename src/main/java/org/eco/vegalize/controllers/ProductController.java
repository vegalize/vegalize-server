package org.eco.vegalize.controllers;

import org.eco.vegalize.models.Product;
import org.eco.vegalize.models.User;
import org.eco.vegalize.services.ProductService;
import org.eco.vegalize.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/{providerId}")
    public ResponseEntity<?> registerProduct(@Valid @RequestBody Product product, Errors errors, @PathVariable Integer providerId){
        if (errors.hasErrors()){
            String message = errors.getAllErrors().get(0).getDefaultMessage();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, message);
        }
        Optional<User> provider = productService.findProvider(providerId);
        if(provider.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Provider not exist");
        }
        Product obj;
        try {
            product.setProvider(provider.get());
            obj = productService.save(product);
        } catch (ChangeSetPersister.NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
        }
        return ResponseEntity.status(201).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProducts(){
        List<Product> products = (List) productService.findAllProducts();
        return ResponseEntity.status(200).body(products);
    }
    
    @PostMapping("/image/{productId}")
    public ResponseEntity<URI> uploadImage(
            @RequestParam(name = "file") MultipartFile multipartFile,
            @PathVariable int productId) throws IOException, URISyntaxException {
        Optional<Product> product = productService.findById(productId);
        if (product.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }
        URI uri = productService.savePictureProduct(multipartFile, product.get());
        return ResponseEntity.ok().body(uri);
    }
}

