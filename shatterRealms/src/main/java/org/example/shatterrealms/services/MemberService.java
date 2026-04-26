package org.example.shatterrealms.services;

import org.example.shatterrealms.models.Member;
import org.example.shatterrealms.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Masifah Mazhar 418003492

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    // Read

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> searchByName(String name) {
        return memberRepository.findByMemberNameContainingIgnoreCase(name);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByMemberEmail(email);
    }

    // Create

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // Full update (PUT)

    @Transactional
    public Optional<Member> updateMember(Long id, Member updated) {
        return memberRepository.findById(id).map(existing -> {
            existing.setMemberName(updated.getMemberName());
            existing.setMemberEmail(updated.getMemberEmail());
            if (updated.getMemberPassword() != null && !updated.getMemberPassword().isBlank()) {
                existing.setMemberPassword(updated.getMemberPassword());
            }
            return memberRepository.save(existing);
        });
    }

    // Partial field update via @Modifying query

    @Transactional
    public boolean updateMemberName(Long id, String newName) {
        return memberRepository.updateMemberNameById(id, newName) > 0;
    }

    // Delete

    public boolean deleteMember(Long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
