package org.eco.vegalize.controllers;

import org.eco.vegalize.models.Purchase;
import org.eco.vegalize.models.User;
import org.eco.vegalize.services.PurchaseService;
import org.eco.vegalize.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private UserService userService;

    @PostMapping("/{userID}")
    public ResponseEntity<Purchase> buy(@RequestBody Purchase purchase, @PathVariable int userID){
        Optional<User> user = userService.findUserById(userID);
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        Purchase purchaseOBJ = purchaseService.savePurchase(purchase, userID);

        return ResponseEntity.status(201).body(purchaseOBJ);
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<Purchase> findById(@PathVariable int purchaseId){
        Optional<Purchase> purchase = purchaseService.findPurchase(purchaseId);
        if(purchase.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Purchase id not exist");
        }
        return ResponseEntity.ok().body(purchase.get());
    }
}
