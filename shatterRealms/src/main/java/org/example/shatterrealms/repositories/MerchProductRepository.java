package org.example.shatterrealms.repositories;

import org.example.shatterrealms.models.MerchProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Safia Siddique 761008460

@Repository
public interface MerchProductRepository extends JpaRepository<MerchProduct, Long> {

    // Find method by name, case insensitive
    List<MerchProduct> findByProductNameContainingIgnoreCase(String productName);

    // Update method
    @Modifying
    @Transactional
    @Query("UPDATE MerchProduct p SET p.stock = :stock WHERE p.productId = :id")
    int updateStockById(@Param("id") Long id, @Param("stock") int stock);
}
