package org.example.shatterrealms.controllers;

import org.example.shatterrealms.models.MerchProduct;
import org.example.shatterrealms.services.MerchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Safia Siddique 761008460
@RestController
@RequestMapping("/api/merch")
@CrossOrigin(origins = "*")
public class MerchProductController {

    @Autowired
    private MerchProductService productService;

    // GET method to return all products
    @GetMapping
    public ResponseEntity<List<MerchProduct>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // GET method for returning one product by id
    @GetMapping("/{id}")
    public ResponseEntity<MerchProduct> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET method for searching for product by name, case insensitive
    @GetMapping("/search")
    public ResponseEntity<List<MerchProduct>> searchProducts(@RequestParam String name) {
        return ResponseEntity.ok(productService.searchByName(name));
    }

    // POST method for creating new product
    @PostMapping
    public ResponseEntity<MerchProduct> createProduct(@RequestBody MerchProduct product) {
        MerchProduct saved = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT method for updating everything for an existing product
    @PutMapping("/{id}")
    public ResponseEntity<MerchProduct> updateProduct(@PathVariable Long id,
                                                      @RequestBody MerchProduct product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE method for deleting product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}