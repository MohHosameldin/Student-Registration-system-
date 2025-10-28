package com.university.registration.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString; 

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"enrollments"})
@ToString(exclude = {"enrollments"}) 
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_sequence", initialValue = 250001, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Major major;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();

    public enum Major {
        COMPUTER_ENGINEERING,
        MECHANICAL_ENGINEERING
    }

    public Student(Long id, String name, Major major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }
}
