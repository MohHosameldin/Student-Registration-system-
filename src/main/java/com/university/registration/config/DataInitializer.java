package com.university.registration.config;

import com.university.registration.entity.*;
import com.university.registration.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            initializeData();
            System.out.println(">>> Initial database data loaded successfully! <<<");
        } else {
             System.out.println(">>> Database already contains data. Skipping initialization. <<<");
        }
    }

    private void initializeData() {
        Instructor prof1 = new Instructor(null, "Dr. Ahmed Hassan", new HashSet<>());
        Instructor prof2 = new Instructor(null, "Dr. Sarah Mohamed", new HashSet<>());
        Instructor prof3 = new Instructor(null, "Dr. Omar Ali", new HashSet<>());
        Instructor prof4 = new Instructor(null, "Dr. Fatima Ibrahim", new HashSet<>());
        
        prof1 = instructorRepository.save(prof1);
        prof2 = instructorRepository.save(prof2);
        prof3 = instructorRepository.save(prof3);
        prof4 = instructorRepository.save(prof4);

        
        Course mth101 = createCourse("MTH101", "Calculus I", prof1);
        Course mth102 = createCourse("MTH102", "Calculus II", prof1);
        Course mth201 = createCourse("MTH201", "Linear Algebra", prof2);
        Course mth301 = createCourse("MTH301", "Differential Equations", prof2);

        // Computer Engineering Courses
        Course ece121 = createCourse("ECE121", "Digital Logic Design", prof3);
        Course ece221 = createCourse("ECE221", "Data Structures", prof3);
        Course ece321 = createCourse("ECE321", "Control Systems", prof3);
        Course ece322 = createCourse("ECE322", "Computer Architecture", prof4);
        Course ece421 = createCourse("ECE421", "Embedded Systems", prof4);
        Course ece422 = createCourse("ECE422", "Artificial Intelligence", prof4);

        // Mechanical Engineering Courses
        Course mng101 = createCourse("MNG101", "Engineering Mechanics", prof2);
        Course mng201 = createCourse("MNG201", "Thermodynamics", prof2);
        Course mng301 = createCourse("MNG301", "Fluid Mechanics", prof1);
        Course mng302 = createCourse("MNG302", "Heat Transfer", prof1);
        Course mng401 = createCourse("MNG401", "Machine Design", prof3);

        // Set prerequisites (Must be done AFTER courses are created)
        mth102.setPrerequisites(new HashSet<>(Arrays.asList(mth101)));
        mth201.setPrerequisites(new HashSet<>(Arrays.asList(mth101)));
        mth301.setPrerequisites(new HashSet<>(Arrays.asList(mth102, mth201)));
        
        ece221.setPrerequisites(new HashSet<>(Arrays.asList(ece121)));
        ece321.setPrerequisites(new HashSet<>(Arrays.asList(ece121, mth201)));
        ece322.setPrerequisites(new HashSet<>(Arrays.asList(ece221)));
        ece421.setPrerequisites(new HashSet<>(Arrays.asList(ece321, ece322)));
        ece422.setPrerequisites(new HashSet<>(Arrays.asList(ece221, mth301)));
        
        mng201.setPrerequisites(new HashSet<>(Arrays.asList(mng101, mth101)));
        mng301.setPrerequisites(new HashSet<>(Arrays.asList(mng201, mth102)));
        mng302.setPrerequisites(new HashSet<>(Arrays.asList(mng301)));
        mng401.setPrerequisites(new HashSet<>(Arrays.asList(mng101, mth201)));

        courseRepository.saveAll(List.of(
            mth101, mth102, mth201, mth301,
            ece121, ece221, ece321, ece322, ece421, ece422,
            mng101, mng201, mng301, mng302, mng401
        ));

        Student student1 = new Student(null, "Ali Khaled", Student.Major.COMPUTER_ENGINEERING, new HashSet<>());
        Student student2 = new Student(null, "Nour Mahmoud", Student.Major.MECHANICAL_ENGINEERING, new HashSet<>());
        
        student1 = studentRepository.save(student1);
        student2 = studentRepository.save(student2);

        
        User admin = new User(null, "admin", passwordEncoder.encode("admin123"), User.Role.ADMIN, null);
        
        User user1 = new User(null, "ali", passwordEncoder.encode("ali123"), User.Role.STUDENT, student1);
        
        User user2 = new User(null, "nour", passwordEncoder.encode("nour123"), User.Role.STUDENT, student2);

        userRepository.saveAll(List.of(admin, user1, user2));
    }
private Course createCourse(String code, String name, Instructor instructor) {
        Course course = new Course();
        course.setCode(code);
        course.setName(name);
        course.setInstructors(new HashSet<>(Arrays.asList(instructor)));
        course.setPrerequisites(new HashSet<>());
        course.setEnrollments(new HashSet<>());
        return course;
    }
}
