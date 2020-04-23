package org.eco.vegalize.repositories;

import org.eco.vegalize.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
