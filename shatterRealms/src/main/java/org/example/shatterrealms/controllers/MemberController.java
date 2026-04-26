package org.example.shatterrealms.controllers;

import org.example.shatterrealms.models.Member;
import org.example.shatterrealms.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Masifah Mazhar 418003492

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    // GET /api/members
    /** Return all members. */
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    // GET /api/members/{id}
    /** Return one member by ID. */
    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/members/search?name=xxx
    /** Search members by name (case-insensitive, partial match). */
    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(@RequestParam String name) {
        return ResponseEntity.ok(memberService.searchByName(name));
    }

    // POST /api/members
    /** Create a new member. */
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member) {
        Member saved = memberService.saveMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /api/members/{id}
    /** Full update of an existing member. */
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id,
                                               @RequestBody Member member) {
        return memberService.updateMember(id, member)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/members/{id}
    /** Delete a member by ID. */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        return memberService.deleteMember(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
