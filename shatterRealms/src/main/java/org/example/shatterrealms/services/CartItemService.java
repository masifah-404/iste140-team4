package org.example.shatterrealms.services;

import org.example.shatterrealms.models.CartItem;
import org.example.shatterrealms.models.MerchProduct;
import org.example.shatterrealms.repositories.CartItemRepository;
import org.example.shatterrealms.repositories.MerchProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Arina Baiazitova 761008753

@Service
@Transactional
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MerchProductRepository productRepository;

    // Read method

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    public List<CartItem> findByProductId(Long productId) {
        return cartItemRepository.findByProduct_ProductId(productId);
    }

    // Create new cart item


    public CartItem addToCart(Long productId, int quantity) {
        MerchProduct product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        // Update quantity
        List<CartItem> existing = cartItemRepository.findByProduct_ProductId(productId);
        if (!existing.isEmpty()) {
            CartItem item = existing.get(0);
            item.setQuantity(item.getQuantity() + quantity);
            return cartItemRepository.save(item);
        }

        CartItem newItem = new CartItem(product, quantity);
        return cartItemRepository.save(newItem);
    }

    //Full cart item update


    public Optional<CartItem> updateCartItem(Long id, Long productId, int quantity) {
        return cartItemRepository.findById(id).map(existing -> {
            if (productId != null) {
                productRepository.findById(productId).ifPresent(existing::setProduct);
            }
            existing.setQuantity(quantity);
            return cartItemRepository.save(existing);
        });
    }

    // Partial field update

    public boolean updateQuantity(Long id, int newQuantity) {
        return cartItemRepository.updateQuantityById(id, newQuantity) > 0;
    }

    //Delete Cart Item

    public boolean deleteCartItem(Long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
