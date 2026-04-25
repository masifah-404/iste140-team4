package org.example.shatterrealms.models;

import jakarta.persistence.*;

// Safia Siddique 761008460

@Entity
@Table(name = "merch_products")
public class MerchProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false, length = 150)
    private String productName;

    @Column(length = 500)
    private String productDescription;

    @Column(nullable = false)
    private Double productPrice;

    @Column(nullable = false)
    private int stock;

    public MerchProduct() {}

    public MerchProduct(String productName, String productDescription,
                        Double productPrice, int stock) {
        this.productName        = productName;
        this.productDescription = productDescription;
        this.productPrice       = productPrice;
        this.stock              = stock;
    }

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
}
