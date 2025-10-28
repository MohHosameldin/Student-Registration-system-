package com.university.registration.controller;

import com.university.registration.entity.User;
import com.university.registration.repository.UserRepository;
import com.university.registration.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final CourseRegistrationService registrationService;
    private final UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        model.addAttribute("student", user.getStudent());
        model.addAttribute("enrollments", registrationService.getStudentEnrollments(user.getStudent().getId()));
        model.addAttribute("courses", registrationService.getAllCourses());
        return "student-dashboard";
    }

    @PostMapping("/register")
    public String registerCourse(Authentication authentication, 
                                @RequestParam String courseCode,
                                Model model) {
        try {
            User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
            registrationService.registerCourse(user.getStudent().getId(), courseCode);
            return "redirect:/student/dashboard?success";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/student/dashboard?error=" + e.getMessage();
        }
    }
}
