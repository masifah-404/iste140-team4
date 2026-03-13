package org.example.shatterrealms.models;

<<<<<<< HEAD
import java.util.List;

public class Cart {
    private Long cartId;
    private Member member;
    private List<CartItem> items;

    public Long getCartId() { return cartId; }
    public void setCartId(Long cartId) { this.cartId = cartId; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}

=======
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cart {
    private Long productId;
    private Member member;
    private List<CartItem> items;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
}
>>>>>>> 0b0acb7d0c7fb8db43152217d5a7440354814268
