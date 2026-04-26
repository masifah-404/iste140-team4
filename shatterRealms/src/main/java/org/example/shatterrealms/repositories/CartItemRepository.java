package org.example.shatterrealms.repositories;

import org.example.shatterrealms.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
//Arina Baiazitova 761008753
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    // Find method for product ID
    List<CartItem> findByProduct_ProductId(Long productId);

    @Query("SELECT c FROM CartItem c WHERE c.quantity >= :minQty ORDER BY c.quantity DESC")
    List<CartItem> findByMinQuantity(@Param("minQty") int minQty);

    // Update

    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = :quantity WHERE c.id = :id")
    int updateQuantityById(@Param("id") Long id, @Param("quantity") int quantity);

    List<CartItem> findByQuantityGreaterThan(Integer qty);

    List<CartItem> findAllByOrderByQuantityDesc();
}
