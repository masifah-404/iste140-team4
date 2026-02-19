package org.example.shatterrealms.models;

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
