package com.university.registration.repository;

import com.university.registration.entity.Enrollment;
import com.university.registration.entity.Student;
import com.university.registration.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByStatus(Enrollment.Status status);
    List<Enrollment> findByStudentAndStatus(Student student, Enrollment.Status status);
}