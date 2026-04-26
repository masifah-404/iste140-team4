package org.example.shatterrealms.services;

import org.example.shatterrealms.models.MerchProduct;
import org.example.shatterrealms.repositories.CartItemRepository;
import org.example.shatterrealms.repositories.MerchProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Safia Siddique 761008460
@Service
@Transactional
public class MerchProductService {

    @Autowired
    private MerchProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    // Read methods
    public List<MerchProduct> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<MerchProduct> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<MerchProduct> searchByName(String name) {
        return productRepository.findByProductNameContainingIgnoreCase(name);
    }

    public List<MerchProduct> findByMaxPrice(Double maxPrice) {
        return productRepository.findByMaxPrice(maxPrice);
    }

    // Create method

    public MerchProduct saveProduct(MerchProduct product) {
        return productRepository.save(product);
    }

    // Full product updating method

    public Optional<MerchProduct> updateProduct(Long id, MerchProduct updated) {
        return productRepository.findById(id).map(existing -> {
            existing.setProductName(updated.getProductName());
            existing.setProductDescription(updated.getProductDescription());
            existing.setProductPrice(updated.getProductPrice());
            existing.setStock(updated.getStock());
            return productRepository.save(existing);
        });
    }

    // Partial field updating

    public boolean updateStock(Long id, int newStock) {
        return productRepository.updateStockById(id, newStock) > 0;
    }

    // Cascading delete method

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            List<org.example.shatterrealms.models.CartItem> itemsInCart =
                    cartItemRepository.findByProduct_ProductId(id);

            cartItemRepository.deleteAll(itemsInCart);

            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
