package com.university.registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode; // Import this
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString; // Import this

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"instructors", "prerequisites", "enrollments"})
@ToString(exclude = {"instructors", "prerequisites", "enrollments"}) 
public class Course {

    @Id
    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
        name = "course_instructors",
        joinColumns = @JoinColumn(name = "course_code"),
        inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Instructor> instructors = new HashSet<>();

    // ManyToMany relationship for prerequisites
    @ManyToMany
    @JoinTable(
        name = "course_prerequisites",
        joinColumns = @JoinColumn(name = "course_code"),
        inverseJoinColumns = @JoinColumn(name = "prerequisite_code")
    )
    private Set<Course> prerequisites = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments = new HashSet<>();

    public Course(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public Course(Object o, String code, String name, HashSet<Object> prerequisites, HashSet<Object> enrollments) {
        this.code = code;
        this.name = name;
    }
}
