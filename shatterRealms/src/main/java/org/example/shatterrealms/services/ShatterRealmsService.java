package org.example.shatterrealms.services;

import org.example.shatterrealms.models.CartItem;
import org.example.shatterrealms.models.Member;
import org.example.shatterrealms.models.MerchProduct;
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

    // Load sample data so the app has something to show right away
    private void seedMembers() {
        Member m1 = new Member();
        m1.setMemberId(1L);
        m1.setMemberName("Masifah");
        m1.setMemberEmail("masifahm@shatterrealms.com");
        m1.setMemberPassword("m@sif@h");

        Member m2 = new Member();
        m2.setMemberId(2L);
        m2.setMemberName("Safia");
        m2.setMemberEmail("safias@shatterrealms.com");
        m2.setMemberPassword("s@fi@");

        Member m3 = new Member();
        m3.setMemberId(3L);
        m3.setMemberName("Arina");
        m3.setMemberEmail("arinab@shatterrealms.com");
        m3.setMemberPassword("@rin@");

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
        p3.setProductName("Dice Set - Gold & Maroon");
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
        ci1.setProduct(products.getFirst());
        ci1.setQuantity(2);

        CartItem ci2 = new CartItem();
        ci2.setProductId(3L);
        ci2.setProduct(products.get(2));
        ci2.setQuantity(1);

        cartItems.add(ci1);
        cartItems.add(ci2);
    }

    // Member-related methods
    public List<Member> getMembers() {
        return members;
    }

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
        if (member == null) {
            return;
        }
        if (name != null && !name.isBlank()) {
            member.setMemberName(name);
        }
        if (email != null && !email.isBlank()) {
            member.setMemberEmail(email);
        }
        if (password != null && !password.isBlank()) {
            member.setMemberPassword(password);
        }
    }

    // Product catalogue methods - these only read from the seeded list
    public List<MerchProduct> getProducts() {
        return products;
    }

    public MerchProduct findProductById(Long id) {
        return products.stream()
                .filter(p -> p.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Shopping cart methods.
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    // Add a product to the cart, or bump the quantity if it's already there
    public void addCartItem(Long productId, int quantity) {
        MerchProduct product = findProductById(productId);
        if (product == null) {
            return;
        }

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

    // Update the quantity for a product that is already in the cart
    public void updateCartItem(Long productId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(newQuantity);
                return;
            }
        }
    }

    // Remove a product from the cart completely
    public void removeCartItem(Long productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }
}
