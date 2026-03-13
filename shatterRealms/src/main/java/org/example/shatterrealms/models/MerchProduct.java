package org.example.shatterrealms.models;

<<<<<<< HEAD
public class MerchProduct {
=======
import org.springframework.stereotype.Component;

@Component
public class MerchProduct {

>>>>>>> 0b0acb7d0c7fb8db43152217d5a7440354814268
    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;
    private int stock;

<<<<<<< HEAD
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public Double getProductPrice() { return productPrice; }
    public void setProductPrice(Double productPrice) { this.productPrice = productPrice; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
=======

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
>>>>>>> 0b0acb7d0c7fb8db43152217d5a7440354814268
}
