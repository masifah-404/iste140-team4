package org.example.shatterrealms.models;

import jakarta.persistence.*;

//Arina Baiazitova 761008753
@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private MerchProduct product;

    @Column(nullable = false)
    private int quantity;


    public CartItem() {}

    public CartItem(MerchProduct product, int quantity) {
        this.product  = product;
        this.quantity = quantity;
    }

    public Double getLineTotal() {
        if (product != null && product.getProductPrice() != null) {
            return product.getProductPrice() * quantity;
        }
        return 0.0;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public MerchProduct getProduct() { return product; }
    public void setProduct(MerchProduct product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
