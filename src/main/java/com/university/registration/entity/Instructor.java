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
@Table(name = "instructors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"courses"}) 
@ToString(exclude = {"courses"}) 
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "instructors") 
    private Set<Course> courses = new HashSet<>();
}
