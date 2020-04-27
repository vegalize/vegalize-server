package org.eco.vegalize.repositories;


import org.eco.vegalize.models.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
