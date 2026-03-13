package org.example.shatterrealms.services;

import org.example.shatterrealms.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShatterRealmsService {

    private final List<Member> members = new ArrayList<>();
    private final List<MerchProduct> products = new ArrayList<>();
    private final List<CartItem> cartItems = new ArrayList<>();

    public ShatterRealmsService() {
        seedMembers();
        seedProducts();
        seedCartItems();
    }

    // ── Seed data ──────────────────────────────────────────────────────────────

    private void seedMembers() {
        Member m1 = new Member();
        m1.setMemberId(1L);
        m1.setMemberName("Thorin Stonehelm");
        m1.setMemberEmail("thorin@shatterrealms.com");
        m1.setMemberPassword("dwarven123");

        Member m2 = new Member();
        m2.setMemberId(2L);
        m2.setMemberName("Lyria Moonwhisper");
        m2.setMemberEmail("lyria@shatterrealms.com");
        m2.setMemberPassword("elven456");

        Member m3 = new Member();
        m3.setMemberId(3L);
        m3.setMemberName("Garrus the Bold");
        m3.setMemberEmail("garrus@shatterrealms.com");
        m3.setMemberPassword("fighter789");

        members.add(m1);
        members.add(m2);
        members.add(m3);
    }

    private void seedProducts() {
        MerchProduct p1 = new MerchProduct();
        p1.setProductId(1L);
        p1.setProductName("Shattered Realms T-Shirt");
        p1.setProductDescription("Premium cotton tee featuring the campaign crest.");
        p1.setProductPrice(29.99);
        p1.setStock(50);

        MerchProduct p2 = new MerchProduct();
        p2.setProductId(2L);
        p2.setProductName("Eldoria Map Poster");
        p2.setProductDescription("High-detail illustrated map of the shattered continent.");
        p2.setProductPrice(19.99);
        p2.setStock(30);

        MerchProduct p3 = new MerchProduct();
        p3.setProductId(3L);
        p3.setProductName("Dice Set – Gold & Maroon");
        p3.setProductDescription("7-piece polyhedral dice set in campaign colours.");
        p3.setProductPrice(14.99);
        p3.setStock(100);

        MerchProduct p4 = new MerchProduct();
        p4.setProductId(4L);
        p4.setProductName("Campaign Enamel Pin");
        p4.setProductDescription("Collectible enamel pin of the Shattered Realms logo.");
        p4.setProductPrice(9.99);
        p4.setStock(200);

        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
    }

    private void seedCartItems() {
        CartItem ci1 = new CartItem();
        ci1.setProductId(1L);
        ci1.setProduct(products.get(0));
        ci1.setQuantity(2);

        CartItem ci2 = new CartItem();
        ci2.setProductId(3L);
        ci2.setProduct(products.get(2));
        ci2.setQuantity(1);

        cartItems.add(ci1);
        cartItems.add(ci2);
    }

    // ── Members ────────────────────────────────────────────────────────────────

    public List<Member> getMembers() { return members; }

    public Member findMemberById(Long id) {
        return members.stream()
                .filter(m -> m.getMemberId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addMember(Member member) {
        member.setMemberId((long) (members.size() + 1));
        members.add(member);
    }

    public void updateMember(Long memberId, String name, String email, String password) {
        Member member = findMemberById(memberId);
        if (member == null) return;
        if (name != null && !name.isBlank())         member.setMemberName(name);
        if (email != null && !email.isBlank())       member.setMemberEmail(email);
        if (password != null && !password.isBlank()) member.setMemberPassword(password);
    }

    // ── Products (read-only catalogue) ────────────────────────────────────────

    public List<MerchProduct> getProducts() { return products; }

    public MerchProduct findProductById(Long id) {
        return products.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ── Cart CRUD ──────────────────────────────────────────────────────────────

    public List<CartItem> getCartItems() { return cartItems; }

    // CREATE – add a new item, or increase quantity if already present
    public void addCartItem(Long productId, int quantity) {
        MerchProduct product = findProductById(productId);
        if (product == null) return;

        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem();
        newItem.setProductId(productId);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        cartItems.add(newItem);
    }

    // UPDATE – change the quantity of an existing cart item
    public void updateCartItem(Long productId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
    }

    // DELETE – remove an item from the cart entirely
    public void removeCartItem(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }
}
