package dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")

    private Long id;
    private String title;
    private String description;
    private String instructor;
    private String department;
    private String level;
    private Integer duration;
    private Integer credits;
    private String language;
    private String format;
    private Double price;
}
