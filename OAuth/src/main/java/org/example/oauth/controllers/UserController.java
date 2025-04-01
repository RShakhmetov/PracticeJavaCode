package org.example.oauth.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String login() {
        return "Hello World";
    }

    @GetMapping("/gatorade")
    public String gatorade() {
        return "Welcome to the Gatorade";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome to the Admin`s page";
    }

    @GetMapping("access-denied")
    public String noAccess(){
        return "Access denied";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
        model.addAttribute("name", principal.getAttribute("name"));
        model.addAttribute("login", principal.getAttribute("login"));
        model.addAttribute("id", principal.getAttribute("id"));
        model.addAttribute("email", principal.getAttribute("email"));
        return "This is only for users " + model.getAttribute("login");
    }
}
