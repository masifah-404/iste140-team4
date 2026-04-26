package org.example.shatterrealms.controllers;

import org.example.shatteredrealms.models.CartItem;
import org.example.shatteredrealms.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//Arina Baiazitova 761008753
@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    // GET method to return all items

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    // GET method to return items by ID

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable Long id) {
        return cartItemService.getCartItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET method to search items by ID

    @GetMapping("/search")
    public ResponseEntity<List<CartItem>> searchByProduct(@RequestParam Long productId) {
        return ResponseEntity.ok(cartItemService.findByProductId(productId));
    }

    // POST method for creating new item
    @PostMapping
    public ResponseEntity<?> addToCart(@RequestBody Map<String, Object> body) {
        try {
            Long productId = Long.valueOf(body.get("productId").toString());
            int  quantity  = Integer.parseInt(body.get("quantity").toString());
            CartItem item  = cartItemService.addToCart(productId, quantity);
            return ResponseEntity.status(HttpStatus.CREATED).body(item);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // PUT method for updating existing item
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable Long id,
                                            @RequestBody Map<String, Object> body) {
        Long productId = body.containsKey("productId")
                ? Long.valueOf(body.get("productId").toString()) : null;
        int quantity   = Integer.parseInt(body.get("quantity").toString());
        return cartItemService.updateCartItem(id, productId, quantity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE method to delete item by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Long id) {
        return cartItemService.deleteCartItem(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
