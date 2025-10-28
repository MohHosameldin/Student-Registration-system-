package com.university.registration.controller;

import com.university.registration.entity.User;
import com.university.registration.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UsernameNotFoundException; 

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;

   
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    
    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    
    @GetMapping("/home")
    public String home(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found after login: " + username));
        
        if (user.getRole() == User.Role.ADMIN) {
            return "redirect:/admin/dashboard"; 
        } else if (user.getRole() == User.Role.STUDENT) {
            return "redirect:/student/dashboard"; 
        } else {
             
             return "redirect:/login?error=unknownRole"; 
        }
    }
}
