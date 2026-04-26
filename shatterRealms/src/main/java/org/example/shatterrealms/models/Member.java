package org.example.shatterrealms.models;

import jakarta.persistence.*;

// Masifah Mazhar 418003492

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, length = 100)
    private String memberName;

    @Column(nullable = false, unique = true, length = 150)
    private String memberEmail;

    @Column(nullable = false, length = 255)
    private String memberPassword;

    // Constructors
    public Member() {}

    public Member(String memberName, String memberEmail, String memberPassword) {
        this.memberName     = memberName;
        this.memberEmail    = memberEmail;
        this.memberPassword = memberPassword;
    }

    // Getters & Setters
    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getMemberEmail() { return memberEmail; }
    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

    public String getMemberPassword() { return memberPassword; }
    public void setMemberPassword(String memberPassword) { this.memberPassword = memberPassword; }
}
