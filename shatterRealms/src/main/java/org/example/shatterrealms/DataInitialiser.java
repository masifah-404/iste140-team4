package org.example.shatterrealms;

import org.example.shatterrealms.models.Member;
import org.example.shatterrealms.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitialiser implements CommandLineRunner {

    @Autowired private MemberRepository memberRepository;

    @Override
    public void run(String... args) {
        seedMembers();
    }

    private void seedMembers() {
        if (memberRepository.count() > 0) return;
        memberRepository.save(new Member("Masifah",  "masifahm@shatterrealms.com", "m@sif@h"));
        memberRepository.save(new Member("Safia",    "safias@shatterrealms.com",   "s@fi@"));
        memberRepository.save(new Member("Arina",    "arinab@shatterrealms.com",   "@rin@"));
    }

}
