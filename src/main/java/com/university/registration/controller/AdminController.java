package com.university.registration.controller;

import com.university.registration.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CourseRegistrationService registrationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("pendingEnrollments", registrationService.getPendingEnrollments());
        model.addAttribute("courses", registrationService.getAllCourses());
        return "admin-dashboard";
    }

    @PostMapping("/approve/{id}")
    public String approveEnrollment(@PathVariable Long id) {
        registrationService.approveEnrollment(id);
        return "redirect:/admin/dashboard?approved";
    }

    @PostMapping("/reject/{id}")
    public String rejectEnrollment(@PathVariable Long id) {
        registrationService.rejectEnrollment(id);
        return "redirect:/admin/dashboard?rejected";
    }

    @PostMapping("/grade/{id}")
    public String setGrade(@PathVariable Long id, 
                          @RequestParam Double grade,
                          @RequestParam Boolean passed) {
        registrationService.setGrade(id, grade, passed);
        return "redirect:/admin/dashboard?graded";
    }
}