package org.example.shatterrealms.models;

<<<<<<< HEAD
public class CartItem {
=======
import org.springframework.stereotype.Component;

@Component
public class CartItem {

>>>>>>> 0b0acb7d0c7fb8db43152217d5a7440354814268
    private Long productId;
    private MerchProduct product;
    private int quantity;

<<<<<<< HEAD
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public MerchProduct getProduct() { return product; }
    public void setProduct(MerchProduct product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    // Helper for template: calculate line total
    public Double getLineTotal() {
        if (product != null && product.getProductPrice() != null) {
            return product.getProductPrice() * quantity;
        }
        return 0.0;
    }
}
=======
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
>>>>>>> 0b0acb7d0c7fb8db43152217d5a7440354814268
