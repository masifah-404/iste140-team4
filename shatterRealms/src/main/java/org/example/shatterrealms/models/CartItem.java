package org.example.shatterrealms.models;

import org.springframework.stereotype.Component;

@Component
public class CartItem {

    private Long productId;
    private MerchProduct product;
    private int quantity;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public MerchProduct getProduct() {
        return product;
    }

    public void setProduct(MerchProduct product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
