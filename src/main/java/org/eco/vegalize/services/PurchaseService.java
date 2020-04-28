package org.eco.vegalize.services;

import org.eco.vegalize.models.Product;
import org.eco.vegalize.models.Purchase;
import org.eco.vegalize.models.User;
import org.eco.vegalize.repositories.ProductRepository;
import org.eco.vegalize.repositories.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    public Optional<Purchase> findPurchase(int purchaseId){
        return purchaseRepository.findById(purchaseId);
    }

    public Purchase verifyItems(Purchase purchase){
        List<Product> productList = new ArrayList<>();
        for (Product product: purchase.getItems()) {
            Optional<Product> optinal = productRepository.findById(product.getId());
            if (optinal.isPresent()){
                productList.add(optinal.get());
            }
        }
        purchase.setItems(productList);
        return purchase;
    }


    public Purchase savePurchase(Purchase purchase, int userId){
        Purchase obj = verifyItems(purchase);
        List<User> providers = new ArrayList();
        double amout = 0;
        for (Product product: purchase.getItems()) {
            amout += product.getPrice();
            if (!providers.contains(product.getProvider())){
                providers.add(product.getProvider());
            }
        }

        Optional<User> userObj = userService.findUserById(userId);
        if (userObj.isPresent()){
            purchase.setBuyer(userObj.get());
        }
        obj.setProvider(providers);
        obj.setAmout(amout);
        return purchaseRepository.save(obj);
    }
}
