package com.university.registration.service;

import com.university.registration.entity.*;
import com.university.registration.exception.*;
import com.university.registration.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseRegistrationService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public Enrollment registerCourse(Long studentId, String courseCode) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));

        Course course = courseRepository.findById(courseCode)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with code: " + courseCode));

        // Check if already enrolled
        if (enrollmentRepository.findByStudentAndCourse(student, course).isPresent()) {
            throw new CourseAlreadyAddedException("Student already enrolled in course: " + courseCode);
        }

        // Validate course major
        validateCourseMajor(student, course);

        // Check prerequisites
        checkPrerequisites(student, course);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setStatus(Enrollment.Status.PENDING);

        return enrollmentRepository.save(enrollment);
    }

    private void validateCourseMajor(Student student, Course course) {
        String courseCode = course.getCode();
        
        // MTH courses are for both majors
        if (courseCode.startsWith("MTH")) {
            return;
        }

        // ECE courses for Computer Engineering
        if (courseCode.startsWith("ECE") && student.getMajor() != Student.Major.COMPUTER_ENGINEERING) {
            throw new WrongCourseMajorException(
                "Computer Engineering course " + courseCode + " not available for " + student.getMajor()
            );
        }

        // MNG courses for Mechanical Engineering
        if (courseCode.startsWith("MNG") && student.getMajor() != Student.Major.MECHANICAL_ENGINEERING) {
            throw new WrongCourseMajorException(
                "Mechanical Engineering course " + courseCode + " not available for " + student.getMajor()
            );
        }
    }

    private void checkPrerequisites(Student student, Course course) {
        if (course.getPrerequisites().isEmpty()) {
            return;
        }

        Set<String> completedCourses = enrollmentRepository.findByStudent(student).stream()
                .filter(e -> e.getPassed() && e.getStatus() == Enrollment.Status.APPROVED)
                .map(e -> e.getCourse().getCode())
                .collect(Collectors.toSet());

        for (Course prerequisite : course.getPrerequisites()) {
            if (!completedCourses.contains(prerequisite.getCode())) {
                throw new PrerequisiteNotMetException(
                    "Prerequisite not met: " + prerequisite.getCode() + " is required for " + course.getCode()
                );
            }
        }
    }

    public List<Enrollment> getStudentEnrollments(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with ID: " + studentId));
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getPendingEnrollments() {
        return enrollmentRepository.findByStatus(Enrollment.Status.PENDING);
    }

    public List<Enrollment> getApprovedEnrollments() {
        return enrollmentRepository.findByStatus(Enrollment.Status.APPROVED);
    }

    @Transactional
    public Enrollment approveEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setStatus(Enrollment.Status.APPROVED);
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment rejectEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setStatus(Enrollment.Status.REJECTED);
        return enrollmentRepository.save(enrollment);
    }

    @Transactional
    public Enrollment setGrade(Long enrollmentId, Double grade, Boolean passed) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        enrollment.setGrade(grade);
        enrollment.setPassed(passed);
        return enrollmentRepository.save(enrollment);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    
}