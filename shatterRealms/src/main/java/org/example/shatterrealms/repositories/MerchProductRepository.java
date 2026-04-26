package org.example.shatterrealms.repositories;

import org.example.shatterrealms.models.MerchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// Safia Siddique 761008460

@Repository
public interface MerchProductRepository extends JpaRepository<MerchProduct, Long> {

    // Find method by name, case insensitive
    List<MerchProduct> findByProductNameContainingIgnoreCase(String productName);

    // Update method
    @Modifying
    @Query("UPDATE MerchProduct p SET p.stock = :stock WHERE p.productId = :id")
    int updateStockById(@Param("id") Long id, @Param("stock") int stock);

    // Find method for exact name search
    List<MerchProduct> findByProductName(String name);

    // Find method for items in low stock
    List<MerchProduct> findByStockLessThan(int limit);

    // Find method for finding product in a price range
    List<MerchProduct> findByProductPriceBetween(Double min, Double max);

    // Find method for finding products and sorting them in descending order of price
    List<MerchProduct> findAllByOrderByProductPriceDesc();
}
