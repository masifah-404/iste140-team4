package org.example.shatterrealms;

import org.example.shatterrealms.models.CartItem;
import org.example.shatterrealms.models.MerchProduct;
import org.example.shatterrealms.repositories.MerchProductRepository;
import org.example.shatterrealms.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitialiser implements CommandLineRunner {

    @Autowired private MerchProductRepository productRepository;
    Autowired private CartItemRepository    cartItemRepository;

    @Override
    public void run(String... args) {
        seedProducts();
        seedCartItems();
    }

    private void seedProducts() {
        if (productRepository.count() > 0) return;
        productRepository.save(new MerchProduct(
                "Shattered Realms T-Shirt",
                "Premium cotton tee featuring the campaign crest.", 29.99, 50));
        productRepository.save(new MerchProduct(
                "Eldoria Map Poster",
                "High-detail illustrated map of the shattered continent.", 19.99, 30));
        productRepository.save(new MerchProduct(
                "Dice Set – Gold & Maroon",
                "7-piece polyhedral dice set in campaign colours.", 14.99, 100));
        productRepository.save(new MerchProduct(
                "Campaign Enamel Pin",
                "Collectible enamel pin of the Shattered Realms logo.", 9.99, 200));
    }
    private void seedCartItems() {
        if (cartItemRepository.count() > 0) return;
        productRepository.findById(1L).ifPresent(p -> cartItemRepository.save(new CartItem(p, 2)));
        productRepository.findById(3L).ifPresent(p -> cartItemRepository.save(new CartItem(p, 1)));
    }

}
