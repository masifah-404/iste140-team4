package org.example.shatterrealms.repositories;

import org.example.shatterrealms.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Masifah Mazhar 418003492

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // ── Find ─────────────────────────────────────────────────────────────────

    /** Derived query: find a member by their exact username. */
    Optional<Member> findByMemberName(String memberName);

    /** Derived query: case-insensitive name search (used by the search bar). */
    List<Member> findByMemberNameContainingIgnoreCase(String memberName);

    /**
     * Custom JPQL: find a member by email address.
     * Demonstrates a JPQL query beyond the main identifier.
     */
    @Query("SELECT m FROM Member m WHERE LOWER(m.memberEmail) = LOWER(:email)")
    Optional<Member> findByMemberEmail(@Param("email") String email);

    // ── Update ───────────────────────────────────────────────────────────────

    /** Update only the memberName field for a given id. */
    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.memberName = :name WHERE m.memberId = :id")
    int updateMemberNameById(@Param("id") Long id, @Param("name") String name);
}