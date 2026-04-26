package org.example.shatterrealms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Base controller to handle mapping for static pages
@Controller
public class ShatterRealmsController {
    @GetMapping("/")
    public String home() { return "forward:/index.html"; }

    // Keep old path aliases working (nav links in character-guide / lore pages)
    @GetMapping("/members")
    public String members() { return "redirect:/members.html"; }

    @GetMapping("/merch")
    public String merch() { return "redirect:/merch.html"; }

    @GetMapping("/cart")
    public String cart() { return "redirect:/cart.html"; }
}

