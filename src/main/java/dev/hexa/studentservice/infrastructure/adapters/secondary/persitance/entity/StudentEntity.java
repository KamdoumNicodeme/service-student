package dev.hexa.studentservice.infrastructure.adapters.secondary.persitance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
}
