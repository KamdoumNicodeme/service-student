package dev.hexa.studentservice.domain.model;

import jakarta.persistence.Entity;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String address;
}
