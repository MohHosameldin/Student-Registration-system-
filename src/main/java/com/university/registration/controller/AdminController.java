package com.university.registration.controller;

import com.university.registration.entity.Enrollment;
import com.university.registration.service.CourseRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CourseRegistrationService registrationService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Enrollment> pendingEnrollments = registrationService.getPendingEnrollments();
        List<Enrollment> approvedEnrollments = registrationService.getApprovedEnrollments();
        
        System.out.println("=== ADMIN DASHBOARD ===");
        System.out.println("Pending: " + pendingEnrollments.size());
        System.out.println("Approved: " + approvedEnrollments.size());
        
        model.addAttribute("pendingEnrollments", pendingEnrollments);
        model.addAttribute("approvedEnrollments", approvedEnrollments);
        model.addAttribute("courses", registrationService.getAllCourses());
        return "admin-dashboard";
    }

    @PostMapping("/approve/{id}")
    public String approveEnrollment(@PathVariable Long id) {
        System.out.println("Approving enrollment ID: " + id);
        Enrollment enrolled = registrationService.approveEnrollment(id);
        System.out.println("Enrollment status after approval: " + enrolled.getStatus());
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
        System.out.println("Grading enrollment ID: " + id + ", Grade: " + grade + ", Passed: " + passed);
        registrationService.setGrade(id, grade, passed);
        return "redirect:/admin/dashboard?graded";
    }
}